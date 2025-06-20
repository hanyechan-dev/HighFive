import { useEffect, useState } from "react";
import { usePagination } from "../../../common/customHooks/usePagination";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import type { JobPostingSummary } from "../props/JobPostingProps";
import { JobPostingListApi, JobPostingDeleteApi } from "../apis/JobPostingApi";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../common/components/PageTitle";
import CompanyEmptyState from "../../common/components/CompanyEmptyState";
import JobPostingListHeader from "../components/JobPostingListHeader";
import JobPostingSummaryRow from "../components/JobPostingSummaryRow";
import Button from "../../../common/components/button/Button";
import Pagination from "../../../common/components/pagination/Pagination";
import JobPostingUpdateModal from "../modals/JobPostingUpdateModal";
import JobPostingCreateModal from "../modals/JobPostingCreateModal";

// 임시 mock 데이터 (백엔드 DTO 구조에 맞춤)
const mockJobPostings: JobPostingSummary[] = [
  {
    id: 1,
    title: "프론트엔드 개발자 모집",
    companyName: "테크컴퍼니",
    type: "대기업",
    job: "프론트엔드 개발자",
    workLocation: "서울특별시",
    careerType: "1~3년",
    educationLevel: "학사",
    createdDate: "2024-01-15"
  },
  {
    id: 2,
    title: "백엔드 개발자 모집",
    companyName: "스타트업",
    type: "중소기업",
    job: "백엔드 개발자",
    workLocation: "서울특별시",
    careerType: "신입",
    educationLevel: "석사",
    createdDate: "2024-01-10"
  }
];

export default function JobPostingManagementPage() {
  const [jobPostings, setJobPostings] = useState<JobPostingSummary[]>(mockJobPostings);
  const [totalElements, setTotalElements] = useState(0);
  const [selectedId, setSelectedId] = useState<number | null>(null);
  const [isCreateOpen, setCreateOpen] = useState(false);
  const [isEditOpen, setEditOpen] = useState(false);
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

  useEffect(() => {
    const fetchJobPostings = async () => {
      setIsLoading(true);
      try {
        const res = await JobPostingListApi(clickedPage - 1, 10);
        if (res && res.content) {
          setJobPostings(res.content);
          setTotalElements(res.totalElements);
        } else {
          setJobPostings(mockJobPostings);
          setTotalElements(mockJobPostings.length);
        }
      } catch (err) {
        printErrorInfo(err);
        setJobPostings(mockJobPostings);
        setTotalElements(mockJobPostings.length);
      } finally {
        setIsLoading(false);
      }
    };
    fetchJobPostings();
  }, [clickedPage]);

  const handleEdit = (id: number) => {
    setSelectedId(id);
    setEditOpen(true);
  };

  const handleDelete = async (id: number) => {
    if (!window.confirm('정말 삭제하시겠습니까?')) return;
    try {
      await JobPostingDeleteApi(id);
      setJobPostings(prev => prev.filter(j => j.id !== id));
      setTotalElements(prev => prev - 1);
    } catch (err) {
      printErrorInfo(err);
      alert('삭제에 실패했습니다.');
    }
  };

  const handleCreate = () => {
    setCreateOpen(true);
  };

  const handleModalSuccess = () => {
    setEditOpen(false);
    setCreateOpen(false);

  };

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto font-roboto">
        <div className="flex items-center justify-between mb-8">
          <PageTitle
            title="채용공고 관리"
            description="등록된 채용공고를 관리하세요"
          />
          
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
          <div className="text-center py-12 text-gray-400">로딩 중...</div>
        ) : (
          <>
            <JobPostingListHeader />
            {jobPostings.length === 0 ? (
              <CompanyEmptyState
                title="등록된 채용공고가 없습니다."
                text="우측 상단의 + 작성 버튼을 눌러 채용공고를 등록해보세요."
              />
            ) : (
              jobPostings.map((job) => (
                <JobPostingSummaryRow
                  key={job.id}
                  job={job}
                  onEdit={handleEdit}
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
          <JobPostingUpdateModal
            isOpen={isEditOpen}
            onClose={() => setEditOpen(false)}
            jobPostingId={selectedId}
            onSuccess={handleModalSuccess}
          />
        )}

        <JobPostingCreateModal
          isOpen={isCreateOpen}
          onClose={() => setCreateOpen(false)}
          onSuccess={handleModalSuccess}
        />
      </div>
    </CommonPage>
  );
} 