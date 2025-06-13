"use client"

import { useState, useEffect } from "react"
import { type TalentDetail, TabType, TabLabels } from "../../types/talent-types"
import { InfoItem } from "./InfoItem"
import { CertificationCard } from "./CertificationCard"
import { LanguageCard } from "./languageCard"
import { TabButton } from "./TebButton"
import CommonModal from "../common/CommonModal"

// 더미 데이터
const DUMMY_TALENT: TalentDetail = {
  id: 100,
  name: "김인재",
  email: "talent@example.com",
  gender: "여성",
  birthDate: "1992-05-20",
  job: "UX 디자이너",
  hasCareer: true,
  phone: "010-1234-5678",
  education: {
    id: 1,
    schoolName: "서울대학교",
    educationLevel: "학사",
    major: "시각 디자인",
    gpa: 4.2,
    location: "서울",
    enterDate: "2011-03-01",
    graduateDate: "2015-02-28",
  },
  career: {
    id: 1,
    companyName: "네이버",
    job: "UX/UI 디자이너",
    department: "디자인 센터",
    position: "선임 디자이너",
    startDate: "2015-03-01",
    endDate: "2023-12-31",
  },
  certifications: [
    {
      id: 1,
      certificationName: "웹디자인 기능사",
      issuingOrg: "한국산업인력공단",
      grade: "합격",
      certificationNo: "WEB-12345",
      acquisitionDate: "2014-11-15",
    },
  ],
  languageTests: [
    {
      id: 1,
      languageType: "영어",
      testName: "TOEIC",
      issuingOrg: "ETS",
      score: "950점",
      acquisitionDate: "2016-03-01",
    },
  ],
}

interface TalentDetailModalProps {
  isOpen: boolean
  onClose: () => void
  talentId?: number
}

export default function TalentDetailModal({ isOpen, onClose, talentId }: TalentDetailModalProps) {
  const [talent, setTalent] = useState<TalentDetail | null>(null)
  const [isLoading, setIsLoading] = useState<boolean>(true)
  const [activeTab, setActiveTab] = useState<TabType>(TabType.EDUCATION)

  // 데이터 로딩 (실제로는 API 호출)
  useEffect(() => {
    if (isOpen) {
      setIsLoading(true)
      // 실제 구현에서는 API 호출
      const timer = setTimeout(() => {
        setTalent(DUMMY_TALENT)
        setIsLoading(false)
      }, 500)

      return () => clearTimeout(timer)
    } else {
      setTalent(null)
    }
  }, [isOpen, talentId])

  if (!isOpen) return null

  // 학력사항 렌더링
  const renderEducation = () => {
    if (!talent?.education) return <EmptyState message="등록된 학력 정보가 없습니다." />

    return (
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        <InfoItem label="학교명" value={talent.education.schoolName} />
        <InfoItem label="전공" value={talent.education.major} />
        <InfoItem label="학위" value={talent.education.educationLevel} />
        <InfoItem label="소재지" value={talent.education.location} />
        <InfoItem label="입학일" value={talent.education.enterDate} />
        <InfoItem label="졸업일" value={talent.education.graduateDate} />
      </div>
    )
  }

  // 경력사항 렌더링
  const renderCareer = () => {
    if (!talent?.career) return <EmptyState message="등록된 경력 정보가 없습니다." />

    return (
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        <InfoItem label="회사명" value={talent.career.companyName} />
        <InfoItem label="직무" value={talent.career.job} />
        <InfoItem label="부서" value={talent.career.department} />
        <InfoItem label="직위" value={talent.career.position} />
        <InfoItem label="시작일" value={talent.career.startDate} />
        <InfoItem label="종료일" value={talent.career.endDate || "재직중"} />
      </div>
    )
  }

  // 자격증 렌더링
  const renderCertifications = () => {
    if (!talent?.certifications || talent.certifications.length === 0) {
      return <EmptyState message="등록된 자격증 정보가 없습니다." />
    }

    return (
      <div className="space-y-4">
        {talent.certifications.map((cert) => (
          <CertificationCard key={cert.id} certification={cert} />
        ))}
      </div>
    )
  }

  // 어학 렌더링
  const renderLanguageTests = () => {
    if (!talent?.languageTests || talent.languageTests.length === 0) {
      return <EmptyState message="등록된 어학 정보가 없습니다." />
    }

    return (
      <div className="space-y-4">
        {talent.languageTests.map((lang) => (
          <LanguageCard key={lang.id} languageTest={lang} />
        ))}
      </div>
    )
  }

  // 탭 내용 렌더링
  const renderTabContent = () => {
    switch (activeTab) {
      case TabType.EDUCATION:
        return renderEducation()
      case TabType.CAREER:
        return renderCareer()
      case TabType.CERTIFICATION:
        return renderCertifications()
      case TabType.LANGUAGE:
        return renderLanguageTests()
      default:
        return null
    }
  }

  return (
    <CommonModal size="l" onClose={onClose}>
      <div className="px-6 pb-6">
        <div className="mb-6">
          <h2 className="text-lg font-bold">{talent ? `${talent.name} 님의 정보` : "인재 상세 정보"}</h2>
        </div>

        {/* 기본 정보 */}
        {!isLoading && talent && (
          <div className="mb-6">
            <h3 className="text-base font-medium mb-3">기본 정보</h3>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-2">
              <div className="flex items-center">
                <span className="text-gray-500 mr-2">이름:</span>
                <span>{talent.name}</span>
              </div>
              <div className="flex items-center">
                <span className="text-gray-500 mr-2">이메일:</span>
                <span className="text-blue-500">{talent.email}</span>
              </div>
              <div className="flex items-center">
                <span className="text-gray-500 mr-2">성별:</span>
                <span>{talent.gender}</span>
              </div>
              <div className="flex items-center">
                <span className="text-gray-500 mr-2">생년월일:</span>
                <span>{talent.birthDate}</span>
              </div>
              <div className="flex items-center">
                <span className="text-gray-500 mr-2">연락처:</span>
                <span>{talent.phone}</span>
              </div>
              <div className="flex items-center">
                <span className="text-gray-500 mr-2">직업:</span>
                <span>{talent.job}</span>
                <span className="ml-2 px-2 py-0.5 bg-pink-100 text-pink-600 text-xs rounded-full">
                  {talent.hasCareer ? "경력" : "신입"}
                </span>
              </div>
            </div>
          </div>
        )}

        {/* 탭 메뉴 */}
        {!isLoading && talent && (
          <div>
            <div className="grid grid-cols-4 gap-1 mb-4">
              {Object.values(TabType).map((tab) => (
                <TabButton
                  key={tab}
                  label={TabLabels[tab]}
                  isActive={activeTab === tab}
                  onClick={() => setActiveTab(tab)}
                />
              ))}
            </div>

            {/* 탭 내용 */}
            <div className="mt-4">{renderTabContent()}</div>
          </div>
        )}

        {/* 로딩 상태 */}
        {isLoading && (
          <div className="flex justify-center items-center h-40">
            <div className="animate-spin rounded-full h-8 w-8 border-t-2 border-b-2 border-pink-500"></div>
          </div>
        )}

        {/* 모달 푸터 */}
        {!isLoading && talent && (
          <div className="mt-6 pt-4 flex justify-end space-x-2 border-t border-gray-100">
            <button
              onClick={onClose}
              className="px-4 py-1 bg-white text-gray-700 border border-gray-300 rounded-md hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500 transition-colors"
            >
              취소하기
            </button>
            <button
              onClick={() => {}}
              className="px-4 py-1 bg-pink-500 text-white rounded-md hover:bg-pink-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-pink-500 transition-colors"
            >
              저장하기
            </button>
          </div>
        )}
      </div>
    </CommonModal>
  )
}

// 빈 상태 컴포넌트
function EmptyState({ message }: { message: string }) {
  return (
    <div className="flex flex-col items-center justify-center py-8 text-gray-500">
      <p className="text-center">{message}</p>
    </div>
  )
}
