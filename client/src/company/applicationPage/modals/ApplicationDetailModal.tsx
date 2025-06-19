import { useEffect, useState } from "react";
import CommonModal from "../../../common/modals/CommonModal";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { ApplicationDetailApi } from "../apis/ApplicationApi";
import type { ApplicationDetail } from "../props/ApplicationProps";
import Badge from "../../common/components/Badge";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import TextArea from "../../../common/components/input/TextArea";
import MemberInfoBox from "../../memberPoolPage/components/MemberInfoBox";
import { TabButton } from "../../memberPoolPage/components/TabButton";
import CompanyEmptyState from "../../common/components/CompanyEmptyState";
import { parseResumeFromJsonStrings } from "../../utils/ResumeParseUtil";

interface ApplicationDetailModalProps {
  isOpen: boolean;
  onClose: () => void;
  applicationId: number;
}

const TABS = ["학력 사항", "경력 사항", "자격증", "어학", "경력기술서", "자기소개서"];

// 개발 중 모달 UI 형태 확인을 위한 Mock 데이터
const mockApplication: ApplicationDetail = {
  id: 1,
  title: "프론트엔드 개발자 모집",
  companyName: "테크컴퍼니",
  job: "프론트엔드 개발자",
  createdDate: "2024-01-15",
  isPassed: false,
  resumeJson: JSON.stringify({
    educationResponseDtos: [{
      id: 1,
      schoolName: "서울대학교",
      educationLevel: "학사",
      major: "컴퓨터공학과",
      gpa: 4.2,
      location: "서울",
      enterDate: "2018-03-01",
      graduateDate: "2022-02-28"
    }],
    careerResponseDtos: [{
      id: 1,
      companyName: "네이버",
      job: "프론트엔드 개발자",
      department: "개발팀",
      position: "사원",
      startDate: "2022-03-01",
      endDate: "2024-02-29"
    }],
    certificationResponseDtos: [],
    languageTestResponseDtos: []
  }),
  coverLetterJson: JSON.stringify({
    coverLetterResponseDto: {
      id: 1,
      title: "자기소개서",
      contents: [{
        id: 1,
        item: "지원동기",
        content: "귀사의 혁신적인 프로젝트에 참여하고 싶습니다. 사용자 경험을 개선하는데 기여하고 싶습니다."
      }]
    }
  }),
  careerDescriptionJson: JSON.stringify({
    careerDescriptionResponseDto: {
      id: 1,
      title: "경력기술서",
      contents: [{
        id: 1,
        item: "주요 프로젝트",
        content: "3년간 React, TypeScript를 사용한 웹 개발 경험. 대규모 사용자를 대상으로 한 서비스 개발 및 유지보수 경험을 보유하고 있습니다."
      }]
    }
  })
};













export default function ApplicationDetailModal({ isOpen, onClose, applicationId }: ApplicationDetailModalProps) {
  const [application, setApplication] = useState<ApplicationDetail | null>(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [activeTab, setActiveTab] = useState(TABS[0]);

  const fetchApplicationDetail = () => {
    if (!applicationId) return;
    setIsLoading(true);
    setError(null);

    ApplicationDetailApi(applicationId)
      .then((response: any) => {
        setApplication(response.data);
      })
      .catch((err: any) => {
        console.error('지원자 상세 정보 조회 실패:', err);
        setError('지원자 상세 정보를 불러오지 못했습니다.');
        
        // 개발 중 모달 UI 형태 확인을 위한 Mock 데이터
        setApplication({ ...mockApplication, id: applicationId });
      })
      .finally(() => {
        setIsLoading(false);
      });
  };

  useEffect(() => {
    if (isOpen && applicationId) {
      fetchApplicationDetail();
    }
  }, [isOpen, applicationId]);

  if (!isOpen) return null;

  // ResumeParseUtil 사용
  const parsedData = application ? parseResumeFromJsonStrings(
    application.resumeJson, 
    application.careerDescriptionJson, 
    application.coverLetterJson
  ) : null;

  // 각 탭별 내용
  const renderTabContent = () => {
    if (!parsedData) return null;

    if (activeTab === "학력 사항") {
      return parsedData.education ? (
        <>
          <div className="flex">
            <Input label="학교명" placeholder="" size="m" disabled type="text" value={parsedData.education.schoolName} setValue={() => {}} />
            <Input label="전공" placeholder="" size="m" disabled type="text" value={parsedData.education.major} setValue={() => {}} />
          </div>
          <div className="flex">
            <Input label="학위" placeholder="" size="m" disabled type="text" value={parsedData.education.educationLevel} setValue={() => {}} />
            <Input label="학점" placeholder="" size="m" disabled type="text" value={parsedData.education.gpa.toString()} setValue={() => {}} />
          </div>
          <div className="flex">
            <Input label="소재지" placeholder="" size="m" disabled type="text" value={parsedData.education.location} setValue={() => {}} />
            <div className="flex items-end">
              <Input label="재학 기간" placeholder="" size="s" disabled type="text" value={parsedData.education.enterDate} setValue={() => {}} />
              <Input label="" placeholder="" size="s" disabled type="text" value={parsedData.education.graduateDate} setValue={() => {}} />
            </div>
          </div>
        </>
      ) : (
        <CompanyEmptyState title="학력 정보가 없습니다" text="지원자가 학력 정보를 입력하지 않았습니다" />
      );
    }

    if (activeTab === "경력 사항") {
      return parsedData.career ? (
        <>
          <div className="flex">
            <Input label="회사명" placeholder="" size="m" disabled type="text" value={parsedData.career.companyName} setValue={() => {}} />
            <Input label="직무" placeholder="" size="m" disabled type="text" value={parsedData.career.job} setValue={() => {}} />
          </div>
          <div className="flex">
            <Input label="부서" placeholder="" size="m" disabled type="text" value={parsedData.career.department} setValue={() => {}} />
            <Input label="직급" placeholder="" size="m" disabled type="text" value={parsedData.career.position} setValue={() => {}} />
          </div>
          <div className="flex items-end">
            <Input label="근무기간" placeholder="" size="s" disabled type="text" value={parsedData.career.startDate} setValue={() => {}} />
            <Input label="" placeholder="" size="s" disabled type="text" value={parsedData.career.endDate} setValue={() => {}} />
          </div>
        </>
      ) : (
        <CompanyEmptyState title="경력 정보가 없습니다" text="지원자가 경력 정보를 입력하지 않았습니다" />
      );
    }

    if (activeTab === "자격증") {
      return parsedData.certification ? (
        <>
          <div className="flex">
            <Input label="자격증명" placeholder="" size="m" disabled type="text" value={parsedData.certification.certificationName} setValue={() => {}} />
            <Input label="발급기관" placeholder="" size="m" disabled type="text" value={parsedData.certification.issuingOrg} setValue={() => {}} />
          </div>
          <div className="flex">
            <Input label="등급" placeholder="" size="m" disabled type="text" value={parsedData.certification.grade} setValue={() => {}} />
            <Input label="점수" placeholder="" size="m" disabled type="text" value={parsedData.certification.score} setValue={() => {}} />
          </div>
          <div className="flex">
            <Input label="자격증번호" placeholder="" size="m" disabled type="text" value={parsedData.certification.certificationNo} setValue={() => {}} />
            <Input label="취득일" placeholder="" size="m" disabled type="text" value={parsedData.certification.acquisitionDate} setValue={() => {}} />
          </div>
        </>
      ) : (
        <CompanyEmptyState title="자격증 정보가 없습니다" text="지원자가 자격증 정보를 입력하지 않았습니다" />
      );
    }

    if (activeTab === "어학") {
      return parsedData.languageTest ? (
        <>
          <div className="flex">
            <Input label="언어" placeholder="" size="m" disabled type="text" value={parsedData.languageTest.languageType} setValue={() => {}} />
            <Input label="시험명" placeholder="" size="m" disabled type="text" value={parsedData.languageTest.testName} setValue={() => {}} />
          </div>
          <div className="flex">
            <Input label="발급기관" placeholder="" size="m" disabled type="text" value={parsedData.languageTest.issuingOrg} setValue={() => {}} />
            <Input label="등급" placeholder="" size="m" disabled type="text" value={parsedData.languageTest.grade} setValue={() => {}} />
          </div>
          <div className="flex">
            <Input label="점수" placeholder="" size="m" disabled type="text" value={parsedData.languageTest.score} setValue={() => {}} />
            <Input label="취득일" placeholder="" size="m" disabled type="text" value={parsedData.languageTest.acquisitionDate} setValue={() => {}} />
          </div>
        </>
      ) : (
        <CompanyEmptyState title="어학 정보가 없습니다" text="지원자가 어학 정보를 입력하지 않았습니다" />
      );
    }

    if (activeTab === "경력기술서") {
      return parsedData.careerDescription && parsedData.careerDescription.contents && parsedData.careerDescription.contents.length > 0 ? (
        <TextArea
          label="경력기술서"
          placeholder=""
          size="l"
          disabled={true}
          value={parsedData.careerDescription.contents[0].content}
          setValue={() => {}}
        />
      ) : (
        <CompanyEmptyState title="경력기술서가 없습니다" text="지원자가 경력기술서를 입력하지 않았습니다" />
      );
    }

    if (activeTab === "자기소개서") {
      return parsedData.coverLetter && parsedData.coverLetter.contents && parsedData.coverLetter.contents.length > 0 ? (
        <TextArea
          label="자기소개서"
          placeholder=""
          size="l"
          disabled={true}
          value={parsedData.coverLetter.contents[0].content}
          setValue={() => {}}
        />
      ) : (
        <CompanyEmptyState title="자기소개서가 없습니다" text="지원자가 자기소개서를 입력하지 않았습니다" />
      );
    }

    return null;
  };

  return (
    <CommonModal size="l" onClose={onClose}>
      {isLoading ? (
        <div className="text-center py-12 text-gray-400">로딩 중...</div>
      ) : application ? (
        <>
          <ModalTitle title="지원자 상세 정보" />
          
          {/* 지원자 기본 정보 */}
         <MemberInfoBox items={[
                            { label: "이름", value: application.title },
                            { label: "회사명", value: application.companyName },
                            { label: "지원 직무", value: application.job },
                            { label: "지원일", value: application.createdDate },
                            { label: "합격 여부", value: <Badge 
                              label={application.isPassed ? '합격' : '검토중'} 
                              color={application.isPassed ? 'approved' : 'waiting'} 
                            /> }
                            ]} />
      

          {/* 탭 */}
          <div className="flex border-b mb-4 ml-6 w-[952px]">
            {TABS.map(tab => (
              <TabButton key={tab} label={tab} isActive={activeTab === tab} onClick={() => setActiveTab(tab)} />
            ))}
          </div>

          {/* 탭별 내용 */}
          <div className="ml-6">
            {renderTabContent()}
          </div>
        </>
      ) : error ? (
        <div className="text-center py-12 text-red-500">
          <p className="mb-4">{error}</p>
          <Button
            color="theme"
            size="s"
            text="다시 시도"
            disabled={false}
            type="button"
            onClick={() => {
              setError(null);
              if (applicationId) {
                fetchApplicationDetail();
              }
            }}
          />
        </div>
      ) : (
        <div className="text-center py-12 text-gray-500">
          지원자 정보가 없습니다.
        </div>
      )}
    </CommonModal>
  );
} 