import Badge from "../../../common/components/badge/Badge";
import Button from "../../../common/components/button/Button";
import ImageOutputArea from "../../../common/components/image/ImageOutputArea";
import InfoBox from "../../../common/components/infoBox/InfoBox";
import Input from "../../../common/components/input/Input";
import TextArea from "../../../common/components/input/TextArea";
import LoadingSpinner from "../../../common/components/loading/LoadingSpinner";
import ModalTitle from "../../../common/components/title/ModalTitle";
import CareerTypeIcon from "../../../common/icons/CareerTypeIcon";
import CompanyTypeIcon from "../../../common/icons/CompanyTypeIcon";
import GraduationCapIcon from "../../../common/icons/GraduationCapIcon";
import CommonModal from "../../../common/modals/CommonModal";
import { useJobPostingForMemberController } from "../customHooks/useJobPostingForMemberController";

interface JobPostingDetailForMemberModalProps {
    isJobPostingModalLoading: boolean
}

const JobPostingDetailForMemberModal = ({ isJobPostingModalLoading }: JobPostingDetailForMemberModalProps) => {

    const {
        jobPostingForMemberResponseDto,
        setShowJobPostingForMemberDetailModal,
        setShowApplicationModal,
        setShowModalNumber,
    } = useJobPostingForMemberController();


    const {
        companyName,
        title,
        companyType,
        workingHours,
        workLocation,
        job,
        careerType,
        educationLevel,
        salary,
        content,
        requirement,
        imageUrls,
        createdDate,
        expiredDate
    } = jobPostingForMemberResponseDto

    const onClickApply = () => {
        setShowModalNumber(0);
        setShowApplicationModal(true);
        setShowJobPostingForMemberDetailModal(false)
    }




    const convertedSalary = `${salary}만원`;


    return (
        <>
            <CommonModal size={"l"} onClose={() => { setShowJobPostingForMemberDetailModal(false) }}>
                <ModalTitle title={"채용공고 상세"} />
                {isJobPostingModalLoading ? (<LoadingSpinner message="채용 공고를 불러오는 중..." size="lg" color="theme" />) : (
                    <>
                        <div className="flex justify-between ml-6">
                            <InfoBox label={"기업명"} value={companyName} />

                            <div className="flex">
                                <InfoBox label={"등록 일자"} value={createdDate} />
                                <InfoBox label={"만료 일자"} value={expiredDate} />
                            </div>
                        </div>

                        <Input label={"공고명"} placeholder={""} size={"l"} disabled={true} type={"text"} value={title} setValue={() => { }} />

                        <div className="flex">
                            <Input label={"근무 시간"} placeholder={""} size={"m"} disabled={true} type={"text"} value={workingHours} setValue={() => { }} />
                            <Input label={"근무지"} placeholder={""} size={"m"} disabled={true} type={"text"} value={workLocation} setValue={() => { }} />
                        </div>

                        <div className="flex">
                            <Input label={"모집 부문"} placeholder={""} size={"m"} disabled={true} type={"text"} value={job} setValue={() => { }} />
                            <Input label={"급여"} placeholder={""} size={"m"} disabled={true} type={"text"} value={convertedSalary} setValue={() => { }} />
                        </div>

                        <div className="flex justify-end mr-6 mb-6 gap-6">
                            <Badge icon={<CareerTypeIcon />} text={careerType} />
                            <Badge icon={<GraduationCapIcon />} text={educationLevel} />
                            <Badge icon={<CompanyTypeIcon />} text={companyType} />
                        </div>

                        <div className="flex justify-center">
                            <div className="w-[952px] border mb-6">

                            </div>
                        </div>


                        <TextArea label={"공고 내용"} placeholder={""} disabled={true} value={content} setValue={() => { }} size={"l"} />

                        <TextArea label={"자격 요건"} placeholder={""} disabled={true} value={requirement} setValue={() => { }} size={"l"} />

                        {imageUrls.map((imageUrl, index) => (
                            <ImageOutputArea key={index} size={"l"} imageUrl={imageUrl} />
                        ))}


                        <div className="flex justify-end pr-6">
                            <Button color={"theme"} size={"s"} disabled={false} text={"지원하기"} type={"button"} onClick={onClickApply} />
                        </div>
                    </>
                )}

            </CommonModal>
        </>
    );

}

export default JobPostingDetailForMemberModal;