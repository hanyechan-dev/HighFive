export interface Comment {
  id: number
  postId: number
  author: string
  content: string
  createdAt: string
}

export interface Post {
  id: number
  title: string
  content: string
  author: string
  createdAt: string
  views: number
  comments: Comment[]
}
