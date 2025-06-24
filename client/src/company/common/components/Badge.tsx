import * as React from "react";

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