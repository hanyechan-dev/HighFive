export type ConsultingType = "첨삭" | "피드백"
export type WorkStateType = "승인" | "완료" | "대기"

interface ConsultingCurrentManagementProps {
  userName: string;
  targetJob: string;
  targetCompanyName: string;
  requestDate: string;
  consultingType: ConsultingType;
  workStateType: WorkStateType;
}

const ConsultingCurrentManagement =({
    userName,
    targetJob,
    targetCompanyName,
    requestDate,
    consultingType,
    workStateType,
}: ConsultingCurrentManagementProps) =>{
    return (
        <div className="grid grid-cols-6 items-center w-full border-b py-10 text-center text-2xl text-[#333]">
      <div>{userName}</div>
      <div>{targetJob}</div>
      <div>{targetCompanyName}</div>
      <div>{requestDate}</div>
      <div>{consultingType}</div>
      <div>{workStateType}</div>
      </div>
    )
}

export default ConsultingCurrentManagement;