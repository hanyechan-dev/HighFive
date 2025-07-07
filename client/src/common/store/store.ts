import { configureStore, combineReducers } from '@reduxjs/toolkit';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';

import AuthSlice from '../../features/auth/slices/AuthSlice';

import jobPostingFilterSlice from '../../features/jobPostingForMember/slices/JobPostingSlice';
import memberPoolFilterSlice from '../../features/memberPool/slices/MemberPoolSlice';

import AuthModalSlice from '../slices/AuthModalSlice';
import ChatControlSlice from '../../features/chat/ChatControlSlice';
import ChatNotificationSlice from '../../features/chat/ChatNotificationSlice';
import NotificationSlice from '../../features/notification/NotificationSlice';
import NotificationControlSlice from '../../features/notification/NotificationControlSlice';

const rootReducer = combineReducers({
    auth: AuthSlice,
    jobPostingFilter: jobPostingFilterSlice,
    memberPoolFilter: memberPoolFilterSlice,
    chat: ChatControlSlice,
    authModal: AuthModalSlice,
    chatNotification: ChatNotificationSlice,
    notification: NotificationSlice,
    notificationControl: NotificationControlSlice
});

const persistConfig = {
    key: 'root',
    storage,
    whitelist: ['auth', 'jobPostingFilter', 'memberPoolFilter'],
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
