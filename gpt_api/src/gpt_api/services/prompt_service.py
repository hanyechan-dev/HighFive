from gpt_api.schemas.prompt_schema import PromptRequestSchema, AiConsultingCreateSchema, AiConsultingContentCreateSchema
from openai import OpenAI
import os
from dotenv import load_dotenv

load_dotenv()
client = OpenAI(api_key=os.getenv("prompt_api_key"))

def generate_edit_result(request: PromptRequestSchema) -> AiConsultingCreateSchema:
    response = client.chat.completions.create(
        model="gpt-4o", # 쓰고싶은 모델명
        messages=[item.model_dump() for item in request.prompt_list], # 각 리스트를 순회하며 객체화/ 그후 리스트로 묶음
        temperature=0.7, # 0.0~2.0의 값 높을수록 창의적 낮을수록 보수적
        n=1 # 기대하는 응답의 개수
    )
    
    json_response = response.choices[0].message.content
    parsed = json.loads(json_response)

    items = [
        AiConsultingContentCreateSchema(**edit)
        for edit in parsed["첨삭"]
    ]

    return AiConsultingCreateSchema(
        consultingType="첨삭",
        aiConsultingContentCreateDtos=items
    )
    
    
def generate_feedback_result(request: PromptRequestSchema) -> AiConsultingCreateSchema:
    response = client.chat.completions.create(
        model="gpt-4o", # 쓰고싶은 모델명
        messages=[item.model_dump() for item in request.prompt_list], # 각 리스트를 순회하며 객체화/ 그후 리스트로 묶음
        temperature=0.7, # 0.0~2.0의 값 높을수록 창의적 낮을수록 보수적
        n=1 # 기대하는 응답의 개수
    )
    json_response = response.choices[0].message.content
    parsed = json.loads(json_response)

    items = [
        AiConsultingContentCreateSchema(**edit)
        for edit in parsed["피드백"]
    ]

    return AiConsultingCreateSchema(
        consultingType="피드백",
        aiConsultingContentCreateDtos=items
    )
