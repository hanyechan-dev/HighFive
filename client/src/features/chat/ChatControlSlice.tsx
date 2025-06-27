import { createSlice, type PayloadAction } from '@reduxjs/toolkit';

interface ChatState {
  // 새로 채팅을 시작할 대상 유저 정보를 담을 상태
  newChatTarget: { id: number; name: string } | null;
}

const initialState: ChatState = {
  newChatTarget: null,
};

const chatSlice = createSlice({
  name: 'chat',
  initialState,
  reducers: {
    // 채팅 시작을 요청하는 액션
    startNewChat: (state, action: PayloadAction<{ id: number; name: string }>) => {
      state.newChatTarget = action.payload;
    },
    // 채팅방이 만들어진 후, 상태를 초기화하는 액션
    clearNewChatTarget: (state) => {
      state.newChatTarget = null;
    },
  },
});

export const { startNewChat, clearNewChatTarget } = chatSlice.actions;
export default chatSlice.reducer;