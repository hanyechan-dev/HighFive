// 스케줄 관리 페이지에서 사용할 타입들 (백엔드 DTO 기반)

export interface ScheduleSummary {
  id: number;
  title: string;
  date: string;
}

export interface ScheduleDetail {
  id: number;
  title: string;
  content: string;
  date: string;
}

export interface ScheduleCreateRequest {
  title: string;
  content: string;
  date: string;
}

export interface ScheduleUpdateRequest {
  id: number;
  title: string;
  content: string;
  date: string;
}

// FullCalendar 이벤트 타입
export interface CalendarEvent {
  id: string;
  title: string;
  start: string;
  end?: string;
  allDay?: boolean;
  extendedProps?: {
    content?: string;
  };
} 