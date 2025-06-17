import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import ModalTitle from "../../common/components/title/ModalTitle.tsx";
import Input from "../../common/components/input/Input.tsx";
import Button from "../../common/components/button/Button.tsx";
import CommonModal from "../../common/modals/CommonModal.tsx";
import Select from '../../common/components/input/Select.tsx';
import { setMemberPoolFilter, type MemberFilter } from './MemberPoolSlice';
import { educationLevelEnum } from '../../common/enum/Enum.tsx';

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
            const newFilter: MemberFilter = {};
            if (hasCareer !== undefined) {
                newFilter.hasCareer = (hasCareer === 'true');
            }
            if (education.trim() !== '') {
                newFilter.educationLevel = education;
            }

            if (address.trim() !== '') {
                newFilter.address = address.trim();
            }
            if (job.trim() !== '') {
                newFilter.job = job.trim();
            }

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
                <Input
                    label={'지역'}
                    placeholder={'주소 입력'}
                    size={'s'}
                    disabled={false}
                    type={'text'}
                    value={address}
                    setValue={setAddress}
                />
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