import { InternalBox } from "../../../common/components/box/Box";
import Input from "../../../common/components/input/Input";
import type { CertificationResponseDto } from "../../myPageForMember/props/myPageForMemberProps";




interface CertificationInfoProps {
    certificationResponseDtos: CertificationResponseDto[];
}

const CertificationInfo = ({ certificationResponseDtos }: CertificationInfoProps) => {
    return (
        certificationResponseDtos.map((certification,index) => (
            <InternalBox key={index}>
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
