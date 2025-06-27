import { createSlice } from "@reduxjs/toolkit";

const AuthModalSlice = createSlice({
  name: "authModal",
  initialState: {
    showAuthModal: false,
  },
  reducers: {
    openAuthModal: (state) => {
      state.showAuthModal = true;
    },
    closeAuthModal: (state) => {
      state.showAuthModal = false;
    },
  },
});

export const { openAuthModal, closeAuthModal } = AuthModalSlice.actions;
export default AuthModalSlice.reducer;