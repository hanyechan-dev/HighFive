import { InternalBox } from "../../../common/components/box/Box";
import Input from "../../../common/components/input/Input";
import type { CareerResponseDto } from "../props/RequestProps";





interface CareerInfoProps {
    careerResponseDtos: CareerResponseDto[];
}



const CareerInfo = ({ careerResponseDtos }: CareerInfoProps) => {
    return (
        careerResponseDtos.map((career,index) => (

            <InternalBox key={index}>
                <div className="flex">
                    <Input label="회사명" placeholder="" size="ibm" disabled={true} type="text" value={career.companyName} setValue={() => { }} />
                    <Input label="직무" placeholder="" size="ibm" disabled={true} type="text" value={career.job} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="부서" placeholder="" size="ibm" disabled={true} type="text" value={career.department} setValue={() => { }} />
                    <Input label="직급" placeholder="" size="ibm" disabled={true} type="text" value={career.position} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="입사일" placeholder="" size="ibs" disabled={true} type="date" value={career.startDate} setValue={() => { }} />
                    <Input label="퇴사일" placeholder="" size="ibs" disabled={true} type="date" value={career.endDate} setValue={() => { }} />
                </div>

            </InternalBox>
        ))
    )
};

export default CareerInfo;
