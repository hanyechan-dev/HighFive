import { listRowClass } from "../../common/listStyles";
import type { ProposalSummary } from "../props/ProposalProps";
import Badge from "../../common/components/Badge";

interface ProposalSummaryRowProps {
  proposal: ProposalSummary;
  onClick: (id: number) => void;
}

function getAge(birthDate: string): number {
  const today = new Date();
  const birth = new Date(birthDate);
  let age = today.getFullYear() - birth.getFullYear();
  const m = today.getMonth() - birth.getMonth();
  if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) {
    age--;
  }
  return age;
}

function getStatusColor(status: string): string {
  switch (status) {
    case 'WAITING':
      return 'bg-yellow-50 text-yellow-600 border-yellow-100';
    case 'APPROVED':
      return 'bg-green-50 text-green-600 border-green-100';
    case 'REJECTED':
      return 'bg-red-50 text-red-600 border-red-100';
    default:
      return 'bg-gray-50 text-gray-600 border-gray-100';
  }
}

function getStatusText(status: string): string {
  switch (status) {
    case 'WAITING':
      return '대기';
    case 'APPROVED':
      return '승인';
    case 'REJECTED':
      return '거부';
    default:
      return status;
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