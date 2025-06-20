import { InternalBox } from "../../../common/components/box/Box";
import Input from "../../../common/components/input/Input";
import type { CertificationResponseDto } from "../../myPageForMember/props/myPageForMemberProps";




interface CertificationInfoProps {
    certificationResponseDto: CertificationResponseDto;
}

const CertificationInfo = ({ certificationResponseDto }: CertificationInfoProps) => {
    return (
            <InternalBox>
                <div className="flex">
                    <Input label="자격증명" placeholder="" size="ibm" disabled={true} type="text" value={certificationResponseDto.certificationName} setValue={() => { }} />
                    <Input label="발급기관" placeholder="" size="ibm" disabled={true} type="text" value={certificationResponseDto.issuingOrg} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="등급" placeholder="" size="ibm" disabled={true} type="text" value={certificationResponseDto.grade} setValue={() => { }} />
                    <Input label="점수" placeholder="" size="ibm" disabled={true} type="text" value={certificationResponseDto.score} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="자격증 번호" placeholder="" size="ibm" disabled={true} type="text" value={certificationResponseDto.certificationNo} setValue={() => { }} />
                    <Input label="취득일" placeholder="" size="ibm" disabled={true} type="date" value={certificationResponseDto.acquisitionDate} setValue={() => { }} />
                </div>
            </InternalBox>
    );
};

export default CertificationInfo;
