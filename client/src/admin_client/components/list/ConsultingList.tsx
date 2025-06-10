import Button from "../../../common/components/button/Button"

export type ConsultingType = "첨삭" | "피드백"

interface ConsultingListProps {
  userName: string;
  targetJob: string;
  targetCompanyName: string;
  requestDate: string;
  consultingType: ConsultingType;
  completed: boolean;
  onApprove: () => void;
}

const ConsultingList = ({
  userName,
  targetJob,
  targetCompanyName,
  requestDate,
  consultingType,
  completed,
  onApprove,
}: ConsultingListProps) => {
  return (
    <div className="grid grid-cols-6 items-center w-full border-b py-3 text-center text-xl text-[#333]">
      <div>{userName}</div>
      <div>{targetJob}</div>
      <div>{targetCompanyName}</div>
      <div>{requestDate}</div>
      <div>{consultingType}</div>
      <div>
        <Button color={"theme"} 
        size={"s"}
        disabled={false} 
        text={"승인"} 
        type={"button"} />
      </div>
    </div>
  )
}

export default ConsultingList;
