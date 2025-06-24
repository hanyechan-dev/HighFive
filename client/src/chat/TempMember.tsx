import { Avatar, AvatarImage, AvatarFallback } from "@radix-ui/react-avatar";
import { User } from "lucide-react";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { startNewChat } from "./ChatControlSlice";

const TempMember = () => {

    interface MockUser {
        id: number
        name: string
        userType: string
        avatar: string
    }

    const dispatch = useDispatch();
    const [selectedUser, setSelectedUser] = useState<MockUser | null>(null);

    const handleUserClick = (user: MockUser) => {
        setSelectedUser(user);
    };

    const closeModal = () => {
        setSelectedUser(null);
    };

    const handleChatClick = () => {
        if (selectedUser) {
            dispatch(startNewChat({ targetId: 1, name: "샘숭맨" }));
            closeModal();
        }
    };

    // 임시 유저 리스트
    const mockUsers: MockUser[] = [
        {
            id: 1,
            name: "샘숭맨",
            userType: "기업회원",
            avatar: "/placeholder.svg?height=40&width=40",
        },
        {
            id: 2,
            name: "리짜이밍",
            userType: "일반회원",
            avatar: "/placeholder.svg?height=40&width=40",
        },
        {
            id: 3,
            name: "준스톤",
            userType: "일반회원",
            avatar: "/placeholder.svg?height=40&width=40",
        },
        {
            id: 4,
            name: "한화",
            userType: "기업회원",
            avatar: "/placeholder.svg?height=40&width=40",
        },
    ];

    return (
        <div className="w-[200px] bg-white p-5 shadow-md rounded-lg relative">
            {mockUsers.map((user) => (
                <div
                    key={user.id}
                    onClick={() => handleUserClick(user)}
                    className="flex items-center p-3 hover:bg-gray-50 rounded-lg cursor-pointer transition-colors"
                >
                    <Avatar className="h-10 w-10 mr-3">
                        <AvatarImage src={user.avatar} alt={user.name} />
                        <AvatarFallback>
                            <User className="h-5 w-5" />
                        </AvatarFallback>
                    </Avatar>
                    <div className="flex-1 min-w-0">
                        <p className="font-medium text-gray-900 truncate">{user.name}</p>
                    </div>
                </div>
            ))}

            {/* 모달 */}
            {selectedUser && (
                <div className="fixed inset-0 flex items-center justify-center z-50">
                    {/* 모달 백그라운드(클릭 시 닫힘) */}
                    <div
                        className="absolute inset-0 bg-gray-900 opacity-50"
                        onClick={closeModal}
                    ></div>

                    {/* 모달 내용 */}
                    <div className="relative bg-white rounded-lg shadow-lg p-6 w-80 z-10 flex flex-col items-center text-center">
                        {/* 프로필 이미지 */}
                        <Avatar className="h-20 w-20 mb-4">
                            <AvatarImage src={selectedUser.avatar} alt={selectedUser.name} />
                            <AvatarFallback>
                                <User className="h-8 w-8" />
                            </AvatarFallback>
                        </Avatar>

                        <h2 className="text-xl font-semibold mb-2">회원 정보</h2>
                        <p><strong>이름:</strong> {selectedUser.name}</p>
                        <p className="mb-6"><strong>회원 유형:</strong> {selectedUser.userType}</p>

                        <button
                            onClick={handleChatClick}
                            className="w-full py-2 px-4 bg-[#EE57CD] text-white rounded-md hover:bg-[#f07fd7] transition-colors"
                        >
                            채팅하기
                        </button>

                        <button
                            onClick={closeModal}
                            className="mt-3 w-full py-2 px-4 border border-gray-300 rounded-md hover:bg-gray-100 transition-colors"
                        >
                            닫기
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default TempMember;
