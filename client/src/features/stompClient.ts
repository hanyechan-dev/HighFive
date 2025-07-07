import { Client, type Frame, type IMessage, type StompSubscription } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

let stompClient: Client | null = null;
let isConnected = false;
// 여러 개의 구독을 관리하기 위한 객체
let subscriptions: { [key: string]: StompSubscription } = {};

// 콜백 등록부
let onMessageCallbacks: ((message: IMessage) => void)[] = [];

// 메시지 콜백 등록
export const registerMessageCallback = (callback: (message: IMessage) => void) => {
    onMessageCallbacks.push(callback);
    return onMessageCallbacks.length - 1; // 추후 언마운트를 위해 콜백 인덱스 반환
};

// 특정 콜백 해제
export const unregisterMessageCallback = (index: number) => {
    if (index > -1 && index < onMessageCallbacks.length) {
        onMessageCallbacks[index] = () => { }; // 빈 함수로 교체하여 인덱스 밀리는 에러 방지
    }
};

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

// 웹소켓 연결 (로그인 시 호출)
export const connectWebSocket = (token: string) => {
    // 이미 연결되어 있거나 연결 시도 중이라면 중복 실행 방지
    if (stompClient && stompClient.active) {
        console.log("WebSocket이 이미 연결되어 있습니다.");
        return;
    }
    // 웹소켓 인스턴스 생성
    const client = new Client({
        webSocketFactory: () => new SockJS('/ws'),

        // 연결 시 전송할 헤더 (인증 토큰 등)
        connectHeaders: {
            Authorization: `Bearer ${token}`,
        },

        // 디버그 메시지 출력
        debug: (str) => {
            console.log(new Date(), str);
        },

        // 자동 재연결 딜레이 시간
        reconnectDelay: 5000,

        // 연결에 성공한 경우
        onConnect: (frame: Frame) => {
            isConnected = true;
            stompClient = client; // 연결 성공 시에만 전역 변수에 할당
            console.log('WebSocket Connected!', frame);
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
            console.log('웹소켓 연결 종료.');
            subscriptions = {}; // 구독 리스트 초기화
        }
    });

    // 웹소켓 연결 시작
    client.activate();
};

// 채팅방 구독 기능 (메시지 수신 시 콜백 함수 실행)
export const subscribeToTopic = async (roomId: number) => {
    const topic = `/topic/${roomId}`;

    let maxTries = 25; // 최대 5초 대기 (25 * 200ms)
    while (!stompClient?.active && maxTries > 0) {
        console.log(`[구독 대기] 토픽 ${roomId}: 웹소켓 연결을 기다립니다... (남은 시도: ${maxTries})`);
        // 0.2초 대기
        await new Promise(resolve => setTimeout(resolve, 200));
        stompClient = getStompClient(); // 최신 클라이언트 상태 다시 가져오기
        maxTries--;
    }

    // 연결이 되었거나, 대기 시간이 초과된 후 최종적으로 구독 시도
    if (stompClient?.active) {
        // 이미 해당 토픽을 구독중이라면 중복 실행 방지
        if (!subscriptions[topic]) {
            console.log(`[구독 성공] 토픽 ${roomId}: 구독을 시작합니다.`);
            const subscription = stompClient.subscribe(topic, (message: IMessage) => {
                if (onMessageCallbacks) {
                    onMessageCallbacks.forEach((callback) => {
                        if (message) {
                            callback(message);
                        }
                    });
                }
            });
            subscriptions[topic] = subscription;
            return subscription;
        }
    } else {
        // 결국 연결에 실패한 경우
        console.error(`[구독 실패] 토픽 ${roomId}: 웹소켓 연결에 실패하여 구독할 수 없습니다.`);
    }
    return null;
};

// 특정 토픽 구독 해제 기능 (필요 시 사용)
export const unsubscribeFromTopic = (roomId: number) => {
    const topic = `/topic/chat/${roomId}`;
    if (subscriptions[topic]) {
        subscriptions[topic].unsubscribe();
        delete subscriptions[topic];
        console.log(`다음 채팅방 구독 해제: ${topic}`);
    }
}

// 메시지 발행(전송) 기능
export const publishMessage = (
    destination: string,
    headers: { [key: string]: any },
    body: object
) => {
    if (stompClient?.active) {
        stompClient.publish({
            destination,
            headers,
            body: JSON.stringify(body),
        });
    } else {
        console.error("메시지 발송 실패.");
    }
};

// 웹소켓 연결 종료(로그아웃 시 호출)
export const disconnectWebSocket = () => {
    if (stompClient) {
        stompClient.deactivate();
        stompClient = null;
        isConnected = false;
    }
};