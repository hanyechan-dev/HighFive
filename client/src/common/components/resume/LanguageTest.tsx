import type { languageTestProps } from "../../props/resumeProps";
import Input from "../input/Input";



interface LanguageTestProps {
    languageTests: languageTestProps[];
}

const LanguageTest = ({ languageTests }: LanguageTestProps) => {
    return (
        languageTests.map((languageTest) => (
            <div className="w-[950px] border border-gray-300 rounded-lg mb-[24px] pt-[24px]">
                <div className="flex">
                    <Input label="언어 종류" placeholder="" size="sm" disabled={true} type="text" value={languageTest.languageType} setValue={() => { }} />
                    <Input label="시험명" placeholder="" size="sm" disabled={true} type="text" value={languageTest.testName} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="발급기관" placeholder="" size="sm" disabled={true} type="text" value={languageTest.issuingOrg} setValue={() => { }} />
                    <Input label="등급" placeholder="" size="sm" disabled={true} type="text" value={languageTest.grade} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="점수" placeholder="" size="sm" disabled={true} type="text" value={languageTest.score} setValue={() => { }} />
                    <Input label="자격증 번호" placeholder="" size="sm" disabled={true} type="text" value={languageTest.certificationNo} setValue={() => { }} />
                </div>
                <div className="flex">
                    <Input label="취득일" placeholder="" size="sm" disabled={true} type="date" value={languageTest.acquisitionDate} setValue={() => { }} />
                </div>
            </div>
        ))
    );
};

export default LanguageTest;
