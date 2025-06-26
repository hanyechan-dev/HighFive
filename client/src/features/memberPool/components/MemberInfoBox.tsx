import * as React from "react";

interface InfoItem {
  label: string;
  value: React.ReactNode;
}

interface MemberInfoBoxProps {
  items: InfoItem[];
  className?: string;
}

export default function MemberInfoBox({ items, className = "" }: MemberInfoBoxProps) {
  return (
    <div className="py-6 ml-6 w-[952px] font-roboto">
    <div className={`bg-gray-50 rounded-lg p-3 ${className}`}>
      <div className="grid grid-cols-2 gap-x-8 gap-y-4">
        {items.map((item, idx) => (
          <div key={idx}>
            <div className="text-sm text-gray-500 mb-1">{item.label}</div>
            <div className="text-base">{item.value}</div>
          </div>
        ))}
        </div>
      </div>
    </div>
  );
}