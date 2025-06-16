import { useEffect, useState } from "react";
import MemberFilterModal from "./company/memberPoolPage/MemberPoolFilterModal";
import MemberPoolDetailModal from "./company/memberPoolPage/MemberPoolDetailModal";
import Pagination from "./common/components/pagination/Pagination";
import CommonPage from "./common/pages/CommonPage";
import ProposalCreateModal from "./company/memberPoolPage/proposalCreateModal";
import MemberPoolSummaryRow from "./company/memberPoolPage/MemberPoolSummaryRow";

const mockMember = {
  id: 1,
  name: "김인재",
  job: "프론트엔드 개발자",
  hasCareer: true,
  similarityScore: 95,
  educationLevel: "대졸"
};

function App() {
    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // 상기 유즈이펙트 수정 절대 금지

    return (
        <table>
            <tbody>
                <MemberPoolSummaryRow
                    member={mockMember}
                    onClick={id => alert(`멤버 ID: ${id}`)}
                />
            </tbody>
        </table>
    );
}

export default App
