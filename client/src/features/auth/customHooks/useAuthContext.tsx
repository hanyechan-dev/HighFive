import { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";



export const useAuthContext = () => {
    const context = useContext(AuthContext);
    if (!context) throw new Error("AuthContext는 Provider 안에서 사용해야 함");
    return context;
};
