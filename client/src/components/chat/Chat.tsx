import { useCallback, useRef, useState, useEffect } from 'react';
import type { IMessage } from '@stomp/stompjs';

// 채팅 메시지 객체 타입 정의
interface ChatMessage {
    chatRoomId: number;
    senderId: number;
    name: string;
    content: string;
    createdAt: string;
}

// SockJS와 Stomp를 이용해서 웹 소켓 서버로 연결하고 메시지를 주고 받는 기능 구현
const Chat = () => {

const [chatRoomId, setChatRoomId] = useState<number | null>(null);
const [senderId, setSenderId] = useState<number | null>(null);
const [targetId, setTargetId] = useState<number | null>(null);
const [content, setContent] = useState<string | null>('');
const [chatHistory, setChatHistory] = useState<string | null>('');

// ref 변수 정의
const refDialogDiv = useRef<HTMLDivElement | null>(null);
const refSenderInput = useRef<HTMLInputElement | null>(null);
const refMessageInput = useRef<HTMLTextAreaElement | null>(null);

// 토큰에서 ID 추출하여, 발신자 ID로 세팅팅
useEffect(() => {
    setSenderId(extractedId);
})


// 채팅방 생성 또는 기존 채팅방 참여
const createChatRoom() => {
    token = // 클라이언트 토큰 할당
    setTargetId(extractedTargetId); // 채팅 대상 회원 ID 추출 (추후 수정 필요)

    const createChatRoom = () => {
    await fetch("https://localhost:9000/chats", {
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
    }

    // 채팅방 구독 후, 채팅 대상 또한 동일한 채팅방을 구독하도록 서버에 요청
    stompClient.current.subscribe(`/topic/${chatRoomId}`, receiveMessage);
    stompClient.current.publish({
        destination: "app/chat/subscribe",
        body: JSON.stringify({ targetId, chatRoomId })
    });
}


// 메세지 발송
const sendMessage = useCallback(() => {
    if (stompClient.current && content) { // 메시지가 있을 때만 전송
        stompClient.current.publish({
            destination: '/app/chat/send',
            headers: {},
            body: JSON.stringify({ senderId, targetId, chatRoomId, content })
        });
    }
    setContent('');
}, [content]);


// 메세지 수신
const receiveMessage = useCallback((payload: IMessage) => { // STOMP 라이브러리에서 지원하는 메시지 인터페이스스
    const receivedMessage: ChatMessage = JSON.parse(payload.body);

    if (receivedMessage.history) {
        setChatHistory(prevHistory => [...prevHistory, ...receivedMessage.history!]);
    }
    } else {
        setChatHistory(prevChatHistory => [...prevChatHistory, receivedMessage]);
    }
}, [chatHistory]);


// 채팅 시 화면 자동 스크롤
useEffect(() => {
    if (refDialogDiv.current) {
        refDialogDiv.current.scroll({
            top: refDialogDiv.current.scrollHeight, 
            behavior: 'smooth'
        });
    }
}, [chatHistory]);

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

export default Chat;