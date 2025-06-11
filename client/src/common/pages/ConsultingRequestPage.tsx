import { useState, useEffect } from "react";
import ConsultingList from "../../admin_client/components/list/ConsultingList";
import { consultingRequest, listClick, consultingApprve } from "../../features/ConsultingRequest/ConsultingRequestApi";
import EmptyState from "../components/emptyState/EmptyState";
import Pagination from "../components/pagination/Pagination";
import ModalTitle from "../components/title/ModalTitle";
import { usePagination } from "../coustomHooks/usePagination";
import AiConsultingEditDetailModal from "../modals/AiConsultingEditDetailModal";
import AiConsultingFeedbackDetailModal from "../modals/AiConsultingFeedbackDetailModal";
import type { aiConsultingDetailProps } from "../props/AiConsultingProps";
import CommonPage from "./CommonPage";


interface consultingProps {
    aiConsultingId: number;
    userName: string;
    targetJob: string;
    targetCompanyName: string;
    requestedDate: string;
    consultingType: string;
}

const elementsPerPage = 10;
const pagesPerBlock = 10;



const ConsultingRequestPage = () => {

    const [consultings, setConsultings] = useState<consultingProps[]>([]);
    const [totalElements, setTotalElements] = useState<number>(0);
    const [showAiFeedbackDetailModal, setShowAiFeedbackDetailModal] = useState(false);
    const [showAiEditDetailModal, setShowAiEditDetailModal] = useState(false);
    const [aiConsultingDetail, setAiConsultingDetail] = useState<aiConsultingDetailProps>();

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
                const res = await consultingRequest(clickedPage, elementsPerPage);
                setConsultings(res.data.content);
                setTotalElements(res.data.totalElements)
            } catch (err) {
                console.error(err);
            }
        }

        fetchData();

    }, [clickedPage]);

    const onClick = (id: number, consultingType: string) => {

        const fetchDataAndShowDetail = async () => {
            try {
                const res = await listClick(id);
                setAiConsultingDetail(res.data.content);

                if (consultingType === '첨삭') {
                    setShowAiFeedbackDetailModal(false);
                    setShowAiEditDetailModal(true);
                } else if (consultingType === '피드백') {
                    setShowAiEditDetailModal(false);
                    setShowAiFeedbackDetailModal(true);
                }


            } catch (err) {
                console.error(err);
            }
        }


        fetchDataAndShowDetail();
    }

    const onApprove = (id: number) => {
        const approve = async () => {
            try {
                await consultingApprve(id);
                setClickedPage(prev => prev);

            } catch (err) {
                console.error(err);
            }
        }
        approve();
    }


    return (

        <>

            <div className="ml-[200px]">
                <CommonPage>

                    <ModalTitle title="컨설팅 요청" />


                    <div className="grid grid-cols-6 items-center w-full border-b pb-3 pt-4 rounded-t-xl bg-gray-100 text-center text-sm font-semibold text-[#666] font-roboto">
                        <div>회원 이름</div>
                        <div>희망 부문</div>
                        <div>희망 기업</div>
                        <div>요청 일자</div>
                        <div>유형</div>
                        <div>승인</div>
                    </div>


                    {consultings.length > 0 ? (consultings.map((consulting) => (
                        <ConsultingList
                            consulting={consulting}
                            onApprove={onApprove}
                            onClick={onClick} />

                    ))) :
                        <EmptyState title={"요청이 없습니다."} text={"회원가입 및 로그인을 해주십시오."} />

                    }


                    <div className="flex justify-center mt-6">


                        <Pagination currentPageBlockIndex={pageBlockIndex}
                            lastPageBlockIndex={lastPageBlockIndex}
                            pagesPerBlock={pagesPerBlock}
                            lastPage={lastPage}
                            clickedPage={clickedPage}
                            onClickFirst={onClickFirst}
                            onClickPrev={onClickPrev}
                            onClickNext={onClickNext}
                            onClickLast={onClickLast}
                            onClickPage={setClickedPage} />
                    </div>

                </CommonPage>
            </div>


            {showAiEditDetailModal && aiConsultingDetail && (<AiConsultingEditDetailModal aiConsultingDetail={aiConsultingDetail} />)}
            {showAiFeedbackDetailModal && aiConsultingDetail && (<AiConsultingFeedbackDetailModal aiConsultingDetail={aiConsultingDetail} />)}

        </>

    )
};

export default ConsultingRequestPage;



