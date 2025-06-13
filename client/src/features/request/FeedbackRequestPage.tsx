
import Button from "../../common/components/button/Button";
import ModalTitle from "../../common/components/title/ModalTitle";
import CommonPage from "../../common/pages/CommonPage";
import { useEffect, useState } from "react";
import RequestModal from "./RequestModal";
import RequestListTop from "./RequestListTop";
import RequestListItem from "./RequestListItem";
import EmptyState from "../../common/components/emptyState/EmptyState";
import Pagination from "../../common/components/pagination/Pagination";
import { usePagination } from "../../common/coustomHooks/usePagination";
import { createConsultantRequestApi, readCompletedRequestApi, readRequestApi, readRequestsApi } from "../member/MemberApi";
import type { CompletedRequestDetailDto, RequestDetailDto, RequestSummaryDto } from "./RequestProps";
import RequestDetailModal from "./RequestDetailModal";
import CompletedRequestDetailModal from "./CompletedRequestDetailModal";


const consultingType = '피드백'
const elementsPerPage = 10;
const pagesPerBlock = 10;




const FeedbackRequestPage = () => {

    const [totalElements, setTotalElements] = useState(0);
    const [requestSummaryDtos, setRequestSummaryDtos] = useState<RequestSummaryDto[]>([]);
    const [showModal, setShowModal] = useState(false);
    const [showDetailModal, setShowDetailModal] = useState(true);
    const [requestDetailDto, setRequestDetailDto] = useState<RequestDetailDto>();
    const [completedRequestDetailDto, setCompletedRequestDetailDto] = useState<CompletedRequestDetailDto>();
    const [isCompleted, setIsCompleted] = useState(false);

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
                const res = await readRequestsApi(clickedPage - 1, elementsPerPage, consultingType);
                const totalElements = res?.data?.totalElements ?? 0;
                setTotalElements(totalElements);
                const requests = res?.data?.content ?? [];
                setRequestSummaryDtos(requests);
            } catch (err) {
                console.error(err);
            }
        };
        fetchData();
    }, [clickedPage]);


    const onClickRequestModal = () => {
        setShowModal(true);
    }

    const onCloseRequestModal = () => {
        setShowModal(false)
    }

    const onClickRequestDetailModal = (id: number) => {

        const fetchData = async () => {
            try {
                const res = await readRequestApi(id);
                const content = res.data.content as RequestDetailDto;
                setRequestDetailDto(content);
            } catch (err) {
                console.error(err);
            }
        };

        const fetchCompletedData = async () => {
            try {
                const res = await readCompletedRequestApi(id);
                const content = res.data.content as CompletedRequestDetailDto;
                setCompletedRequestDetailDto(content);
            } catch (err) {
                console.error(err);
            }
        };



        if (requestSummaryDtos.find(item => item.id === id)?.requestStatus === "완료") {
            setIsCompleted(true);
            fetchCompletedData();
        } else {
            setIsCompleted(false);
            fetchData();
        }




        setShowDetailModal(true);
    }

    const onCloseRequestDetailModal = () => {
        setShowDetailModal(false);
    }

    const onClickRequestToConsultant = async (id: number) => {
        try{
            await createConsultantRequestApi(id);
            setShowDetailModal(false);

        }catch(err){
            console.error(err);
        }
    }




    return (
        <>


            <CommonPage>
                <ModalTitle title={"AI + 컨설턴트 피드백"} />
                <div className="flex justify-end">
                    <Button color={"theme"} size={"m"} disabled={false} text={"새 피드백 요청하기"} type={"button"} onClick={onClickRequestModal} />
                </div>
                <RequestListTop />
                <div className="mb-3">
                    {requestSummaryDtos.length > 0 ? requestSummaryDtos.map(requestSummaryDto =>
                        <RequestListItem requestSummaryDto={requestSummaryDto} key={requestSummaryDto.id} onClick={() => onClickRequestDetailModal(requestSummaryDto.id)} />
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

            {showModal && <RequestModal onClose={onCloseRequestModal} consultingType={consultingType} />}
            {showDetailModal && isCompleted && completedRequestDetailDto && (
                <CompletedRequestDetailModal completedRequestDetailDto={completedRequestDetailDto} onClose={onCloseRequestDetailModal} />
            )}

            {showDetailModal && !isCompleted && requestDetailDto && (
                <RequestDetailModal requestDetailDto={requestDetailDto} onClose={onCloseRequestDetailModal} onClick={() => onClickRequestToConsultant(requestDetailDto.requestResponseDto.id)} />
            )}

        </>
    )
};

export default FeedbackRequestPage;
