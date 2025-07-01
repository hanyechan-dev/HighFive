import { useState, useEffect, useCallback, useRef } from "react";
import type { IMessage } from "@stomp/stompjs";
import { type RootState } from "../../common/store/store";
import { useDispatch, useSelector } from "react-redux";
import { MessageCircle, X, Send, User, MessageCircleWarning } from "lucide-react";
import AuthUtil from "../../common/utils/AuthUtil";
import {
  getStompClient,
  publishMessage,
  registerMessageCallback,
  subscribeToTopic,
  unregisterMessageCallback,
} from "./stompClient";
import { clearNewChatTarget } from "./ChatControlSlice";
import { Button } from "../../common/components/ui/button";
import { ScrollArea } from "../../common/components/ui/scroll-area";
import { Avatar, AvatarFallback, AvatarImage } from "../../common/components/ui/avatar";
import { Dialog, DialogContent, DialogHeader, DialogTitle } from "../../common/components/ui/dialog";
import { Input } from "../../common/components/ui/input";
import { addUnreadChat, removeUnreadChat, selectUnreadChatRooms } from "../notification/NotificationSlice";
import { toast } from "sonner";

interface ChatRoom {
  chatRoomId: number;
  contentId?: number;
  senderId?: number;
  receiverId: number;
  name: string;
  content?: string;
  createdAt?: string;
}

interface ChatMessage {
  chatRoomId: number;
  contentId: number;
  senderId: number;
  name: string;
  content: string;
  createdAt: string;
}

// SockJS와 Stomp를 이용해서 웹 소켓 서버로 연결하고 메시지를 주고 받는 기능 구현
const Chat = () => {
  const token = useSelector((state: RootState) => state.auth.accessToken); // 토큰 획득
  const unreadChatRooms = useSelector(selectUnreadChatRooms);
  const chatTarget = useSelector(
    (state: RootState) => state.chat.newChatTarget
  ); // 채팅 상대 정보 획득(id, name)
  const dispatch = useDispatch();

  const [chatRoomList, setChatRoomList] = useState<ChatRoom[]>([]);
  const [chatRoomId, setChatRoomId] = useState<number | null>(null);
  const [chatRoomName, setChatRoomName] = useState<string | null>("");
  const [senderId, setSenderId] = useState<number | null>(null);
  const [targetId, setTargetId] = useState<number | null>(null);
  const [content, setContent] = useState<string>("");
  const [chatHistory, setChatHistory] = useState<ChatMessage[]>([]);
  const [showChatList, setShowChatList] = useState(false);
  const [selectedChat, setSelectedChat] = useState<boolean>(false);
  const [targetIdState, setTargetIdState] = useState<boolean>(false);

  const scrollViewportRef = useRef<HTMLDivElement | null>(null);
  const stompClient = getStompClient();

  // 토큰에서 ID 추출
  useEffect(() => {
    const id = AuthUtil.getIdFromToken(token); // ID 추출
    if (id != null) {
      setSenderId(id);
    }
  }, [token]);

  // 상대방이 채팅 요청 시 알림 및 채팅방 리스트 불러오기
  useEffect(() => {
    if (stompClient) {
      stompClient.subscribe(`/topic/notifications`, ({ body }) => {
        try {
          const notification = JSON.parse(body);

          if (notification.targetId == senderId) {
            console.log("채팅 요청을 수신합니다.")
            if (!selectedChat) {
              getChatRoomList();
              toast.message('새로운 채팅 요청이 도착했습니다.')
              dispatch(addUnreadChat(notification.chatRoomId)) // 안 읽은 채팅방에 추가
            } else {
              console.log("새로운 채팅 요청을 사용자가 이미 확인 중입니다.")
            }
          }
        } catch (error) {
          console.error(`채팅 요청 수신 및 채팅방 리스트 불러오기 실패: `, error);
        }
      });
    }
  }, [stompClient, stompClient?.connected, dispatch])

  // 채팅방 생성 또는 기존 채팅방 참여
  const createChatRoom = async () => {
    if (senderId != null && targetId != null) {
      try {
        const response = await fetch("http://localhost:8090/chats", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify({
            id: targetId,
          }),
        });
        const roomId = await response.json(); // JSON -> Javascript 객체로 변환

        setChatRoomId(roomId);
        subscribeToTopic(roomId); // 채팅방 구독
        return roomId;
      } catch (error) {
        console.log("chatRoomId를 가져오지 못했습니다: ", error);
      }
    } else {
      console.log(
        `채팅방 생성 실패: senderId 또는 targetId가 null입니다: senderId:${senderId} / targetId: ${targetId}`
      );
    }
  };

  // "전송" 버튼 클릭 시, 메시지 전송
  const handleSendMessage = () => {
    if (content.trim() && stompClient && token) {
      publishMessage(
        "/app/chat/send",
        {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        { senderId, targetId, chatRoomId, content }
      );
      setContent("");
    } else {
      console.log("메시지 전송 실패.");
    }
  };

  // Enter키를 통한 메시지 전송
  const handleKeyPress = (e: React.KeyboardEvent) => {
    if (e.key === "Enter") {
      handleSendMessage();
    }
  };

  // 메시지 수신 기능
  const receiveMessage = useCallback(
    (payload: IMessage) => {
      const receivedMessage: ChatMessage = JSON.parse(payload.body);
      if ((receivedMessage.chatRoomId == chatRoomId) && selectedChat) {
        setChatHistory((prevHistory) => [...prevHistory, receivedMessage]);
      } else if (receivedMessage.chatRoomId != chatRoomId || !selectedChat) { // 읽지 않은 메시지 알림
        dispatch(addUnreadChat(receivedMessage.chatRoomId)); // 읽지 않은 채팅방 추가

        toast.message(`새로운 메시지: ${receivedMessage.name}`, {
          description: receivedMessage.content,
          style: {
            '--button-background': '#EE57CD',
            '--button-text': '#ffffff',
            '--button-hover-background': '#c949ad',
          } as React.CSSProperties,
          action: {
            label: "확인하기",
            onClick: () => {
              // Toast 클릭 시 해당 채팅방으로 바로 이동
              handleChatSelect({
                chatRoomId: receivedMessage.chatRoomId,
                name: receivedMessage.name,
                receiverId: receivedMessage.senderId
              })
            },
          },
          className: 'my-custom-toast'
        });
      } else {
        console.log("receiveMessage 처리 중 에러 발생")
      }
    },
    [selectedChat, dispatch]
  );

  // 상기 메소드를 stompClient.ts에 콜백 함수로 등록하여 관리
  useEffect(() => {
    const callbackIndex = registerMessageCallback(receiveMessage);
    return () => {
      unregisterMessageCallback(callbackIndex);
    };
  }, [receiveMessage]);

  // 채팅방 리스트 불러오기
  const getChatRoomList = useCallback(async () => {
    try {
      if (token) {
        const response = await fetch("http://localhost:8090/chats", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        });
        const tempChatRoomList: ChatRoom[] = await response.json();
        setChatRoomList(tempChatRoomList);
      } else {
        console.log(
          "Token값이 null로 추정됩니다. Token 값은 다음과 같습니다: ",
          token
        );
      }
    } catch (error) {
      console.log("채팅방 리스트 불러오기 실패: ", error);
    }
  }, [token]);

  // 채팅 내역 불러오기
  const getHistory = async (id: number) => {
    // IMessage : STOMP 라이브러리에서 지원하는 메시지 인터페이스
    try {
      const response = await fetch(`http://localhost:8090/chats/detail`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ id }),
      });
      const chatContents: ChatMessage[] = await response.json();

      if (chatContents) {
        setChatHistory(() => [...chatContents]); // 대화 내용 업데이트
      } else {
        console.log("chatContents값 확인: " + chatContents);
      }
    } catch (error) {
      console.error("채팅 내역 불러오기 실패: ", error);
    }
  };

  // 채팅 아이콘 클릭 시, 채팅 리스트 출력
  const handleChatIconClick = () => {
    if (token) {
      getChatRoomList();
      setShowChatList(!showChatList);
    } else {
      console.log("token값이 null이거나 undefined입니다.");
    }
  };

  // 채팅방 클릭 시, 채팅 모달과 채팅 메시지 출력
  const handleChatSelect = (chatRoom: ChatRoom) => {
    getHistory(chatRoom.chatRoomId);
    setChatRoomId(chatRoom.chatRoomId);
    setChatRoomName(chatRoom.name);
    setTargetId(chatRoom.receiverId);
    setShowChatList(false);
    setSelectedChat(true);
    dispatch(removeUnreadChat(chatRoom.chatRoomId));
  };

  // 채팅 시 화면 자동 스크롤
  useEffect(() => {
    if (scrollViewportRef.current) {
      scrollViewportRef.current.scrollIntoView({
        behavior: "smooth",
      });
    }
  }, [chatHistory]);

  // 메시지 수발신 또는 로그인 시, 채팅방 리스트 자동 렌더링
  useEffect(() => {
    if (token) {
      getChatRoomList();
    } else {
      console.log("token값이 null이거나 undefined입니다.");
    }
  }, [token, getChatRoomList, chatHistory]);

  // 가져온 채팅방 리스트를 토대로 채팅방 순회하면서 모든 채팅방 구독
  useEffect(() => {
    if (chatRoomList.length > 0) {
      chatRoomList.forEach((chatRoom) => {
        subscribeToTopic(chatRoom.chatRoomId);
      });
    } else {
      console.log("구독할 채팅방이 존재하지 않습니다.");
    }
  }, [chatRoomList]);

  // "채팅하기" 버튼 클릭 시, targetId 상태 변경 (비동기 이슈로 인한 코드 분리)
  useEffect(() => {
    if (chatTarget && chatTarget.step == 2) {
      setTargetId(chatTarget.id);
      setTargetIdState(true);
    }
  }, [chatTarget]);

  // "채팅하기" 버튼 클릭 시, 채팅방 생성 및 채팅 모달 출력
  useEffect(() => {
    const enterNewChatRoom = async () => {
      if (chatTarget != null && targetIdState) {
        const roomId = await createChatRoom();
        if (roomId !== null) {
          await getHistory(roomId);
          setChatRoomName(chatTarget.name);
          setSelectedChat(true);
        }
        dispatch(clearNewChatTarget());
        setTargetIdState(false);
      }
    };
    enterNewChatRoom();
  }, [targetIdState, createChatRoom]);

  return (
    <div className="fixed bottom-6 right-6 z-50">
      {/* 채팅 아이콘 */}
      {token && ( // 로그인 상태일 때만 채팅 아이콘 활성화
        <div className="relative">
          <Button
            onClick={handleChatIconClick}
            className="rounded-full w-14 h-14 bg-[#EE57CD] hover:bg-[#EE57CD]/90 shadow-lg"
            size="icon"
          >
            <MessageCircle className="w-6 h-6 text-white" />
          </Button>
          {/* 채팅 아이콘 배지 (읽지 않은 메시지가 있을 때) */}
          {unreadChatRooms.length > 0 && (
            <span className="absolute top-0 right-0 block h-3 w-3 transform -translate-y-1/2 translate-x-1/2 rounded-full bg-red-500 ring-2 ring-white" />
          )}
        </div>
      )}

      {/* 채팅 리스트 */}
      {showChatList && (
        <div className="absolute bottom-16 right-0 w-80 bg-white rounded-lg shadow-xl border border-gray-200 max-h-96 overflow-hidden">
          <div className="p-4 border-b border-gray-200">
            <div className="flex items-center justify-between">
              <h3 className="font-semibold text-gray-900">채팅</h3>
              <Button
                variant="ghost"
                size="icon"
                onClick={() => setShowChatList(false)}
                className="h-6 w-6"
              >
                <X className="h-4 w-4" />
              </Button>
            </div>
          </div>
          <ScrollArea className="max-h-80">
            <div className="p-2">
              {chatRoomList.map((chatRoom) => (
                <div
                  key={chatRoom.chatRoomId}
                  onClick={() => handleChatSelect(chatRoom)}
                  className="flex items-center p-3 hover:bg-gray-50 rounded-lg cursor-pointer transition-colors"
                >
                  <Avatar className="h-10 w-10 mr-3">
                    <AvatarImage
                      src={
                        "/placeholder.svg"
                      }
                      alt={chatRoom.name}
                    />
                    <AvatarFallback>
                      <User className="h-5 w-5" />
                    </AvatarFallback>
                  </Avatar>
                  <div className="flex-1 min-w-0 flex justify-between items-center">
                    <p className="font-medium text-gray-900 truncate">
                      {chatRoom.name}
                    </p>
                    {/* 채팅방 별 배지 (읽지 않은 메시지가 있을 때) */}
                    {unreadChatRooms.includes(chatRoom.chatRoomId) && (
                      <MessageCircleWarning className="h-6 w-6 text-yellow-500 ml-2" /> // 느낌표 아이콘
                    )}
                  </div>
                </div>
              ))}
            </div>
          </ScrollArea>
        </div>
      )}

      {/* 채팅 모달 */}
      <Dialog open={selectedChat} onOpenChange={() => setSelectedChat(false)}>
        <DialogContent className="sm:max-w-md bg-white">
          <DialogHeader className="flex flex-row items-center justify-between space-y-0 pb-4">
            <DialogTitle className="flex items-center space-x-3">
              <Avatar className="h-8 w-8">
                <AvatarImage src={/* selectedChat?.avatar || */"/placeholder.svg"} alt={chatRoomName!} />
                <AvatarFallback>
                  <User className="h-4 w-4" />
                </AvatarFallback>
              </Avatar>
              <span>{chatRoomName}</span>
            </DialogTitle>
          </DialogHeader>

          {/* 메시지 영역 */}
          <ScrollArea className="h-80 w-full pr-4">
            <div className="space-y-3">
              {chatHistory.map((message) => (
                <div key={message.contentId} className={`flex ${message.senderId === senderId ? "justify-end" : "justify-start"}`}>
                  <div
                    className={`max-w-xs px-4 py-2 rounded-lg ${message.senderId === senderId
                      ? "bg-[#EE57CD] text-white"
                      : "bg-white border border-[#EE57CD] text-[#EE57CD]"
                      }`}
                  >
                    <p className="text-sm">{message.content}</p>
                  </div>
                  <div ref={scrollViewportRef}></div>
                </div>
              ))}
            </div>
          </ScrollArea>

          {/* 메시지 입력 영역 */}
          <div className="flex items-center space-x-2 pt-4 border-t">
            <Input
              value={content}
              onChange={(e) => setContent(e.target.value)}
              onKeyPress={handleKeyPress}
              placeholder="메시지를 입력하세요..."
              className="flex-1"
            />
            <Button
              onClick={handleSendMessage}
              className="bg-[#EE57CD] hover:bg-[#EE57CD]/90 text-white"
              size="icon"
            >
              <Send className="h-4 w-4" />
            </Button>
          </div>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default Chat;
