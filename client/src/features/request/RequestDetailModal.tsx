import { useState } from "react";
import RadioButton from "../../common/components/button/RadioButton";
import ModalTitle from "../../common/components/title/ModalTitle";
import { documentTypeEnum, resumeTypeEnum, type consultingTypeEnum } from "../../common/enum/Enum";
import CommonModal from "../../common/modals/CommonModal";
import TargetInfo from "./TargetInfo";
import { ExternalBox, InternalBox } from "../../common/components/box/Box";
import EducationInfo from "../../common/components/resume/EducationInfo";
import CareerInfo from "../../common/components/resume/CareerInfo";
import CertificationInfo from "../../common/components/resume/CertificationInfo";
import LanguageTest from "../../common/components/resume/LanguageTest";
import type { aiContentsProps, careerDescriptionProps, careerProps, certificationProps, coverLetterProps, educationProps, languageTestProps } from "../../common/props/AiConsultingProps";
import CareerDescriptionInfo from "../../common/components/careerDescription/CareerDescriptionInfo";
import AiConsulting from "../../common/components/aiConsulting/AiConsulting";
import CoverLetterInfo from "../../common/components/coverLetter/CoverLetterInfo";
import Button from "../../common/components/button/Button";

const mockEducationList : educationProps[] = [
    {
        id: 1,
        schoolName: "서울대학교",
        educationLevel: "대학교",
        major: "컴퓨터공학",
        gpa: 4.5,
        location: "서울",
        enterDate: "2022-01-01",
        graduateDate: "2022-01-01",
    },
    {
        id: 2,
        schoolName: "서울대학교",
        educationLevel: "대학교",
        major: "컴퓨터공학",
        gpa: 4.5,
        location: "서울",
        enterDate: "2022-01-01",
        graduateDate: "2022-01-01",
    }
]

const mockCareerList : careerProps[] = [
    {
        id: 1,
        companyName: "삼성",
        job: "백엔드 개발",
        department: "개발",
        position: "개발자",
        startDate: "2022-01-01",
        endDate: "2022-01-01",
    },
    {
        id: 2,
        companyName: "삼성",
        job: "백엔드 개발",
        department: "개발",
        position: "개발자",
        startDate: "2022-01-01",
        endDate: "2022-01-01",
    }
]

const mockCertificationList : certificationProps[] = [
    {
        id: 1,
        certificationName: "정보처리기사",
        issuingOrg: "큐넷",
        grade: "실기합격",
        score: "",
        certificationNo: "124lksdsa7564565",
        acquisitionDate: "2022-01-01",
    },
    {
        id: 2,
        certificationName: "컴퓨터활용능력력",
        issuingOrg: "대한상공회의소소",
        grade: "1급",
        score: "",
        certificationNo: "124lksdsa7564565",
        acquisitionDate: "2022-01-01",
    }
]

const mockLanguageTestList : languageTestProps[] = [
    {
        id: 1,
        languageType: "한국어",
        testName: "한국어",
        issuingOrg: "큐넷",
        grade: "실기합격",
        score: "",
        certificationNo: "124lksdsa7564565",
        acquisitionDate: "2022-01-01",
    },
    {
        id: 2,
        languageType: "영어",
        testName: "영어",
        issuingOrg: "대한상공회의소소",
        grade: "1급",
        score: "",
        certificationNo: "124lksdsa7564565",
        acquisitionDate: "2022-01-01",
    }
]

const mockCareerDescription : careerDescriptionProps = 
    {
        id: 1,
        title: "경력기술서1",
        contents: [
            {
                id: 1,
                item: "N+1 문제 해결",
                content: "쿼리 존나 나감 근데 그거 해결 좀 많이 빨라짐 개꿀"
            },
            {
                id: 2,
                item: "코드 리뷰",
                content: "애들 코드 리뷰도 좀 많이 해줬음 애들 ㅄ이라 내가 짱임 ㅋㅋ"
            }
        ]
    }

    const mockAiContents : aiContentsProps = 
    {
        aiContents: [
            {
                item: "N+1 문제 해결",
                content: "좀 애지간히 N+1 써라 개나소나  N+1써다그러네네"
            },
            {
                item: "코드 리뷰",
                content: "좀 애지간히 코드 리뷰도 작작 하셈 너도 ㅄ임 ㅋㅋ"
            }
        ]
    }

    const mockCoverLetter : coverLetterProps = 
    {
        id: 1,
        title: "자기소개서1",
        contents: [
            {
                id: 1,
                item: "N+1 문제 해결",
                content: "좀 애지간히 N+1 써라 개나소나  N+1써다그러네"
            },
            {
                id: 2,
                item: "코드 리뷰",
                content: "좀 애지간히 코드 리뷰도 작작 하셈 너도 ㅄ임 ㅋㅋ"
            }
        ]
    }











interface RequestDetailModalProps {
    consultingType: consultingTypeEnum;
}

const RequestDetailModal = ({consultingType}: RequestDetailModalProps) => {
    const [checkedDocument, setCheckedDocument] = useState(documentTypeEnum[0].value);
    const [checkedResume, setCheckedResume] = useState(resumeTypeEnum[0].value);


    return (
        <CommonModal size="l" onClose={()=>{}}>
            <ModalTitle title={`${consultingType} 요청 상세`} />
            <TargetInfo targetCompanyName={"삼성"} targetJob={"백엔드 개발"} />
            <div className="flex justify-start text-lg font-semibold text-gray-600 font-roboto ml-12">
                요청정보
            </div>
            <div className="border-b w-[952px] mt-2 ml-6"></div>
            <RadioButton name={""} textList={documentTypeEnum} checkedText={checkedDocument} setCheckedText={setCheckedDocument} size="document" />
            {checkedDocument === "이력서" &&  
            <ExternalBox>
                <RadioButton name={""} textList={resumeTypeEnum} checkedText={checkedResume} setCheckedText={setCheckedResume} size="resume" />
                    {checkedResume === "학력사항" && <EducationInfo educations={mockEducationList} />}
                    {checkedResume === "경력사항" && <CareerInfo careers={mockCareerList} />}
                    {checkedResume === "자격증" && <CertificationInfo certifications={mockCertificationList} />}    
                    {checkedResume === "어학" && <LanguageTest languageTests={mockLanguageTestList} />}
            </ExternalBox>}

            {checkedDocument === "경력기술서" && 
                <>
                    <CareerDescriptionInfo careerDescription={mockCareerDescription} />
                    <AiConsulting aiContents={mockAiContents} consultingType={"첨삭"} />
                </>
            }
            {checkedDocument === "자기소개서" && 
                <>
                    <CoverLetterInfo coverLetter={mockCoverLetter} />
                    <AiConsulting aiContents={mockAiContents} consultingType={"피드백"} />
                </>
            }

            <div className="flex justify-end mr-6">
                <Button color="theme" size="m" text="컨설턴트 컨설팅 요청" onClick={() => { } } disabled={false} type={"button"} />
            </div>
        </CommonModal>
    );
};

export default RequestDetailModal;