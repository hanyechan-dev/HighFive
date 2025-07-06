import { Bell, X } from 'lucide-react';
import { Button } from '../../common/components/ui/button';
import { useDispatch, useSelector } from 'react-redux';
import type { RootState } from '../../common/store/store';
import { useCallback, useEffect, useRef, useState } from 'react';
import { ScrollArea } from '../../common/components/ui/scroll-area';
import {
  addUnreadNotification,
  clearAllNotifications,
  selectUnreadNotifications,
} from './NotificationSlice';
import { api } from '../../common/Axios';
import { getStompClient } from '../stompClient';
import type { IMessage, StompSubscription } from '@stomp/stompjs';
import { toast } from 'sonner';
import AuthUtil from '../../common/utils/AuthUtil';

let stompClient = getStompClient();
let subscriptions: { [key: string]: StompSubscription } = {};
const topic = `/topic/notification`;

const AppNotification = () => {
  interface Notification {
    notificationId: number;
    id: number;
    message: string;
    createdTime: string;
  }

  const dispatch = useDispatch();
  const token = useSelector((state: RootState) => state.auth.accessToken);
  const currentUserId = AuthUtil.getIdFromToken(token);
  const notificationTarget = useSelector(
    (state: RootState) => state.notificationControl.notificationTarget,
  );
  const unreadNotifications = useSelector(selectUnreadNotifications);

  const [showNotificationList, setShowNotificationList] = useState<boolean>(false);
  const [notificationList, setNotificationList] = useState<Notification[]>([]);
  const [highlightedIds, setHighlightedIds] = useState<Set<number>>(new Set());

  const showNotificationListRef = useRef(showNotificationList);
  showNotificationListRef.current = showNotificationList;

  // 알림 발송
  const publishNotification = (id: number, receiverId: number, notificationType: string) => {
    if (stompClient?.active) {
      stompClient.publish({
        destination: '/app/notification/send',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          id: id,
          receiverId: receiverId,
          notificationType: notificationType,
        }),
      });
    } else {
      console.error('알림 발송 실패.');
    }
  };

  // 다른 컴포넌트에서의 알림 발송 요청을 처리
  useEffect(() => {
    if (notificationTarget) {
      publishNotification(
        notificationTarget.id,
        notificationTarget.receiverId,
        notificationTarget.notificationType,
      );
    }
  }, [notificationTarget]);

  // 알림 수신 시, NotificationList 반영
  const receiveNotification = (payload: IMessage) => {
    const data: Notification = JSON.parse(payload.body);
    if (data.id == currentUserId) {
      setNotificationList((prev) => [...prev, data]);
      if (!showNotificationListRef.current) {
        dispatch(addUnreadNotification(data.notificationId));
        console.log('data.notificationId: ' + data.notificationId);
        console.log(unreadNotifications);
        toast.message('새로운 알림이 도착했습니다.');
      }
    } else {
      console.log(data);
    }
  };

  // 알림 리스트 데이터 요청
  const getNotifications = useCallback(async () => {
    try {
      const response = await api(true).get('/notifications');
      setNotificationList(response.data);
    } catch (error) {
      console.log('알림 불러오기 실패: ', error);
    }
  }, []);

  // 알림 아이콘 클릭 시, 알림 리스트 출력
  const handleNotificationIconClick = () => {
    console.log('클릭 시점의 안 읽은 알림 IDs (from Redux):', unreadNotifications);
    console.log('클릭 시점의 전체 알림 리스트 (from local state):', notificationList);
    getNotifications();
    setHighlightedIds(new Set(unreadNotifications));
    setShowNotificationList(true);
    dispatch(clearAllNotifications());
  };

  const subscribeNotificationTopic = async () => {
    // 알림 토픽 구독 기능 (메시지 수신 시 콜백 함수 실행)

    let maxTries = 25; // 최대 5초 대기 (25 * 200ms)
    while (!stompClient?.active && maxTries > 0) {
      console.log(
        `[구독 대기] 토픽 Notification: 웹소켓 연결을 기다립니다... (남은 시도: ${maxTries})`,
      );
      // 0.2초 대기
      await new Promise((resolve) => setTimeout(resolve, 200));
      stompClient = getStompClient(); // 최신 클라이언트 상태 다시 가져오기
      maxTries--;
    }

    // 연결이 되었거나, 대기 시간이 초과된 후 최종적으로 구독 시도
    if (stompClient?.active) {
      // 이미 해당 토픽을 구독중이라면 중복 실행 방지
      if (!subscriptions[topic]) {
        console.log(`[구독 성공] 토픽 notification: 구독을 시작합니다.`);
        const subscription = stompClient.subscribe(topic, (message: IMessage) => {
          receiveNotification(message);
        });
        subscriptions[topic] = subscription;
        return subscription;
      }
    } else {
      // 결국 연결에 실패한 경우
      console.error(`[구독 실패] 토픽 Notification: 웹소켓 연결에 실패하여 구독할 수 없습니다.`);
    }
    return null;
  };

  // 로그인 시, 알림 리스트 불러오기
  useEffect(() => {
    if (token) {
      getNotifications();
      subscribeNotificationTopic();
    }
  }, [token, getNotifications]);

  // 하이라이트 효과를 위한 타이머 설정
  useEffect(() => {
    if (showNotificationList && highlightedIds.size > 0) {
      const timer = setTimeout(() => {
        setHighlightedIds(new Set());
      }, 2000); // 2초 후에 배열을 비워서 하이라이트 효과 제거

      return () => {
        clearTimeout(timer); // 만약 2초가 지나기 전에 사용자가 알림창을 닫으면, 설정된 타이머를 취소하여 메모리 누수와 버그를 방지
      };
    }
  }, [showNotificationList, highlightedIds]);

  return (
    <div className="fixed bottom-24 right-6 z-50">
      {/* 알림 아이콘 */}
      {token && ( // 로그인 상태일 때만 알림 아이콘 활성화
        <div className="relative">
          <Button
            onClick={handleNotificationIconClick}
            className="rounded-full w-14 h-14 bg-[#EE57CD] hover:bg-[#EE57CD]/90 shadow-lg"
            size="icon"
          >
            <Bell />
          </Button>
          {/* 알림 아이콘 배지 (읽지 않은 메시지가 있을 때) */}
          {unreadNotifications.length > 0 && !showNotificationList && (
            <span className="absolute top-0 right-0 block h-3 w-3 transform -translate-y-1/2 translate-x-1/2 rounded-full bg-red-500 ring-2 ring-white" />
          )}
        </div>
      )}

      {/* 알림 리스트 */}
      {showNotificationList && (
        <div className="absolute bottom-0 right-20 w-80 bg-white rounded-lg shadow-xl border border-gray-200 max-h-96 overflow-hidden">
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
          <ScrollArea className="h-80">
            <div className="p-2">
              {notificationList.map((notification) => {
                const isHighlighted = highlightedIds.has(notification.notificationId);

                return (
                  <div
                    key={notification.notificationId}
                    className={`
                    flex items-center p-3 rounded-lg cursor-context-menu
                    transition-all duration-500 ease-in-out
                    space-y-0 border-t border-gray-200 first:border-t-0
                    ${isHighlighted ? 'bg-yellow-100 scale-105' : 'hover:bg-gray-100'}
                `}
                  >
                    <div className="flex-1 min-w-0 flex justify-between items-center">
                      <p className="font-medium text-gray-900 truncate">{notification.message}</p>
                    </div>
                  </div>
                );
              })}
            </div>
          </ScrollArea>
        </div>
      )}
    </div>
  );
};

export default AppNotification;
