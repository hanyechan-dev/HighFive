import { useReducer, type ReactNode } from "react";
import { ApplicationTabContext } from "./ApplicationTabContext";
import { initialState, reducer } from "./ApplicationTabReducer";

export const ApplicationTabProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <ApplicationTabContext.Provider value={{ state, dispatch }}>
            {children}
        </ApplicationTabContext.Provider>
    );
};
