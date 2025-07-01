import { useState, useCallback, useEffect } from 'react';
import Button from '../../../common/components/button/Button';
import Input from '../../../common/components/input/Input';
import ModalTitle from '../../../common/components/title/ModalTitle';
import CommonModal from '../../../common/modals/CommonModal';

import MemberInfoBox from '../components/MemberInfoBox';
import ProposalCreateModal from './proposalCreateModal';
import type { MemberPoolDetail } from '../props/MemberPoolProps';
import { MemberPoolDetailApi } from '../apis/MemberPoolApi';
import { printErrorInfo } from '../../../common/utils/ErrorUtil';
import { TabButton } from '../../../common/components/button/TabButton';

const TABS = ['학력 사항', '경력 사항', '자격증', '어학'];

interface MemberPoolDetailModalProps {
  isOpen: boolean;
  onClose: () => void;
  memberId: number;
}

export default function MemberPoolDetailModal({
  isOpen,
  onClose,
  memberId,
}: MemberPoolDetailModalProps) {
  const [activeTab, setActiveTab] = useState(TABS[0]);
  const [detail, setDetail] = useState<MemberPoolDetail | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [isProposalOpen, setProposalOpen] = useState(false);

  const onCloseProposalCreateModal = () => {
    setProposalOpen(false);
  };

  const fetchDetail = useCallback(() => {
    if (memberId == null) return;
    setLoading(true);
    setError(null);
    MemberPoolDetailApi(memberId)
      .then((res) => {
        if (!res || !res.data) throw new Error('데이터를 불러올 수 없습니다.');
        setDetail(res.data);
      })
      .catch((err) => {
        printErrorInfo(err);
      })
      .finally(() => setLoading(false));
  }, [memberId]);

  useEffect(() => {
    if (isOpen && memberId) {
      fetchDetail();
    }
  }, [memberId]);

  if (!isOpen) return null;

  // 각 탭별 내용
  const renderTabContent = () => {
    if (!detail) return null;

    if (activeTab === '학력 사항' && detail.educationResponseDto) {
      const edu = detail.educationResponseDto;
      return (
        <>
          <div className="flex">
            <Input
              label="학교명"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={edu.schoolName ?? ''}
              setValue={() => {}}
            />
            <Input
              label="전공"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={edu.major ?? ''}
              setValue={() => {}}
            />
          </div>
          <div className="flex">
            <Input
              label="학위"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={edu.educationLevel ?? ''}
              setValue={() => {}}
            />
            <Input
              label="학점"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={edu.gpa?.toString() ?? ''}
              setValue={() => {}}
            />
          </div>
          <div className="flex">
            <Input
              label="소재지"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={edu.location ?? ''}
              setValue={() => {}}
            />
            <div className="flex items-end">
              <Input
                label="재학 기간"
                placeholder=""
                size="s"
                disabled
                type="text"
                value={edu.enterDate ?? ''}
                setValue={() => {}}
              />
              <Input
                label=""
                placeholder=""
                size="s"
                disabled
                type="text"
                value={edu.graduateDate ?? ''}
                setValue={() => {}}
              />
            </div>
          </div>
        </>
      );
    }
    if (activeTab === '경력 사항' && detail.careerResponseDto) {
      const career = detail.careerResponseDto;
      return (
        <>
          <div className="flex">
            <Input
              label="회사명"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={career.companyName ?? ''}
              setValue={() => {}}
            />
            <Input
              label="직무"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={career.job ?? ''}
              setValue={() => {}}
            />
          </div>
          <div className="flex">
            <Input
              label="부서"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={career.department ?? ''}
              setValue={() => {}}
            />
            <Input
              label="직급"
              placeholder=""
              size="m"
              disabled
              type="text"
              value={career.position ?? ''}
              setValue={() => {}}
            />
          </div>
          <div className="flex items-end">
            <Input
              label="근무기간"
              placeholder=""
              size="s"
              disabled
              type="text"
              value={career.startDate ?? ''}
              setValue={() => {}}
            />
            <Input
              label=""
              placeholder=""
              size="s"
              disabled
              type="text"
              value={career.endDate ?? ''}
              setValue={() => {}}
            />
          </div>
        </>
      );
    }
    if (activeTab === '자격증' && detail.certificationResponseDto) {
      const certifications = detail.certificationResponseDto;
      if (certifications.length === 0) return null;

      return (
        <>
          {certifications.map((cert, idx) => (
            <div key={cert.id || idx} className="mb-4">
              <div className="flex">
                <Input
                  label="자격증명"
                  placeholder=""
                  size="m"
                  disabled
                  type="text"
                  value={cert.certificationName ?? ''}
                  setValue={() => {}}
                />
                <Input
                  label="발급기관"
                  placeholder=""
                  size="m"
                  disabled
                  type="text"
                  value={cert.issuingOrg ?? ''}
                  setValue={() => {}}
                />
              </div>
              <div className="flex">
                <Input
                  label="취득일"
                  placeholder=""
                  size="m"
                  disabled
                  type="text"
                  value={cert.acquisitionDate ?? ''}
                  setValue={() => {}}
                />
              </div>
            </div>
          ))}
        </>
      );
    }

    if (activeTab === '어학' && detail.languageTestResponseDto) {
      const languages = detail.languageTestResponseDto;
      if (languages.length === 0) return null;

      return (
        <>
          {languages.map((lang, idx) => (
            <div key={lang.id || idx} className="mb-4">
              <div className="flex">
                <Input
                  label="언어"
                  placeholder=""
                  size="m"
                  disabled
                  type="text"
                  value={lang.languageType ?? ''}
                  setValue={() => {}}
                />
                <Input
                  label="시험명"
                  placeholder=""
                  size="m"
                  disabled
                  type="text"
                  value={lang.testName ?? ''}
                  setValue={() => {}}
                />
              </div>
              <div className="flex">
                <Input
                  label="점수"
                  placeholder=""
                  size="m"
                  disabled
                  type="text"
                  value={lang.score ?? ''}
                  setValue={() => {}}
                />
                <Input
                  label="취득일"
                  placeholder=""
                  size="m"
                  disabled
                  type="text"
                  value={lang.acquisitionDate ?? ''}
                  setValue={() => {}}
                />
              </div>
            </div>
          ))}
        </>
      );
    }
    return null;
  };

  return (
    <>
      <CommonModal size="l" onClose={onClose}>
        {loading ? (
          <div className="p-8 text-center font-roboto">로딩 중...</div>
        ) : error ? (
          <div className="p-8 text-center text-red-500">
            <p className="mb-4">{error}</p>
            <Button
              color="theme"
              size="s"
              text="다시 시도"
              disabled={false}
              type="button"
              onClick={() => {
                setError(null);
                if (memberId) {
                  fetchDetail();
                }
              }}
            />
          </div>
        ) : !detail ? (
          <div className="p-8 text-center text-gray-500 font-roboto">멤버 정보가 없습니다.</div>
        ) : (
          <>
            <ModalTitle title={`${detail.name} 님의 상세`} />
            <MemberInfoBox
              items={[
                { label: '이름', value: detail.name },
                { label: '이메일', value: detail.email },
                { label: '성별', value: detail.genderType },
                { label: '생년월일', value: detail.birthDate },
                { label: '연락처', value: detail.phone },
                { label: '직무', value: detail.job },
              ]}
            />
            {/* 탭 */}
            <div className="flex border-b mb-4 ml-6 w-[952px]">
              {TABS.map((tab) => (
                <TabButton
                  key={tab}
                  label={tab}
                  isActive={activeTab === tab}
                  onClick={() => setActiveTab(tab)}
                />
              ))}
            </div>
            {/* 탭별 내용 */}
            <div>{renderTabContent()}</div>
            {/* 하단 버튼 */}
            <div className="flex justify-end mr-6">
              <Button
                color="white"
                size="s"
                disabled={false}
                text="채팅하기"
                type="button"
                onClick={() => {
                  /* 채팅 */
                }}
              />
              <Button
                color="theme"
                size="s"
                disabled={false}
                text="채용 제안"
                type="button"
                onClick={() => setProposalOpen(true)}
              />
            </div>
          </>
        )}
      </CommonModal>

      <ProposalCreateModal
        isOpen={isProposalOpen}
        onClose={onCloseProposalCreateModal}
        memberId={memberId}
      />
    </>
  );
}
