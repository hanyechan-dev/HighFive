import { Client, type Frame, type IMessage } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

let stompClient: Client | null = null;
let isConnected = false;

// --- 콜백 등록부: 이제 두 종류의 콜백을 관리합니다. ---
let onInvitationCallback: ((roomId: number) => void) | null = null;
let onMessageCallback: ((message: IMessage) => void) | null = null;

// [등록 함수 1] 채팅방 초대 콜백 등록 (App.tsx에서 사용될 수 있음)
export const registerInvitationCallback = (callback: (roomId: number) => void) => {
    onInvitationCallback = callback;
};

// [등록 함수 2] 실제 메시지 콜백 등록 (Chat.tsx에서 사용)
export const registerMessageCallback = (callback: (message: IMessage) => void) => {
    onMessageCallback = callback;
};

// [해제 함수] 모든 콜백을 한번에 해제 (Chat.tsx에서 사용)
export const unregisterAllCallbacks = () => {
    onInvitationCallback = null;
    onMessageCallback = null;
};
// ----------------------------------------------------

export const getStompClient = (): Client | null => {
    return stompClient;
};

// 외부에서 연결 상태를 확인할 수 있는 함수
export const getIsConnected = (): boolean => {
    return isConnected;
}

// Frame 객체인지 확인할 커스텀 타입 가드 설정
const isFrame = (error: any): error is Frame => {
    return error && typeof error === 'object' && 'headers' in error;
};

// 웹소켓 연결 시작(로그인 시 호출)
export const connectWebSocket = (token: string) => {
    // 이미 연결되어 있거나 연결 시도 중이라면 중복 실행 방지
    if (stompClient && stompClient.active) {
        console.log("WebSocket이 이미 연결되어 있습니다.");
        return;
    }

    const client = new Client({
        webSocketFactory: () => new SockJS('https://localhost:8090/ws'),

        // 연결 시 전송할 헤더 (인증 토큰 등)
        connectHeaders: {
            Authorization: `Bearer ${token}`,
        },

        // 디버그 메시지 출력
        debug: (str) => {
            console.log(new Date(), str);
        },

        // 자동 재연결 딜레이 (ms)
        reconnectDelay: 5000,

        // 연결에 성공한 경우
        onConnect: (frame: Frame) => {
            isConnected = true;
            stompClient = client; // 연결 성공 시에만 전역 변수에 할당
            console.log('WebSocket Connected!', frame);

            // 개인 Queue 구독
            client.subscribe(`/user/queue/chat`, ({ body }) => {
                const data = JSON.parse(body);
                const chatRoomId = data.chatRoomId;

                // 등록된 '초대장 처리' 콜백이 있다면 실행
                if (chatRoomId && onInvitationCallback) {
                    onInvitationCallback(chatRoomId);
                }
            });
        },

        // 연결에 실패한 경우
        onStompError: (frame: Frame) => {
            isConnected = false;
            console.error('STOMP Error! Details:', frame);
            if (isFrame(frame)) {
                console.error(`Error: ${frame.headers['message']} | Body: ${frame.body}`);
            }
        },

        // 연결을 종료한 경우
        onDisconnect: () => {
            isConnected = false;
            console.log('WebSocket Disconnected!');
        }
    });

    // 클라이언트 활성화 (연결 시작)
    client.activate();
};

// [핵심] 토픽 구독 함수는 이제 메시지를 받으면 '메시지 처리' 콜백을 실행합니다.
export const subscribeToTopic = (roomId: number) => {
    if (stompClient?.active) {
        console.log(`Subscribing to /topic/chat/${roomId}`);
        return stompClient.subscribe(`/topic/chat/${roomId}`, (message: IMessage) => {
            // 등록된 '메시지 처리' 콜백이 있다면 실행
            if (onMessageCallback) {
                onMessageCallback(message);
            }
        });
    }
    return null;
};

// 웹소켓 연결 종료(로그아웃 시 호출)
export const disconnectWebSocket = () => {
    if (stompClient) {
        stompClient.deactivate();
        stompClient = null;
        isConnected = false;
    }
};