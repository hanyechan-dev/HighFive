import { useContext } from "react";
import { ProposalTapContext } from "../../contexts/ProposalTap/ProposalTapContext";



export const useProposalTapContext = () => {
    const context = useContext(ProposalTapContext);
    if (!context) throw new Error("MyPageForMemberPageContext는 Provider 안에서 사용해야 함");
    return context;
};
