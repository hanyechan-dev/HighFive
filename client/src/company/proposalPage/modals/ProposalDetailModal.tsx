import { useEffect, useState } from "react";
import CommonModal from "../../../common/modals/CommonModal";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { ProposalDetailApi } from "../apis/ProposalApi";
import type { ProposalDetail } from "../props/ProposalProps";

interface ProposalDetailModalProps {
  isOpen: boolean;
  onClose: () => void;
  proposalId: number;
}

export default function ProposalDetailModal({ isOpen, onClose, proposalId }: ProposalDetailModalProps) {
  const [proposal, setProposal] = useState<ProposalDetail | null>(null);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    if (isOpen && proposalId) {
      fetchProposalDetail();
    }
  }, [isOpen, proposalId]);

  const fetchProposalDetail = async () => {
    setIsLoading(true);
    try {
      const res = await ProposalDetailApi(proposalId);
      if (res) {
        setProposal(res);
      }
    } catch (err) {
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  const getStatusColor = (status: string): string => {
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
  };

  const getStatusText = (status: string): string => {
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
  };

  if (!isOpen) return null;

  return (
    <CommonModal size="l" onClose={onClose}>
      <ModalTitle title="제안서 상세" />
      {isLoading ? (
        <div className="text-center py-12 text-gray-400">로딩 중...</div>
      ) : proposal ? (
        <div className="space-y-6 px-6">
          {/* 제목 */}
          <div>
            <h3 className="text-lg font-semibold mb-2">{proposal.proposalTitle}</h3>
            <div className="text-sm text-gray-500">{proposal.companyName}</div>
          </div>

          {/* 기본 정보 */}
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">직무</label>
              <div className="text-sm">{proposal.proposalJob}</div>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">희망 연봉</label>
              <div className="text-sm">{proposal.proposalSalary.toLocaleString()}원</div>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">제안일</label>
              <div className="text-sm">{proposal.proposalDate}</div>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">상태</label>
              <div>
                <span className={`inline-block rounded-full px-2 py-1 text-xs font-semibold border ${getStatusColor(proposal.proposalStatus)}`}>
                  {getStatusText(proposal.proposalStatus)}
                </span>
              </div>
            </div>
          </div>

          {/* 제안 내용 */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">제안 내용</label>
            <div className="bg-gray-50 p-4 rounded-lg text-sm whitespace-pre-wrap">
              {proposal.proposalContent}
            </div>
          </div>
        </div>
      ) : (
        <div className="text-center py-12 text-gray-400">제안서 정보를 불러올 수 없습니다.</div>
      )}
    </CommonModal>
  );
} 