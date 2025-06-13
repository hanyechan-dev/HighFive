import type { CareerResponseDto } from "../../../features/request/RequestProps";
import { InternalBox } from "../box/Box";
import Input from "../input/Input";




interface CareerInfoProps {
    careerResponseDtos: CareerResponseDto[];
}



const CareerInfo = ({ careerResponseDtos }: CareerInfoProps) => {
    return (
        careerResponseDtos.map((career) => (

            <InternalBox >
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
