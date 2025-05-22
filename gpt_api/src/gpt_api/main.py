from fastapi import FastAPI
import uvicorn

app = FastAPI()

@app.get("/ask")
def ask():
    return {"result": f"응답!"}

def main():
    uvicorn.run("gpt_api.main:app", host="127.0.0.1", port=9000, reload=True)
