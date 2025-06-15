import { configureStore, combineReducers } from '@reduxjs/toolkit';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';

import AuthSlice from '../../features/auth/AuthSlice';
import jobPostingFilterSlice from '../../features/jobPostingForMember/slices/JobPostingSlice';

const rootReducer = combineReducers({
  auth: AuthSlice,
  jobPostingFilter: jobPostingFilterSlice
});

const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['auth', 'jobPostingFilter'],
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
