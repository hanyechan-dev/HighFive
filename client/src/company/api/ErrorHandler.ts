export const handleApiError = (error: any) => {
    if (error.response?.status === 404) {
        throw new Error("데이터를 찾을 수 없습니다.");
    }
    if (error.response?.status === 403) {
        throw new Error("권한이 없습니다. 기업 회원이 맞는지, 승인된 상태인지, 구독 상태인지 확인해주세요.");
    }
    throw error;
}; 