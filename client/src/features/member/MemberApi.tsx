import { api } from "../../common/Axios.tsx";

export const nicknameInputApi = (
    nickname: string

) => {
    return api(true).post('/members', {
        nickname,
    });
};


