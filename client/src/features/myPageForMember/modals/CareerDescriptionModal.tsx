import CommonModal from "../../../common/modals/CommonModal";
import { useDocumentTabApi } from "../customHooks/DocumentTab/useDocumentTabApi";
import { useDocumentTabController } from "../customHooks/DocumentTab/useDocumentTabController";
import CareerDescriptionCreateModal from "./CareerDescriptionCreateModal";
import CareerDescriptionDetailModal from "./CareerDescriptionDetailModal";
import CareerDescriptionUpdateModal from "./CareerDescriptionUpdateModal";

const CareerDescriptionModal = () => {

    const {
        careerDescriptionResponseDto,
        setShowModal,
        showModalType,
        setShowModalType,
        setClickedCareerDescriptionId,
        setCareerDescriptionSummaryDtos,
        careerDescriptionSummaryDtos
    } = useDocumentTabController();

    const { createCareerDescription, deleteCareerDescription, updateCareerDescription } = useDocumentTabApi();

    const onClose = () => {
        setShowModal(false);
        setShowModalType("detail");
        setClickedCareerDescriptionId(-1);
    }
    return (
        <CommonModal size={"l"} onClose={onClose}>
            {showModalType === "detail" &&
                <CareerDescriptionDetailModal
                careerDescriptionResponseDto={careerDescriptionResponseDto}
                setShowModalType={setShowModalType}
                onClickDelete={deleteCareerDescription}
                setShowModal={setShowModal}
                careerDescriptionSummaryDtos={careerDescriptionSummaryDtos} 
                setCareerDescriptionSummaryDtos={setCareerDescriptionSummaryDtos}/>}


            {showModalType === "update" &&
                <CareerDescriptionUpdateModal
                    careerDescriptionResponseDto={careerDescriptionResponseDto}
                    onUpdate={updateCareerDescription}
                    setShowModalType={setShowModalType} />}

            {showModalType === "create" &&
                <CareerDescriptionCreateModal
                    onCreate={createCareerDescription}
                    setShowModalType={setShowModalType} />}
        </CommonModal>
    )


};

export default CareerDescriptionModal;