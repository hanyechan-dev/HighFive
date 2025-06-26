import { listHeaderClass } from "../../../common/components/styles/listStyles";

export default function MemberPoolListHeader() {
  return (
    <div className={listHeaderClass + " items-center"}>
      <div className="w-[200px] flex items-center justify-center">이름</div>
      <div className="w-[200px] flex items-center justify-center">나이</div>
      <div className="w-[200px] flex items-center justify-center">학력</div>
      <div className="w-[200px] flex items-center justify-center">경력</div>
      <div className="w-[200px] flex items-center justify-center">직무</div>
      <div className="w-[200px] flex items-center justify-center">성별</div>
      <div className="w-[200px] flex items-center justify-center">매칭률</div>
    </div>
  );
} 