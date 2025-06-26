import { api } from "../../../common/Axios";
import type { ScheduleSummary, ScheduleDetail, ScheduleCreateRequest, ScheduleUpdateRequest } from "../props/ScheduleProps";

// 스케줄 목록 조회
export const ScheduleListApi = () => {
  return api(true).get<ScheduleSummary[]>('/schedules');
};

// 스케줄 상세 조회
export const ScheduleDetailApi = (id: number) => {
  return api(true).post<ScheduleDetail>('/schedules/detail', { id });
};

// 스케줄 생성
export const ScheduleCreateApi = (data: ScheduleCreateRequest) => {
  return api(true).post('/schedules', data);
};

// 스케줄 수정
export const ScheduleUpdateApi = (data: ScheduleUpdateRequest) => {
  return api(true).put('/schedules', data);
};

// 스케줄 삭제
export const ScheduleDeleteApi = (id: number) => {
  return api(true).post<void>('/schedules/deletion', { id });
}; 