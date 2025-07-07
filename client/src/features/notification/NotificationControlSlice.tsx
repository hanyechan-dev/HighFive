import { createSlice, type PayloadAction } from '@reduxjs/toolkit';

interface NotificationState {
  // 알림을 발송할 대상 유저 정보를 담을 상태
  notificationTarget: { id: number, receiverId: number; notificationType: string; } | null;
}

const initialState: NotificationState = {
  notificationTarget: null,
};

const notificationSlice = createSlice({
  name: 'notificationControl',
  initialState,
  reducers: {
    // 알림 발송 액션
    sendNotification: (state, action: PayloadAction<{
      id: number;
      receiverId: number;
      notificationType: string;
    }>) => {
      state.notificationTarget = action.payload;
    },
    // 알림 발송 후, 상태를 초기화하는 액션
    clearNotificationTarget: (state) => {
      state.notificationTarget = null;
    },
  },
});

export const { sendNotification, clearNotificationTarget } = notificationSlice.actions;
export default notificationSlice.reducer;