import { createContext } from "react";
import type { PaymentTabAction, PaymentTabState } from "./PaymentTabTypes";

export const PaymentTabContext = createContext<{
    state: PaymentTabState;
    dispatch: React.Dispatch<PaymentTabAction>;
} | undefined>(undefined);
