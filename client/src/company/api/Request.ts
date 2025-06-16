import { api } from '../../common/Axios';
import { handleApiError } from './ErrorHandler';

export const Request = {
    post: async <T>(url: string, data: any) => {
        try {
            const response = await api(true).post<T>(url, data);
            return response.data;
        } catch (error: any) {
            handleApiError(error);
        }
    }
}; 