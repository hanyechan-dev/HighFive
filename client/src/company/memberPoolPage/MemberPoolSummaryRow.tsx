import type { MemberPoolSummary } from "./MemberPoolApi";
import { useState } from "react";
import SimilarityScore from './components/SimilarityScore';

interface MemberPoolSummaryRowProps {
  member: MemberPoolSummary;
  onClick: (id: number) => void;
}

const getSimilarityColor = (score: number) => {
  if (score >= 90) return 'text-theme bg-theme/10';
  if (score >= 80) return 'text-theme/80 bg-theme/5';
  if (score >= 70) return 'text-theme/60 bg-theme/5';
  return 'text-gray-600 bg-gray-50';
};

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
      className="w-full flex items-center bg-white border border-gray-100 rounded-2xl group cursor-pointer transition-all duration-200 relative overflow-hidden hover:shadow-xl hover:scale-[1.01] hover:border-theme"
      onClick={() => onClick(member.id)}
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      {/* 이름 */}
      <div className="w-[200px] flex items-center justify-center">
        <span className="font-semibold text-gray-600 text-lg group-hover:text-theme transition-colors duration-200 truncate">{member.name}</span>
      </div>
      {/* 나이 */}
      <div className="w-[200px] flex items-center justify-center">
        <span className="text-gray-600 font-semibold text-lg">{getAge(member.birthDate)}세</span>
      </div>
      {/* 학력 */}
      <div className="w-[200px] flex items-center justify-center">
        <span className="text-gray-600 font-semibold text-lg">{member.educationLevel}</span>
      </div>
      {/* 경력/신입 */}
      <div className="w-[200px] flex items-center justify-center">
        <span className="font-semibold text-gray-600 text-lg">{member.hasCareer ? "경력" : "신입"}</span>
      </div>
      {/* 직무 */}
      <div className="w-[200px] flex items-center justify-center">
        <span className="text-gray-600 font-semibold text-lg group-hover:text-gray-900 transition-colors duration-300 tracking-wide">{member.job}</span>
      </div>
      {/* 성별 */}
      <div className="w-[200px] flex items-center justify-center">
        <span className={`inline-block rounded-full px-4 py-1.5 text-lg font-semibold border ${member.genderType === '남성' ? 'bg-blue-50 text-blue-600 border-blue-100' : 'bg-semi_theme/10 text-theme border-pink-100'}`}>{member.genderType}</span>
      </div>
      {/* 매칭률 */}
      <div className="w-[200px] flex items-center justify-center">
        <SimilarityScore score={member.similarityScore} size={56}  active={isHovered} />
      </div>
    </div>
  );
} 