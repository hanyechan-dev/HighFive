import { useState, useEffect, type ChangeEvent } from "react";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import type { JobPostingUpdateRequest, JobPostingDetail, JobPostingSummary } from "../props/JobPostingProps";
import { JobPostingDetailApi } from "../apis/JobPostingApi";
import Select from "../../../common/components/input/Select";
import { educationLevelEnum, careerTypeEnum, companyTypeEnum } from "../../../common/enum/Enum";


interface JobPostingUpdateModalProps {
    isOpen: boolean;
    onClose: () => void;
    jobPostingId: number;
    onSuccess?: (updatedData: JobPostingSummary) => void;
}

export default function JobPostingUpdateModal({ isOpen, onClose, jobPostingId, onSuccess }: JobPostingUpdateModalProps) {
    const [saving, setSaving] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [formData, setFormData] = useState<JobPostingUpdateRequest>({
        id: jobPostingId,
        title: "",
        workingHours: "",
        workLocation: "",
        job: "",
        careerType: "",
        educationLevel: "",
        salary: 0,
        content: "",
        requirement: "",
        companyType: ""
    });
    const [images, setImages] = useState<File[]>([]);
    const [imagePreviews, setImagePreviews] = useState<string[]>([]);

    useEffect(() => {
        if (!isOpen || !jobPostingId) return;
        setLoading(true);
        setError(null);
        JobPostingDetailApi(jobPostingId)
            .then(res => {
                const data: JobPostingDetail = res.data;
                setFormData({
                    id: data.id,
                    title: data.title,
                    workingHours: data.workingHours,
                    workLocation: data.workLocation,
                    job: data.job,
                    careerType: data.careerType,
                    educationLevel: data.educationLevel,
                    salary: data.salary,
                    content: data.content,
                    requirement: data.requirement,
                    companyType: data.companyType
                });
                setImagePreviews(data.imageUrls || []);
                setImages([]);
            })
            .catch(() => setError("채용공고 정보를 불러오지 못했습니다."))
            .finally(() => setLoading(false));
    }, [isOpen, jobPostingId]);

    const handleImageChange = (e: ChangeEvent<HTMLInputElement>) => {
        if (!e.target.files) return;
        const files = Array.from(e.target.files);
        if (images.length + files.length > 5) {
            alert("최대 5개의 이미지만 업로드할 수 있습니다.");
            return;
        }
        setImages(prev => [...prev, ...files]);
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = (ev) => {
                setImagePreviews(prev => [...prev, ev.target?.result as string]);
            };
            reader.readAsDataURL(file);
        });
    };

    const handleImageDelete = (idx: number) => {
        setImages(prev => prev.filter((_, i) => i !== idx));
        setImagePreviews(prev => prev.filter((_, i) => i !== idx));
    };

    const handleSave = async () => {
        if (!formData.title || !formData.companyType || !formData.workingHours || !formData.workLocation || !formData.job || !formData.careerType || !formData.educationLevel || !formData.salary || !formData.content || !formData.requirement) {
            alert("모든 필수 항목을 입력해주세요.");
            return;
        }
        setSaving(true);
        setError(null);
        try {
            const updatedSummary: JobPostingSummary = {
                id: formData.id,
                title: formData.title,
                companyName: formData.companyType,
                type: formData.companyType,
                job: formData.job,
                workLocation: formData.workLocation,
                careerType: formData.careerType,
                educationLevel: formData.educationLevel,
                createdDate: new Date().toISOString().slice(0, 10)
            };
            onSuccess?.(updatedSummary);
            onClose();
        } catch (err) {
            setError("채용공고 수정에 실패했습니다.");
            printErrorInfo(err);
        } finally {
            setSaving(false);
        }
    };

    if (!isOpen) return null;
    if (loading) return <CommonModal size="l" onClose={onClose}><div className="p-8 text-center font-roboto">로딩 중...</div></CommonModal>;

    return (
        <CommonModal size="l" onClose={onClose}>
            <ModalTitle title="채용공고 수정" />
            <div className="flex gap-1 mb-4">
                <Input label="공고명" placeholder="공고명을 입력하세요" size="m" disabled={false} type="text" value={formData.title} setValue={v => setFormData(f => ({ ...f, title: v }))} />
                <Select label="기업형태" options={companyTypeEnum} size="m" disabled={false} value={formData.companyType || ""} setValue={v => setFormData(f => ({ ...f, companyType: v }))} />
            </div>
            <div className="flex gap-1 mb-4">
                <Input label="근무 시간" placeholder="근무 시간을 입력하세요" size="m" disabled={false} type="text" value={formData.workingHours} setValue={v => setFormData(f => ({ ...f, workingHours: v }))} />
                <Input label="근무지" placeholder="근무지를 입력하세요" size="m" disabled={false} type="text" value={formData.workLocation} setValue={v => setFormData(f => ({ ...f, workLocation: v }))} />
            </div>
            <div className="flex gap-1 mb-4">
                <Input label="모집 부문" placeholder="모집 부문을 입력하세요" size="m" disabled={false} type="text" value={formData.job} setValue={v => setFormData(f => ({ ...f, job: v }))} />
                <Select label="경력" options={careerTypeEnum} size="m" disabled={false} value={formData.careerType || ""} setValue={v => setFormData(f => ({ ...f, careerType: v }))} />
            </div>
            <div className="flex gap-1 mb-4">
                <Select label="학력" options={educationLevelEnum} size="m" disabled={false} value={formData.educationLevel || ""} setValue={v => setFormData(f => ({ ...f, educationLevel: v }))} />
                <Input label="급여" placeholder="급여를 입력하세요" size="m" disabled={false} type="text" value={formData.salary ? formData.salary.toString() : ""} setValue={v => setFormData(f => ({ ...f, salary: parseInt(v) || 0 }))} />
            </div>
            <TextArea label="내용" placeholder="공고 내용을 입력하세요" disabled={false} value={formData.content} setValue={v => setFormData(f => ({ ...f, content: v }))} size="l" />
            <TextArea label="자격 요건" placeholder="자격 요건을 입력하세요" disabled={false} value={formData.requirement} setValue={v => setFormData(f => ({ ...f, requirement: v }))} size="l" />
            <div className="mb-4">
                <div className="font-roboto text-base mb-2 ml-[24px]">채용공고 이미지</div>
                <div className="w-[952px] flex gap-4 items-center border border-gray-200 rounded-lg p-4 ml-[24px]">
                    {imagePreviews.map((url, idx) => (
                        <div key={idx} className="relative w-[180px] h-[120px] border rounded-lg overflow-hidden flex items-center justify-center bg-gray-100">
                            <img src={url} alt="preview" className="object-contain w-full h-full" />
                            <button type="button" className="absolute top-1 right-1 bg-white rounded-full px-2 py-1 text-xs shadow" onClick={() => handleImageDelete(idx)}>삭제</button>
                        </div>
                    ))}
                    {images.length + imagePreviews.length < 5 && (
                        <label className="w-[180px] h-[120px] border-2 border-dashed border-gray-300 rounded-lg flex flex-col items-center justify-center cursor-pointer bg-gray-50">
                            <span className="text-2xl text-gray-400 mb-2">📷</span>
                            <span className="text-gray-500 text-sm">이미지 추가</span>
                            <input type="file" accept="image/*" multiple style={{ display: 'none' }} onChange={handleImageChange} />
                        </label>
                    )}
                </div>
                <div className="w-[952px] text-xs text-gray-400 ml-[24px] mt-2">* 최대 5개의 이미지를 업로드할 수 있습니다.</div>
            </div>
            {error && (
                <div className="text-red-500 text-sm text-center mb-4">{error}</div>
            )}
            <div className="flex justify-end mr-6">
                <Button color="theme" size="s" disabled={saving} text={saving ? "수정 중..." : "수정하기"} type="button" onClick={handleSave} />
            </div>
        </CommonModal>
    );
} 