import type { RequestSummaryDto } from "./RequestProps";

interface RequestListItemProps {
    requestSummaryDto: RequestSummaryDto;
    onClick : (id:number)=>void
}


const defaultSetting = "grid grid-cols-4 items-center w-[1450px] border-b py-3 text-center text-[#666] font-roboto hover:bg-semi_theme cursor-pointer"

const RequestListItem = ({requestSummaryDto, onClick}: RequestListItemProps) => {

    return (
        <div className={defaultSetting} onClick={()=>onClick(requestSummaryDto.id)}>
            <div>{requestSummaryDto.targetJob}</div>
            <div>{requestSummaryDto.targetCompanyName}</div>
            <div>{requestSummaryDto.requestStatus}</div>
            <div>{requestSummaryDto.createdDate}</div>
        </div>
    )
}

export default RequestListItem
