import { Avatar, AvatarImage, AvatarFallback } from "@radix-ui/react-avatar";
import { User } from "lucide-react";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { startNewChat } from "./ChatControlSlice";
import type { RootState } from "../../common/store/store";

const ChatButtonModal = () => {

    interface TargetUser {
        id: number
        name: string
        avatar: string
    }
    const dispatch = useDispatch();
    const target = useSelector((state: RootState) => (state.chat.newChatTarget));
    const [targetUser, setTargetUser] = useState<TargetUser | null>(null);

    useEffect(() => {
        if(target != null && target != undefined && target.step == 1){
            console.log(target);
            setTargetUser(target);
        } else {
            console.log("ChatButtonModal에서 target 획득 실패")
        }
    }, [target])

    const closeModal = () => {
        setTargetUser(null);
    };

    const handleChatClick = () => {
        if (targetUser) {
            dispatch(startNewChat({
                id: targetUser.id,
                name: targetUser.name,
                avatar: targetUser.avatar,
                step: 2
            }))
            closeModal();
        }
    };

    return (
        <div className="w-[200px] bg-white p-5 shadow-md rounded-lg relative">
            {/* 모달 */}
            {targetUser && (
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
                            <AvatarImage src={targetUser.avatar} alt={targetUser.name} />
                            <AvatarFallback>
                                <User className="h-16 w-16 relative top-3 left-2" />
                            </AvatarFallback>
                        </Avatar>

                        <h2 className="text-xl font-semibold mb-2 relative bottom-1">회원 정보</h2>
                        <p className="relative bottom-2"><strong>이름:</strong> {targetUser.name}</p>

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

export default ChatButtonModal;
