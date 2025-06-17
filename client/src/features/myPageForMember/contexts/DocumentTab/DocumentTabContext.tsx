import { createContext } from "react";

import type { DocumentTabAction, DocumentTabState } from "./DocumentTabTypes";

export const DocumentTabContext = createContext<{
    state: DocumentTabState;
    dispatch: React.Dispatch<DocumentTabAction>;
} | undefined>(undefined);
