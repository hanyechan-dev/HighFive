import type { ProposalSummaryForMemberDto } from "../props/myPageForMemberProps";
import dayjs from "dayjs"; //  타입스크립트 경고 문제 없음
interface ProposalListItemProps {
    proposalSummaryDto: ProposalSummaryForMemberDto;
    onClick: (id: number) => void
}

const defaultSetting = "grid grid-cols-4 items-center w-[1120px] border-b py-3 text-center font-roboto mx-[24px] hover:bg-semi_theme cursor-pointer"

const ProposalListItem = ({ proposalSummaryDto, onClick }: ProposalListItemProps) => {
    return (

        <div className={defaultSetting} onClick={() => onClick(proposalSummaryDto.id)}>
            <div>{proposalSummaryDto.proposalTitle}</div>
            <div>{proposalSummaryDto.companyName}</div>
            <div>{proposalSummaryDto.proposalStatus}</div>
            <div>{dayjs(proposalSummaryDto.proposalDate).format('YYYY-MM-DD')}</div>
        </div>

    )
}
export default ProposalListItem




