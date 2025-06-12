
import Button from "../../common/components/button/Button";
import ModalTitle from "../../common/components/title/ModalTitle";
import CommonPage from "../../common/pages/CommonPage";
import { useEffect, useState } from "react";
import RequestModal from "./RequestModal";
import RequestListTop from "./RequestListTop";
import RequestListItem from "./RequestListItem";
import type { requestSummaryProps } from "../../common/props/AiConsultingProps";
import EmptyState from "../../common/components/emptyState/EmptyState";
import Pagination from "../../common/components/pagination/Pagination";
import { usePagination } from "../../common/coustomHooks/usePagination";
import { readRequestsApi } from "../member/MemberApi";


const consultingType = '피드백'
const elementsPerPage = 10;
const pagesPerBlock = 10;






const FeedbackRequestPage = () => {

    const [totalElements, setTotalElements] = useState(0);
    const [requests, setRequests] = useState<requestSummaryProps[]>([]);

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
        const fetchData = async () => {
            try {
                const res = await readRequestsApi(clickedPage-1, elementsPerPage, consultingType);
                const totalElements = res?.data?.totalElements ?? 0;
                setTotalElements(totalElements);
                const requests = res?.data?.content ?? [];
                setRequests(requests);
            } catch (err) {
                console.error(err);
            }
        };
        fetchData();
    }, [clickedPage]);



    const [showModal, setShowModal] = useState(false);

    const onClick = () => {
        setShowModal(true);
    }

    const onClose = () => {
        setShowModal(false)
    }




    return (
        <>


            <CommonPage>
                <ModalTitle title={"AI + 컨설턴트 피드백"} />
                <div className="flex justify-end">
                    <Button color={"theme"} size={"m"} disabled={false} text={"새 피드백 요청하기"} type={"button"} onClick={onClick} />
                </div>
                <RequestListTop />
                <div className="mb-3">
                    {requests.length>0 ? requests.map(request =>
                        <RequestListItem request={request} key={request.id} />
                    ) : <EmptyState title={"요청 내역이 없습니다."} text={"요청을 등록해주세요."} />}
                </div>

                <div className="flex justify-center">
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

            </CommonPage>

            {showModal && <RequestModal onClose={onClose} consultingType={consultingType} />}

        </>
    )
};

export default FeedbackRequestPage;
