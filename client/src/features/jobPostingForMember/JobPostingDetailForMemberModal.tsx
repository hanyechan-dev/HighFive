import { useState } from "react";
import CommonModal from "../../common/modals/CommonModal";
import ModalTitle from "../../common/components/title/ModalTitle";
import InfoBox from "../../common/components/infoBox/InfoBox";
import Input from "../../common/components/input/Input";
import TextArea from "../../common/components/input/TextArea";
import ImageOutputArea from "../../common/components/image/ImageOutputArea";
import Button from "../../common/components/button/Button";
import Badge from "../../common/components/badge/Badge";
import CareerTypeIcon from "../../common/icons/CareerTypeIcon";
import GraduationCapIcon from "../../common/icons/GraduationCapIcon";


interface JobPostingDetailForMemberModalProps {
    id: number;
    title: string;
    companyType: string;
    workingHours: string;
    workLocation: string;
    job: string;
    careerType: string;
    educationLevel: string;
    salary: number;
    content: string;
    requirement: string;
    imageUrls: string[];
}

const JobPostingDetailForMemberModal = ({
    id,
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
    imageUrls
}: JobPostingDetailForMemberModalProps) => {

    const [showModal, setShowModal] = useState(true);
    const onClick = (id:number) =>{

    }
    const onClose = () => { setShowModal(false) };



    if (!showModal) return null;

    const convertedSalary = `${salary /10000}만원`; 


    return (
        <CommonModal size={"l"} onClose={onClose} >
            <ModalTitle title={"채용공고 상세"} />
            <div className="flex justify-end">
                                <div className="flex mt-6 mr-6 gap-6 justify-end">
                <Badge icon={<CareerTypeIcon />} text={careerType} />
                <Badge icon={<GraduationCapIcon />} text={educationLevel} />
            </div>
                <InfoBox label={"등록 일자"} value={"2024-05-05"} />
                
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

            {imageUrls.map(imageUrl => {
               return <ImageOutputArea size={"l"} imageUrl={imageUrl} />
            })}
            

            <div className="flex justify-end pr-6">
                <Button color={"theme"} size={"s"} disabled={false} text={"지원"} type={"button"} onClick={()=>{onClick(id)}} />
            </div>

        </CommonModal>
    );

}

export default JobPostingDetailForMemberModal;