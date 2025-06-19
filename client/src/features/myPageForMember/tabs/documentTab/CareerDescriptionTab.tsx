import { useEffect } from "react";
import Button from "../../../../common/components/button/Button";
import MyPageSelect from "../../components/MyPageSelect";
import { useDocumentTabApi } from "../../customHooks/DocumentTab/useDocumentTabApi";
import { useDocumentTabController } from "../../customHooks/DocumentTab/useDocumentTabController";
import CareerDescriptionModal from "../../modals/CareerDescriptionModal";
import EmptyState from "../../../../common/components/emptyState/EmptyState";


const CareerDescriptionTab = () => {



    const {
        clickedCareerDescriptionId,
        setClickedCareerDescriptionId,
        careerDescriptionSummaryDtos,
        showModal,
        setShowModal,
        setShowModalType
    } = useDocumentTabController();

    const {
        readCareerDescriptionResponseDto,
        readCareerDescriptionSummaryDtos,
    } = useDocumentTabApi();

    useEffect(() => {
        readCareerDescriptionSummaryDtos();
    }, [])

    const onClickList = (id: number) => {
        setClickedCareerDescriptionId(id);
        readCareerDescriptionResponseDto(id);
        setShowModal(true);
    };

    const onClickCreateButton = () => {
        setShowModal(true);
        setShowModalType("create");
    }




    return (
        <>
            <div>
                {careerDescriptionSummaryDtos.length > 0 ? (careerDescriptionSummaryDtos.map(careerDescriptionSummaryDto => (
                    <MyPageSelect
                        careerOrCoverLetterSummaryDto={careerDescriptionSummaryDto}
                        isClicked={clickedCareerDescriptionId === careerDescriptionSummaryDto.id}
                        onClickList={() => onClickList(careerDescriptionSummaryDto.id)} />
                ))) :
                    <EmptyState title={"등록된 경력기술서가 없습니다."} text={"경력기술서를 추가해주세요"} />
                }
                <div className="flex justify-end mr-6">
                    <Button color={"theme"} size={"m"} disabled={false} text={"경력기술서 추가"} type={"button"} onClick={onClickCreateButton} />
                </div>
            </div>

            {showModal && <CareerDescriptionModal />}
        </>
    )
}

export default CareerDescriptionTab
