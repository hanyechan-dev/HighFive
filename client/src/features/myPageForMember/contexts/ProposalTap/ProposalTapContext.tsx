import { createContext } from "react";

import type { ProposalTapAction, ProposalTapState } from "./ProposalTapTypes";

export const ProposalTapContext = createContext<{
    state: ProposalTapState;
    dispatch: React.Dispatch<ProposalTapAction>;
} | undefined>(undefined);
