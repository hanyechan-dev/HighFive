import axios from "axios";

export const printErrorInfo = (err: unknown) => {
    console.error(err);
    if (axios.isAxiosError(err)) {
        const message = err.response?.data?.message || err.message;
        console.error("[Axios(통신) 오류] : ", message);
        alert(message);
    }
    else if (err instanceof Error) {
        console.error("[일반 오류] : ", err.message);
    }
    else {
        console.error("[알 수 없는 에러] : ", err);
    }
}