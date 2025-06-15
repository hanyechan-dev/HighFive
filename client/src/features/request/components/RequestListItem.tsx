import type { RequestSummaryDto } from "../props/RequestProps";

interface RequestListItemProps {
    requestSummaryDto: RequestSummaryDto;
    onClick: (id: number) => void
}


const defaultSetting = "grid grid-cols-4 items-center w-[1450px] border-b py-3 text-center font-roboto mx-[24px] hover:bg-semi_theme cursor-pointer"

const RequestListItem = ({ requestSummaryDto, onClick }: RequestListItemProps) => {
    const {
        id,
        targetJob,
        targetCompanyName,
        requestStatus,
        createdDate
    } = requestSummaryDto

    return (
        <div className={defaultSetting} onClick={() => onClick(id)}>
            <div>{targetJob}</div>
            <div>{targetCompanyName}</div>
            <div>{requestStatus}</div>
            <div>{createdDate}</div>
        </div>
    )
}

export default RequestListItem
