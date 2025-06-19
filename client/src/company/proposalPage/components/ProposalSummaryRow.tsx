import { listRowClass } from "../../common/listStyles";
import type { ProposalSummary } from "../props/ProposalProps";
import Badge from "../../common/components/Badge";
import { getAge } from "../../utils/DateUtil";

interface ProposalSummaryRowProps {
  proposal: ProposalSummary;
  onClick: (id: number) => void;
}

function getStatusText(status: string): string {
  switch (status) {
    case 'WAITING':
      return '대기중';
    case 'APPROVED':
      return '승인';
    case 'REJECTED':
      return '거절';
    default:
      return '알 수 없음';
  }
}

export default function ProposalSummaryRow({ proposal, onClick }: ProposalSummaryRowProps) {
  return (
    <div
      className={listRowClass + " cursor-pointer"}
      onClick={() => onClick(proposal.id)}
    >
      {/* 이름 */}
      <div className="w-[200px] flex items-center justify-center">{proposal.name}</div>
      {/* 성별 */}
      <div className="w-[150px] flex items-center justify-center">
        <Badge
          label={proposal.genderType}
          color={proposal.genderType === '남성' ? 'male' : 'female'}
        />
      </div>
      {/* 나이 */}
      <div className="w-[150px] flex items-center justify-center">{getAge(proposal.birthDate)}세</div>
      {/* 직무 */}
      <div className="w-[200px] flex items-center justify-center">{proposal.job}</div>
      {/* 경력 */}
      <div className="w-[150px] flex items-center justify-center">{proposal.hasCareer ? "경력" : "신입"}</div>
      {/* 학력 */}
      <div className="w-[150px] flex items-center justify-center">{proposal.educationLevel}</div>
      {/* 제안일 */}
      <div className="w-[200px] flex items-center justify-center">{proposal.proposalDate}</div>
      {/* 상태 */}
      <div className="w-[150px] flex items-center justify-center">
        <Badge
          label={getStatusText(proposal.proposalStatus)}
          color={proposal.proposalStatus === 'WAITING' ? 'waiting' : proposal.proposalStatus === 'APPROVED' ? 'approved' : proposal.proposalStatus === 'REJECTED' ? 'rejected' : 'default'}
        />
      </div>
    </div>
  );
} 