import Badge from "../../../common/components/badge/Badge";
import Button from "../../../common/components/button/Button";
import ImageOutputArea from "../../../common/components/image/ImageOutputArea";
import InfoBox from "../../../common/components/infoBox/InfoBox";
import Input from "../../../common/components/input/Input";
import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import CareerTypeIcon from "../../../common/icons/CareerTypeIcon";
import GraduationCapIcon from "../../../common/icons/GraduationCapIcon";
import CommonModal from "../../../common/modals/CommonModal";
import type { JobPostingForMemberResponseDto } from "../props/JobPostingForMemberProps";



interface JobPostingDetailForMemberModalProps {
    jobPostingForMemberResponseDto: JobPostingForMemberResponseDto;
    onClick: (id: number) => void;
    onClose: () => void;
}

const JobPostingDetailForMemberModal = ({ jobPostingForMemberResponseDto, onClick, onClose }: JobPostingDetailForMemberModalProps) => {
    const {
        id,
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




    const convertedSalary = `${salary / 10000}만원`;


    return (
        <CommonModal size={"l"} onClose={onClose} >
            <ModalTitle title={"채용공고 상세"} />
            <div className="flex justify-end">
                <InfoBox label={"기업명"} value={companyName} />
                <InfoBox label={"등록 일자"} value={createdDate} />
                <InfoBox label={"만료 일자"} value={expiredDate} />
                <div className="flex mt-6 mr-6 gap-6 justify-end">
                    <Badge icon={<CareerTypeIcon />} text={careerType} />
                    <Badge icon={<GraduationCapIcon />} text={educationLevel} />
                </div>


            </div>

            <Input label={"공고명"} placeholder={""} size={"l"} disabled={true} type={"text"} value={title} setValue={() => { }} />

            <div className="flex">
                <Input label={"기업 형태"} placeholder={""} size={"m"} disabled={true} type={"text"} value={companyType} setValue={() => { }} />
                <Input label={"근무 시간"} placeholder={""} size={"m"} disabled={true} type={"text"} value={workingHours} setValue={() => { }} />
            </div>

            <div className="flex">
                <Input label={"근무지"} placeholder={""} size={"m"} disabled={true} type={"text"} value={workLocation} setValue={() => { }} />
                <Input label={"모집 부문"} placeholder={""} size={"m"} disabled={true} type={"text"} value={job} setValue={() => { }} />
            </div>

            <div className="flex">
                <Input label={"급여"} placeholder={""} size={"m"} disabled={true} type={"text"} value={convertedSalary} setValue={() => { }} />
            </div>



            <TextArea label={"공고 내용"} placeholder={""} disabled={true} value={content} setValue={() => { }} size={"l"} />

            <TextArea label={"자격 요건"} placeholder={""} disabled={true} value={requirement} setValue={() => { }} size={"l"} />

            {imageUrls.map(imageUrl => (
                <ImageOutputArea size={"l"} imageUrl={imageUrl} />
            ))}


            <div className="flex justify-end pr-6">
                <Button color={"theme"} size={"s"} disabled={false} text={"지원하기"} type={"button"} onClick={() => onClick(id)} />
            </div>

        </CommonModal>
    );

}

export default JobPostingDetailForMemberModal;