from fastapi import APIRouter, HTTPException
from typing import List, Optional
from pydantic import BaseModel
from services.gpt.chat import ChatService

router = APIRouter()

class ChatRequest(BaseModel):
    messages: List[dict]
    model: str = "gpt-3.5-turbo"
    temperature: float = 1.0
    max_tokens: Optional[int] = None

class ChatResponse(BaseModel):
    id: str
    object: str
    created: int
    model: str
    choices: List[dict]

@router.post("/chat", response_model=ChatResponse)
async def chat(request: ChatRequest):
    try:
        service = ChatService()
        response = service.generate_chat_response(request)
        return response
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
