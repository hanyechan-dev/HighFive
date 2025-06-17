import { useReducer, type ReactNode } from "react";
import { MyPageForMemberPageContext } from "./MyPageForMemberPageContext";
import { initialState, reducer } from "./MyPageForMemberPageReducer";

export const MyPageForMemberPageProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <MyPageForMemberPageContext.Provider value={{ state, dispatch }}>
            {children}
        </MyPageForMemberPageContext.Provider>
    );
};
