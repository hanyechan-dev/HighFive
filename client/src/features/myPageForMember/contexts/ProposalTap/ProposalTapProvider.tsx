import { useReducer, type ReactNode } from "react";
import { initialState, reducer } from "./ProposalTapReducer";
import { ProposalTapContext } from "./ProposalTapContext";


export const ProposalTapProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <ProposalTapContext.Provider value={{ state, dispatch }}>
            {children}
        </ProposalTapContext.Provider>
    );
};
