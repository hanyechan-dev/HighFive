import { useState } from "react";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import type { ScheduleCreateRequest } from "../props/ScheduleProps";
import { ScheduleCreateApi } from "../apis/ScheduleApi";

interface ScheduleCreateModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSuccess?: () => void;
  selectedDate?: string;
}

export default function ScheduleCreateModal({
  isOpen,
  onClose,
  onSuccess,
  selectedDate
}: ScheduleCreateModalProps) {
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [formData, setFormData] = useState<ScheduleCreateRequest>({
    title: "",
    content: "",
    date: selectedDate || new Date().toISOString().split('T')[0]
  });

  const handleSave = async () => {
    // 유효성 검사
    if (!formData.title || !formData.content || !formData.date) {
      alert("모든 필수 항목을 입력해주세요.");
      return;
    }

    if (formData.title.length > 30) {
      alert("제목은 최대 30자까지 입력 가능합니다.");
      return;
    }

    if (formData.content.length > 100) {
      alert("내용은 최대 100자까지 입력 가능합니다.");
      return;
    }

    setSaving(true);
    setError(null);
    try {
      await ScheduleCreateApi(formData);
      onSuccess?.();
      onClose();
      setFormData({
        title: "",
        content: "",
        date: selectedDate || new Date().toISOString().split('T')[0]
      });
    } catch (err) {
      setError("스케줄 등록에 실패했습니다.");
      printErrorInfo(err);
    } finally {
      setSaving(false);
    }
  };

  if (!isOpen) return null;

  return (
    <CommonModal size="m" onClose={onClose}>
      <ModalTitle title="스케줄 작성" />
      <div className="mb-4">
        <Input 
          label="제목" 
          placeholder="제목을 입력하세요 (최대 30자)" 
          size="m" 
          disabled={false} 
          type="text" 
          value={formData.title} 
          setValue={v => setFormData(f => ({ ...f, title: v }))} 
        />
      </div>
      <div className="mb-4">
        <Input 
          label="날짜" 
          placeholder="날짜를 선택하세요" 
          size="m" 
          disabled={false} 
          type="date" 
          value={formData.date} 
          setValue={v => setFormData(f => ({ ...f, date: v }))} 
        />
      </div>
      <div className="mb-4">
        <TextArea 
          label="내용" 
          placeholder="내용을 입력하세요 (최대 100자)" 
          disabled={false} 
          value={formData.content} 
          setValue={v => setFormData(f => ({ ...f, content: v }))} 
          size="m" 
        />
      </div>
      {error && (
        <div className="text-red-500 text-sm text-center mb-4">{error}</div>
      )}
      <div className="flex justify-end mr-6">
        <Button 
          color="theme" 
          size="s" 
          disabled={saving} 
          text={saving ? "등록 중..." : "작성하기"} 
          type="button" 
          onClick={handleSave} 
        />
      </div>
    </CommonModal>
  );
} 