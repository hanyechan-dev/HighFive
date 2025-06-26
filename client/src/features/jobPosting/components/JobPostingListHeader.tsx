import { listHeaderClass } from "../../../common/components/styles/listStyles";

export default function JobPostingListHeader() {
  return (
    <div className={listHeaderClass +" gap-3"}>
      <div className="w-[121px] flex items-center justify-start">기업명</div>
      <div className="w-[110px] flex items-center justify-start">기업형태</div>
      <div className="w-[270px] flex items-center justify-start">공고명</div>
      <div className="w-[270px] flex items-center justify-start">모집부문</div>
      <div className="w-[121px] flex items-center justify-start">근무지</div>
      <div className="w-[120px] flex items-center justify-center">경력구분</div>
      <div className="w-[120px] flex items-center justify-center">학력</div>
      <div className="w-[120px] flex items-center justify-center">등록 일자</div>
      <div className="w-[60px] flex items-center justify-center">삭제</div>
    </div>
  );
} 