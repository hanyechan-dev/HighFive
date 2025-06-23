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
        setShowModalType
    } = useDocumentTabController();

    const { createCareerDescription, deleteCareerDescription, updateCareerDescription } = useDocumentTabApi();

    const onClose = () => {
        setShowModal(false);
        setShowModalType("detail");
    }
    return (
        <CommonModal size={"l"} onClose={onClose}>
            {showModalType === "detail" &&
                <CareerDescriptionDetailModal
                careerDescriptionResponseDto={careerDescriptionResponseDto}
                setShowModalType={setShowModalType}
                onClickDelete={deleteCareerDescription}
                setShowModal={setShowModal} />}


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