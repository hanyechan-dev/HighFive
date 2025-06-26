import { listHeaderClass } from "../../../common/components/styles/listStyles";

const PassListHeader = () => (
  <div className={listHeaderClass + " gap-10"}>
    <div className="w-[200px] flex items-center justify-center">이름</div>
    <div className="w-[200px] flex items-center justify-center">성별</div>
    <div className="w-[120px] flex items-center justify-center">나이</div>
    <div className="w-[200px] flex items-center justify-center">경력</div>
    <div className="w-[200px] flex items-center justify-center">학력</div>
    <div className="w-[200px] flex items-center justify-center">지원일</div>
  </div>
);

export default PassListHeader; 