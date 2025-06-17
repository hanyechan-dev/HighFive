import { useReducer, type ReactNode } from "react";
import { DocumentTabContext } from "./DocumentTabContext";
import { initialState, reducer } from "./DocumentTabReducer";

export const DocumentTabProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <DocumentTabContext.Provider value={{ state, dispatch }}>
            {children}
        </DocumentTabContext.Provider>
    );
};
