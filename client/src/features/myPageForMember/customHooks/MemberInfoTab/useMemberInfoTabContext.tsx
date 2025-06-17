import { useContext } from "react";
import { MemberInfoTabContext } from "../../contexts/MemberInfoTab/MemberInfoTabContext";


export const useMemberInfoTabContext = () => {
    const context = useContext(MemberInfoTabContext);
    if (!context) throw new Error("MemberInfoTabContext는 Provider 안에서 사용해야 함");
    return context;
};
