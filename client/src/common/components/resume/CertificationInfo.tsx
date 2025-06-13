
import type { CertificationResponseDto } from "../../../features/request/RequestProps";
import { InternalBox } from "../box/Box";
import Input from "../input/Input";


interface CertificationInfoProps {
    certificationResponseDtos: CertificationResponseDto[];
}

const CertificationInfo = ({ certificationResponseDtos }: CertificationInfoProps) => {
    return (
        certificationResponseDtos.map((certification) => (
            <InternalBox>
                <div className="flex">
                    <Input label="자격증명" placeholder="" size="ibm" disabled={true} type="text" value={certification.certificationName} setValue={() => { }} />
                    <Input label="발급기관" placeholder="" size="ibm" disabled={true} type="text" value={certification.issuingOrg} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="등급" placeholder="" size="ibm" disabled={true} type="text" value={certification.grade} setValue={() => { }} />
                    <Input label="점수" placeholder="" size="ibm" disabled={true} type="text" value={certification.score} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="자격증 번호" placeholder="" size="ibm" disabled={true} type="text" value={certification.certificationNo} setValue={() => { }} />
                    <Input label="취득일" placeholder="" size="ibm" disabled={true} type="date" value={certification.acquisitionDate} setValue={() => { }} />
                </div>
            </InternalBox>
        ))
    );
};

export default CertificationInfo;
