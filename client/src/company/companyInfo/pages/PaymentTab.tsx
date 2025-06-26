import { useEffect, useState } from "react";
import { getCompanyPaymentsApi } from "../apis/CompanyApi";
import type { PaymentResponseDto } from "../props/CompanyProps";
import { usePagination } from "../../../common/customHooks/usePagination";
import Pagination from "../../../common/components/pagination/Pagination";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import LoadingSpinner from "../../common/components/LoadingSpinner";

const elementsPerPage = 10;
const pagesPerBlock = 10;

const PaymentTab = () => {
  const [paymentResponseDtos, setPaymentResponseDtos] = useState<PaymentResponseDto[]>([]);
  const [totalElements, setTotalElements] = useState(0);
  const [isLoading, setIsLoading] = useState(false);

  const {
    clickedPage,
    pageBlockIndex,
    lastPage,
    lastPageBlockIndex,
    onClickFirst,
    onClickPrev,
    onClickNext,
    onClickLast,
    setClickedPage,
  } = usePagination({
    totalElements: totalElements,
    elementsPerPage: elementsPerPage,
    pagesPerBlock: pagesPerBlock,
  });

  const readPayments = async (page: number, size: number) => {
    setIsLoading(true);
    try {
      const response = await getCompanyPaymentsApi(page, size);
      setPaymentResponseDtos(response.data.content);
      setTotalElements(response.data.totalElements);
    } catch (err) {
      console.error("결제내역 조회 실패:", err);
      setPaymentResponseDtos([]);
      setTotalElements(0);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    readPayments(clickedPage - 1, elementsPerPage);
  }, [clickedPage]);

  return (
    <div className="w-full max-w-[1070px] mx-auto">
      <div className="mb-6">
        <h2 className="text-xl font-semibold text-gray-800 mb-2">결제내역</h2>
        <p className="text-gray-600">기업의 결제 내역을 확인하세요</p>
      </div>

      {/* 결제내역 헤더 */}
      <div className="grid grid-cols-4 items-center w-full border-b py-4 rounded-t-xl bg-gray-100 text-center text-sm font-semibold text-gray-600 font-roboto">
        <div>결제 금액</div>
        <div>결제 내역</div>
        <div>결제 수단</div>
        <div>결제 일자</div>
      </div>

      {/* 결제내역 목록 */}
      {isLoading ? (
        <LoadingSpinner message="결제내역을 불러오는 중..." />
      ) : paymentResponseDtos.length > 0 ? (
        <>
          {paymentResponseDtos.map((paymentResponseDto) => (
            <div key={paymentResponseDto.paymentId} className="grid grid-cols-4 items-center w-full border-b py-3 text-center font-roboto">
              <div className="font-medium">{paymentResponseDto.paymentAmount.toLocaleString()}원</div>
              <div className="text-gray-700">{paymentResponseDto.content}</div>
              <div className="text-gray-600">{paymentResponseDto.method}</div>
              <div className="text-gray-600">{new Date(paymentResponseDto.createdTime).toLocaleDateString()}</div>
            </div>
          ))}
          
          {/* 페이지네이션 */}
          <div className="flex justify-center mt-8">
            <Pagination
              currentPageBlockIndex={pageBlockIndex}
              lastPageBlockIndex={lastPageBlockIndex}
              pagesPerBlock={pagesPerBlock}
              lastPage={lastPage}
              clickedPage={clickedPage}
              onClickFirst={onClickFirst}
              onClickPrev={onClickPrev}
              onClickNext={onClickNext}
              onClickLast={onClickLast}
              onClickPage={setClickedPage}
            />
          </div>
        </>
      ) : (
        <EmptyState title="결제 내역이 없습니다." text="아직 결제 내역이 없습니다." />
      )}
    </div>
  );
};

export default PaymentTab; 