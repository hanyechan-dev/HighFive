import { useReducer, type ReactNode } from "react";
import { RequestPageContext } from "./RequestPageContext";
import { initialState, reducer } from "./RequestPageReducer";

export const RequestPageProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <RequestPageContext.Provider value={{ state, dispatch }}>
            {children}
        </RequestPageContext.Provider>
    );
};
