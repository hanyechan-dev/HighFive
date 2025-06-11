import type { educationProps } from "../../props/resumeProps";
import Input from "../input/Input"


interface EducationInfoProps {
    educations: educationProps[]
}

const EducationInfo = ({ educations }: EducationInfoProps) => {


    return (
        educations.map((education) => (
        <div className="w-[952px] border border-gray-300 rounded-lg  mb-[24px] pt-[24px]">
            <div className="flex">
                <Input label="학교명" placeholder="" size="sm" disabled={true} type="text" value={education.schoolName} setValue={() => { }} />
                <Input label="전공" placeholder="" size="sm" disabled={true} type="text" value={education.major} setValue={() => { }} />
            </div>
            <div className="flex">
                <Input label="학위" placeholder="" size="sm" disabled={true} type="text" value={education.educationLevel} setValue={() => { }} />
                <Input label="학점" placeholder="" size="sm" disabled={true} type="text" value={education.gpa.toString()} setValue={() => { }} />
            </div>
            <div className="flex">
                <Input label="소재지" placeholder="" size="sm" disabled={true} type="text" value={education.location} setValue={() => { }} />
                <div className="flex">
                    <Input label="입학일" placeholder="" size="xs" disabled={true} type="date" value={education.enterDate} setValue={() => { }} />
                    <Input label="졸업일" placeholder="" size="xs" disabled={true} type="date" value={education.graduateDate} setValue={() => { }} />
                </div>
            </div>
        </div>
        ))
    )
};

export default EducationInfo;
