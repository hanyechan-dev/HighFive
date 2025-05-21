from fastapi import APIRouter, HTTPException
from typing import List
from pydantic import BaseModel
from services.gpt.embeddings import EmbeddingsService

router = APIRouter()

class EmbeddingRequest(BaseModel):
    input: List[str]
    model: str = "text-embedding-3-large"

class EmbeddingResponse(BaseModel):
    data: List[dict]
    model: str
    usage: dict

@router.post("/embeddings", response_model=EmbeddingResponse)
async def embeddings(request: EmbeddingRequest):
    try:
        service = EmbeddingsService()
        response = service.generate_embeddings(request)
        return response
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
