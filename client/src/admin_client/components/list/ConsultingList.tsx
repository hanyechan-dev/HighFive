import Button from "../../../common/components/button/Button"

export type ConsultingType = "첨삭" | "피드백"

interface ConsultingListProps {
    id: number;
    userName: string;
    targetJob: string;
    targetCompanyName: string;
    requestDate: string;
    consultingType: ConsultingType;
    completed?: boolean;
    onApprove: (id: number) => void;
    onClick: (id: number) => void;
}

const defaultSetting = "h-[50px] grid grid-cols-6 items-center w-full border-b text-center text-lg text-[#333] font-roboto hover:bg-semi_theme cursor-pointer"

const ConsultingList = ({
    id,
    userName,
    targetJob,
    targetCompanyName,
    requestDate,
    consultingType,
    completed,
    onApprove,
    onClick,
}: ConsultingListProps) => {
    return (
        <div key={id} onClick={()=>{onClick(id)}} className={defaultSetting}>
            <div className="mb-5">{userName}</div>
            <div className="mb-5">{targetJob}</div>
            <div className="mb-5">{targetCompanyName}</div>
            <div className="mb-5">{requestDate}</div>
            <div className="mb-5">{consultingType}</div>
            <div className="mt-1">
                <Button color={"theme"}
                    size={"s"}
                    disabled={false}
                    text={"승인"}
                    type={"button"}
                    onClick={()=>{onApprove(id)}} />
            </div>
        </div>
    )
}

export default ConsultingList;
