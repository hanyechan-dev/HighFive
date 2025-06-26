import { api } from "../../../common/Axios";
import type { PostCreateDto, CommentCreateDto, PostUpdateDto, IdDto } from "../props/PostProps";



export const createPost = (dto: PostCreateDto) => {
    return api(true).post('/posts', dto);
}

export const readPostPage = (page : number, size : number) => {
    return api(true).get('/posts',{
         params : {
            page,
            size
        }
    });
}

export const readPostDetail = (id: number) => {
  return api(true).get(`/posts/${id}`);
};

export const createComment = (dto : CommentCreateDto) => {
  return api(true).post('/posts/comments', dto);
}

export const updatePost = (dto : PostUpdateDto) => {
  return api(true).put('posts', dto);
}

export const deletePost = (dto : IdDto) => {
  return api(true).post('/posts/deletion', dto)
}
