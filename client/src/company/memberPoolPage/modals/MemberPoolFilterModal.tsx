import { useState } from "react";
import { useDispatch } from "react-redux";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import Select from "../../../common/components/input/Select";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { educationLevelEnum, regionEnum } from "../../../common/enum/Enum";
import CommonModal from "../../../common/modals/CommonModal";
import { type MemberFilter, setMemberPoolFilter } from "../slices/MemberPoolSlice";


interface MemberFilterModalProps {
    isOpen: boolean;
    onClose: () => void;
}

const MemberFilterModal = ({ isOpen, onClose }: MemberFilterModalProps) => {
    const dispatch = useDispatch();
    const careerOptions = [
        { label: '경력', value: 'true' },
        { label: '신입', value: 'false' }
    ];
    const [hasCareer, setHasCareer] = useState<string>('');
    const [education, setEducation] = useState<string>('');
    const [address, setAddress] = useState('');
    const [job, setJob] = useState('');

    const applyFilter = () => {
        try {
            const newFilter: MemberFilter = {
                hasCareer : (hasCareer === 'true'),
                educationLevel : education,
                address : address,
                job : job.trim(),
            };


            dispatch(setMemberPoolFilter(newFilter));
            onClose();
        } catch (err) {
            console.error('필터 적용 실패:', err);
        }
    };

    if (!isOpen) return null;

    return (
        <CommonModal size="m" onClose={onClose}>
            <ModalTitle title={'인재 필터'} />
            <div className="flex">
                <Select
                    label={'경력'}
                    options={careerOptions}
                    size={'s'}
                    disabled={false}
                    value={hasCareer}
                    setValue={setHasCareer}
                />
                <Select label={'학력'}
                    options={educationLevelEnum}
                    size={'s'}
                    disabled={false}
                    value={education}
                    setValue={setEducation} />
            </div>
            <div className="flex">

                <Select label={'지역'}
                    options={regionEnum}
                    size={'s'}
                    disabled={false}
                    value={address}
                    setValue={setAddress} />

                <Input
                    label={'직무'}
                    placeholder={'직무 입력'}
                    size={'s'}
                    disabled={false}
                    type={'text'}
                    value={job}
                    setValue={setJob}
                />
            </div>
            <div className='flex justify-end mr-6' >
                <Button
                    color={"theme"}
                    size={"s"}
                    disabled={false}
                    text={"필터 적용"}
                    onClick={applyFilter}
                    type={"button"}
                />
            </div>

        </CommonModal>
    );
};

export default MemberFilterModal;