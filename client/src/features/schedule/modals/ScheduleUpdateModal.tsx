import { useState, useEffect } from "react";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import type { ScheduleUpdateRequest, ScheduleDetail } from "../props/ScheduleProps";
import { ScheduleUpdateApi, ScheduleDetailApi, ScheduleDeleteApi } from "../apis/ScheduleApi";

interface ScheduleUpdateModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSuccess?: () => void;
  scheduleId: number;
}

export default function ScheduleUpdateModal({
  isOpen,
  onClose,
  onSuccess,
  scheduleId
}: ScheduleUpdateModalProps) {
  const [saving, setSaving] = useState(false);
  const [deleting, setDeleting] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [formData, setFormData] = useState<ScheduleUpdateRequest>({
    id: scheduleId,
    title: "",
    content: "",
    date: ""
  });

  useEffect(() => {
    if (isOpen && scheduleId) {
      fetchScheduleDetail();
    }
  }, [isOpen, scheduleId]);

  const fetchScheduleDetail = async () => {
    setLoading(true);
    try {
      const response = await ScheduleDetailApi(scheduleId);
      const schedule: ScheduleDetail = response.data;
      setFormData({
        id: schedule.id,
        title: schedule.title,
        content: schedule.content,
        date: schedule.date
      });
    } catch (err) {
      printErrorInfo(err);
    } finally {
      setLoading(false);
    }
  };

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
      await ScheduleUpdateApi(formData);
      onSuccess?.();
      onClose();
    } catch (err) {
      setError("스케줄 수정에 실패했습니다.");
      printErrorInfo(err);
    } finally {
      setSaving(false);
    }
  };

  const handleDelete = async () => {
    if (!confirm("정말로 이 스케줄을 삭제하시겠습니까?")) {
      return;
    }

    setDeleting(true);
    setError(null);
    try {
      await ScheduleDeleteApi(scheduleId);
      onSuccess?.();
      onClose();
    } catch (err) {
      setError("스케줄 삭제에 실패했습니다.");
      printErrorInfo(err);
    } finally {
      setDeleting(false);
    }
  };

  if (!isOpen) return null;

  return (
    <CommonModal size="m" onClose={onClose}>
      <ModalTitle title="스케줄 수정" />
      {loading ? (
        <div className="text-center py-8 text-gray-400">로딩 중...</div>
      ) : (
        <>
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
          <div className="flex justify-between mr-6">
            <Button 
              color="action" 
              size="s" 
              disabled={deleting} 
              text={deleting ? "삭제 중..." : "삭제"} 
              type="button" 
              onClick={handleDelete} 
            />
            <Button 
              color="theme" 
              size="s" 
              disabled={saving} 
              text={saving ? "수정 중..." : "수정하기"} 
              type="button" 
              onClick={handleSave} 
            />
          </div>
        </>
      )}
    </CommonModal>
  );
} 