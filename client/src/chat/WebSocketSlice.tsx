// features/websocket/websocketSlice.ts
import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import { Client as StompClient } from '@stomp/stompjs';

interface WebSocketState {
    client: StompClient | null;
}

const initialState: WebSocketState = {
    client: null,
};

const websocketSlice = createSlice({
    name: 'websocket',
    initialState,
    reducers: {
        setClient(state, action: PayloadAction<StompClient | null>) {
            state.client = action.payload;
        },
    },
});

export const { setClient } = websocketSlice.actions;
export default websocketSlice.reducer;