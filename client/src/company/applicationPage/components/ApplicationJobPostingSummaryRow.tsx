import type { JobPostingSummary } from "../../jobPostingPage/props/JobPostingProps";
import Button from "../../../common/components/button/Button";
import { listRowClass } from "../../common/listStyles";

interface Props {
  job: JobPostingSummary;
  onShowApplicants: (jobPostingId: number) => void;
}

const ApplicationJobPostingSummaryRow = ({ job, onShowApplicants }: Props) => (
  <div className={listRowClass}>
    <div className="w-[121px] flex items-center justify-start">{job.companyName}</div>
    <div className="w-[110px] flex items-center justify-start">{job.type}</div>
    <div className="w-[270px] flex items-center justify-start">{job.title}</div>
    <div className="w-[270px] flex items-center justify-start">{job.job}</div>
    <div className="w-[121px] flex items-center justify-start">{job.workLocation}</div>
    <div className="w-[120px] flex items-center justify-center">{job.careerType}</div>
    <div className="w-[120px] flex items-center justify-center">{job.educationLevel}</div>
    <div className="w-[120px] flex items-center justify-center">{job.createdDate}</div>
    <div className="flex-1 flex items-center justify-end pr-6" style={{ marginBottom: '-24px' }}>
      <Button
        color="theme"
        size="s"
        text="지원자 확인"
        type="button"
        onClick={() => onShowApplicants(job.id)}
        disabled={false}
      />
    </div>
  </div>
);

export default ApplicationJobPostingSummaryRow; 