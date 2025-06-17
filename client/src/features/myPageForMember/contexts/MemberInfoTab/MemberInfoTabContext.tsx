import { createContext } from "react";

import type { MemberInfoTabAction, MemberInfoTabState } from "./MemberInfoTabTypes";

export const MemberInfoTabContext = createContext<{
    state: MemberInfoTabState;
    dispatch: React.Dispatch<MemberInfoTabAction>;
} | undefined>(undefined);
