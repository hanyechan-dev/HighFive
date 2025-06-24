import { listRowClass } from "../../common/listStyles";
import type { ProposalSummary } from "../props/ProposalProps";
import Badge, { type BadgeColor } from "../../common/components/Badge";
import { getAge } from "../../utils/DateUtil";

interface ProposalSummaryRowProps {
  proposal: ProposalSummary;
  onClick: (id: number) => void;
}

const ProposalSummaryRow = ({ proposal, onClick }: ProposalSummaryRowProps) => {
  const getStatusColor = (status: string): BadgeColor => {
    switch (status) {
      case 'APPROVED':
        return 'approved';
      case 'REJECTED':
        return 'rejected';
      case 'WAITING':
        return 'waiting';
      default:
        return 'default';
    }
  };

  const getStatusText = (status: string): string => {
    switch (status) {
      case 'APPROVED':
        return '승인';
      case 'REJECTED':
        return '거절';
      case 'WAITING':
        return '대기';
      default:
        return '알 수 없음';
    }
  };

  return (
    <div 
      className={listRowClass + " gap-10 cursor-pointer"}
      onClick={() => onClick(proposal.id)}
    >
      {/* 이름 */}
      <div className="w-[200px] flex items-center justify-center">{proposal.name}</div>
      {/* 성별 */}
      <div className="w-[200px] flex items-center justify-center">
        <Badge
          label={proposal.genderType}
          color={proposal.genderType === '남성' ? 'male' : 'female'}
        />
      </div>
      {/* 나이 */}
      <div className="w-[120px] flex items-center justify-center">{getAge(proposal.birthDate)}</div>
      {/* 직무 */}
      <div className="w-[200px] flex items-center justify-center">{proposal.job}</div>
      {/* 경력 */}
      <div className="w-[200px] flex items-center justify-center">{proposal.hasCareer ? "경력" : "신입"}</div>
      {/* 학력 */}
      <div className="w-[200px] flex items-center justify-center">{proposal.educationLevel}</div>
      {/* 제안일 */}
      <div className="w-[200px] flex items-center justify-center">{proposal.proposalDate}</div>
      {/* 상태 */}
      <div className="w-[200px] flex items-center justify-center">
        <Badge
          label={getStatusText(proposal.proposalStatus)}
          color={getStatusColor(proposal.proposalStatus)}
        />
      </div>
    </div>
  );
};

export default ProposalSummaryRow; 