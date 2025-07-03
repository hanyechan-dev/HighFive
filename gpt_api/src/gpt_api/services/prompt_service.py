from gpt_api.schemas.prompt_schema import PromptRequestSchema, AiConsultingCreateSchema, AiConsultingContentCreateSchema
from openai import AsyncOpenAI
import os
from dotenv import load_dotenv
import json
import time
from datetime import datetime
import re

load_dotenv()
client = AsyncOpenAI(api_key=os.getenv("prompt_api_key"))

async def generate_edit_result(request: PromptRequestSchema) -> AiConsultingCreateSchema:
    start = time.time()
    start_time = datetime.now()
    print(">>>>> OpenAI 시작 <<<<<", start_time.strftime("%Y-%m-%d %H:%M:%S"))

    response = await client.chat.completions.create(
        model="gpt-4o",
        messages=[item.model_dump() for item in request.prompt_list],
        temperature=0.7,
        n=1
    )

    end_time = datetime.now()
    print(">>>>> OpenAI 끝 <<<<<", end_time)
    print("총 소요시간:", time.time() - start, "초")

    json_response = response.choices[0].message.content

    clean_json = json_response.strip()



    if clean_json.startswith("```"):
        clean_json = re.sub(r"^```json", "", clean_json.strip(), flags=re.IGNORECASE)
        clean_json = re.sub(r"^```", "", clean_json.strip()) 
        clean_json = re.sub(r"```$", "", clean_json.strip())

    clean_json = clean_json.strip()

    if not clean_json.startswith("{"):
        raise ValueError(f"Unexpected format: {repr(clean_json)}")

    parsed = json.loads(clean_json)

    items = [
        AiConsultingContentCreateSchema(**edit)
        for edit in parsed["첨삭"]
    ]

    return AiConsultingCreateSchema(
        consultingType="첨삭",
        aiConsultingContentCreateDtos=items
    )

async def generate_feedback_result(request: PromptRequestSchema) -> AiConsultingCreateSchema:
    start = time.time()
    start_time = datetime.now()
    print(">>>>> OpenAI 시작 <<<<<", start_time.strftime("%Y-%m-%d %H:%M:%S"))

    response = await client.chat.completions.create(
        model="gpt-4o",
        messages=[item.model_dump() for item in request.prompt_list],
        temperature=0.7,
        n=1
    )

    end_time = datetime.now()
    print(">>>>> OpenAI 끝 <<<<<", end_time)
    print("총 소요시간:", time.time() - start, "초")

    json_response = response.choices[0].message.content

    clean_json = json_response.strip()

    if clean_json.startswith("```"):
        clean_json = re.sub(r"^```json", "", clean_json.strip(), flags=re.IGNORECASE)
        clean_json = re.sub(r"^```", "", clean_json.strip()) 
        clean_json = re.sub(r"```$", "", clean_json.strip())

    clean_json = clean_json.strip()

    if not clean_json.startswith("{"):
        raise ValueError(f"Unexpected format: {repr(clean_json)}")

    parsed = json.loads(clean_json)

    items = [
        AiConsultingContentCreateSchema(**edit)
        for edit in parsed["피드백"]
    ]

    return AiConsultingCreateSchema(
        consultingType="피드백",
        aiConsultingContentCreateDtos=items
    )