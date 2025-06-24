import { useRef } from 'react';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import type { CalendarEvent } from '../props/ScheduleProps';

interface ScheduleCalendarProps {
  events: CalendarEvent[];
  onDateClick: (dateStr: string) => void;
  onEventClick: (eventId: string) => void;
}

export default function ScheduleCalendar({ events, onDateClick, onEventClick }: ScheduleCalendarProps) {
  const calendarRef = useRef<FullCalendar>(null);

  return (
    <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
      <style>
        {`
          /* 테마 색상 커스터마이징 */
          .fc-theme-standard .fc-button-primary {
            background-color: #EE57CD !important;
            border-color: #EE57CD !important;
            color: white !important;
          }
          
          .fc-theme-standard .fc-button-primary:hover {
            background-color: #d94bb8 !important;
            border-color: #d94bb8 !important;
          }
          
          .fc-theme-standard .fc-button-primary:focus {
            box-shadow: 0 0 0 0.2rem rgba(238, 87, 205, 0.25) !important;
          }
          
          .fc-theme-standard .fc-button-primary:disabled {
            background-color: #f8b3e8 !important;
            border-color: #f8b3e8 !important;
            opacity: 0.6 !important;
          }
          
          /* 오늘 날짜 하이라이트 */
          .fc-day-today {
            background-color: #FEEFFA !important;
          }
          
          /* 이벤트 색상 */
          .fc-event {
            background-color: #EE57CD !important;
            border-color: #EE57CD !important;
          }
          
          /* 헤더 배경 */
          .fc-col-header {
            background-color: #FEEFFA !important;
          }
          
          /* 요일 헤더 텍스트 */
          .fc-col-header-cell {
            color: #EE57CD !important;
            font-weight: 600 !important;
          }
          
          /* 제목 색상 */
          .fc-toolbar-title {
            color: #EE57CD !important;
            font-weight: 600 !important;
          }
          
          /* 날짜 칸 높이 조정 */
          .fc-daygrid-day {
            min-height: 120px !important;
          }
          
          .fc-daygrid-day-frame {
            min-height: 120px !important;
          }
          
          /* 날짜 숫자 위치 조정 */
          .fc-daygrid-day-top {
            padding: 8px 8px 4px 8px !important;
          }
          
          /* 이벤트 영역 여백 조정 */
          .fc-daygrid-day-events {
            margin-top: 4px !important;
            min-height: 60px !important;
          }
        `}
      </style>
      <FullCalendar
        ref={calendarRef}
        plugins={[dayGridPlugin, interactionPlugin]}
        initialView="dayGridMonth"
        headerToolbar={{
          left: 'prev,next',
          center: 'title',
          right: ''
        }}
        locale="ko"
        height="auto"
        aspectRatio={1.35}
        events={events}
        dateClick={(info) => {
          onDateClick(info.dateStr);
        }}
        eventClick={(info) => {
          onEventClick(info.event.id);
        }}
        eventDisplay="block"
        eventColor="#EE57CD"
        eventTextColor="#FFFFFF"
        dayMaxEvents={true}
        moreLinkClick="popover"
        selectable={true}
        selectMirror={true}
        weekends={true}
        editable={false}
        selectConstraint="businessHours"
        businessHours={{
          daysOfWeek: [1, 2, 3, 4, 5], // 월-금
          startTime: '09:00',
          endTime: '18:00',
        }}
        fixedWeekCount={false}
      />
    </div>
  );
} 