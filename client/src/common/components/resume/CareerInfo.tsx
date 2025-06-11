import type { careerProps } from "../../props/resumeProps";
import Input from "../input/Input";



interface CareerInfoProps {
    careers: careerProps[];
}



const CareerInfo = ({ careers }: CareerInfoProps) => {
    return (
        careers.map((career) => (
            <div className="w-[950px] border border-gray-300 rounded-lg mb-[24px] pt-[24px]">
                <div className="flex">
                    <Input label="회사명" placeholder="" size="sm" disabled={true} type="text" value={career.companyName} setValue={() => { }} />
                    <Input label="직무" placeholder="" size="sm" disabled={true} type="text" value={career.job} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="부서" placeholder="" size="sm" disabled={true} type="text" value={career.department} setValue={() => { }} />
                    <Input label="직급" placeholder="" size="sm" disabled={true} type="text" value={career.position} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="입사일" placeholder="" size="xs" disabled={true} type="date" value={career.startDate} setValue={() => { }} />
                    <Input label="퇴사일" placeholder="" size="xs" disabled={true} type="date" value={career.endDate} setValue={() => { }} />
                </div>
            </div>
        ))
    )
};

export default CareerInfo;
