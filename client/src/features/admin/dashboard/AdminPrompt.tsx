"use client"

import { useState, useEffect } from "react"

import { Trash } from "lucide-react"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "../../../components/ui/tabs"
import { Card, CardContent } from "../../../components/ui/card"
import { Label } from "../../../components/ui/label"
import { Input } from "../../../components/ui/input"
import { Button } from "../../../components/ui/button"
import { Textarea } from "../../../components/ui/textarea"

// 임시 데이터
const initialPrompts = {
  correction: [
    {
      id: 1,
      title: "이력서 첨삭 가이드",
      content: "다음 이력서를 첨삭해주세요. 문법 오류, 표현 개선, 내용 보완 등을 중점적으로 확인해주세요.",
      isApplied: true,
    },
    {
      id: 2,
      title: "자기소개서 첨삭 가이드",
      content: "다음 자기소개서를 첨삭해주세요. 내용의 일관성, 구체적인 사례, 문장 구조 등을 중점적으로 확인해주세요.",
      isApplied: false,
    },
  ],
  feedback: [
    {
      id: 1,
      title: "이력서 피드백 가이드",
      content: "다음 이력서에 대한 피드백을 제공해주세요. 강점, 개선점, 전반적인 인상 등을 포함해주세요.",
      isApplied: true,
    },
    {
      id: 2,
      title: "자기소개서 피드백 가이드",
      content: "다음 자기소개서를 첨삭해주세요. 내용의 설득력, 차별성, 구체성 등을 중점적으로 평가해주세요.",
      isApplied: false,
    },
  ],
}

export default function PromptsPage() {
  const [activeTab, setActiveTab] = useState("correction")
  const [prompts, setPrompts] = useState(initialPrompts)
  const [currentPrompt, setCurrentPrompt] = useState({ id: 0, title: "", content: "" })
  const [appliedPromptTitle, setAppliedPromptTitle] = useState({
    correction: "이력서 첨삭 가이드",
    feedback: "이력서 피드백 가이드",
  })

  useEffect(() => {
    // 탭이 변경될 때 입력 폼 초기화
    setCurrentPrompt({ id: 0, title: "", content: "" })
  }, [activeTab])

  const handlePromptSelect = (prompt: any) => {
    setCurrentPrompt({
      id: prompt.id,
      title: prompt.title,
      content: prompt.content,
    })
  }

  const handleAddPrompt = () => {
    if (!currentPrompt.title || !currentPrompt.content) {
      alert("제목과 내용을 모두 입력해주세요.")
      return
    }

    const newPrompt = {
      id: Math.max(0, ...prompts[activeTab as keyof typeof prompts].map((p) => p.id)) + 1,
      title: currentPrompt.title,
      content: currentPrompt.content,
      isApplied: false,
    }

    setPrompts({
      ...prompts,
      [activeTab]: [...prompts[activeTab as keyof typeof prompts], newPrompt],
    })

    setCurrentPrompt({ id: 0, title: "", content: "" })
  }

  const handleUpdatePrompt = () => {
    if (!currentPrompt.id) {
      return
    }

    if (!currentPrompt.title || !currentPrompt.content) {
      alert("제목과 내용을 모두 입력해주세요.")
      return
    }

    const updatedPrompts = prompts[activeTab as keyof typeof prompts].map((prompt) =>
      prompt.id === currentPrompt.id
        ? { ...prompt, title: currentPrompt.title, content: currentPrompt.content }
        : prompt,
    )

    setPrompts({
      ...prompts,
      [activeTab]: updatedPrompts,
    })

    setCurrentPrompt({ id: 0, title: "", content: "" })
  }

  const handleDeletePrompt = (id: number) => {
    const updatedPrompts = prompts[activeTab as keyof typeof prompts].filter((prompt) => prompt.id !== id)

    setPrompts({
      ...prompts,
      [activeTab]: updatedPrompts,
    })

    if (currentPrompt.id === id) {
      setCurrentPrompt({ id: 0, title: "", content: "" })
    }
  }

  const handleApplyPrompt = () => {
    if (!currentPrompt.id && (!currentPrompt.title || !currentPrompt.content)) {
      alert("적용할 프롬프트를 선택하거나 새로 작성해주세요.")
      return
    }

    let promptToApply
    let updatedPrompts

    if (currentPrompt.id) {
      // 기존 프롬프트 적용
      updatedPrompts = prompts[activeTab as keyof typeof prompts].map((prompt) => ({
        ...prompt,
        isApplied: prompt.id === currentPrompt.id,
      }))
      promptToApply = prompts[activeTab as keyof typeof prompts].find((p) => p.id === currentPrompt.id)
    } else {
      // 새 프롬프트 생성 및 적용
      promptToApply = {
        id: Math.max(0, ...prompts[activeTab as keyof typeof prompts].map((p) => p.id)) + 1,
        title: currentPrompt.title,
        content: currentPrompt.content,
        isApplied: true,
      }
      updatedPrompts = prompts[activeTab as keyof typeof prompts].map((prompt) => ({
        ...prompt,
        isApplied: false,
      }))
      updatedPrompts.push(promptToApply)
    }

    if (promptToApply) {
      setAppliedPromptTitle({
        ...appliedPromptTitle,
        [activeTab]: promptToApply.title,
      })
    }

    setPrompts({
      ...prompts,
      [activeTab]: updatedPrompts,
    })

    setCurrentPrompt({ id: 0, title: "", content: "" })
  }

  return (
    <div className="space-y-6">
      <h1 className="text-3xl font-bold text-[#EE57CD]">프롬프트 설정</h1>

      <Tabs value={activeTab} onValueChange={setActiveTab}>
        <TabsList className="grid w-full max-w-md grid-cols-2">
          <TabsTrigger value="correction">첨삭 프롬프트</TabsTrigger>
          <TabsTrigger value="feedback">피드백 프롬프트</TabsTrigger>
        </TabsList>

        <TabsContent value="correction" className="space-y-4">
          <Card>
            <CardContent className="pt-6">
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="prompt-title">프롬프트 제목</Label>
                    <Input
                      id="prompt-title"
                      placeholder="프롬프트 제목을 입력하세요"
                      value={currentPrompt.title}
                      onChange={(e) => setCurrentPrompt({ ...currentPrompt, title: e.target.value })}
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="prompt-content">프롬프트 내용</Label>
                    <Textarea
                      id="prompt-content"
                      placeholder="프롬프트 내용을 입력하세요"
                      className="min-h-[200px]"
                      value={currentPrompt.content}
                      onChange={(e) => setCurrentPrompt({ ...currentPrompt, content: e.target.value })}
                    />
                  </div>
                  <div className="flex justify-center space-x-2 mt-4">
                    <Button className="bg-[#EE57CD] hover:bg-[#d33eb3]" onClick={handleApplyPrompt}>
                      적용
                    </Button>
                    <Button className="bg-[#EE57CD] hover:bg-[#d33eb3]" onClick={handleAddPrompt}>
                      추가
                    </Button>
                    <Button onClick={handleUpdatePrompt} disabled={!currentPrompt.id}>
                      수정
                    </Button>
                  </div>
                </div>

                <div className="space-y-4">
                  <div className="font-medium text-[#EE57CD]">현재 적용 중: {appliedPromptTitle.correction}</div>
                  <div className="space-y-2">
                    <h3 className="font-medium text-[#666666]">프롬프트 리스트</h3>
                    <div className="space-y-2 max-h-[400px] overflow-y-auto">
                      {prompts.correction.map((prompt) => (
                        <div
                          key={prompt.id}
                          className={`p-3 border rounded-md cursor-pointer flex justify-between items-center ${
                            prompt.isApplied ? "bg-[#FFE6FB]" : ""
                          }`}
                          onClick={() => handlePromptSelect(prompt)}
                        >
                          <div>
                            <div className="font-medium text-[#666666]">{prompt.title}</div>
                            {prompt.isApplied && <div className="text-xs text-[#EE57CD] mt-1">적용 중</div>}
                          </div>
                          <Button
                            variant="ghost"
                            size="icon"
                            onClick={(e) => {
                              e.stopPropagation()
                              handleDeletePrompt(prompt.id)
                            }}
                          >
                            <Trash className="h-4 w-4 text-[#EE57CD]" />
                          </Button>
                        </div>
                      ))}
                    </div>
                  </div>
                </div>
              </div>
            </CardContent>
          </Card>
        </TabsContent>

        <TabsContent value="feedback" className="space-y-4">
          <Card>
            <CardContent className="pt-6">
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="prompt-title-feedback">프롬프트 제목</Label>
                    <Input
                      id="prompt-title-feedback"
                      placeholder="프롬프트 제목을 입력하세요"
                      value={currentPrompt.title}
                      onChange={(e) => setCurrentPrompt({ ...currentPrompt, title: e.target.value })}
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="prompt-content-feedback">프롬프트 내용</Label>
                    <Textarea
                      id="prompt-content-feedback"
                      placeholder="프롬프트 내용을 입력하세요"
                      className="min-h-[200px]"
                      value={currentPrompt.content}
                      onChange={(e) => setCurrentPrompt({ ...currentPrompt, content: e.target.value })}
                    />
                  </div>
                  <div className="flex justify-center space-x-2 mt-4">
                    <Button className="bg-[#EE57CD] hover:bg-[#d33eb3]" onClick={handleApplyPrompt}>
                      적용
                    </Button>
                    <Button className="bg-[#EE57CD] hover:bg-[#d33eb3]" onClick={handleAddPrompt}>
                      추가
                    </Button>
                    <Button onClick={handleUpdatePrompt} disabled={!currentPrompt.id}>
                      수정
                    </Button>
                  </div>
                </div>

                <div className="space-y-4">
                  <div className="font-medium text-[#EE57CD]">현재 적용 중: {appliedPromptTitle.feedback}</div>
                  <div className="space-y-2">
                    <h3 className="font-medium text-[#666666]">프롬프트 리스트</h3>
                    <div className="space-y-2 max-h-[400px] overflow-y-auto">
                      {prompts.feedback.map((prompt) => (
                        <div
                          key={prompt.id}
                          className={`p-3 border rounded-md cursor-pointer flex justify-between items-center ${
                            prompt.isApplied ? "bg-[#FFE6FB]" : ""
                          }`}
                          onClick={() => handlePromptSelect(prompt)}
                        >
                          <div>
                            <div className="font-medium text-[#666666]">{prompt.title}</div>
                            {prompt.isApplied && <div className="text-xs text-[#EE57CD] mt-1">적용 중</div>}
                          </div>
                          <Button
                            variant="ghost"
                            size="icon"
                            onClick={(e) => {
                              e.stopPropagation()
                              handleDeletePrompt(prompt.id)
                            }}
                          >
                            <Trash className="h-4 w-4 text-[#EE57CD]" />
                          </Button>
                        </div>
                      ))}
                    </div>
                  </div>
                </div>
              </div>
            </CardContent>
          </Card>
        </TabsContent>
      </Tabs>
    </div>
  )
}
