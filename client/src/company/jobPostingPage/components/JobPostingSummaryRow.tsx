import type { JobPostingSummary } from '../props/JobPostingProps';
import { listRowClass } from "../../common/listStyles";

interface Props {
  job: JobPostingSummary;
  onEdit: (id: number) => void;
  onDelete: (id: number) => void;
}

export default function JobPostingSummaryRow({ job, onEdit, onDelete }: Props) {
  return (
    <div
      className={listRowClass + " justify-center gap-3"}
      onClick={() => onEdit(job.id)}
    >
      <div className="w-[121px] flex items-center justify-start ">{job.companyName}</div>
      <div className="w-[110px] flex items-center justify-start">{job.companyType}</div>
      <div className="w-[270px] flex items-center justify-start">{job.title}</div>
      <div className="w-[270px] flex items-center justify-start">{job.job}</div>
      <div className="w-[121px] flex items-center justify-start">{job.workLocation}</div>
      <div className="w-[120px] flex items-center justify-center">{job.careerType}</div>
      <div className="w-[120px] flex items-center justify-center">{job.educationLevel}</div>
      <div className="w-[120px] flex items-center justify-center">{job.createdDate}</div>
      <div className="w-[60px] flex items-center justify-center">
        <button
          className="text-pink-500 hover:text-pink-700"
          onClick={e => { e.stopPropagation(); onDelete(job.id); }}
          aria-label="삭제"
        >
          <svg width="18" height="18" viewBox="0 0 24 24" fill="currentColor">
            <path d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"/>
          </svg>
        </button>
      </div>
    </div>
  );
} 