import { useEffect } from "react";
import { usePagination } from "../../../../common/customHooks/usePagination";
import { useApplicationTabApi } from "../../customHooks/ApplicationTap/useApplicationTabApi";
import { useApplicationTabController } from "../../customHooks/ApplicationTap/useApplicationTabController";
import { PageBox } from "../../../../common/components/box/Box";
import ModalTitle from "../../../../common/components/title/ModalTitle";
import ApplicationListTop from "../../components/ApplicationListTop";
import ApplicationListItem from "../../components/ApplicationListItem";
import Pagination from "../../../../common/components/pagination/Pagination";
import EmptyState from "../../../../common/components/emptyState/EmptyState";
import ApplicationDetailModal from "../../modals/ApplicationDetailModal";

const elementsPerPage = 10;
const pagesPerBlock = 10;


const ApplicationTab = () => {

    const {
        showModal,
        setShowModal,
        applicationSummaryForMemberDtos,
        applicationResponseDto,
        totalElements } = useApplicationTabController();

    const {
        readApplications,
        readApplication } = useApplicationTabApi();

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
        readApplications(clickedPage - 1, elementsPerPage);
    }, [clickedPage]);

    const onClickListItem = (id: number) => {
        readApplication(id);
        setShowModal(true);
    }

    return (
        <>
            <PageBox>
                <ModalTitle title="지원내역" />
                <ApplicationListTop />
                {applicationSummaryForMemberDtos.length > 0 ? applicationSummaryForMemberDtos.map((applicationSummaryDto) => (
                    <ApplicationListItem
                        key={applicationSummaryDto.id}
                        applicationSummaryDto={applicationSummaryDto}
                        onClick={onClickListItem}
                    />
                )) : <EmptyState title={"지원 내역이 없습니다."} text={""} />}
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

            {showModal && <ApplicationDetailModal
                applicationResponseDto={applicationResponseDto}
                onClose={() => setShowModal(false)} />}
        </>
    )
};

export default ApplicationTab


