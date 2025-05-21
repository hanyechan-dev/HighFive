from fastapi import APIRouter, HTTPException
from typing import Optional
from pydantic import BaseModel
from services.gpt.completion import CompletionService

router = APIRouter()

class CompletionRequest(BaseModel):
    prompt: str
    model: str = "text-davinci-003"
    temperature: float = 1.0
    max_tokens: Optional[int] = None
    top_p: float = 1.0
    frequency_penalty: float = 0.0
    presence_penalty: float = 0.0

class CompletionResponse(BaseModel):
    id: str
    object: str
    created: int
    model: str
    choices: List[dict]

@router.post("/completion", response_model=CompletionResponse)
async def completion(request: CompletionRequest):
    try:
        service = CompletionService()
        response = service.generate_completion(request)
        return response
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
