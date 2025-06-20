import { InternalBox } from "../../../common/components/box/Box";
import Input from "../../../common/components/input/Input";
import type { EducationResponseDto } from "../../myPageForMember/props/myPageForMemberProps";



interface EducationInfoProps {
    educationResponseDto: EducationResponseDto;
}

const EducationInfo = ({ educationResponseDto }: EducationInfoProps) => {


    return (
            <InternalBox>
                <div className="flex">
                    <Input label="학교명" placeholder="" size="ibm" disabled={true} type="text" value={educationResponseDto.schoolName} setValue={() => { }} />
                    <Input label="전공" placeholder="" size="ibm" disabled={true} type="text" value={educationResponseDto.major} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="학위" placeholder="" size="ibm" disabled={true} type="text" value={educationResponseDto.educationLevel} setValue={() => { }} />
                    <Input label="학점" placeholder="" size="ibm" disabled={true} type="text" value={educationResponseDto.gpa.toString()} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="소재지" placeholder="" size="ibm" disabled={true} type="text" value={educationResponseDto.location} setValue={() => { }} />
                    <div className="flex">
                        <Input label="입학일" placeholder="" size="ibs" disabled={true} type="date" value={educationResponseDto.enterDate} setValue={() => { }} />
                        <Input label="졸업일" placeholder="" size="ibs" disabled={true} type="date" value={educationResponseDto.graduateDate || ""} setValue={() => { }} />
                    </div>
                </div>
            </InternalBox>
    )
};

export default EducationInfo;
