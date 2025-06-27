from fastapi import UploadFile
from openai import OpenAI
from typing import List
import numpy as np
from PIL import Image
from io import BytesIO
import os
from dotenv import load_dotenv
import easyocr
import json

load_dotenv()
client = OpenAI(api_key=os.getenv("prompt_api_key"))

def generate_vector_job_posting_result(metadata: str , images : List[UploadFile]):
    
    embedding_text = metadata
    
    embedding_text = embedding_text + "\n[본문 영역]\n"
    
    for image in images:
        result_text = get_text_from_image(image)
        embedding_text = embedding_text + "\n" + result_text
        
    if len(embedding_text) > 3000:
        embedding_text = embedding_text[:3000]
    
    response = client.embeddings.create(
        model="text-embedding-3-small", # 쓰고싶은 모델명
        input= embedding_text # 임베딩 할 텍스트
    )
    
    embedding_vector = response.data[0].embedding

    return json.dumps(embedding_vector)

def generate_vector_member_result(data : str):
    response = client.embeddings.create(
        model="text-embedding-3-small", # 쓰고싶은 모델명
        input= data # 임베딩 할 텍스트
    )
    
    embedding_vector = response.data[0].embedding
    
    return json.dumps(embedding_vector)

def get_text_from_image(image : UploadFile):
    image_bytes = image.file.read()
    image_stream = BytesIO(image_bytes)
    pil_image = Image.open(image_stream)
    np_image = np.array(pil_image)
    reader = easyocr.Reader(['ko', 'en'])
    results = reader.readtext(np_image)
    texts = [text for (_, text, confidence) in results if confidence >= 0.75]
    
    result_string = "\n".join(texts)
    return result_string

