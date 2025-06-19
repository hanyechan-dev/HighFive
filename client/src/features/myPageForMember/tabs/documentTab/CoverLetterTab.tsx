import { useEffect } from "react";
import Button from "../../../../common/components/button/Button";
import MyPageSelect from "../../components/MyPageSelect";
import { useDocumentTabApi } from "../../customHooks/DocumentTab/useDocumentTabApi";
import { useDocumentTabController } from "../../customHooks/DocumentTab/useDocumentTabController";
import CoverLetterModal from "../../modals/CoverLetterModal";
import EmptyState from "../../../../common/components/emptyState/EmptyState";


const CoverLetterTab = () => {



    const {
        clickedCoverLetterId,
        setClickedCoverLetterId,
        coverLetterSummaryDtos,
        showModal,
        setShowModal,
        setShowModalType
    } = useDocumentTabController();

    const {
        readCoverLetterResponseDto,
        readCoverLetterSummaryDtos,
    } = useDocumentTabApi();

    useEffect(() => {
        readCoverLetterSummaryDtos();
    }, [])

    const onClickList = (id: number) => {
        setClickedCoverLetterId(id);
        readCoverLetterResponseDto(id);
        setShowModal(true);
    };

    const onClickCreateButton = () => {
        setShowModal(true);
        setShowModalType("create");
    }




    return (
        <>
            <div>
                {coverLetterSummaryDtos.length > 0 ? (coverLetterSummaryDtos.map(coverLetterSummaryDto => (
                    <MyPageSelect
                        careerOrCoverLetterSummaryDto={coverLetterSummaryDto}
                        isClicked={clickedCoverLetterId === coverLetterSummaryDto.id}
                        onClickList={() => onClickList(coverLetterSummaryDto.id)} />
                ))) :
                    <EmptyState title={"등록된 자기소개서가 없습니다."} text={"자기소개서를 추가해주세요"} />
                }
                <div className="flex justify-end mr-6">
                    <Button color={"theme"} size={"m"} disabled={false} text={"자기소개서 추가"} type={"button"} onClick={onClickCreateButton} />
                </div>
            </div>

            {showModal && <CoverLetterModal />}
        </>
    )
}

export default CoverLetterTab
