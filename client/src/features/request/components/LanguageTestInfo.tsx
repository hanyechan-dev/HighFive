import { InternalBox } from "../../../common/components/box/Box";
import Input from "../../../common/components/input/Input";
import type { LanguageTestResponseDto } from "../../myPageForMember/props/myPageForMemberProps";




interface LanguageTestInfoProps {
    languageTestResponseDto: LanguageTestResponseDto;
}

const LanguageTestInfo = ({ languageTestResponseDto }: LanguageTestInfoProps) => {
    return (
            
            <InternalBox key={languageTestResponseDto.id}>
                <div className="flex">
                    <Input label="언어 종류" placeholder="" size="ibm" disabled={true} type="text" value={languageTestResponseDto.languageType} setValue={() => { }} />
                    <Input label="시험명" placeholder="" size="ibm" disabled={true} type="text" value={languageTestResponseDto.testName} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="발급기관" placeholder="" size="ibm" disabled={true} type="text" value={languageTestResponseDto.issuingOrg} setValue={() => { }} />
                    <Input label="등급" placeholder="" size="ibm" disabled={true} type="text" value={languageTestResponseDto.grade} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="점수" placeholder="" size="ibm" disabled={true} type="text" value={languageTestResponseDto.score} setValue={() => { }} />
                    <Input label="자격증 번호" placeholder="" size="ibm" disabled={true} type="text" value={languageTestResponseDto.certificationNo} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="취득일" placeholder="" size="ibm" disabled={true} type="date" value={languageTestResponseDto.acquisitionDate} setValue={() => { }} />
                </div>
            </InternalBox>
    );
};

export default LanguageTestInfo;
