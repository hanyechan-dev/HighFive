import { useEffect, useState } from "react";
import CommonModal from "../../../common/modals/CommonModal";
import ModalTitle from "../../../common/components/title/ModalTitle";
import Input from "../../../common/components/input/Input";
import TextArea from "../../../common/components/input/TextArea";
import Badge, { getProposalStatusBadgeColor } from "../../common/components/Badge";
import { approvalStatusEnum } from "../../../common/enum/Enum";
import { ProposalDetailApi } from "../apis/ProposalApi";
import type { ProposalDetail } from "../props/ProposalProps";
import LoadingSpinner from "../../common/components/LoadingSpinner";
import EmptyState from "../../../common/components/emptyState/EmptyState";

// 개발 중 모달 UI 형태 확인을 위한 Mock 데이터
const mockProposalDetail: ProposalDetail = {
  id: 1,
  proposalTitle: "프론트엔드 개발자 제안서",
  companyName: "테크컴퍼니",
  proposalContent: `안녕하세요, 테크컴퍼니입니다.

귀하의 뛰어난 개발 능력과 경험을 확인하여 프론트엔드 개발자 포지션에 대한 제안을 드립니다.

【제안 내용】
• React, TypeScript 기반 웹 애플리케이션 개발
• 사용자 경험 개선 및 성능 최적화
• 팀 협업을 통한 프로젝트 진행

【희망 사항】
• 원격 근무 가능
• 유연한 근무 시간
• 지속적인 학습 기회 제공

귀하의 답변을 기다리겠습니다.
감사합니다.`,
  proposalJob: "프론트엔드 개발자",
  proposalSalary: 45000000,
  proposalDate: "2024-01-15",
  proposalStatus: "WAITING"
};

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
      if (res && res.data) {
        setProposal(res.data);
      } else {
        // API 응답이 없으면 mock 데이터 사용
        setProposal({ ...mockProposalDetail, id: proposalId });
      }
    } catch (err) {
      console.error(err);
      // 에러 시에도 mock 데이터 사용
      setProposal({ ...mockProposalDetail, id: proposalId });
    } finally {
      setIsLoading(false);
    }
  };

  const getStatusText = (status: string): string => {
    const statusEnum = approvalStatusEnum.find(item => item.value === status);
    return statusEnum ? statusEnum.label : '대기';
  };

  if (!isOpen) return null;

  return (
    <CommonModal size="l" onClose={onClose}>
      <ModalTitle title="제안서 상세" />
      {isLoading ? (
        <LoadingSpinner message="제안서 정보를 불러오는 중..." />
      ) : proposal ? (
        <div className="font-roboto">
          {/* 제목 */}
          <Input
            label="제안서 제목"
            placeholder=""
            size="l"
            disabled
            type="text"
            value={proposal.proposalTitle}
            setValue={() => { }}
          />
          <div className="flex">
            {/* 회사명 */}
            <Input
              label="회사명"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={proposal.companyName}
              setValue={() => { }}
            />

            {/* 직무와 희망 연봉 */}

            <Input
              label="직무"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={proposal.proposalJob}
              setValue={() => { }}
            />
          </div>
          <div className="flex">
            <Input
              label="희망 연봉"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={`${proposal.proposalSalary.toLocaleString()}원`}
              setValue={() => { }}
            />


            {/* 제안일과 상태 */}

            <Input
              label="제안일"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={proposal.proposalDate}
              setValue={() => { }}
            />
          </div>
          <div className="flex justify-end mr-6">            
              <Badge
                label={getStatusText(proposal.proposalStatus)}
                color={getProposalStatusBadgeColor(proposal.proposalStatus)}
              />            
          </div>
          {/* 제안 내용 */}
          <TextArea
            label="제안 내용"
            placeholder=""
            disabled
            value={proposal.proposalContent}
            setValue={() => { }}
            size="l"
          />

         
        </div>
      ) : (
        <EmptyState title="제안서 정보를 불러올 수 없습니다." text="잠시 후 다시 시도해주세요." />
      )}
    </CommonModal>
  );
} 