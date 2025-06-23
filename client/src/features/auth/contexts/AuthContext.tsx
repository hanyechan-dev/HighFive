import { createContext } from "react";

import type { AuthAction, AuthState } from "./AuthTypes";

export const AuthContext = createContext<{
    state: AuthState;
    dispatch: React.Dispatch<AuthAction>;
} | undefined>(undefined);
