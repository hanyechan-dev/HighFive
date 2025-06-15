import { useState } from "react";
import { useDispatch } from 'react-redux';
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import Select from "../../../common/components/input/Select";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { careerTypeEnum, educationLevelEnum, regionEnum, salaryEnum } from "../../../common/enum/Enum";
import CommonModal from "../../../common/modals/CommonModal";
import { setJobPostingFilter } from "../slices/JobPostingSlice";



const JobPostingFilterModal = () => {
    const dispatch = useDispatch();
    const [showModal, setShowModal] = useState(true);
    const [careerType, setCareerType] = useState(careerTypeEnum[0].value);
    const [educationLevel, setEducationLevel] = useState(educationLevelEnum[0].value);
    const [workLocation, setWorkLocation] = useState(regionEnum[0].value);
    const [job, setJob] = useState('');
    const [salary, setSalary] = useState(salaryEnum[0].value);
    const onClose = () => { setShowModal(false) };



    const applyFilter = async (careerType: string, educationLevel: string, workLocation: string, job: string, salary: string) => {
        try {
            dispatch(setJobPostingFilter({careerType, educationLevel, workLocation, job, salary}));
            onClose();
        } catch (err) {
            console.error('로그인 실패:', err);
        }
    };



    if (!showModal) return null

    return (

        <CommonModal size="m" onClose={onClose} >
            <ModalTitle title={'채용공고 필터'} />
            <div className="flex">
                <Select label={'경력'} options={careerTypeEnum} size={"s"} disabled={false} value={careerType} setValue={setCareerType} />
                <Select label={'학력'} options={educationLevelEnum} size={"s"} disabled={false} value={educationLevel} setValue={setEducationLevel } />
            </div>
            <div className="flex">
                <Select label={'지역'} options={regionEnum} size={'s'} disabled={false} value={workLocation} setValue={setWorkLocation} />
                <Input label={'직무'} placeholder={''} size={'s'} disabled={false} type={'text'} value={job} setValue={setJob} />
            </div>
            
                <Select label={'연봉'} options={salaryEnum} size={'s'} disabled={false} value={salary} setValue={setSalary} />
                
                <Button color={"theme"} size={"m"} disabled={false} text={"필터 적용"} onClick={() => applyFilter(careerType, educationLevel, workLocation, job, salary)} type={"button"} />
            
        </CommonModal>

    );

};

export default JobPostingFilterModal;

