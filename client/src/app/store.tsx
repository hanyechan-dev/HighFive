import { configureStore } from '@reduxjs/toolkit';
import AuthSlice from '../features/auth/AuthSlice';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage'; // localStorage

import { combineReducers } from 'redux';

const rootReducer = combineReducers({
  auth: AuthSlice.reducer,
});

const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['auth'], // persist할 슬라이스
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false, // 필요
    }),
});

export const persistor = persistStore(store);

