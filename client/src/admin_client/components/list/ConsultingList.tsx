import Button from "../../../common/components/button/Button"
import type { ConsultingTypeEnum } from "../../../common/enum/Enum";



interface consultingProps {
    aiConsultingId: number;
    userName: string;
    targetJob: string;
    targetCompanyName: string;
    requestedDate: string;
    consultingType: ConsultingTypeEnum;
}



interface Props {
    consulting : consultingProps
    onApprove: (id: number) => void;
    onClick: (id: number, consultingType : ConsultingTypeEnum) => void;
}

const defaultSetting = "h-[50px] grid grid-cols-6 items-center w-full border-b text-center text-lg text-[#333] font-roboto hover:bg-semi_theme cursor-pointer"

const ConsultingList = ({
    consulting,
    onApprove,
    onClick
}: Props) => {
    return (
        <div key={consulting.aiConsultingId} onClick={()=>{onClick(consulting.aiConsultingId, consulting.consultingType)}} className={defaultSetting}>
            <div className="mb-5">{consulting.userName}</div>
            <div className="mb-5">{consulting.targetJob}</div>
            <div className="mb-5">{consulting.targetCompanyName}</div>
            <div className="mb-5">{consulting.requestedDate}</div>
            <div className="mb-5">{consulting.consultingType}</div>
            <div className="mt-1">
                <Button color={"theme"}
                    size={"s"}
                    disabled={false}
                    text={"승인"}
                    type={"button"}
                    onClick={()=>{onApprove(consulting.aiConsultingId)}} />
            </div>
        </div>
    )
}

export default ConsultingList;
