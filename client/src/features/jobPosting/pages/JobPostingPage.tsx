import { useEffect, useState, useCallback } from 'react';
import { usePagination } from '../../../common/customHooks/usePagination';
import { printErrorInfo } from '../../../common/utils/ErrorUtil';
import type { JobPostingSummary } from '../props/JobPostingProps';
import { JobPostingListApi, JobPostingDeleteApi } from '../apis/JobPostingApi';
import CommonPage from '../../../common/pages/CommonPage';
import EmptyState from '../../../common/components/emptyState/EmptyState';
import JobPostingListHeader from '../components/JobPostingListHeader';
import JobPostingSummaryRow from '../components/JobPostingSummaryRow';
import Button from '../../../common/components/button/Button';
import Pagination from '../../../common/components/pagination/Pagination';
import JobPostingDetailModal from '../modals/JobPostingDetailModal';
import JobPostingUpdateModal from '../modals/JobPostingUpdateModal';
import JobPostingCreateModal from '../modals/JobPostingCreateModal';
import LoadingSpinner from '../../../common/components/loading/LoadingSpinner';
import PageTitle from '../../../common/components/title/PageTitle';

const JobPostingPage = () => {
  const [jobPostings, setJobPostings] = useState<JobPostingSummary[]>([]);
  const [totalElements, setTotalElements] = useState(0);
  const [selectedId, setSelectedId] = useState<number | null>(null);
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const [isDetailModalOpen, setIsDetailModalOpen] = useState(false);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const {
    clickedPage,
    setClickedPage,
    lastPage,
    pageBlockIndex,
    lastPageBlockIndex,
    onClickFirst,
    onClickPrev,
    onClickNext,
    onClickLast,
  } = usePagination({
    totalElements,
    elementsPerPage: 10,
    pagesPerBlock: 10,
  });

  const fetchJobPostings = useCallback(async () => {
    setIsLoading(true);
    try {
      const res = await JobPostingListApi(clickedPage - 1, 10);
      if (res && res.data.content) {
        setJobPostings(res.data.content);
        setTotalElements(res.data.totalElements);
      } else {
        setJobPostings([]);
        setTotalElements(0);
      }
    } catch (err) {
      printErrorInfo(err);
      setJobPostings([]);
      setTotalElements(0);
    } finally {
      setIsLoading(false);
    }
  }, [clickedPage]);

  useEffect(() => {
    fetchJobPostings();
  }, [fetchJobPostings]);

  const handleDetail = (id: number) => {
    setSelectedId(id);
    setIsDetailModalOpen(true);
  };

  const handleDelete = async (id: number) => {
    if (!window.confirm('정말 삭제하시겠습니까?')) return;
    try {
      await JobPostingDeleteApi(id);
      setJobPostings((prev) => prev.filter((j) => j.id !== id));
      setTotalElements((prev) => prev - 1);
    } catch (err) {
      printErrorInfo(err);
      alert('삭제에 실패했습니다.');
    }
  };

  const handleCreate = () => {
    setIsCreateModalOpen(true);
  };

  const handleModalSuccess = () => {
    setIsCreateModalOpen(false);
    setIsEditModalOpen(false);

    // 모달이 닫힐 때 데이터를 다시 불러옴
    fetchJobPostings();
  };

  const handleEditFromDetail = (id: number) => {
    setSelectedId(id);
    setIsDetailModalOpen(false);
    setIsEditModalOpen(true);
  };

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto font-roboto">
        <div className="flex items-center justify-between mb-8">
          <PageTitle title="채용공고 관리" description="등록된 채용공고를 관리하세요" />
        </div>
        <div className="flex justify-end">
          <Button
            color="theme"
            size="s"
            disabled={false}
            text="+작성"
            type="button"
            onClick={handleCreate}
          />
        </div>
        {isLoading ? (
          <LoadingSpinner message="채용공고를 불러오는 중..." />
        ) : (
          <>
            <JobPostingListHeader />
            {jobPostings.length === 0 ? (
              <EmptyState
                title="등록된 채용공고가 없습니다."
                text="우측 상단의 + 작성 버튼을 눌러 채용공고를 등록해보세요."
              />
            ) : (
              jobPostings.map((job) => (
                <JobPostingSummaryRow
                  key={job.id}
                  job={job}
                  onDetail={handleDetail}
                  onDelete={handleDelete}
                />
              ))
            )}
          </>
        )}
        <div className="mt-8 flex justify-center">
          <Pagination
            currentPageBlockIndex={pageBlockIndex}
            lastPageBlockIndex={lastPageBlockIndex}
            pagesPerBlock={10}
            lastPage={lastPage}
            clickedPage={clickedPage}
            onClickFirst={onClickFirst}
            onClickPrev={onClickPrev}
            onClickNext={onClickNext}
            onClickLast={onClickLast}
            onClickPage={setClickedPage}
          />
        </div>

        {/* 모달들 */}
        {selectedId && (
          <JobPostingDetailModal
            isOpen={isDetailModalOpen}
            onClose={() => setIsDetailModalOpen(false)}
            jobPostingId={selectedId}
            onEdit={handleEditFromDetail}
          />
        )}

        {selectedId && (
          <JobPostingUpdateModal
            isOpen={isEditModalOpen}
            onClose={() => setIsEditModalOpen(false)}
            jobPostingId={selectedId}
            onSuccess={handleModalSuccess}
          />
        )}

        <JobPostingCreateModal
          isOpen={isCreateModalOpen}
          onClose={() => setIsCreateModalOpen(false)}
          onSuccess={handleModalSuccess}
        />
      </div>
    </CommonPage>
  );
};

export default JobPostingPage;
