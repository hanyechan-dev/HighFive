import type { ApplicationSummaryForMemberDto } from "../props/myPageForMemberProps";

interface ApplicationListItemProps {
    applicationSummaryDto: ApplicationSummaryForMemberDto;
    onClick: (id: number) => void
}

const defaultSetting = "grid grid-cols-5 items-center w-[1120px] border-b py-3 text-center font-roboto mx-[24px] hover:bg-semi_theme cursor-pointer"

const ApplicationListItem = ({ applicationSummaryDto, onClick }: ApplicationListItemProps) => {
    return (

        <div className={defaultSetting} onClick={() => onClick(applicationSummaryDto.id)}>
            <div>{applicationSummaryDto.title}</div>
            <div>{applicationSummaryDto.companyName}</div>
            <div>{applicationSummaryDto.job}</div>
            <div>{applicationSummaryDto.isPassed}</div>
            <div>{applicationSummaryDto.createdDate}</div>
        </div>

    )
}
export default ApplicationListItem







