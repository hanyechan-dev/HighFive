import { useState } from "react";
import { listRowClass } from "../../common/listStyles";

import SimilarityScore from "./SimilarityScore";
import type { MemberPoolSummary } from "../props/MemberPoolProps";
import Badge from "../../common/components/Badge";

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
      className={listRowClass + " cursor-pointer"}
      onClick={() => onClick(member.id)}
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      {/* 이름 */}
      <div className="w-[200px] flex items-center justify-center">{member.name}</div>
      {/* 나이 */}
      <div className="w-[200px] flex items-center justify-center">{getAge(member.birthDate)}세</div>
      {/* 학력 */}
      <div className="w-[200px] flex items-center justify-center">{member.educationLevel}</div>
      {/* 경력/신입 */}
      <div className="w-[200px] flex items-center justify-center">{member.hasCareer ? "경력" : "신입"}</div>
      {/* 직무 */}
      <div className="w-[200px] flex items-center justify-center">{member.job}</div>
      {/* 성별 */}
      <div className="w-[200px] flex items-center justify-center">
        <Badge
          label={member.genderType}
          color={member.genderType === '남성' ? 'male' : 'female'}
        />
      </div>
      {/* 매칭률 */}
      <div className="w-[200px] flex items-center justify-center">
        <SimilarityScore score={member.similarityScore} size={48}  active={isHovered} />
      </div>
    </div>
  );
} 