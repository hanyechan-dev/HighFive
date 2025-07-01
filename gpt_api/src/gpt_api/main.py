# src/gpt_api/main.py

from fastapi import FastAPI
from gpt_api.routers import embedding_router, prompt_router
import uvicorn

app = FastAPI()

app.include_router(prompt_router.router)
app.include_router(embedding_router.router)


def main():
    uvicorn.run("gpt_api.main:app", host="127.0.0.1", port=9000, reload=True)