
import CommonModal from "../../../common/modals/CommonModal";
import { useJobPostingForMemberController } from "../customHooks/useJobPostingForMemberController";
import ResumeOutputForApplicationModal from "./ResumeOutputForApplicationModal";
import CareerDescriptionSelectForApplicationModal from "./CareerDescriptionSelectForApplicationModal";
import CoverLetterSelectForApplicationModal from "./CoverLetterSelectForApplicationModal";

interface ApplicationModalProps {
    isShowModalNumber0Loading: boolean
    isShowModalNumber1Loading: boolean
    isShowModalNumber2Loading: boolean
    isCLLoading: boolean
    isCDLoading: boolean
}


const ApplicationModal = ({
    isShowModalNumber0Loading,
    isShowModalNumber1Loading,
    isShowModalNumber2Loading,
    isCLLoading,
    isCDLoading,
}: ApplicationModalProps) => {





    const {
        showModalNumber,
        setShowApplicationModal,
        setShowModalNumber,
        setClickedCareerDescriptionId,
        setClickedCoverLetterId,
    } = useJobPostingForMemberController();

    const onClose = () => {
        setShowApplicationModal(false);
        setShowModalNumber(-1)
        setClickedCareerDescriptionId(-1)
        setClickedCoverLetterId(-1)
    }



    return (
        <CommonModal size="l" onClose={onClose}>
            {showModalNumber === 0 &&
                (<ResumeOutputForApplicationModal
                    isShowModalNumber0Loading={isShowModalNumber0Loading} />)}
            {showModalNumber === 1 &&
                (<CareerDescriptionSelectForApplicationModal
                    isShowModalNumber1Loading={isShowModalNumber1Loading}
                    isCDLoading={isCDLoading} />)}
            {showModalNumber === 2 &&
                (<CoverLetterSelectForApplicationModal
                    isShowModalNumber2Loading={isShowModalNumber2Loading}
                    isCLLoading={isCLLoading}/>)}
        </CommonModal>
    )

}
export default ApplicationModal;