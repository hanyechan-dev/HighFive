import type { ReactNode } from "react";

interface CompanyEmptyStateProps {
  title: string;
  text?: string;
  children?: ReactNode;
}

export default function CompanyEmptyState({ title, text, children }: CompanyEmptyStateProps) {
  return (
    <div className="w-full flex flex-col items-center justify-center py-12 text-gray-400 font-roboto">
      {/* 아이콘 등 추가 가능 */}
      <div className="text-xl font-bold mb-2">{title}</div>
      {text && <div className="mb-2">{text}</div>}
      {children}
    </div>
  );
} 