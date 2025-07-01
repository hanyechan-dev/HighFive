import { listHeaderClass } from "../../../common/components/styles/listStyles";

export default function AiConsultingListHeader() {
  return (
    <div className={listHeaderClass + " items-center"}>
      <div className="w-[200px] flex items-center justify-center">요청자</div>
      <div className="w-[200px] flex items-center justify-center">목표 직무</div>
      <div className="w-[200px] flex items-center justify-center">목표 회사</div>
      <div className="w-[200px] flex items-center justify-center">요청 날짜</div>
      <div className="w-[200px] flex items-center justify-center">컨설팅 타입</div>
      <div className="w-[200px] flex items-center justify-center">상세보기</div>
      <div className="w-[200px] flex items-center justify-center">승인</div>
    </div>
  );
} 