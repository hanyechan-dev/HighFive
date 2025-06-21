# src/gpt_api/schemas/prompt_schema.py

from typing import List
from pydantic import BaseModel

class PromptItemSchema(BaseModel):
    role: str
    content: str

class PromptRequestSchema(BaseModel):
    prompt_list : List[PromptItemSchema]

    
class AiConsultingContentCreateSchema(BaseModel):
	item : str
	documentType : str
	content : str
 
class AiConsultingCreateSchema(BaseModel):
    consultingType : str
    aiConsultingContentCreateDtos : List[AiConsultingContentCreateSchema]
    