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
import time

load_dotenv()
client = OpenAI(api_key=os.getenv("prompt_api_key"))
reader = easyocr.Reader(['ko', 'en'])

def generate_vector_job_posting_result(metadata: str , images : List[UploadFile]):
    
    embedding_text = metadata
    
    embedding_text = embedding_text + "\n[본문 영역]\n"
    
    for image in images:
        result_text = get_text_from_image(image)
        embedding_text = embedding_text + "\n" + result_text
        
    if len(embedding_text) > 3000:
        embedding_text = embedding_text[:3000]

    start = time.time()
    print(">>>>> OpenAI Embedding 시작")
    
    response = client.embeddings.create(
        model="text-embedding-3-small", # 쓰고싶은 모델명
        input= embedding_text # 임베딩 할 텍스트
    )
    
    print(">>>>> OpenAI Embedding 끝:", time.time() - start, "초 걸림")
    
    embedding_vector = response.data[0].embedding

    return json.dumps(embedding_vector)

def generate_vector_member_result(data : str):
    start = time.time()
    print(">>>>> OpenAI Embedding 시작")
    
    response = client.embeddings.create(
        model="text-embedding-3-small", # 쓰고싶은 모델명
        input= data # 임베딩 할 텍스트
    )
    
    print(">>>>> OpenAI Embedding 끝:", time.time() - start, "초 걸림")
    
    embedding_vector = response.data[0].embedding
    
    return json.dumps(embedding_vector)

def get_text_from_image(image : UploadFile):
    image.file.seek(0)
    image_bytes = image.file.read()
    image.file.seek(0)
    image_stream = BytesIO(image_bytes)
    pil_image = Image.open(image_stream)
    np_image = np.array(pil_image)
    results = reader.readtext(np_image)
    texts = [text for (_, text, confidence) in results if confidence >= 0.75]
    
    result_string = "\n".join(texts)
    return result_string

