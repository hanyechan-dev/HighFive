import Button from "../../../common/components/button/Button";
import InfoBox from "../../../common/components/infoBox/InfoBox";
import Input from "../../../common/components/input/Input";
import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal";
import type { ProposalResponseDto } from "../props/myPageForMemberProps";

interface ProposalModalProps {
    proposalResponseDto: ProposalResponseDto;
    onClose: () => void;
    onClickAccept: () => void;
    onClickReject: () => void;
}

const ProposalModal = ({ proposalResponseDto, onClose, onClickAccept, onClickReject }: ProposalModalProps) => {

    const isWait = proposalResponseDto.proposalStatus == "대기"


    return (
        <CommonModal size="m" onClose={onClose}>
            <ModalTitle title="채용 제안 상세" />
            <div className="flex justify-end mr-6">
                <InfoBox label={"제안 일자"} value={proposalResponseDto.proposalDate} />
            </div>
            <Input label={"제안 기업"} placeholder={""} size={"m"} disabled={true} type={"text"} value={proposalResponseDto.companyName} setValue={() => { }} />
            <Input label={"제안 제목"} placeholder={""} size={"m"} disabled={true} type={"text"} value={proposalResponseDto.proposalTitle} setValue={() => { }} />
            <TextArea size={"m"} label={"제안 내용"} placeholder={""} disabled={true} value={proposalResponseDto.proposalContent} setValue={() => { }} />
            <div className="flex">
                <Input label={"제안 연봉"} placeholder={""} size={"s"} disabled={true} type={"text"} value={String(proposalResponseDto.proposalSalary)} setValue={() => { }} />
                <Input label={"제안 직무"} placeholder={""} size={"s"} disabled={true} type={"text"} value={proposalResponseDto.proposalJob} setValue={() => { }} />
            </div>
            {isWait && (
                <div className="flex justify-end mr-6">
                    <Button color={"white"} size={"s"} disabled={false} text={"거절"} type={"button"} onClick={onClickReject} />
                    <Button color={"theme"} size={"s"} disabled={false} text={"수락"} type={"button"} onClick={onClickAccept} />
                </div>
            )}



        </CommonModal>
    )
}

export default ProposalModal;
