import { MessageCircle, X } from "lucide-react"
import { Button } from "../../common/components/ui/button"
import { useSelector } from "react-redux"
import type { RootState } from "../../common/store/store"
import { useEffect, useState } from "react"
import { ScrollArea } from "../../common/components/ui/scroll-area"
import { selectUnreadNotifications } from "./NotificationSlice"
import { api } from "../../common/Axios"
import { getStompClient } from "../stompClient"
import type { IMessage } from "@stomp/stompjs"

let stompClient = getStompClient();

export const Notification = () => {

    interface Notification {
        notificationId: number,
        id: number,
        message: string,
        createdTime: string
    }

    const token = useSelector((state: RootState) => (state.auth.accessToken))
    const unreadNotifications = useSelector(selectUnreadNotifications)

    const [showNotificationList, setShowNotificationList] = useState<boolean>(false)
    const [notificationList, setNotificationList] = useState<Notification[]>([])
    const [subsState, setSubsState] = useState<boolean>(false)

    const receiveNotification = (payload: IMessage) => {
        const data : Notification[] = JSON.parse(payload.body)
        data.map((notification) => {
            setNotificationList((prev) => [...prev, notification])
        })
    }

    const getNotifications = async () => {
        try {
            const response = await api(true).get("/notifications")
            setNotificationList(response.data)
        } catch (error) {
            console.log("알림 불러오기 실패: ", error)
        }
    }

    const handleNotificationIconClick = () => {
        setShowNotificationList(true)
    }

    const subscribeNotificationTopic = async () => {
        // 알림 토픽 구독 기능 (메시지 수신 시 콜백 함수 실행)
        const topic = `/topic/notification`;

        let maxTries = 25; // 최대 5초 대기 (25 * 200ms)
        while (!stompClient?.active && maxTries > 0) {
            console.log(`[구독 대기] 토픽 Notification: 웹소켓 연결을 기다립니다... (남은 시도: ${maxTries})`);
            // 0.2초 대기
            await new Promise(resolve => setTimeout(resolve, 200));
            stompClient = getStompClient(); // 최신 클라이언트 상태 다시 가져오기
            maxTries--;
        }

        // 연결이 되었거나, 대기 시간이 초과된 후 최종적으로 구독 시도
        if (stompClient?.active) {
            // 이미 해당 토픽을 구독중이라면 중복 실행 방지
            if (!subsState) {
                console.log(`[구독 성공] 토픽 notification: 구독을 시작합니다.`);
                const subscription = stompClient.subscribe('/topic/notification', (message: IMessage) => {
                    receiveNotification(message)
                });
                setSubsState(true)
                return subscription;
            }
        } else {
            // 결국 연결에 실패한 경우
            console.error(`[구독 실패] 토픽 Notification: 웹소켓 연결에 실패하여 구독할 수 없습니다.`);
        }
        return null;
    }

    // 로그인 시, 알림 리스트 불러오기
    useEffect(() => {
        getNotifications()
        subscribeNotificationTopic()
    }, [token, getNotifications])

    return (
        <div className="fixed top-6 right-6 z-50">
            {/* 알림 아이콘 */}
            {token && ( // 로그인 상태일 때만 알림 아이콘 활성화
                <div className="relative">
                    <Button
                        onClick={handleNotificationIconClick}
                        className="rounded-full w-14 h-14 bg-[#EE57CD] hover:bg-[#EE57CD]/90 shadow-lg"
                        size="icon"
                    >
                        <MessageCircle className="w-6 h-6 text-white" />
                    </Button>
                    {/* 알림 아이콘 배지 (읽지 않은 메시지가 있을 때) */}
                    {unreadNotifications.length > 0 && (
                        <span className="absolute top-0 right-0 block h-3 w-3 transform -translate-y-1/2 translate-x-1/2 rounded-full bg-red-500 ring-2 ring-white" />
                    )}
                </div>
            )}

            알림 리스트
            {showNotificationList && (
                <div className="absolute bottom-16 right-0 w-80 bg-white rounded-lg shadow-xl border border-gray-200 max-h-96 overflow-hidden">
                    <div className="p-4 border-b border-gray-200">
                        <div className="flex items-center justify-between">
                            <h3 className="font-semibold text-gray-900">알림</h3>
                            <Button
                                variant="ghost"
                                size="icon"
                                onClick={() => setShowNotificationList(false)}
                                className="h-6 w-6"
                            >
                                <X className="h-4 w-4" />
                            </Button>
                        </div>
                    </div>
                    <ScrollArea className="max-h-80">
                        <div className="p-2">
                            {notificationList.map((notification) => (
                                <div
                                    key={notification.notificationId}
                                    className="flex items-center p-3 hover:bg-gray-50 rounded-lg cursor-pointer transition-colors"
                                >
                                    <div className="flex-1 min-w-0 flex justify-between items-center">
                                        <p className="font-medium text-gray-900 truncate">
                                            {notification.message}
                                        </p>
                                        {/* 채팅방 별 배지 (읽지 않은 메시지가 있을 때) */}
                                        {unreadNotifications.includes(notification.notificationId) && (
                                                // 어떤 동작을 할지 여기다 지정해야 함
                                            )}
                                    </div>
                                </div>
                            ))}
                        </div>
                    </ScrollArea>
                </div>
            )}
        </div>
    )
}