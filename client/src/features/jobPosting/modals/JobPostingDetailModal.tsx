import { useState, useEffect } from 'react';
import Button from '../../../common/components/button/Button';
import Input from '../../../common/components/input/Input';
import TextArea from '../../../common/components/input/TextArea';
import ModalTitle from '../../../common/components/title/ModalTitle';
import CommonModal from '../../../common/modals/CommonModal';
import Select from '../../../common/components/input/Select';
import { educationLevelEnum, careerTypeEnum, jobTypeEnum } from '../../../common/enum/Enum';

import type { JobPostingDetail } from '../props/JobPostingProps';
import { JobPostingDetailApi } from '../apis/JobPostingApi';
import LoadingSpinner from '../../../common/components/loading/LoadingSpinner';

interface JobPostingDetailModalProps {
  isOpen: boolean;
  onClose: () => void;
  jobPostingId: number;
  onEdit: (jobPostingId: number, data: JobPostingDetail) => void;
}

export default function JobPostingDetailModal({
  isOpen,
  onClose,
  jobPostingId,
  onEdit,
}: JobPostingDetailModalProps) {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [jobPosting, setJobPosting] = useState<JobPostingDetail | null>(null);

  useEffect(() => {
    if (!isOpen || !jobPostingId) return;
    setLoading(true);
    setError(null);
    JobPostingDetailApi(jobPostingId)
      .then((res) => {
        if (!res) throw new Error('데이터를 불러올 수 없습니다.');
        setJobPosting(res.data);
      })
      .catch(() => setError('채용공고 정보를 불러오지 못했습니다.'))
      .finally(() => setLoading(false));
  }, [isOpen, jobPostingId]);

  const handleEdit = () => {
    if (jobPosting) {
      // 기존 이미지가 있는 경우 알림창 표시
      if (jobPosting.imageUrls && jobPosting.imageUrls.length > 0) {
        const confirmed = window.confirm(
          '수정하기를 누르면 기존 이미지들이 모두 제거됩니다.\n새로 이미지를 선택하시겠습니까?',
        );
        if (!confirmed) return;
      }

      onEdit(jobPostingId, jobPosting);
      onClose();
    }
  };

  if (!isOpen) return null;
  if (loading) {
    return (
      <CommonModal size="l" onClose={onClose}>
        <div className="p-8 text-center font-roboto">
          <LoadingSpinner message="채용공고 정보를 불러오는 중..." />
        </div>
      </CommonModal>
    );
  }

  if (error || !jobPosting) {
    return (
      <CommonModal size="l" onClose={onClose}>
        <div className="p-8 text-center font-roboto">
          <div className="text-red-500 mb-4">{error}</div>
          <Button
            color="theme"
            size="s"
            text="닫기"
            type="button"
            onClick={onClose}
            disabled={false}
          />
        </div>
      </CommonModal>
    );
  }

  return (
    <CommonModal size="l" onClose={onClose}>
      <ModalTitle title="채용공고 상세보기" />

      <div className="flex gap-1 mb-4">
        <Input
          label="공고명"
          placeholder="공고명을 입력하세요"
          size="m"
          disabled={true}
          type="text"
          value={jobPosting.title}
          setValue={() => {}}
        />
      </div>
      <div className="flex gap-1 mb-4">
        <Input
          label="근무 시간"
          placeholder="근무 시간을 입력하세요"
          size="m"
          disabled={true}
          type="text"
          value={jobPosting.workingHours}
          setValue={() => {}}
        />
        <Input
          label="근무지"
          placeholder="근무지를 입력하세요"
          size="m"
          disabled={true}
          type="text"
          value={jobPosting.workLocation}
          setValue={() => {}}
        />
      </div>
      <div className="flex gap-1 mb-4">
        <Select
          label="모집 부문"
          options={jobTypeEnum}
          size="m"
          disabled={true}
          value={jobPosting.job || ''}
          setValue={() => {}}
        />
        <Select
          label="경력"
          options={careerTypeEnum}
          size="m"
          disabled={true}
          value={jobPosting.careerType || ''}
          setValue={() => {}}
        />
      </div>
      <div className="flex gap-1 mb-4">
        <Select
          label="학력"
          options={educationLevelEnum}
          size="m"
          disabled={true}
          value={jobPosting.educationLevel || ''}
          setValue={() => {}}
        />
        <Input
          label="급여"
          placeholder="급여를 입력하세요 (만원)"
          size="m"
          disabled={true}
          type="text"
          value={jobPosting.salary ? jobPosting.salary.toString() : ''}
          setValue={() => {}}
        />
      </div>

      <TextArea
        label="내용"
        placeholder="공고 내용을 입력하세요"
        disabled={true}
        value={jobPosting.content}
        setValue={() => {}}
        size="l"
      />
      <TextArea
        label="자격 요건"
        placeholder="자격 요건을 입력하세요"
        disabled={true}
        value={jobPosting.requirement}
        setValue={() => {}}
        size="l"
      />

      <div className="mb-4">
        <div className="font-roboto text-base mb-2 ml-[24px]">채용공고 이미지</div>
        <div className="w-[952px] flex gap-4 items-center border border-gray-200 rounded-lg p-4 ml-[24px]">
          {jobPosting.imageUrls && jobPosting.imageUrls.length > 0 ? (
            jobPosting.imageUrls.map((url, idx) => (
              <div
                key={idx}
                className="w-[180px] h-[120px] border rounded-lg overflow-hidden flex items-center justify-center bg-gray-100"
              >
                <img src={url} alt="preview" className="object-contain w-full h-full" />
              </div>
            ))
          ) : (
            <div className="w-[180px] h-[120px] border-2 border-dashed border-gray-300 rounded-lg flex flex-col items-center justify-center bg-gray-50">
              <span className="text-2xl text-gray-400 mb-2">📷</span>
              <span className="text-gray-500 text-sm">이미지 없음</span>
            </div>
          )}
        </div>
        <div className="w-[952px] text-xs text-gray-400 ml-[24px] mt-2">
          * 최대 5개의 이미지를 업로드할 수 있습니다.
        </div>
      </div>

      <div className="flex justify-end mr-6">
        <Button
          color="theme"
          size="s"
          text="수정하기"
          type="button"
          onClick={handleEdit}
          disabled={false}
        />
      </div>
    </CommonModal>
  );
}
