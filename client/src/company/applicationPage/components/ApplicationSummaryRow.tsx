import Badge, { getGenderBadgeColor } from "../../common/components/Badge";
import { listRowClass } from "../../common/listStyles";
import type { ApplicationSummaryForCompany } from "../props/ApplicationProps";
import { getAge } from "../../utils/DateUtil";

interface ApplicationSummaryRowProps {
  application: ApplicationSummaryForCompany;
  onClick: (applicationId: number) => void;
}

const ApplicationSummaryRow = ({ application, onClick }: ApplicationSummaryRowProps) => (
  <div 
    className={listRowClass + " gap-10 cursor-pointer"}
    onClick={() => onClick(application.id)}
  >
    <div className="w-[200px] flex items-center justify-center">{application.name}</div>
    <div className="w-[200px] flex items-center justify-center">
      <Badge 
        label={application.genderType} 
        color={getGenderBadgeColor(application.genderType)} 
      />
    </div>
    <div className="w-[120px] flex items-center justify-center">{getAge(application.birthDate)}</div>
    <div className="w-[200px] flex items-center justify-center">{application.hasCareer ? '경력' : '신입'}</div>
    <div className="w-[200px] flex items-center justify-center">{application.educationLevel}</div>
    <div className="w-[200px] flex items-center justify-center">{application.createdDate}</div>
  </div>
);

export default ApplicationSummaryRow; 