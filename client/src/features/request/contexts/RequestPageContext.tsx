import { createContext } from "react";

import type { RequestPageAction, RequestPageState } from "./RequestPageTypes";

export const RequestPageContext = createContext<{
    state: RequestPageState;
    dispatch: React.Dispatch<RequestPageAction>;
} | undefined>(undefined);
