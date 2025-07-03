import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import type { RootState } from "../../common/store/store";

interface NotificationState {
  // 읽지 않은 메시지가 있는 채팅방 ID 목록
  unreadNotifications: number[];
}

const initialState: NotificationState = {
  unreadNotifications: [],
};

export const NotificationSlice = createSlice({
  name: "notification",
  initialState,
  reducers: {
    // 읽지 않은 알림 추가
    addUnreadNotification: (state, action: PayloadAction<number>) => {
      // 중복 추가 방지
      if (!state.unreadNotifications.includes(action.payload)) {
        state.unreadNotifications.push(action.payload);
      }
    },
    // 읽은 알림 제거
    removeUnreadNotification: (state, action: PayloadAction<number>) => {
      state.unreadNotifications = state.unreadNotifications.filter(
        (roomId) => roomId !== action.payload
      );
    },
    // 모든 알림 초기화
    clearAllNotificationtNotifications: (state) => {
      state.unreadNotifications = [];
    }
  },
});

export const { addUnreadNotification, removeUnreadNotification, clearAllNotificationtNotifications } = NotificationSlice.actions;

export const selectUnreadNotifications = (state: RootState) => state.notification.unreadNotifications;

export default NotificationSlice.reducer;