# src/gpt_api/routers/embedding_router.py

from fastapi import APIRouter, UploadFile, Form, Body
from typing import List
from gpt_api.schemas.prompt_schema import PromptRequestSchema, AiConsultingCreateSchema
from gpt_api.services.embedding_service import  generate_vector_job_posting_result, generate_vector_member_result
from gpt_api.services.prompt_service import generate_edit_result, generate_feedback_result

router = APIRouter(tags=["GPT"])

@router.post("/embedding-job-posting")
async def generate_vector_job_posting(
    metadata: str = Form(...),
    images: List[UploadFile] = None
):
    return generate_vector_job_posting_result(
        metadata = metadata,
        images = images
        )
    
@router.post("/embedding-member")
async def generate_vector_member(data:str = Body(...)):
    return generate_vector_member_result(data)
    