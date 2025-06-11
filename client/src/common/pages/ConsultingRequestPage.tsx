import { useEffect, useState } from "react";
import ConsultingList, { type ConsultingType } from "../../admin_client/components/list/ConsultingList";
import Pagination from "../components/pagination/Pagination";
import ModalTitle from "../components/title/ModalTitle";
import { usePagination } from "../coustomHooks/usePagination";
import CommonPage from "./CommonPage";
import { consultingApprve, consultingRequest, listClick } from "../../features/ConsultingRequestPage/ConsultingRequestPageApi";
import EmptyState from "../components/emptyState/EmptyState";

interface consultingProps {
    aiConsultingId: number;
    userName: string;
    targetJob: string;
    targetCompanyName: string;
    requestedDate: string;
    consultingType: string;
}



const ConsultingRequestPage = () => {

    const [consultings, setConsultings] = useState<consultingProps[]>();
    const [totalElements, setTotalElements] = useState<number>(0);
    const [clickToggle, setClickToggle] = useState(false);

    useEffect(() => {

        const fetchData = async () => {
            try {
                const res = await consultingRequest();
                setConsultings(res.data.content);
                setTotalElements(res.data.totalElements)
            } catch (err) {
                console.error(err);
            }
        }

        fetchData();

    }, [clickToggle]);

    const elementsPerPage = 10;
    const pagesPerBlock = 10;

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

    const onClick = (id: number) => {


        const click = async () => {
            try{
                const res = await listClick(id);
                res.data.content;

            } catch (err) {
                console.error(err);
            }  
        }


        click();
    }

    const onApprove = (id: number) => {
        const approve = async () => {
            try {
                await consultingApprve(id);

            } catch (err) {
                console.error(err);
            }
        }
        approve();
        setClickToggle(!clickToggle)
    }


return (
    <div className="ml-[200px]">
        <CommonPage>

            <ModalTitle title="컨설팅 요청" />


            <div className="grid grid-cols-6 items-center w-full border-b pb-3 pt-4 text-center text-sm font-semibold text-[#666] bg-[#f5f5f5] font-roboto">
                <div>회원 이름</div>
                <div>희망 부문</div>
                <div>희망 기업</div>
                <div>요청 일자</div>
                <div>유형</div>
                <div>승인</div>
            </div>


            {consultings ? (consultings.map((consulting) => (
                <ConsultingList
                    key={consulting.aiConsultingId}
                    id={consulting.aiConsultingId}
                    userName={consulting.userName}
                    targetJob={consulting.targetJob}
                    targetCompanyName={consulting.targetCompanyName}
                    requestDate={consulting.requestedDate}
                    consultingType={consulting.consultingType as ConsultingType}
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

)
};

export default ConsultingRequestPage;
