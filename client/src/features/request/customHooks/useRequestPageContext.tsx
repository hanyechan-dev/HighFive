import { useContext } from "react";
import { RequestPageContext } from "../contexts/RequestPageContext";


export const useRequestPageContext = () => {
    const context = useContext(RequestPageContext);
    if (!context) throw new Error("RequestPageContext는 Provider 안에서 사용해야 함");
    return context;
};
