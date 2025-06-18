import * as React from "react";
import { useParams, useNavigate } from "react-router-dom";
import ApplicationListHeader from "../components/ApplicationListHeader";
import ApplicationSummaryRow from "../components/ApplicationSummaryRow";
import type { ApplicationSummaryForCompany } from "../props/ApplicationProps";
import { ApplicationListApi } from "../apis/ApplicationApi";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../common/components/PageTitle";
import Pagination from "../../../common/components/pagination/Pagination";
import { usePagination } from "../../../common/customHooks/usePagination";
import CompanyEmptyState from "../../common/components/CompanyEmptyState";

export default function ApplicationPage() {
  const { jobPostingId } = useParams<{ jobPostingId: string }>();
  const [applications, setApplications] = React.useState<ApplicationSummaryForCompany[]>([]);
  const [totalElements, setTotalElements] = React.useState(0);
  const [isLoading, setIsLoading] = React.useState(false);
  const [error, setError] = React.useState<string | null>(null);
  const navigate = useNavigate();

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

  React.useEffect(() => {
    if (!jobPostingId) return;
    setIsLoading(true);
    setError(null);
    ApplicationListApi(Number(jobPostingId), clickedPage - 1, 10)
      .then(res => {
        setApplications(res.data.content || []);
        setTotalElements(res.data.totalElements || 0);
      })
      .catch(() => {
        setError("지원자 목록을 불러오지 못했습니다.");
        setApplications([]);
        setTotalElements(0);
      })
      .finally(() => setIsLoading(false));
  }, [jobPostingId, clickedPage]);

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto font-roboto">
        <div className="flex items-center justify-between mb-8">
          <PageTitle
            title="지원자 리스트"
            description="해당 채용공고에 지원한 지원자 목록입니다"
          />
          <button
            className="px-4 py-2 bg-theme text-white rounded"
            onClick={() => navigate(-1)}
          >
            ← 채용공고 목록으로
          </button>
        </div>
        {isLoading ? (
          <div className="text-center py-12 text-gray-400">로딩 중...</div>
        ) : error ? (
          <div className="text-center py-12 text-red-400">{error}</div>
        ) : applications.length === 0 ? (
          <CompanyEmptyState
            title="지원자가 없습니다."
            text="아직 지원자가 없습니다."
          />
        ) : (
          <>
            <ApplicationListHeader />
            {applications.map((app) => (
              <ApplicationSummaryRow key={app.id} application={app} />
            ))}
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
      </div>
    </CommonPage>
  );
} 