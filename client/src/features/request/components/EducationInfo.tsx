import { InternalBox } from "../../../common/components/box/Box";
import Input from "../../../common/components/input/Input";
import type { EducationResponseDto } from "../props/RequestProps";



interface EducationInfoProps {
    educationResponseDtos: EducationResponseDto[];
}

const EducationInfo = ({ educationResponseDtos }: EducationInfoProps) => {


    return (
        educationResponseDtos.map((education, index) => (
            <InternalBox key={index}>
                <div className="flex">
                    <Input label="학교명" placeholder="" size="ibm" disabled={true} type="text" value={education.schoolName} setValue={() => { }} />
                    <Input label="전공" placeholder="" size="ibm" disabled={true} type="text" value={education.major} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="학위" placeholder="" size="ibm" disabled={true} type="text" value={education.educationLevel} setValue={() => { }} />
                    <Input label="학점" placeholder="" size="ibm" disabled={true} type="text" value={education.gpa.toString()} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="소재지" placeholder="" size="ibm" disabled={true} type="text" value={education.location} setValue={() => { }} />
                    <div className="flex">
                        <Input label="입학일" placeholder="" size="ibs" disabled={true} type="date" value={education.enterDate} setValue={() => { }} />
                        <Input label="졸업일" placeholder="" size="ibs" disabled={true} type="date" value={education.graduateDate} setValue={() => { }} />
                    </div>
                </div>
            </InternalBox>
        ))
    )
};

export default EducationInfo;
