import { useEffect, useState, useCallback } from "react";
import CommonModal from "../../../common/modals/CommonModal";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { ApplicationDetailApi, passApplicationApi } from "../apis/ApplicationApi";
import type { ApplicationDetail } from "../props/ApplicationProps";
import Badge from "../../common/components/Badge";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import TextArea from "../../../common/components/input/TextArea";
import MemberInfoBox from "../../memberPoolPage/components/MemberInfoBox";

import CompanyEmptyState from "../../common/components/CompanyEmptyState";
import { parseResumeFromJsonStrings } from "../../utils/ResumeParseUtil";
import { TabButton } from "../../common/components/TabButton";

interface ApplicationDetailModalProps {
  isOpen: boolean;
  onClose: () => void;
  applicationId: number;
}

const MAIN_TABS = ["이력서", "경력기술서", "자기소개서"];
const RESUME_TABS = ["학력 사항", "경력 사항", "자격증", "어학"];

// 개발 중 모달 UI 형태 확인을 위한 Mock 데이터
const mockApplication: ApplicationDetail = {
  id: 1,
  name: "김철수",
  email: "kim@example.com",
  genderType: "남자",
  birthDate: "1995-01-15",
  job: "프론트엔드 개발자",
  phone: "010-1234-5678",
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
  const [activeMainTab, setActiveMainTab] = useState(MAIN_TABS[0]);
  const [activeSubTab, setActiveSubTab] = useState(RESUME_TABS[0]);

  const fetchApplicationDetail = useCallback(() => {
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
  }, [applicationId]);

  const handlePass = useCallback(async () => {
    if (!application || !applicationId) return;

    try {
      await passApplicationApi(applicationId);
      alert('합격 처리되었습니다.');
      setApplication(prev => prev ? { ...prev, isPassed: true } : null);
    } catch (error) {
      console.error('합격 처리 실패:', error);
      alert('합격 처리에 실패했습니다. 다시 시도해주세요.');
    }
  }, [application, applicationId]);

  useEffect(() => {
    if (isOpen && applicationId) {
      fetchApplicationDetail();
    }
  }, [isOpen, applicationId, fetchApplicationDetail]);

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

    if (activeMainTab === '이력서') {
      if (activeSubTab === "학력 사항") {
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

      if (activeSubTab === "경력 사항") {
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

      if (activeSubTab === "자격증") {
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

      if (activeSubTab === "어학") {
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
    }

    if (activeMainTab === "경력기술서") {
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

    if (activeMainTab === "자기소개서") {
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
                            { label: "이름", value: application.name },
                            { label: "이메일", value: application.email },
                            { label: "성별", value: application.genderType },
                            { label: "생년월일", value: application.birthDate },
                            { label: "전화번호", value: application.phone },
                            { label: "지원 직무", value: application.job },
                            { label: "지원일", value: application.createdDate },
                            { label: "합격 여부", value: <Badge 
                              label={application.isPassed ? '합격' : '검토중'} 
                              color={application.isPassed ? 'approved' : 'waiting'} 
                            /> }
                            ]} />
      

          {/* 탭 */}
          <div className="flex border-b mb-4 ml-6 w-[952px]">
            {MAIN_TABS.map(tab => (
              <TabButton
                key={tab}
                label={tab}
                variant="main"
                isActive={activeMainTab === tab}
                onClick={() => {
                  setActiveMainTab(tab);
                  if (tab === "이력서") {
                    setActiveSubTab(RESUME_TABS[0]);
                  }
                }}
              />
            ))}
          </div>

          {activeMainTab === "이력서" && (
            <div className="flex border-b mb-4 ml-6 w-[952px]">
              {RESUME_TABS.map(tab => (
                <TabButton key={tab} label={tab} isActive={activeSubTab === tab} onClick={() => setActiveSubTab(tab)} />
              ))}
            </div>
          )}

          {/* 탭별 내용 */}
          <div>
            {renderTabContent()}
          </div>
          <div className="flex justify-end mr-6 mt-10">
              <Button color="white" size="s" disabled={false} text="채팅하기" type="button" onClick={() => {/* 채팅 */ }} />
              <Button
                  color="theme"
                  size="s"
                  disabled={application.isPassed}
                  text={application.isPassed ? "합격 완료" : "합격"}
                  type="button"
                  onClick={handlePass}
              />
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
            onClick={fetchApplicationDetail}
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