import { useState } from 'react';
import CommonModal from '../../common/modals/CommonModal';
import ModalTitle from '../../common/components/title/ModalTitle';
import Input from '../../common/components/input/Input';
import Button from '../../common/components/button/Button';
import { createProposalApi } from './MemberPoolApi';
import TextArea from '../../common/components/input/TextArea';

interface ProposalCreateModalProps {
    isOpen: boolean;
    onClose: () => void;
    memberId: number;
}

export default function ProposalCreateModal({ isOpen, onClose, memberId }: ProposalCreateModalProps) {
    if (!isOpen) return null;
    const [proposalTitle, setProposalTitle] = useState('');
    const [proposalContent, setProposalContent] = useState('');
    const [proposalJob, setProposalJob] = useState('');
    const [proposalSalary, setProposalSalary] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [success, setSuccess] = useState(false);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setError(null);
        setSuccess(false);
        try {
            await createProposalApi({
                memberId,
                proposalTitle,
                proposalContent,
                proposalJob,
                proposalSalary: Number(proposalSalary),
            });
            setSuccess(true);
            setProposalTitle('');
            setProposalContent('');
            setProposalJob('');
            setProposalSalary('');
            setTimeout(() => {
                setSuccess(false);
                onClose();
            }, 1200);
        } catch (err: any) {
            setError(err?.message || '제안 전송에 실패했습니다.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <CommonModal size="m" onClose={onClose}>
            <form onSubmit={handleSubmit}>
                <ModalTitle title="채용 제안 작성" />
                <Input
                    label="제목"
                    value={proposalTitle}
                    setValue={setProposalTitle}
                    placeholder="제목을 입력하세요"
                    size="m"
                    disabled={loading}
                    type="text"
                />
                <TextArea size={'m'}
                 label={'제안 내용'} 
                 placeholder={'제안 내용을 입력하세요'} 
                 disabled={loading} 
                 value={proposalContent} 
                 setValue={setProposalContent}/>

                <div className='flex'>
                <Input
                    label="제안 직무"
                    value={proposalJob}
                    setValue={setProposalJob}
                    placeholder="직무를 입력하세요"
                    size="s"
                    disabled={loading}
                    type="text"
                />
                <Input
                    label="제안 연봉(만원)"
                    value={proposalSalary}
                    setValue={setProposalSalary}
                    placeholder="연봉을 입력하세요"
                    size="s"
                    disabled={loading}
                    type="text"
                />
                </div>
                {error && <div className='flex justify-end mr-6'>{error}</div>}
                {success && <div className='flex'>제안이 성공적으로 전송되었습니다!</div>}
               <div className='flex justify-end mr-6'>
                   <Button type="submit" disabled={loading} text={loading ? '전송 중...' : '제안 보내기'} color="theme" size="s" />
               </div>
            </form>
        </CommonModal>
    );
}

