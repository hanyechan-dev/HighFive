import { createContext } from "react";

import type { ApplicationTabAction, ApplicationTabState } from "./ApplicationTabTypes";

export const ApplicationTabContext = createContext<{
    state: ApplicationTabState;
    dispatch: React.Dispatch<ApplicationTabAction>;
} | undefined>(undefined);
