import { configureStore, combineReducers } from '@reduxjs/toolkit';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';

import AuthSlice from '../../features/auth/slices/AuthSlice';

import jobPostingFilterSlice from '../../features/jobPostingForMember/slices/JobPostingSlice';
import memberPoolFilterSlice from '../../features/memberPool/slices/MemberPoolSlice';

import AuthModalSlice from '../slices/AuthModalSlice';
import ChatControlSlice from '../../features/chat/ChatControlSlice';

const rootReducer = combineReducers({
    auth: AuthSlice,
    jobPostingFilter: jobPostingFilterSlice,
    memberPoolFilter: memberPoolFilterSlice,
    chat: ChatControlSlice,
    authModal: AuthModalSlice
});

const persistConfig = {
    key: 'root',
    storage,
    whitelist: ['auth', 'jobPostingFilter', 'memberPoolFilter', 'chat'],
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
