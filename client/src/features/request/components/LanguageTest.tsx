import { InternalBox } from "../../../common/components/box/Box";
import Input from "../../../common/components/input/Input";
import type { LanguageTestResponseDto } from "../props/RequestProps";




interface LanguageTestProps {
    languageTestResponseDtos: LanguageTestResponseDto[];
}

const LanguageTest = ({ languageTestResponseDtos }: LanguageTestProps) => {
    return (
        languageTestResponseDtos.map((languageTest) => (
            
            <InternalBox key={languageTest.id}>
                <div className="flex">
                    <Input label="언어 종류" placeholder="" size="ibm" disabled={true} type="text" value={languageTest.languageType} setValue={() => { }} />
                    <Input label="시험명" placeholder="" size="ibm" disabled={true} type="text" value={languageTest.testName} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="발급기관" placeholder="" size="ibm" disabled={true} type="text" value={languageTest.issuingOrg} setValue={() => { }} />
                    <Input label="등급" placeholder="" size="ibm" disabled={true} type="text" value={languageTest.grade} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="점수" placeholder="" size="ibm" disabled={true} type="text" value={languageTest.score} setValue={() => { }} />
                    <Input label="자격증 번호" placeholder="" size="ibm" disabled={true} type="text" value={languageTest.certificationNo} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="취득일" placeholder="" size="ibm" disabled={true} type="date" value={languageTest.acquisitionDate} setValue={() => { }} />
                </div>
            </InternalBox>
        ))
    );
};

export default LanguageTest;
