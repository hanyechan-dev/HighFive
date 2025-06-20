import { useReducer, type ReactNode } from "react";
import { PaymentTabContext } from "./PaymentTabContext";
import { reducer, initialState } from "./PaymentTabReducer";


export const PaymentTabProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <PaymentTabContext.Provider value={{ state, dispatch }}>
            {children}
        </PaymentTabContext.Provider>
    );
};
