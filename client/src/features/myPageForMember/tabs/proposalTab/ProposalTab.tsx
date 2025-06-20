import { PageBox } from "../../../../common/components/box/Box"
import ModalTitle from "../../../../common/components/title/ModalTitle"
import ProposalListTop from "../../components/ProposalListTop"
import ProposalListItem from "../../components/ProposalListItem"
import { useProposalTapController } from "../../customHooks/ProposalTap/useProposalTapController";
import { useProposalTapApi } from "../../customHooks/ProposalTap/useProposalTapApi";
import { useEffect } from "react";
import { usePagination } from "../../../../common/customHooks/usePagination";
import Pagination from "../../../../common/components/pagination/Pagination";
import ProposalModal from "../../modals/ProposalModal";
import EmptyState from "../../../../common/components/emptyState/EmptyState";


const elementsPerPage = 10;
const pagesPerBlock = 10;

const ProposalTab = () => {

    const { showModal, setShowModal, proposalSummaryForMemberDtos, proposalResponseDto, totalElements } = useProposalTapController();

    const { readProposals, readProposal, updateProposal } = useProposalTapApi();

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
        readProposals(clickedPage - 1, elementsPerPage);
    }, [clickedPage]);

    const onClickListItem = (id: number) => {
        readProposal(id);
        setShowModal(true);
    }

    const onClickAccept = () => {
        updateProposal({
            ...proposalResponseDto,
            proposalStatus: "수락"
        });

        setShowModal(false);
    }

    const onClickReject = () => {
        updateProposal({
            ...proposalResponseDto,
            proposalStatus: "거절"
        });
        setShowModal(false);
    }

    return (
        <>
            <PageBox>
                <ModalTitle title="채용제안" />
                <ProposalListTop />
                {proposalSummaryForMemberDtos.length > 0 ? proposalSummaryForMemberDtos.map((proposalSummaryDto) => (
                    <ProposalListItem key={proposalSummaryDto.id} proposalSummaryDto={proposalSummaryDto} onClick={() => onClickListItem(proposalSummaryDto.id)} />
                )) : <EmptyState title={"채용 제안이 없습니다."} text={""}/>}

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

            {showModal && <ProposalModal 
            proposalResponseDto={proposalResponseDto} 
            onClose={() => setShowModal(false)} 
            onClickAccept={onClickAccept} 
            onClickReject={onClickReject} />}
        </>
    )
}

export default ProposalTab