import { useContext } from "react";
import { MyPageForMemberPageContext } from "../contexts/MyPageForMemberPageContext";


export const useMyPageForMemberPageContext = () => {
    const context = useContext(MyPageForMemberPageContext);
    if (!context) throw new Error("MyPageForMemberPageContext는 Provider 안에서 사용해야 함");
    return context;
};
