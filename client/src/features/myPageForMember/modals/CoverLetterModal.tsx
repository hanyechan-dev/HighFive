import CommonModal from "../../../common/modals/CommonModal";
import { useDocumentTabApi } from "../customHooks/DocumentTab/useDocumentTabApi";
import { useDocumentTabController } from "../customHooks/DocumentTab/useDocumentTabController";
import CoverLetterCreateModal from "./CoverLetterCreateModal";
import CoverLetterDetailModal from "./CoverLetterDetailModal";
import CoverLetterUpdateModal from "./CoverLetterUpdateModal";

const CoverLetterModal = () => {

    const {
        coverLetterResponseDto,
        setShowModal,
        showModalType,
        setShowModalType
    } = useDocumentTabController();

    const { createCoverLetter, deleteCoverLetter, updateCoverLetter } = useDocumentTabApi();

    const onClose = () => {
        setShowModal(false);
        setShowModalType("detail");
    }
    return (
        <CommonModal size={"l"} onClose={onClose}>
            {showModalType === "detail" &&
                <CoverLetterDetailModal
                    coverLetterResponseDto={coverLetterResponseDto}
                    setShowModalType={setShowModalType}
                    onClickDelete={deleteCoverLetter} />}


            {showModalType === "update" &&
                <CoverLetterUpdateModal
                    coverLetterResponseDto={coverLetterResponseDto}
                    onUpdate={updateCoverLetter}
                    setShowModalType={setShowModalType} />}

            {showModalType === "create" &&
                <CoverLetterCreateModal
                    onCreate={createCoverLetter}
                    setShowModalType={setShowModalType} />}
        </CommonModal>
    )


};

export default CoverLetterModal;