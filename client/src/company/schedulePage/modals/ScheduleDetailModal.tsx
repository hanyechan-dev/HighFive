import { useState, useEffect } from "react";
import Button from "../../../common/components/button/Button";
import Input from "../../../common/components/input/Input";
import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import type { ScheduleDetail } from "../props/ScheduleProps";
import { ScheduleDetailApi } from "../apis/ScheduleApi";

// 현재 날짜 기준으로 목데이터 생성
const getCurrentDate = () => {
  const now = new Date();
  const year = now.getFullYear();
  const month = now.getMonth() + 1;
  return { year, month };
};

const { year, month } = getCurrentDate();

// 상세 조회용 목데이터
const mockScheduleDetails: { [key: number]: ScheduleDetail } = {
  1: { id: 1, title: "팀 회의", content: "주간 업무 진행상황 공유 및 다음 주 계획 수립 회의입니다. 모든 팀원 참석 필수입니다.", date: `${year}-${month.toString().padStart(2, '0')}-01` },
  2: { id: 2, title: "면접 일정", content: "신입 개발자 면접 일정입니다. 기술 면접과 인성 면접을 진행합니다.", date: `${year}-${month.toString().padStart(2, '0')}-02` },
  3: { id: 3, title: "프로젝트 마감", content: "A 프로젝트 최종 마감일입니다. 모든 문서 제출 및 코드 리뷰 완료 필요합니다.", date: `${year}-${month.toString().padStart(2, '0')}-03` },
  4: { id: 4, title: "고객 미팅", content: "B사 고객과의 제품 데모 및 요구사항 논의 미팅입니다.", date: `${year}-${month.toString().padStart(2, '0')}-04` },
  5: { id: 5, title: "신입사원 교육", content: "신입사원 온보딩 교육 1일차입니다. 회사 소개 및 조직 문화 교육을 진행합니다.", date: `${year}-${month.toString().padStart(2, '0')}-05` },
  6: { id: 6, title: "월간 실적 회의", content: "이번 달 실적 점검 및 다음 달 목표 설정 회의입니다. 각 부서장 참석 필수입니다.", date: `${year}-${month.toString().padStart(2, '0')}-06` },
  7: { id: 7, title: "제품 출시 준비", content: "신제품 출시를 위한 최종 점검 및 마케팅 자료 준비 완료일입니다.", date: `${year}-${month.toString().padStart(2, '0')}-07` },
  8: { id: 8, title: "부서장 회의", content: "전사 부서장 회의입니다. 회사 전략 및 정책 논의를 진행합니다.", date: `${year}-${month.toString().padStart(2, '0')}-08` },
  9: { id: 9, title: "연말 정산", content: "연말 정산 관련 서류 제출 마감일입니다. 모든 직원 필수 제출입니다.", date: `${year}-${month.toString().padStart(2, '0')}-09` },
  10: { id: 10, title: "새해 계획 수립", content: "새해 사업 계획 수립 회의입니다. 각 부서별 목표 및 전략을 논의합니다.", date: `${year}-${month.toString().padStart(2, '0')}-10` },
  11: { id: 11, title: "시스템 점검", content: "전사 시스템 정기 점검 및 보안 업데이트를 진행합니다. 서비스 중단 예정입니다.", date: `${year}-${month.toString().padStart(2, '0')}-11` },
  12: { id: 12, title: "보안 교육", content: "전사 보안 교육을 진행합니다. 개인정보 보호 및 보안 정책에 대해 학습합니다.", date: `${year}-${month.toString().padStart(2, '0')}-12` },
  13: { id: 13, title: "분기별 실적 발표", content: "분기별 실적 발표 및 성과 공유 회의입니다. 전사원 참석 필수입니다.", date: `${year}-${month.toString().padStart(2, '0')}-13` },
  14: { id: 14, title: "팀 빌딩", content: "팀 빌딩 활동을 진행합니다. 회사 근처 레스토랑에서 저녁 식사 및 팀워크 활동을 합니다.", date: `${year}-${month.toString().padStart(2, '0')}-14` },
  15: { id: 15, title: "고객 만족도 조사", content: "고객 만족도 조사 결과 분석 및 개선 방안 논의 회의입니다.", date: `${year}-${month.toString().padStart(2, '0')}-15` },
  16: { id: 16, title: "신기술 세미나", content: "최신 기술 트렌드 세미나를 진행합니다. 외부 전문가 초청 강연이 있습니다.", date: `${year}-${month.toString().padStart(2, '0')}-16` },
  17: { id: 17, title: "인사 평가", content: "연말 인사 평가를 진행합니다. 각 직원별 성과 평가 및 피드백을 제공합니다.", date: `${year}-${month.toString().padStart(2, '0')}-17` },
  18: { id: 18, title: "예산 회의", content: "다음 해 예산 계획 수립 회의입니다. 각 부서별 예산 요청 및 검토를 진행합니다.", date: `${year}-${month.toString().padStart(2, '0')}-18` },
  19: { id: 19, title: "마케팅 전략 회의", content: "새해 마케팅 전략 수립 회의입니다. 브랜드 전략 및 캠페인 계획을 논의합니다.", date: `${year}-${month.toString().padStart(2, '0')}-19` },
  20: { id: 20, title: "제품 QA 테스트", content: "신제품 QA 테스트를 진행합니다. 품질 검증 및 버그 수정을 완료합니다.", date: `${year}-${month.toString().padStart(2, '0')}-20` }
};

interface ScheduleDetailModalProps {
  isOpen: boolean;
  onClose: () => void;
  scheduleId: number;
  onEdit?: () => void;
  onDelete?: () => void;
}

export default function ScheduleDetailModal({
  isOpen,
  onClose,
  scheduleId,
  onEdit,
  onDelete
}: ScheduleDetailModalProps) {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [schedule, setSchedule] = useState<ScheduleDetail | null>(null);

  useEffect(() => {
    if (isOpen && scheduleId) {
      fetchScheduleDetail();
    }
  }, [isOpen, scheduleId]);

  const fetchScheduleDetail = async () => {
    setLoading(true);
    try {
      const response = await ScheduleDetailApi(scheduleId);
      setSchedule(response.data);
    } catch (err) {
      printErrorInfo(err);
      // API 호출 실패 시 목데이터 사용
      const mockDetail = mockScheduleDetails[scheduleId];
      if (mockDetail) {
        setSchedule(mockDetail);
      } else {
        setError("스케줄 정보를 불러오는데 실패했습니다.");
      }
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = () => {
    if (window.confirm('정말 삭제하시겠습니까?')) {
      onDelete?.();
    }
  };

  if (!isOpen) return null;

  return (
    <CommonModal size="m" onClose={onClose}>
      <ModalTitle title="스케줄 상세" />
      {loading ? (
        <div className="text-center py-8 text-gray-400">로딩 중...</div>
      ) : error ? (
        <div className="text-red-500 text-center py-8">{error}</div>
      ) : schedule ? (
        <>
          <div className="mb-4">
            <Input
              label="제목"
              value={schedule.title}
              setValue={() => {}}
              disabled={true}
              size="m"
              type="text"
              placeholder=""
            />
          </div>
          <div className="mb-4">
            <Input
              label="날짜"
              value={schedule.date}
              setValue={() => {}}
              disabled={true}
              size="m"
              type="date"
              placeholder=""
            />
          </div>
          <div className="mb-6">
            <TextArea
              label="내용"
              value={schedule.content}
              setValue={() => {}}
              disabled={true}
              size="m"
              placeholder=""
            />
          </div>
          <div className="flex justify-end mr-6">
            {onEdit && (
              <Button
                color="theme"
                size="s"
                disabled={false}
                text="수정"
                type="button"
                onClick={onEdit}
              />
            )}
            {onDelete && (
              <Button
                color="action"
                size="s"
                disabled={false}
                text="삭제"
                type="button"
                onClick={handleDelete}
              />
            )}
          </div>
        </>
      ) : (
        <div className="text-center py-8 text-gray-400">스케줄 정보가 없습니다.</div>
      )}
    </CommonModal>
  );
} 