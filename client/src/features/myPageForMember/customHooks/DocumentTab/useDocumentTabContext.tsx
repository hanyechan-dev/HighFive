import { useContext } from "react";
import { DocumentTabContext } from "../../contexts/DocumentTab/DocumentTabContext";


export const useDocumentTabContext = () => {
    const context = useContext(DocumentTabContext);
    if (!context) throw new Error("DocumentTabContext는 Provider 안에서 사용해야 함");
    return context;
};
