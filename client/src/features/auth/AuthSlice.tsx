import { createSlice } from "@reduxjs/toolkit"


const AuthSlice = createSlice({
  name: 'auth', // 슬라이스 이름
  initialState: {
    accessToken: null,
    refreshToken: null,
  },
  reducers: {
    setToken: (state, action) => {
      state.accessToken = action.payload.accessToken;
      state.refreshToken = action.payload.refreshToken;
    },
    clearToken: (state) => {
      state.accessToken = null;
      state.refreshToken = null;
    },
  },
});

export const { setToken, clearToken } = AuthSlice.actions;
export default AuthSlice;