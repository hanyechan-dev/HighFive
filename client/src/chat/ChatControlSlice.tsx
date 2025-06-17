import { createSlice, type PayloadAction } from '@reduxjs/toolkit';

// 다른 컴포넌트에서 넘어올 유저 정보의 타입
interface TargetUser {
    id: number;
    name: string;
    avatar: string;
}

interface ChatControlState {
    // 채팅을 시작할 대상 유저 정보. 없으면 null
    pendingTargetUser: TargetUser | null;
}

const initialState: ChatControlState = {
    pendingTargetUser: null,
};

const chatControlSlice = createSlice({
    name: 'chatControl',
    initialState,
    reducers: {
        // 채팅 시작 요청 액션. 다른 컴포넌트에서 이 액션을 호출함
        requestChat: (state, action: PayloadAction<TargetUser>) => {
            state.pendingTargetUser = action.payload;
        },
        // 채팅 요청 처리 완료 액션. Chat 컴포넌트에서 요청을 처리한 뒤 호출함
        clearChatRequest: (state) => {
            state.pendingTargetUser = null;
        },
    },
});

export const { requestChat, clearChatRequest } = chatControlSlice.actions;
export default chatControlSlice.reducer;