import { useCallback, useRef, useState, useEffect } from "react";
import type { Frame } from "@stomp/stompjs";
import { Client as StompClient } from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import type { RootState } from "../store/store";
import { useDispatch, useSelector } from "react-redux";
import { setClient } from "../../components/chat/WebSocketSlice";
import AuthUtil from "./AuthUtil";

const useWebSocket = () => {
  const stompClient = useRef<StompClient | null>(null);
  const [id, setId] = useState<number | null>(null);

  // 토큰에서 ID 추출
  const token = useSelector((state: RootState) => state.auth.accessToken); // 토큰 획득

  useEffect(() => {
    const extractedId = AuthUtil.getIdFromToken(token); // ID 추출
    if (extractedId != null) {
      setId(extractedId);
    }
  }, [token])

  // 웹소켓 연결 (로그인 시 호출)
  useEffect(() => {
    if (!id) return; // 로그인 시에만 연결

    stompClient.current = new StompClient({
      webSocketFactory: () => new SockJS("http://localhost:9000/ws"),
      connectHeaders: {},
      onConnect: onConnected,
      onStompError: onError,
      reconnectDelay: 5000,
    });
    stompClient.current.activate();

    const dispatch = useDispatch();
    dispatch(setClient(stompClient.current)); // StompClient 인스턴스를 Redux에 저장
  }, [id]);

  const client = stompClient.current;

  // 연결에 성공한 경우
  const onConnected = useCallback(() => {
    if (client) {
      client.subscribe(`/user/queue/chat`, ({ body }) => {  // User Queue 구독
        const data = JSON.parse(body);
        const chatRoomId = data.chatRoomId;
        client.subscribe(`/topic/chat/${chatRoomId}`, () => { }); // 채팅방 자동 구독
      });
    }
  }, [id]);

  // 연결에 실패한 경우
  const onError = (error: Frame | string | Error) => {
    console.log("연결실패", error);
    // error는 세 가지 타입일 수 있다.
    // - string → STOMP에서 간단한 오류 메시지를 문자열로 반환하는 경우.
    // - Frame → STOMP 프로토콜에서 오류 메시지를 프레임 형태로 전달하는 경우.
    // - Error → 일반적인 JavaScript 오류 객체.

    if (typeof error === "string") {
      console.error("STOMP Error: " + error);
    } else if (isFrame(error)) {
      console.error(
        "STOMP Frame Error: " + error.headers?.message || error.body
      );
    } else {
      console.error("STOMP Connection Error", error);
    }
  };

  // 커스텀 타입 가드 설정
  const isFrame = (error: any): error is Frame => {
    return error && typeof error === "object" && "headers" in error;
  };

  // 로그아웃 시 소켓 연결 deactivate (작성 예정)

  return;
};

export default useWebSocket;
