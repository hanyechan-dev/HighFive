import React, { useCallback, useRef, useState, useEffect } from 'react';
import './Chat.css';
import type { IMessage, Frame } from '@stomp/stompjs';
import { Stomp, Client as StompClient } from '@stomp/stompjs'; // Client, IMessage, Frame 타입 가져오기
import SockJS from 'sockjs-client';

// 채팅 메시지 객체의 타입을 정의합니다.
interface ChatMessage {
    type: 'JOIN' | 'LEAVE' | 'CHAT' | string; // 좀 더 구체적인 타입을 원하면 추가 가능
    sender: string;
    message: string;
    createdDt?: string; // JSX에서 사용되므로 추가
    history?: ChatMessage[]; // JOIN 메시지 수신 시 사용될 수 있음
}

// SockJS와 Stomp를 이용해서 웹 소켓 서버로 연결하고 메시지를 주고 받는 기능을 구현합니다.
const Chatting2: React.FC = () => {
    // 상태 변수 정의
    const [isJoin, setIsJoin] = useState<boolean>(false);
    const [chatHistory, setChatHistory] = useState<ChatMessage[]>([]);
    const [sender, setSender] = useState<string>('');
    const [message, setMessage] = useState<string>('');
    
    // ref 변수 정의
    const refDialogDiv = useRef<HTMLDivElement | null>(null);
    const refSenderInput = useRef<HTMLInputElement | null>(null);
    const refMessageInput = useRef<HTMLTextAreaElement | null>(null);
    const stompClient = useRef<StompClient | null>(null); // StompClient 타입 사용
    

    // 채팅 참여
    const joinChatting = useCallback((e: React.MouseEvent<HTMLInputElement> | React.KeyboardEvent<HTMLInputElement>) => {
        e.preventDefault();

        if (!sender) {
            alert('닉네임을 입력하세요.');
            refSenderInput.current?.focus(); // Optional chaining
            return;
        }

        // Stomp.over는 StompClient 인스턴스를 반환합니다.
        // SockJS 생성자 함수는 WebSocket-like 객체를 반환해야 합니다.
        // Stomp.over의 첫 번째 인자는 () => WebSocket 또는 WebSocket 인스턴스여야 합니다.
        // SockJS는 WebSocket 인터페이스를 따르므로 직접 전달 가능합니다.
        stompClient.current = Stomp.over(() => new SockJS('http://localhost:8080/ws'));  
        stompClient.current.connect({}, onConnected, onError); // 헤더가 없으면 {}
    }, [sender]); // onConnected, onError를 deps에 추가하거나 useCallback으로 감싸야 할 수 있습니다. 아래에서 수정.

    // 연결에 성공한 경우
    // onConnected와 onError는 joinChatting의 deps에 포함되므로,
    // 이들이 변경되지 않도록 useCallback으로 감싸거나,
    // joinChatting의 deps에서 제거하고 싶다면 useRef 등으로 관리해야 합니다.
    // 여기서는 onConnected, onError 내부에서 사용하는 sender에 대한 의존성만 명시합니다.
    const onConnected = useCallback(() => {
        if (stompClient.current) {
            stompClient.current.subscribe('/topic/chatting', onMessageReceived);
            stompClient.current.send('/app/chat.addUser', {}, JSON.stringify({ sender, type: 'JOIN' }));
        }
    }, [sender]); // onMessageReceived도 deps에 필요할 수 있습니다. 아래에서 수정.

    // 연결에 실패한 경우
    // Frame | string 타입도 가능 (stompjs connect의 onError 타입 참고)
    const onError = useCallback((error: Frame | string | Error) => {
        console.log('연결실패', error);
        // error가 string일 수도 있고, Frame 객체일 수도, Error 객체일 수도 있습니다.
        if (typeof error === 'string') {
            console.error('STOMP Error: ' + error);
        } else if (error instanceof Frame) {
            console.error('STOMP Frame Error: ' + error.headers?.message || error.body);
        } else {
            console.error('STOMP Connection Error', error);
        }
    }, []);

    // 메시지를 수신한 경우
    // onMessageReceived는 onConnected의 deps에 포함되므로, useCallback으로 감싸줍니다.
    const onMessageReceived = useCallback((payload: IMessage) => { // IMessage 타입 사용
        const receivedMessage: ChatMessage = JSON.parse(payload.body);

        // JOIN 메시지이고, 현재 사용자의 sender와 동일한 경우
        if (receivedMessage.type === 'JOIN' && receivedMessage.sender === sender) {
            setIsJoin(true);
            if (receivedMessage.history) {
                // 이전 채팅 이력을 추가할 때 함수형 업데이트를 사용하면 최신 상태를 보장할 수 있습니다.
                setChatHistory(prevHistory => [...prevHistory, ...receivedMessage.history!]);
            }
        } else {
            setChatHistory(prevChatHistory => [...prevChatHistory, receivedMessage]);
        }
    }, [sender]); // sender를 의존성 배열에 추가

    // 채팅 메시지를 전달하는 경우
    const sendMessage = useCallback((e: React.MouseEvent<HTMLInputElement> | React.KeyboardEvent<HTMLTextAreaElement>) => {
        e.preventDefault();

        if (stompClient.current && message) { // 메시지가 있을 때만 전송
            stompClient.current.send('/app/chat.sendMessage', {}, JSON.stringify({ sender, message, type: 'CHAT' }));
        }
        
        setMessage('');
        refMessageInput.current?.focus(); // Optional chaining
    }, [message, sender]); // sender도 메시지 전송 시 사용되므로 deps에 추가

    // 채팅 내용 출력 영역을 자동 스크롤
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
                    <div id='dialog' ref={refDialogDiv}>
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
                    </div>
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