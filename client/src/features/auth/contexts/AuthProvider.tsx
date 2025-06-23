import { useReducer, type ReactNode } from "react";
import { AuthContext } from "./AuthContext";
import { initialState, reducer } from "./AuthReducer";

export const AuthProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <AuthContext.Provider value={{ state, dispatch }}>
            {children}
        </AuthContext.Provider>
    );
};
