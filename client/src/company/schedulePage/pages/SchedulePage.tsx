import { useEffect, useState } from "react";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import type { ScheduleSummary, CalendarEvent } from "../props/ScheduleProps";
import { ScheduleListApi } from "../apis/ScheduleApi";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../common/components/PageTitle";
import CompanyEmptyState from "../../common/components/CompanyEmptyState";
import Button from "../../../common/components/button/Button";
import ScheduleCalendar from "../components/ScheduleCalendar";
import ScheduleCreateModal from "../modals/ScheduleCreateModal";
import ScheduleUpdateModal from "../modals/ScheduleUpdateModal";
import { mockSchedules } from "../../common/mockData/CompanyMockData";

const SchedulePage = () => {
  const [schedules, setSchedules] = useState<ScheduleSummary[]>(mockSchedules);
  const [calendarEvents, setCalendarEvents] = useState<CalendarEvent[]>([]);
  const [selectedId, setSelectedId] = useState<number | null>(null);
  const [selectedDate, setSelectedDate] = useState<string>("");
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    fetchSchedules();
  }, []);

  useEffect(() => {
    // 스케줄 데이터를 FullCalendar 이벤트 형식으로 변환
    const events: CalendarEvent[] = schedules.map(schedule => ({
      id: schedule.id.toString(),
      title: schedule.title,
      start: schedule.date,
      allDay: true,
      extendedProps: {
        content: ""
      }
    }));
    setCalendarEvents(events);
  }, [schedules]);

  const fetchSchedules = async () => {
    setIsLoading(true);
    try {
      const res = await ScheduleListApi();
      if (res && res.data) {
        setSchedules(res.data);
      } else {
        setSchedules(mockSchedules);
      }
    } catch (err) {
      printErrorInfo(err);
      // API 호출 실패 시 목데이터 사용
      setSchedules(mockSchedules);
    } finally {
      setIsLoading(false);
    }
  };

  const handleDateClick = (dateStr: string) => {
    setSelectedDate(dateStr);
    setIsCreateModalOpen(true);
  };

  const handleEventClick = (eventId: string) => {
    const scheduleId = parseInt(eventId);
    setSelectedId(scheduleId);
    setIsEditModalOpen(true);
  };

  const handleCreate = () => {
    setSelectedDate("");
    setIsCreateModalOpen(true);
  };

  const handleModalSuccess = () => {
    setIsEditModalOpen(false);
    setIsCreateModalOpen(false);
    fetchSchedules(); // 스케줄 목록 새로고침
  };

  return (
    <CommonPage>
      <div className="w-[1452px] mx-auto font-roboto">
        <div className="flex items-center justify-between mb-8">
          <PageTitle
            title="스케줄 관리"
            description="회사 일정을 관리하세요"
          />
        </div>
        <div className="flex justify-end">
          <Button
            color="theme"
            size="s"
            disabled={false}
            text="+작성"
            type="button"
            onClick={handleCreate}
          />
        </div>
        
        {/* 개선된 캘린더 섹션 */}
        <div className="relative">
          {/* 배경 장식 요소 */}
          <div className="absolute -top-10 -left-10 w-40 h-40 bg-gradient-to-br from-pink-200/20 to-purple-200/20 rounded-full blur-3xl"></div>
          <div className="absolute -bottom-10 -right-10 w-32 h-32 bg-gradient-to-br from-blue-200/20 to-cyan-200/20 rounded-full blur-3xl"></div>
          
          {isLoading ? (
            <div className="flex items-center justify-center py-20">
              <div className="relative">
                <div className="w-12 h-12 border-4 border-pink-200 border-t-pink-500 rounded-full animate-spin"></div>
                <div className="absolute inset-0 w-12 h-12 border-4 border-transparent border-t-purple-500 rounded-full animate-spin" style={{ animationDelay: '0.2s' }}></div>
              </div>
              <span className="ml-4 text-gray-600 font-medium">스케줄을 불러오는 중...</span>
            </div>
          ) : (
            <>
              {schedules.length === 0 ? (
                <div className="bg-white rounded-3xl shadow-xl border border-gray-100 p-12">
                  <CompanyEmptyState
                    title="등록된 스케줄이 없습니다."
                    text="우측 상단의 + 작성 버튼을 눌러 스케줄을 등록해보세요."
                  />
                </div>
              ) : (
                <ScheduleCalendar
                  events={calendarEvents}
                  onDateClick={handleDateClick}
                  onEventClick={handleEventClick}
                />
              )}
            </>
          )}
        </div>

        {/* 모달들 */}
        <ScheduleCreateModal
          isOpen={isCreateModalOpen}
          onClose={() => setIsCreateModalOpen(false)}
          onSuccess={handleModalSuccess}
          selectedDate={selectedDate}
        />

        {selectedId && (
          <ScheduleUpdateModal
            isOpen={isEditModalOpen}
            onClose={() => setIsEditModalOpen(false)}
            onSuccess={handleModalSuccess}
            scheduleId={selectedId}
          />
        )}
      </div>
    </CommonPage>
  );
};

export default SchedulePage; 