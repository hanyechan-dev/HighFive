export interface PostCreateDto{
    title: string;
    content: string;
}

export interface CommentCreateDto{
    postId: number;
    content: string;
}

export interface PostUpdateDto{
    id: number;
    title: string;
    content: string;
}

export interface IdDto{
    id: number;
}