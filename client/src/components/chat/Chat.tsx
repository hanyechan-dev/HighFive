import { useState, useEffect } from 'react';
import type { IMessage } from '@stomp/stompjs';
import { type RootState } from '../../common/store/store';
import { useSelector } from 'react-redux';
import { MessageCircle, X, Send, User } from "lucide-react"
import { Avatar, AvatarImage, AvatarFallback } from '@radix-ui/react-avatar';
import { Dialog, DialogContent, DialogTitle } from '@radix-ui/react-dialog';
import { ScrollArea } from '@radix-ui/react-scroll-area';
import Button from '../../common/components/button/Button';
import Input from '../../common/components/input/Input';
import getIdFromToken from '../../common/utils/AuthUtil';
import AuthUtil from '../../common/utils/AuthUtil';


// 채팅 메시지 객체 타입 정의
interface ChatMessage {
    chatRoomId: number;
    senderId: number;
    name: string;
    content: string;
    createdAt: string;
}

interface ChatUser {
    id: string
    name: string
    avatar: string
    lastMessage: string
}

interface Message {
    id: string
    text: string
    sender: "me" | "other"
    timestamp: Date
}

// 임시 유저 리스트
const mockUsers: ChatUser[] = [
    {
        id: "1",
        name: "김민수",
        avatar: "/placeholder.svg?height=40&width=40",
        lastMessage: "안녕하세요! 잘 지내시나요?",
    },
    {
        id: "2",
        name: "이영희",
        avatar: "/placeholder.svg?height=40&width=40",
        lastMessage: "내일 회의 시간 확인 부탁드려요.",
    },
    {
        id: "3",
        name: "박철수",
        avatar: "/placeholder.svg?height=40&width=40",
        lastMessage: "프로젝트 진행 상황 공유드립니다.",
    },
    {
        id: "4",
        name: "정수진",
        avatar: "/placeholder.svg?height=40&width=40",
        lastMessage: "오늘 수고하셨습니다!",
    },
]

// 임시 메시지 리스트
const mockMessages: { [key: string]: Message[] } = {
    "1": [
        { id: "1", text: "안녕하세요! 잘 지내시나요?", sender: "other", timestamp: new Date() },
        { id: "2", text: "네, 안녕하세요! 잘 지내고 있어요.", sender: "me", timestamp: new Date() },
        { id: "3", text: "오늘 날씨가 정말 좋네요.", sender: "other", timestamp: new Date() },
    ],
    "2": [
        { id: "1", text: "내일 회의 시간 확인 부탁드려요.", sender: "other", timestamp: new Date() },
        { id: "2", text: "오후 2시로 예정되어 있습니다.", sender: "me", timestamp: new Date() },
    ],
    "3": [
        { id: "1", text: "프로젝트 진행 상황 공유드립니다.", sender: "other", timestamp: new Date() },
        { id: "2", text: "감사합니다. 확인해보겠습니다.", sender: "me", timestamp: new Date() },
    ],
    "4": [
        { id: "1", text: "오늘 수고하셨습니다!", sender: "other", timestamp: new Date() },
        { id: "2", text: "수고하셨어요! 내일 봐요.", sender: "me", timestamp: new Date() },
    ],
}

// SockJS와 Stomp를 이용해서 웹 소켓 서버로 연결하고 메시지를 주고 받는 기능 구현
const Chat = () => {

    const [chatRoomId, setChatRoomId] = useState<number | null>(null);
    const [senderId, setSenderId] = useState<number | null>(null);
    const [targetId, setTargetId] = useState<number | null>(null);
    const [content, setContent] = useState<string>('');
    const [chatHistory, setChatHistory] = useState<ChatMessage[]>([]);

    const [showChatList, setShowChatList] = useState(false)
    const [selectedChat, setSelectedChat] = useState<ChatUser | null>(null)
    const [messages, setMessages] = useState<Message[]>([])
    const [newMessage, setNewMessage] = useState("")

    // ref 변수 정의
    // const refDialogDiv = useRef<HTMLDivElement | null>(null);
    // const refSenderInput = useRef<HTMLInputElement | null>(null);
    // const refMessageInput = useRef<HTMLTextAreaElement | null>(null);

    const stompClient = useSelector((state: RootState) => state.websocket.client); // 웹소켓 객체 획득
    const token = useSelector((state: RootState) => state.auth.accessToken); // 토큰 획득

    // 토큰에서 ID 추출하여, 발신자 ID로 세팅
    useEffect(() => {
        const extractedId = AuthUtil.getIdFromToken(token); // ID 추출
        if (extractedId != null) {
            setSenderId(extractedId);
        }
    }, [token])

    // 채팅방 생성 또는 기존 채팅방 참여
    const createChatRoom() => {
        setTargetId(extractedTargetId); // 채팅 대상 회원 ID 추출 (추후 수정 필요)

        const createChatRoom = () => {
            await fetch("https://localhost:9000/chats", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify({
                    id: // 채팅 대상 회원  ID
        })
            })
                .then((response) => response.json()) // JSON -> Javascript 객체로 변환
                .then((data) => setChatRoomId(data.chatRoomId))
                .catch((error) => console.error("chatRoomId를 가져오지 못했습니다.: ", error));
        }

        // 채팅방 구독 후, 채팅 대상 또한 동일한 채팅방을 구독하도록 서버에 요청
        if (stompClient != null) {
            stompClient.subscribe(`/topic/${chatRoomId}`, receiveMessage);
            stompClient.publish({
                destination: "app/chat/subscribe",
                body: JSON.stringify({ targetId, chatRoomId })
            })
        }
    }

    // 메시지 발송
    const sendMessage = () => {
        if (stompClient) {
            stompClient.publish({
                destination: '/app/chat/send',
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify({ senderId, targetId, chatRoomId, content })
            });
        }
    };

    // 메시지 수신
    const receiveMessage = (payload: IMessage) => {
        const receivedMessage: ChatMessage = JSON.parse(payload.body);
        setChatHistory(prevHistory => [...prevHistory, receivedMessage]);
    };

    // 채팅 내역 불러오기
    const getHistory = async () => { // IMessage : STOMP 라이브러리에서 지원하는 메시지 인터페이스
        try {
            const response = await fetch(`http://localhost:8090/chats/detail`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`,
                },
                body: JSON.stringify({ targetId })
            });

            const chatContents: ChatMessage[] = await response.json();

            if (chatContents) {
                setChatHistory(() => [...chatContents]); // 대화 내용 업데이트
            }
        } catch (error) {
            console.error("채팅 내역 불러오기 실패: ", error);
        }
    }

    // 채팅 시 화면 자동 스크롤
    // useEffect(() => {
    //     if (refDialogDiv.current) {
    //         refDialogDiv.current.scroll({
    //             top: refDialogDiv.current.scrollHeight, 
    //             behavior: 'smooth'
    //         });
    //     }
    // }, [chatHistory]);

    // 채팅 아이콘 클릭 시, 채팅 리스트 출력
    const handleChatIconClick = () => {
        setShowChatList(!showChatList)
    }

    // 채팅방 클릭 시, 채팅 메시지 출력
    const handleChatSelect = (user: ChatUser) => {
        setSelectedChat(user)
        setMessages(mockMessages[user.id] || [])
        setShowChatList(false)
    }

    // "전송" 버튼 클릭 시, 메시지 전송
    const handleSendMessage = () => {
        if (newMessage.trim() && selectedChat) {
            const message: Message = {
                id: Date.now().toString(),
                text: newMessage,
                sender: "me",
                timestamp: new Date(),
            }
            setMessages((prev) => [...prev, message])
            setNewMessage("")
        }
    }

    // Enter키로 메시지 전송
    const handleKeyPress = (e: React.KeyboardEvent) => {
        if (e.key === "Enter") {
            handleSendMessage()
        }
    }

    return (
        <div className="fixed bottom-6 right-6 z-50">
            {/* 채팅 아이콘 */}
            <Button
                onClick={handleChatIconClick}
                className="rounded-full w-14 h-14 bg-[#EE57CD] hover:bg-[#EE57CD]/90 shadow-lg"
                size="icon"
            >
                <MessageCircle className="h-6 w-6 text-white" />
            </Button>

            {/* 채팅 리스트 */}
            {showChatList && (
                <div className="absolute bottom-16 right-0 w-80 bg-white rounded-lg shadow-xl border border-gray-200 max-h-96 overflow-hidden">
                    <div className="p-4 border-b border-gray-200">
                        <div className="flex items-center justify-between">
                            <h3 className="font-semibold text-gray-900">채팅</h3>
                            <Button variant="ghost" size="icon" onClick={() => setShowChatList(false)} className="h-6 w-6">
                                <X className="h-4 w-4" />
                            </Button>
                        </div>
                    </div>
                    <ScrollArea className="max-h-80">
                        <div className="p-2">
                            {mockUsers.map((user) => (
                                <div
                                    key={user.id}
                                    onClick={() => handleChatSelect(user)}
                                    className="flex items-center p-3 hover:bg-gray-50 rounded-lg cursor-pointer transition-colors"
                                >
                                    <Avatar className="h-10 w-10 mr-3">
                                        <AvatarImage src={user.avatar || "/placeholder.svg"} alt={user.name} />
                                        <AvatarFallback>
                                            <User className="h-5 w-5" />
                                        </AvatarFallback>
                                    </Avatar>
                                    <div className="flex-1 min-w-0">
                                        <p className="font-medium text-gray-900 truncate">{user.name}</p>
                                        <p className="text-sm text-gray-500 truncate">{user.lastMessage}</p>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </ScrollArea>
                </div>
            )}

            {/* 채팅 모달 */}
            <Dialog open={!!selectedChat} onOpenChange={() => setSelectedChat(null)}>
                <DialogContent className="sm:max-w-md bg-white">
                    <DialogHeader className="flex flex-row items-center justify-between space-y-0 pb-4">
                        <DialogTitle className="flex items-center space-x-3">
                            <Avatar className="h-8 w-8">
                                <AvatarImage src={selectedChat?.avatar || "/placeholder.svg"} alt={selectedChat?.name} />
                                <AvatarFallback>
                                    <User className="h-4 w-4" />
                                </AvatarFallback>
                            </Avatar>
                            <span>{selectedChat?.name}</span>
                        </DialogTitle>
                        <Button variant="ghost" size="icon" onClick={() => setSelectedChat(null)} className="h-6 w-6">
                            <X className="h-4 w-4" />
                        </Button>
                    </DialogHeader>

                    {/* 메시지 영역 */}
                    <ScrollArea className="h-80 w-full pr-4">
                        <div className="space-y-3">
                            {messages.map((message) => (
                                <div key={message.id} className={`flex ${message.sender === "me" ? "justify-end" : "justify-start"}`}>
                                    <div
                                        className={`max-w-xs px-4 py-2 rounded-lg ${message.sender === "me"
                                                ? "bg-white border border-[#EE57CD] text-[#EE57CD]"
                                                : "bg-[#EE57CD] text-white"
                                            }`}
                                    >
                                        <p className="text-sm">{message.text}</p>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </ScrollArea>

                    {/* 메시지 입력 영역 */}
                    <div className="flex items-center space-x-2 pt-4 border-t">
                        <Input
                            value={newMessage}
                            onChange={(e) => setNewMessage(e.target.value)}
                            onKeyPress={handleKeyPress}
                            placeholder="메시지를 입력하세요..."
                            className="flex-1"
                        />
                        <Button onClick={handleSendMessage} className="bg-[#EE57CD] hover:bg-[#EE57CD]/90 text-white" size="icon">
                            <Send className="h-4 w-4" />
                        </Button>
                    </div>
                </DialogContent>
            </Dialog>
        </div>
    )
}

export default Chat;