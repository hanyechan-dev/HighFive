import type { FC } from 'react';
import { useState } from 'react';
import SimilarityScore from './SimilarityScore';

interface MemberPoolSummary {
    id: number;
    name: string;
    job: string;
    hasCareer: boolean;
    similarityScore: number;
    educationLevel: string;
    genderType: string;
    birthDate: string;
}

interface AIRecommendationCardProps {
    member: MemberPoolSummary;
}

function getAge(birthDate: string) {
    const today = new Date();
    const birth = new Date(birthDate);
    let age = today.getFullYear() - birth.getFullYear();
    const m = today.getMonth() - birth.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) {
        age--;
    }
    return age;
}

const AIRecommendationCard: FC<AIRecommendationCardProps> = ({ member }) => {
    const [isHovered, setIsHovered] = useState(false);
    return (
        <div
            className="bg-white rounded-2xl shadow-lg border border-gray-100 p-7 flex flex-col gap-4 transition-all duration-300 hover:shadow-2xl hover:-translate-y-1 relative min-h-[220px] group"
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
        >
            {/* 아이콘 - 왼쪽 상단 */}
            <div className="absolute left-6 top-6">
                <svg width="32" height="32" viewBox="0 0 32 32" fill="none" className="text-theme" xmlns="http://www.w3.org/2000/svg">
                  <circle cx="16" cy="16" r="15" fill="currentColor" fillOpacity="0.12"/>
                  <path d="M16 9l2.09 4.26L22 14.27l-3.45 3.36L19.18 22 16 19.73 12.82 22l.63-4.37L10 14.27l3.91-.99L16 9z" fill="currentColor" stroke="currentColor" strokeWidth="1.2"/>
                </svg>
            </div>
            {/* 유사도 - 우측 상단 */}
            <div className="absolute right-6 top-6 flex flex-col items-end">
                <SimilarityScore score={member.similarityScore} size={56} active={isHovered} />
            </div>
            {/* 메인 정보 */}
            <div className="flex flex-col items-center justify-center mt-10">
                <div className="font-extrabold text-xl text-gray-900 mb-1 text-center truncate w-full tracking-tight" style={{fontFamily:'inherit'}}>{member.name}</div>
                <div className="text-gray-700 font-semibold mb-1 text-center truncate w-full tracking-tight" style={{fontFamily:'inherit'}}>{member.job}</div>
            </div>
            {/* 서브 정보 */}
            <div className="flex flex-wrap justify-center gap-3 mt-2">
                <span className="text-gray-500 text-sm tracking-tight" style={{fontFamily:'inherit'}}>{member.hasCareer ? `경력` : `신입`}</span>
                <span className="text-gray-400 text-sm tracking-tight" style={{fontFamily:'inherit'}}>{member.educationLevel}</span>
                <span className="text-gray-400 text-sm tracking-tight" style={{fontFamily:'inherit'}}>{member.genderType}</span>
                <span className="text-gray-400 text-sm tracking-tight" style={{fontFamily:'inherit'}}>{getAge(member.birthDate)}세</span>
            </div>
        </div>
    );
};

export default AIRecommendationCard; 