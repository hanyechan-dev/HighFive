import { configureStore, combineReducers } from '@reduxjs/toolkit';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';

<<<<<<< HEAD:client/src/common/store/store.tsx
import AuthSlice from '../../features/auth/slices/AuthSlice';

import jobPostingFilterSlice from '../../features/jobPostingForMember/slices/JobPostingSlice';
import memberPoolFilterSlice from  '../../features/memberPool/slices/MemberPoolSlice';

import WebSocketSlice from '../../chat/WebSocketSlice';
import AuthModalSlice from '../slices/AuthModalSlice';
=======
import AuthSlice from '../../features/auth/AuthSlice';
import jobPostingFilterSlice from '../../features/jobPostingForMember/JobPostingSlice';
import ChatControlSlice from '../../chat/ChatControlSlice';
>>>>>>> origin/SOCKET_ADMIN:client/src/common/store/store.ts

const rootReducer = combineReducers({
  auth: AuthSlice,
  jobPostingFilter: jobPostingFilterSlice,
<<<<<<< HEAD:client/src/common/store/store.tsx
  memberPoolFilter: memberPoolFilterSlice,
  websocket: WebSocketSlice,
  authModal: AuthModalSlice
=======
  chatControl: ChatControlSlice,
  chat: ChatControlSlice
>>>>>>> origin/SOCKET_ADMIN:client/src/common/store/store.ts
});

const persistConfig = {
  key: 'root',
  storage,
<<<<<<< HEAD:client/src/common/store/store.tsx
  whitelist: ['auth', 'jobPostingFilter','memberPoolFilter', 'websocket'],
=======
  whitelist: ['auth', 'jobPostingFilter', 'chatControl', 'chat'],
>>>>>>> origin/SOCKET_ADMIN:client/src/common/store/store.ts
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({ serializableCheck: false }),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export const persistor = persistStore(store);
