import { PageBox } from "../../../../common/components/box/Box"
import ModalTitle from "../../../../common/components/title/ModalTitle"
import PaymentListTop from "../../components/PaymentListTop"
import { usePaymentTabController } from "../../customHooks/PaymentTab/usePaymentTabController"
import EmptyState from "../../../../common/components/emptyState/EmptyState"
import { useEffect } from "react"
import { usePagination } from "../../../../common/customHooks/usePagination"
import { usePaymentTabApi } from "../../customHooks/PaymentTab/usePaymentTabApi"
import PaymentListItem from "../../components/PaymentListItem"
import Pagination from "../../../../common/components/pagination/Pagination"

const elementsPerPage = 10;
const pagesPerBlock = 10;


const PaymentTab = () => {

    const {
        paymentResponseDtos,
        totalElements,
    } = usePaymentTabController();

    const { readPayments } = usePaymentTabApi();

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

    useEffect(() => {
        readPayments(clickedPage - 1, elementsPerPage);
    }, [clickedPage]);

    return (
        <PageBox>
            <ModalTitle title={"결제내역"} />
            <PaymentListTop />

            {paymentResponseDtos.length > 0 ? paymentResponseDtos.map((paymentResponseDto) => (
                <PaymentListItem key={paymentResponseDto.paymentId} paymentResponseDto={paymentResponseDto} />
            )) : <EmptyState title={"결제 내역이 없습니다."} text={""} />}

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
                onClickPage={setClickedPage} />
        </PageBox>
    )
}

export default PaymentTab
