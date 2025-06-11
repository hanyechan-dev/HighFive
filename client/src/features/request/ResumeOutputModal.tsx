import { useState } from "react";
import AiEdit from "../../common/components/aiConsulting/AiEdit";
import RadioButton from "../../common/components/button/RadioButton";
import CareerDescriptionInfo from "../../common/components/careerDescription/CareerDescriptionInfo";
import CoverLetterInfo from "../../common/components/coverLetter/CoverLetterInfo";
import InfoBox from "../../common/components/infoBox/InfoBox";
import CareerInfo from "../../common/components/resume/CareerInfo";
import CertificationInfo from "../../common/components/resume/CertificationInfo";
import EducationInfo from "../../common/components/resume/EducationInfo";
import LanguageTest from "../../common/components/resume/LanguageTest";
import ModalTitle from "../../common/components/title/ModalTitle";
import CommonModal from "../../common/modals/CommonModal"
import { ExternalBox } from "../../common/components/box/Box";
import type { educationProps } from "../../common/props/AiConsultingProps";
import Button from "../../common/components/button/Button";


const mockEducations: educationProps[] = [
    {
        id: 1,
        schoolName: "서울대학교",
        educationLevel: "학사",
        major: "컴퓨터공학과",
        gpa: 4.2,
        location: "서울",
        enterDate: "2015-03-01",
        graduateDate: "2019-02-28"
    },
    {
        id: 2,
        schoolName: "고려대학교",
        educationLevel: "석사",
        major: "인공지능학과",
        gpa: 4.0,
        location: "서울",
        enterDate: "2019-03-01",
        graduateDate: "2021-08-31"
    },
    {
        id: 3,
        schoolName: "연세대학교",
        educationLevel: "박사",
        major: "데이터사이언스",
        gpa: 4.5,
        location: "서울",
        enterDate: "2021-09-01",
        graduateDate: "2025-02-28"
    },
    {
        id: 4,
        schoolName: "부산대학교",
        educationLevel: "학사",
        major: "소프트웨어학과",
        gpa: 3.8,
        location: "부산",
        enterDate: "2014-03-01",
        graduateDate: "2018-02-28"
    },
    {
        id: 5,
        schoolName: "한양대학교",
        educationLevel: "전문학사",
        major: "정보보안",
        gpa: 3.9,
        location: "서울",
        enterDate: "2016-03-01",
        graduateDate: "2018-08-31"
    }
];

const resumeTextList = [
    { label: "학력 사항", value: "education" },
    { label: "경력 사항", value: "career" },
    { label: "자격증", value: "certification" },
    { label: "어학", value: "languageTest" },
]



interface ResumeOutputModalPrpos {
    targetJob: string;
    targetCompanyName: string;
    setResumeOutputModal: (showResumeOutputModal: boolean) => void;
    setModalNumber: (modalNumber: number) => void
}



const ResumeOutputModal = ({}:ResumeOutputModalPrpos) => {
    const [selectedTag, setSelectedTag] = useState(resumeTextList[0].value);

    return (
        <CommonModal size="l" onClose={() => console.log("닫기")}>
            <ModalTitle title="이력서" />
            <div>
                <div className="flex gap-[24px] justify-center ml-[-80px] mt-4">
                    <RadioButton
                        name=""
                        textList={resumeTextList}
                        checkedText={selectedTag}
                        setCheckedText={setSelectedTag}
                    />
                </div>

                <div className="flex justify-center">
                    {selectedTag == "education" && (
                        <ExternalBox>
                            <EducationInfo educations={[]} />
                        </ExternalBox>
                    )}
                    {selectedTag == "career" && (
                        <ExternalBox>
                            <CareerInfo careers={[]} />
                        </ExternalBox>
                    )}
                    {selectedTag == "certification" && (
                        <ExternalBox>
                            <CertificationInfo certifications={[]} />
                        </ExternalBox>
                    )}
                    {selectedTag == "languageTest" && (
                        <ExternalBox>
                            <LanguageTest languageTests={[]} />
                        </ExternalBox>
                    )}
                </div>
            </div>

            <div className="flex justify-end mr-6">
                <Button color={"white"} size={"s"} disabled={false} text={"이전"} type={"button"} onClick={()=>{}} />
                <Button color={"theme"} size={"m"} disabled={false} text={"저장 및 다음"} type={"button"} onClick={()=>{}} />
            </div>
        </CommonModal>

    );

}

export default ResumeOutputModal