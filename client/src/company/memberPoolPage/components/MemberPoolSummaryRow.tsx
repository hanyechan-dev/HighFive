import { useState } from "react";

import SimilarityScore from "./SimilarityScore";
import type { MemberPoolSummary } from "../props/MemberPoolProps";

const defaultItemSettring = "font-semibold text-gray-600 text-lg"
const defalutSizeSetting = "w-[200px] flex items-center justify-center"


interface MemberPoolSummaryRowProps {
  member: MemberPoolSummary;
  onClick: (id: number) => void;
}

function getAge(birthDate: string): number {
  const today = new Date();
  const birth = new Date(birthDate);
  let age = today.getFullYear() - birth.getFullYear();
  const m = today.getMonth() - birth.getMonth();
  if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) {
    age--;
  }
  return age;
}

export default function MemberPoolSummaryRow({ member, onClick }: MemberPoolSummaryRowProps) {
  const [isHovered, setIsHovered] = useState(false);
  return (
    <div
      className="w-full flex items-center bg-white border border-gray-100 rounded-2xl group cursor-pointer transition-all duration-200 relative overflow-hidden hover:shadow-xl hover:scale-[1.01] hover:border-theme font-roboto"
      onClick={() => onClick(member.id)}
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      {/* 이름 */}
      <div className={defalutSizeSetting}>
        <span className={` ${defaultItemSettring} group-hover:text-theme transition-colors duration-200 truncate`}>{member.name}</span>
      </div>
      {/* 나이 */}
      <div className={defalutSizeSetting}>
        <span className={` ${defaultItemSettring}`}>{getAge(member.birthDate)}세</span>
      </div>
      {/* 학력 */}
      <div className={defalutSizeSetting}>
        <span className={` ${defaultItemSettring}`}>{member.educationLevel}</span>
      </div>
      {/* 경력/신입 */}
      <div className={defalutSizeSetting}>
        <span className={` ${defaultItemSettring}`}>{member.hasCareer ? "경력" : "신입"}</span>
      </div>
      {/* 직무 */}
      <div className={defalutSizeSetting}>
        <span className={` ${defaultItemSettring} group-hover:text-gray-900 transition-colors duration-300 tracking-wide`}>{member.job}</span>
      </div>
      {/* 성별 */}
      <div className={defalutSizeSetting}>
        <span className={`inline-block rounded-full px-4 py-1.5 text-lg font-semibold border ${member.genderType === '남성' ? 'bg-blue-50 text-blue-600 border-blue-100' : 'bg-semi_theme/10 text-theme border-pink-100'}`}>{member.genderType}</span>
      </div>
      {/* 매칭률 */}
      <div className={defalutSizeSetting}>
        <SimilarityScore score={member.similarityScore} size={56}  active={isHovered} />
      </div>
    </div>
  );
} 