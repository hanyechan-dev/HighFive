import { useCallback, useRef, useState, useEffect } from 'react';
import './Chat.css';
import type { IMessage, Frame } from '@stomp/stompjs';
import { Client as StompClient } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

// 채팅 메시지 객체 타입 정의
interface ChatMessage {
    type: 'JOIN' | 'LEAVE' | 'CHAT' | string;
    sender: string;
    message: string;
    createdDt?: string;
    history?: ChatMessage[];
}

    // 커스텀 타입 가드 설정
    const isFrame = (error : any): error is Frame  => {
        return error && typeof(error) === 'object' && 'headers' in error;
    }

    // SockJS와 Stomp를 이용해서 웹 소켓 서버로 연결하고 메시지를 주고 받는 기능 구현
    const Chatting2 = () => {

    // 상태 변수 정의
    // const [isJoin, setIsJoin] = useState<boolean>(false);
    // const [chatHistory, setChatHistory] = useState<ChatMessage[]>([]);
    const [chatRoomId, setChatRoomId] = useState<number>(0);
    const [sender, setSender] = useState<number>(0);
    const [target, setTarget] = useState<number>(0);
    const [message, setMessage] = useState<string>('');
    
    // ref 변수 정의
    const refDialogDiv = useRef<HTMLDivElement | null>(null);
    const refSenderInput = useRef<HTMLInputElement | null>(null);
    const refMessageInput = useRef<HTMLTextAreaElement | null>(null);
    const stompClient = useRef<StompClient | null>(null);


    // 채팅 참여
    const joinChatting = useCallback(() => {

        // 채팅 대상 회원 ID 추출 (작성 예정)

        // 채팅방 생성 또는 기존 채팅방 참여
        const createChatRoom = useEffect(() => {
            fetch("https://localhost:9000/chats", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                    "Authorization": `Bearer ${token}` // JWT 토큰 추가
                },
                body: JSON.stringify({
                    id: // 채팅 대상 회원으로부터 추출한 ID
                }),
            })
            .then((response) => response.json()) // JSON -> Javascript 객체로 변환
            .then((data) => setChatRoomId(data.chatRoomId))
            .catch((error) => console.error("chatRoomId를 가져오지 못했습니다.: ", error));
        }, [target])

        stompClient.current = new StompClient({
            webSocketFactory: () => new SockJS('http://localhost:9000/ws'),
            connectHeaders: {},
            onConnect: onConnected,
            onStompError: onError,
            reconnectDelay: 5000
        });
        stompClient.current.activate();
    }, [sender]);


    // 연결에 성공한 경우
    const onConnected = useCallback(() => {
        if (stompClient.current) {
            stompClient.current.subscribe(`/topic/${chatRoomId}`, onMessageReceived);
        }
    }, [sender]);


    // 연결에 실패한 경우
    const onError = useCallback((error: Frame | string | Error) => {
        console.log('연결실패', error);
        // error는 세 가지 타입일 수 있다.
        // - string → STOMP에서 간단한 오류 메시지를 문자열로 반환하는 경우.
        // - Frame → STOMP 프로토콜에서 오류 메시지를 프레임 형태로 전달하는 경우.
        // - Error → 일반적인 JavaScript 오류 객체.

        if (typeof error === 'string') {
            console.error('STOMP Error: ' + error);
        } else if (isFrame(error)) {
            console.error('STOMP Frame Error: ' + error.headers?.message || error.body);
        } else {
            console.error('STOMP Connection Error', error);
        }
    }, []);


    // 메시지를 수신한 경우
    const onMessageReceived = useCallback((payload: IMessage) => { // IMessage 타입 사용
        const receivedMessage: ChatMessage = JSON.parse(payload.body);

        // JOIN 메시지이고, 현재 사용자의 sender와 동일한 경우
        if (receivedMessage.type === 'JOIN' && receivedMessage.sender === sender) {
            setIsJoin(true);
            if (receivedMessage.history) {
                setChatHistory(prevHistory => [...prevHistory, ...receivedMessage.history!]);
            }
        } else {
            setChatHistory(prevChatHistory => [...prevChatHistory, receivedMessage]);
        }
    }, [sender]);


    // 채팅 메시지를 전달하는 경우
    const sendMessage = useCallback((e: React.MouseEvent<HTMLInputElement> | React.KeyboardEvent<HTMLTextAreaElement>) => {
        e.preventDefault();

        if (stompClient.current && message) { // 메시지가 있을 때만 전송
            stompClient.current.publish({
                destination: '/app/chat. sendMessage',
                headers: {},
                body: JSON.stringify({ sender, message, type: 'CHAT' })
            });
        }
        
        setMessage('');
        refMessageInput.current?.focus();
    }, [message, sender]); // sender도 메시지 전송 시 사용되므로 deps에 추가


    // 채팅 시 화면 자동 스크롤
    useEffect(() => {
        if (refDialogDiv.current) {
            refDialogDiv.current.scroll({
                top: refDialogDiv.current.scrollHeight, 
                behavior: 'smooth'
            });
        }
    }, [chatHistory]);

    // useEffect(() => {
    //     // 컴포넌트 언마운트 시 웹소켓 연결 해제
    //     return () => {
    //         if (stompClient.current && stompClient.current.connected) {
    //             stompClient.current.disconnect();
    //         }
    //     };
    // }, []); // 빈 배열은 컴포넌트 마운트/언마운트 시 한 번만 실행

    return (
        <>
            <div id='chat-wrap'>
                <div id='chat'>
                    {<div id='dialog' ref={refDialogDiv}>
                        <div className='dialog-board'>
                        {
                            chatHistory.map((item, idx) => (
                                <div key={idx} className={item.sender === sender ? 'me' : 'other'}>
                                    <span><b>{item.sender}</b></span>
                                    {item.createdDt && <span className="date">{item.createdDt}</span>}
                                    <br />
                                    <span>{item.message}</span>
                                </div>
                            ))
                        }
                        </div>
                    </div>}
                    <div id='divSender'>
                        <label htmlFor='senderInput'>닉네임</label> {/* htmlFor 추가 */}
                        <input 
                            id='senderInput' 
                            type='text' 
                            placeholder='닉네임을 입력하세요.' 
                            maxLength={7} 
                            ref={refSenderInput} 
                            value={sender} 
                            disabled={isJoin} 
                            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setSender(e.target.value)} 
                            onKeyDown={(e: React.KeyboardEvent<HTMLInputElement>) => { if (e.key === 'Enter') { joinChatting(e); }}}
                        />
                        <input type='button' value='참가' id='btnJoin' disabled={isJoin} onClick={joinChatting as React.MouseEventHandler<HTMLInputElement>} /> {/* 타입 단언 */}
                    </div>
                    <div id='divMessage'>
                        <label htmlFor='messageInput'>메시지</label> {/* htmlFor 추가 */}
                        <textarea 
                            id='messageInput' 
                            value={message} 
                            ref={refMessageInput}
                            onChange={(e: React.ChangeEvent<HTMLTextAreaElement>) => setMessage(e.target.value)}
                            onKeyDown={(e: React.KeyboardEvent<HTMLTextAreaElement>) => { if (e.key === 'Enter') { sendMessage(e); }}}
                        ></textarea>
                        <input type='button' value='전송' id='btnSend' onClick={sendMessage as React.MouseEventHandler<HTMLInputElement>} /> {/* 타입 단언 */}
                    </div>
                </div>
            </div>
        </>
    );
};

export default Chatting2;