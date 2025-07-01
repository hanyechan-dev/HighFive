import { useState } from "react";
import Button from "../../../common/components/button/Button";
import { listRowClass } from "../../../common/components/styles/listStyles";
import type { AiConsultingSummary } from "../props/AiConsultingProps";

interface AiConsultingSummaryRowProps {
  consulting: AiConsultingSummary;
  onViewDetail: (id: number) => void;
  onApprove: (id: number) => void;
}

export default function AiConsultingSummaryRow({ 
  consulting, 
  onViewDetail, 
  onApprove 
}: AiConsultingSummaryRowProps) {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <div
      className={listRowClass}
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      <div className="w-[200px] flex items-center justify-center">{consulting.nickname}</div>
      <div className="w-[200px] flex items-center justify-center">{consulting.targetJob}</div>
      <div className="w-[200px] flex items-center justify-center">{consulting.targetCompanyName}</div>
      <div className="w-[200px] flex items-center justify-center">{consulting.requestedDate}</div>
      <div className="w-[200px] flex items-center justify-center">{consulting.consultingType}</div>
      <div className="w-[200px] flex items-center justify-center">
        <Button
          color="theme"
          size="s"
          disabled={false}
          text="상세보기"
          type="button"
          onClick={(e) => {
            e.stopPropagation();
            onViewDetail(consulting.aiConsultingId);
          }}
        />
      </div>
      <div className="w-[200px] flex items-center justify-center">
        <Button
          color="action"
          size="s"
          disabled={false}
          text="승인"
          type="button"
          onClick={(e) => {
            e.stopPropagation();
            onApprove(consulting.aiConsultingId);
          }}
        />
      </div>
    </div>
  );
} 