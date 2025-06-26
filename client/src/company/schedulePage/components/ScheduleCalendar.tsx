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
    <div className="bg-white rounded-2xl shadow-2xl border border-gray-100 p-8 backdrop-blur-sm bg-gradient-to-br from-white to-gray-50/30">
      <style>
        {`
          /* 모던한 테마 색상 및 그라데이션 */
          .fc-theme-standard .fc-button-primary {
            background: linear-gradient(135deg, #EE57CD 0%, #FF6B9D 100%) !important;
            border: none !important;
            color: white !important;
            border-radius: 12px !important;
            font-weight: 600 !important;
            transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1) !important;
            box-shadow: 0 4px 15px rgba(238, 87, 205, 0.3) !important;
          }
          
          .fc-theme-standard .fc-button-primary:hover {
            background: linear-gradient(135deg, #d94bb8 0%, #e55a8a 100%) !important;
            transform: translateY(-2px) !important;
            box-shadow: 0 8px 25px rgba(238, 87, 205, 0.4) !important;
          }
          
          .fc-theme-standard .fc-button-primary:focus {
            box-shadow: 0 0 0 3px rgba(238, 87, 205, 0.2) !important;
            outline: none !important;
          }
          
          .fc-theme-standard .fc-button-primary:disabled {
            background: linear-gradient(135deg, #f8b3e8 0%, #ffc4d6 100%) !important;
            opacity: 0.6 !important;
            transform: none !important;
          }
          
          /* 오늘 날짜 하이라이트 - 그라데이션 효과 */
          .fc-day-today {
            background: linear-gradient(135deg, #FEEFFA 0%, #FFF5F8 100%) !important;
            border-radius: 12px !important;
            box-shadow: inset 0 0 0 2px rgba(238, 87, 205, 0.2) !important;
          }
          
          /* 이벤트 스타일링 - 모던한 디자인 */
          .fc-event {
            background: linear-gradient(135deg, #EE57CD 0%, #FF6B9D 100%) !important;
            border: none !important;
            border-radius: 8px !important;
            box-shadow: 0 2px 8px rgba(238, 87, 205, 0.3) !important;
            transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1) !important;
            font-weight: 500 !important;
            padding: 2px 6px !important;
          }
          
          .fc-event:hover {
            transform: translateY(-1px) !important;
            box-shadow: 0 4px 15px rgba(238, 87, 205, 0.4) !important;
          }
          
          /* 헤더 배경 - 그라데이션 */
          .fc-col-header {
            background: linear-gradient(135deg, #FEEFFA 0%, #FFF5F8 100%) !important;
            border-radius: 12px 12px 0 0 !important;
            border-bottom: 2px solid rgba(238, 87, 205, 0.1) !important;
          }
          
          /* 요일 헤더 텍스트 - 모던한 타이포그래피 */
          .fc-col-header-cell {
            color: #EE57CD !important;
            font-weight: 700 !important;
            font-size: 14px !important;
            letter-spacing: 0.5px !important;
            padding: 16px 8px !important;
          }
          
          /* 제목 스타일링 */
          .fc-toolbar-title {
            color: #1a1a1a !important;
            font-weight: 700 !important;
            font-size: 24px !important;
            letter-spacing: -0.5px !important;
            background: linear-gradient(135deg, #EE57CD 0%, #FF6B9D 100%) !important;
            -webkit-background-clip: text !important;
            -webkit-text-fill-color: transparent !important;
            background-clip: text !important;
          }
          
          /* 날짜 칸 스타일링 */
          .fc-daygrid-day {
            min-height: 120px !important;
            cursor: pointer !important;
            border-radius: 8px !important;
            transition: all 0.2s cubic-bezier(0.4, 0, 0.2, 1) !important;
            margin: 2px !important;
          }
          
          .fc-daygrid-day:hover {
            background: linear-gradient(135deg, rgba(238, 87, 205, 0.05) 0%, rgba(255, 107, 157, 0.05) 100%) !important;
            transform: scale(1.02) !important;
          }
          
          .fc-daygrid-day-frame {
            min-height: 120px !important;
            cursor: pointer !important;
            border-radius: 8px !important;
            border: 1px solid rgba(238, 87, 205, 0.1) !important;
            transition: all 0.2s cubic-bezier(0.4, 0, 0.2, 1) !important;
          }
          
          /* 날짜 숫자 스타일링 */
          .fc-daygrid-day-top {
            padding: 12px 12px 8px 12px !important;
            cursor: pointer !important;
            font-weight: 600 !important;
            font-size: 16px !important;
            color: #374151 !important;
          }
          
          /* 이벤트 영역 */
          .fc-daygrid-day-events {
            min-height: 60px !important;
            cursor: pointer !important;
            padding: 4px 8px !important;
          }
          
          /* 이벤트 텍스트 */
          .fc-event-title {
            font-weight: 500 !important;
            font-size: 12px !important;
            line-height: 1.3 !important;
          }
          
          /* 툴바 스타일링 */
          .fc-toolbar {
            margin-bottom: 24px !important;
            padding: 16px 8px 0 8px !important;
            align-items: center !important;
          }
          
          /* 이전/다음 버튼 정렬 조정 */
          .fc-toolbar-chunk:first-child,
          .fc-toolbar-chunk:last-child {
            display: flex !important;
            align-items: center !important;
            transform: translateY(4px) !important;
          }
          
          /* 중앙 제목 정렬 */
          .fc-toolbar-chunk:nth-child(2) {
            display: flex !important;
            align-items: center !important;
          }
          
          /* 캘린더 전체 컨테이너 */
          .fc {
            border-radius: 16px !important;
            overflow: hidden !important;
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1) !important;
          }
          
          /* 스크롤바 스타일링 */
          .fc-scroller::-webkit-scrollbar {
            width: 8px !important;
          }
          
          .fc-scroller::-webkit-scrollbar-track {
            background: #f1f1f1 !important;
            border-radius: 4px !important;
          }
          
          .fc-scroller::-webkit-scrollbar-thumb {
            background: linear-gradient(135deg, #EE57CD 0%, #FF6B9D 100%) !important;
            border-radius: 4px !important;
          }
          
          .fc-scroller::-webkit-scrollbar-thumb:hover {
            background: linear-gradient(135deg, #d94bb8 0%, #e55a8a 100%) !important;
          }
          
          /* 애니메이션 효과 */
          @keyframes fadeInUp {
            from {
              opacity: 0;
              transform: translateY(20px);
            }
            to {
              opacity: 1;
              transform: translateY(0);
            }
          }
          
          .fc-daygrid-day {
            animation: fadeInUp 0.6s cubic-bezier(0.4, 0, 0.2, 1) !important;
          }
          
          /* 포커스 상태 */
          .fc-daygrid-day:focus {
            outline: 2px solid rgba(238, 87, 205, 0.3) !important;
            outline-offset: 2px !important;
          }
        `}
      </style>
      <FullCalendar
        ref={calendarRef}
        plugins={[dayGridPlugin, interactionPlugin]}
        initialView="dayGridMonth"
        headerToolbar={{
          left: 'prev',
          center: 'title',
          right: 'next'
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
        fixedWeekCount={false}
        dayCellContent={(arg) => {
          return (
            <div className="fc-daygrid-day-top">
              <span className="fc-daygrid-day-number">{arg.dayNumberText}</span>
            </div>
          );
        }}
      />
    </div>
  );
} 