

import { BigExternalBox, PageBox } from "../../../common/components/box/Box";

import ModalTitle from "../../../common/components/title/ModalTitle";
import { useMyPageForMemberPageController } from "../customHooks/useMyPageForMemberPageController";

import RadioButton from "../../../common/components/button/RadioButton";
import { useState } from "react";
import { resumeTypeEnum } from "../../../common/enum/Enum";
import EducationInfoForMyPage from "../components/EducationInfoForMyPage";
import Button from "../../../common/components/button/Button";
import EducationCreate from "../components/EducationCreate";
import EducationTab from "./EducationTab";

const documentTextList = [
    { label: "이력서", value: "이력서" },
    { label: "경력기술서", value: "경력기술서" },
    { label: "자기소개서", value: "자기소개서" }
]





const DocumentTab = () => {

    const [resumeText, setResumeText] = useState(resumeTypeEnum[0].value)

    const {
        showDocumentTab,
        setShowDocumentTab,
        setEducationCreateDto,
        setEducationUpdateDto,
    } = useMyPageForMemberPageController();





    return (
        <>
            <PageBox >
                <ModalTitle title={showDocumentTab} />
                <div className="mt-[-12px] ml-6 mb-6 font-roboto text-[#888]">
                    회원님의 이력서, 경력기술서, 자기소개서를 확인하고 관리할 수 있습니다.
                </div>
                <div className="border-b w-[1120px] ml-6 mb-6"></div>
                <RadioButton name={""} textList={documentTextList} checkedText={showDocumentTab} setCheckedText={setShowDocumentTab} size="bigExternalDocument" />
                <BigExternalBox>
                    <div className="mt-[-24px]">
                        <RadioButton name={""} textList={resumeTypeEnum} checkedText={resumeText} setCheckedText={setResumeText} size="bigInternalResume" />
                    </div>
                    {resumeText === "학력사항" && (
                        <EducationTab />
                    )}





                </ BigExternalBox>

            </PageBox>
        </>
    )
};

export default DocumentTab;