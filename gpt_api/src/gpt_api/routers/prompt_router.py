# src/gpt_api/routers/prompt_router.py

from fastapi import APIRouter
from gpt_api.schemas.prompt_schema import PromptRequestSchema, AiConsultingCreateSchema
from gpt_api.services.prompt_service import generate_edit_result, generate_feedback_result

router = APIRouter(tags=["GPT"])

@router.post("/edits", response_model=AiConsultingCreateSchema)
async def generate_edits(request: PromptRequestSchema):
    return await generate_edit_result(request)

@router.post("/feedbacks", response_model=AiConsultingCreateSchema)
async def generate_feedbacks(request: PromptRequestSchema):
    return await generate_feedback_result(request)