import { InternalBox } from "../../../common/components/box/Box";
import Input from "../../../common/components/input/Input";
import type { CareerResponseDto } from "../../myPageForMember/props/myPageForMemberProps";





interface CareerInfoProps {
    careerResponseDto: CareerResponseDto;
}



const CareerInfo = ({ careerResponseDto }: CareerInfoProps) => {
    return (


            <InternalBox>
                <div className="flex">
                    <Input label="회사명" placeholder="" size="ibm" disabled={true} type="text" value={careerResponseDto.companyName} setValue={() => { }} />
                    <Input label="직무" placeholder="" size="ibm" disabled={true} type="text" value={careerResponseDto.job} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="부서" placeholder="" size="ibm" disabled={true} type="text" value={careerResponseDto.department} setValue={() => { }} />
                    <Input label="직급" placeholder="" size="ibm" disabled={true} type="text" value={careerResponseDto.position} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="입사일" placeholder="" size="ibs" disabled={true} type="date" value={careerResponseDto.startDate} setValue={() => { }} />
                    <Input label="퇴사일" placeholder="" size="ibs" disabled={true} type="date" value={careerResponseDto.endDate || ""} setValue={() => { }} />
                </div>

            </InternalBox>
    )
};

export default CareerInfo;
