import { api } from "../../../common/Axios";

export interface PostCreateDto{
    title: string;
    content: string;
}

export const createPost = (dto: PostCreateDto) => {
    return api(true).post('/posts', dto);
}

export const readPostDetail = (id: number) => {
  return api(true).get('/posts/${id}');
};
