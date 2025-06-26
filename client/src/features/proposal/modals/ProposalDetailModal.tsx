import { useEffect, useState } from "react";
import CommonModal from "../../../common/modals/CommonModal";
import ModalTitle from "../../../common/components/title/ModalTitle";
import Input from "../../../common/components/input/Input";
import TextArea from "../../../common/components/input/TextArea";
import Badge, { getProposalStatusBadgeColor } from "../../../common/components/badge/Badge2";
import { approvalStatusEnum } from "../../../common/enum/Enum";
import { ProposalDetailApi } from "../apis/ProposalApi";
import type { ProposalDetail } from "../props/ProposalProps";
import LoadingSpinner from "../../../common/components/loading/LoadingSpinner";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";

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
            }
        } catch (err) {
            printErrorInfo(err);
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