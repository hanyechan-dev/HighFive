import Badge from "../../common/components/Badge";
import { listRowClass } from "../../common/listStyles";
import type { ApplicationSummaryForCompany } from "../props/ApplicationProps";

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

interface ApplicationSummaryRowProps {
  application: ApplicationSummaryForCompany;
}

const ApplicationSummaryRow = ({ application }: ApplicationSummaryRowProps) => (
  <div className={listRowClass}>
    <div className="w-[200px] flex items-center justify-center">{application.name}</div>
    <div className="w-[200px] flex items-center justify-center">
      <Badge label={application.genderType} color={application.genderType === '남성' ? 'male' : 'female'} />
    </div>
    <div className="w-[120px] flex items-center justify-center">{getAge(application.birthDate)}</div>
    <div className="w-[200px] flex items-center justify-center">
      <Badge label={application.hasCareer ? '경력' : '신입'} color={application.hasCareer ? 'career' : 'newbie'} />
    </div>
    <div className="w-[200px] flex items-center justify-center">
      <Badge label={application.educationLevel} color="info" />
    </div>
    <div className="w-[200px] flex items-center justify-center">{application.createdDate}</div>
  </div>
);

export default ApplicationSummaryRow; 