import type { requestSummaryProps } from "../../common/props/AiConsultingProps";

interface RequestListItemProps {
    request: requestSummaryProps;
}


const defaultSetting = "grid grid-cols-4 items-center w-[1450px] border-b py-3 text-center text-[#666] font-roboto hover:bg-semi_theme cursor-pointer"

const RequestListItem = ({request}: RequestListItemProps) => {

    return (
        <div className={defaultSetting}>
            <div>{request.targetJob}</div>
            <div>{request.targetCompanyName}</div>
            <div>{request.requestStatus}</div>
            <div>{request.createdDate}</div>
        </div>
    )
}

export default RequestListItem
