import { createContext } from "react";
import type { MyPageForMemberPageAction, MyPageForMemberPageState } from "./MyPageForMemberPageTypes copy";



export const MyPageForMemberPageContext = createContext<{
    state: MyPageForMemberPageState;
    dispatch: React.Dispatch<MyPageForMemberPageAction>;
} | undefined>(undefined);
