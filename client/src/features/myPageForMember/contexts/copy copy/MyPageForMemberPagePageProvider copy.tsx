import { useReducer, type ReactNode } from "react";
import { MyPageForMemberPageContext } from "./MyPageForMemberPageContext copy";
import { reducer, initialState } from "./MyPageForMemberPageReducer copy";


export const MyPageForMemberPageProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <MyPageForMemberPageContext.Provider value={{ state, dispatch }}>
            {children}
        </MyPageForMemberPageContext.Provider>
    );
};
