
import CommonModal from "../../../common/modals/CommonModal";
import { useJobPostingForMemberController } from "../customHooks/useJobPostingForMemberController";
import ResumeOutputForApplicationModal from "./ResumeOutputForApplicationModal";
import CareerDescriptionSelectForApplicationModal from "./CareerDescriptionSelectForApplicationModal";
import CoverLetterSelectForApplicationModal from "./CoverLetterSelectForApplicationModal";




const ApplicationModal = () =>{

    const {
        showModalNumber,
        setShowApplicationModal,
    } = useJobPostingForMemberController();

    const onClose = () =>{
        setShowApplicationModal(false);
    }

    if (showModalNumber < 0 || showModalNumber >= 3) return null;

    return(
        <CommonModal size="l" onClose={onClose}>
            {showModalNumber === 0 &&
                    (<ResumeOutputForApplicationModal  />)}
                {showModalNumber === 1 &&
                    (<CareerDescriptionSelectForApplicationModal />)}
                {showModalNumber === 2 &&
                    (<CoverLetterSelectForApplicationModal />)}
        </CommonModal>
    )

}
export default ApplicationModal;