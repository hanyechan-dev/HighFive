import type { ProposalSummaryForMemberDto } from "../props/myPageForMemberProps";

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
            <div>{proposalSummaryDto.proposalDate}</div>
        </div>

    )
}
export default ProposalListItem







