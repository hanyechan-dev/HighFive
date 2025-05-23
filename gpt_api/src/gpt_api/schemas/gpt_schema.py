from pydantic import BaseModel, Field
from datetime import date
from enum import Enum


class ConsultingType(str, Enum):
    EDIT = "EDIT"
    FEEDBACK = "FEEDBACK"
    # 필요하면 추가 가능


class ConsultingRequestDto(BaseModel):
    targetJob: str = Field(..., description="희망 직무")
    targetCompanyName: str = Field(..., description="지원 회사명")
    type: ConsultingType = Field(..., description="컨설팅 타입 (EDIT 또는 FEEDBACK)")
    resumeJson: str = Field(..., description="이력서 JSON 문자열")
    coverLetterJson: str = Field(..., description="자소서 JSON 문자열")
    careerDescriptionJson: str = Field(description="경력기술서 JSON 문자열")

class GPTResponse(BaseModel):
    coverLetterJson: str = Field(..., description="자소서 JSON 문자열")
    careerDescriptionJson: str = Field(description="경력기술서 JSON 문자열")