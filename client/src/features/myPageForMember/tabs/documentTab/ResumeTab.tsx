import { useEffect, useState } from "react"

import CareerTab from "./CareerTab"
import CertificationTab from "./CertificationTab"
import EducationTab from "./EducationTab"
import LanguageTestTab from "./LanguageTestTab"
import RadioButton from "../../../../common/components/button/RadioButton"
import { resumeTypeEnum } from "../../../../common/enum/Enum"
import { useDocumentTabApi } from "../../customHooks/DocumentTab/useDocumentTabApi"

const ResumeTab = () => {

    const [resumeText, setResumeText] = useState(resumeTypeEnum[0].value)

    const {readResume} = useDocumentTabApi();

    useEffect(() => {
        readResume();
    }, []);

    return (
        <div>
            <div className="mt-[-24px]">
                <RadioButton name={""} textList={resumeTypeEnum} checkedText={resumeText} setCheckedText={setResumeText} size="bigInternalResume" />
            </div>
            {resumeText === "학력사항" && (
                <EducationTab />
            )}
            {resumeText === "경력사항" && (
                <CareerTab />
            )}
            {resumeText === "자격증" && (
                <CertificationTab />
            )}
            {resumeText === "어학" && (
                <LanguageTestTab />
            )}
        </div>
    )
};

export default ResumeTab;