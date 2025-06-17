import { createContext } from "react";

import type { MyPageForMemberPageAction, MyPageForMemberPageState } from "./MyPageForMemberPageTypes";

export const MyPageForMemberPageContext = createContext<{
    state: MyPageForMemberPageState;
    dispatch: React.Dispatch<MyPageForMemberPageAction>;
} | undefined>(undefined);
