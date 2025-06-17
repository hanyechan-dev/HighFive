import { useReducer, type ReactNode } from "react";
import { MemberInfoTabContext } from "./MemberInfoTabContext";
import { initialState, reducer } from "./MemberInfoTabReducer";

export const MemberInfoTabProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <MemberInfoTabContext.Provider value={{ state, dispatch }}>
            {children}
        </MemberInfoTabContext.Provider>
    );
};
