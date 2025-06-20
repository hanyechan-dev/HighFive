import { useContext } from "react";
import { ApplicationTabContext } from "../../contexts/ApplicationTab/ApplicationTabContext";



export const useApplicationTabContext = () => {
    const context = useContext(ApplicationTabContext);
    if (!context) throw new Error("MyPageForMemberPageContext는 Provider 안에서 사용해야 함");
    return context;
};
