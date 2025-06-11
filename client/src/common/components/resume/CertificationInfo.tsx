import type { certificationProps } from "../../props/resumeProps";
import Input from "../input/Input";


interface CertificationInfoProps {
    certifications: certificationProps[];
}

const CertificationInfo = ({ certifications }: CertificationInfoProps) => {
    return (
        certifications.map((certification) => (
        <div className="w-[950px] border border-gray-300 rounded-lg mb-[24px] pt-[24px]">
        <div className="flex">
            <Input label="자격증명" placeholder="" size="sm" disabled={true} type="text" value={certification.certificationName} setValue={()=>{}} />
            <Input label="발급기관" placeholder="" size="sm" disabled={true} type="text" value={certification.issuingOrg} setValue={()=>{}} />
        </div>
        <div className="flex">
            <Input label="등급" placeholder="" size="sm" disabled={true} type="text" value={certification.grade} setValue={()=>{}} />
            <Input label="점수" placeholder="" size="sm" disabled={true} type="text" value={certification.score} setValue={()=>{}} />
        </div>
        <div className="flex">
            <Input label="자격증 번호" placeholder="" size="sm" disabled={true} type="text" value={certification.certificationNo} setValue={()=>{}} />
            <Input label="취득일" placeholder="" size="sm" disabled={true} type="date" value={certification.acquisitionDate} setValue={()=>{}} />
        </div>
    </div>
    ))
    );
};

export default CertificationInfo;
