import * as React from "react";
import { genderTypeEnum, approvalStatusEnum } from "../../../common/enum/Enum";

export type BadgeColor =
  | "male"
  | "female"
  | "approved"
  | "rejected"
  | "waiting"
  | "career"
  | "newbie"
  | "info"
  | "default";

const colorMap: Record<BadgeColor, string> = {
  male: "bg-blue-100 text-blue-700 border-blue-200",
  female: "bg-pink-100 text-pink-700 border-pink-200",
  approved: "bg-green-100 text-green-700 border-green-300",
  rejected: "bg-red-100 text-red-700 border-red-300",
  waiting: "bg-yellow-100 text-yellow-800 border-yellow-300",
  career: "bg-emerald-100 text-emerald-700 border-emerald-200",
  newbie: "bg-orange-100 text-orange-700 border-orange-200",
  info: "bg-sky-100 text-sky-700 border-sky-200",
  default: "bg-gray-50 text-gray-500 border-gray-100",
};

// Enum 값들을 Badge 색상으로 매핑하는 함수들
export const getGenderBadgeColor = (genderType: string): BadgeColor => {
  switch (genderType) {
    case genderTypeEnum[0].value: // '남자'
      return 'male';
    case genderTypeEnum[1].value: // '여자'
      return 'female';
    default:
      return 'default';
  }
};

export const getApprovalStatusBadgeColor = (status: string): BadgeColor => {
  switch (status) {
    case approvalStatusEnum[1].value: // 'APPROVED'
      return 'approved';
    case approvalStatusEnum[2].value: // 'REJECTED'
      return 'rejected';
    case approvalStatusEnum[0].value: // 'WAITING'
      return 'waiting';
    default:
      return 'default';
  }
};

export const getCareerBadgeColor = (hasCareer: boolean): BadgeColor => {
  return hasCareer ? 'career' : 'newbie';
};

export const getProposalStatusBadgeColor = (status: string): BadgeColor => {
  switch (status) {
    case 'APPROVED':
      return 'approved';
    case 'REJECTED':
      return 'rejected';
    case 'WAITING':
      return 'waiting';
    default:
      return 'default';
  }
};

interface BadgeProps {
  label: string;
  color?: BadgeColor;
  className?: string;
}

const Badge: React.FC<BadgeProps> = ({
  label,
  color = "default",
  className = "",
}) => (
  <span
    className={`
      inline-flex items-center px-3 py-1.5 rounded-full text-sm font-bold
      border shadow-sm
      ${colorMap[color]} 
      ${className}
      transition-all duration-200
    `}
    style={{
      letterSpacing: "0.01em"
    }}
  >
    {label}
  </span>
);

export default Badge; 