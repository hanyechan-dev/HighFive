"use client"

import { useState, useEffect } from "react"

import { Trash } from "lucide-react"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "../../../common/components/ui/tabs"
import { Card, CardContent } from "../../../common/components/ui/card"
import { Label } from "../../../common/components/ui/label"
import { Input } from "../../../common/components/ui/input"
import { Button } from "../../../common/components/ui/button"
import { Textarea } from "../../../common/components/ui/textarea"
import { api } from "../../../common/Axios"
import { printErrorInfo } from "../../../common/utils/ErrorUtil"

// 임시 데이터
interface PromptSettingResponseDto {
    applied: PromptDto;
    list: PromptSummaryDto[];
}

interface PromptDto {
    id: number;
    title: string;
    content: string;
}

interface PromptSummaryDto {
    id: number;
    title: string;
    applied: boolean;
}



export default function PromptsPage() {
    const [activeTab, setActiveTab] = useState("edits")
    const [prompts, setPrompts] = useState<PromptSummaryDto[]>([])

    const [currentPrompt, setCurrentPrompt] = useState<PromptDto>({ id: -1, title: "", content: "" })
    const [appliedPromptTitle, setAppliedPromptTitle] = useState("")

    useEffect(() => {
        const readEditPromptSetting = async () => {
            const res = await api(true).get("/edit-prompts");
            const editPromptSettingResponseDto: PromptSettingResponseDto = res.data;
            const list = editPromptSettingResponseDto.list
            console.log(list)
            setPrompts(list)
            const applied = editPromptSettingResponseDto.applied
            if (applied == null) {
                setAppliedPromptTitle("")
                setCurrentPrompt({ id: -1, title: "", content: "" })
            } else {
                setAppliedPromptTitle(applied.title)
                setCurrentPrompt(applied)
            }
        }

        const readFeedbackPromptSetting = async () => {
            const res = await api(true).get("/feedback-prompts");
            const editPromptSettingResponseDto: PromptSettingResponseDto = res.data;
            const list = editPromptSettingResponseDto.list
            console.log(list)
            setPrompts(list)
            const applied = editPromptSettingResponseDto.applied
            if (applied == null) {
                setAppliedPromptTitle("")
                setCurrentPrompt({ id: -1, title: "", content: "" })
            } else {
                setAppliedPromptTitle(applied.title)
                setCurrentPrompt(applied)
            }

        }

        if (activeTab == "edits") {
            try {
                readEditPromptSetting();
            } catch (err) {
                printErrorInfo(err);
            }
        }

        else if (activeTab == "feedbacks") {
            try {
                readFeedbackPromptSetting();
            } catch (err) {
                printErrorInfo(err);
            }
        }

    }, [activeTab])

    const onClickAddButton = () => {
        const createEditPrompt = async () => {
            const res = await api(true).post("/edit-prompts", {
                title: currentPrompt.title,
                content: currentPrompt.content
            })
            setCurrentPrompt(res.data)
            setPrompts((prev) => [...prev, res.data]);
        }

        const createFeedbackPrompt = async () => {
            const res = await api(true).post("/feedback-prompts", {
                title: currentPrompt.title,
                content: currentPrompt.content
            })
            setCurrentPrompt(res.data)
            setPrompts((prev) => [...prev, res.data]);
        }

        if (activeTab == "edits") {
            try {
                createEditPrompt();
            } catch (err) {
                printErrorInfo(err);
            }
        }

        else if (activeTab == "feedbacks") {
            try {
                createFeedbackPrompt();
            } catch (err) {
                printErrorInfo(err);
            }
        }

    }

    const onClickApplyButton = () => {

        if (currentPrompt.id == -1) {
            alert("아직 추가되지 않은 프롬프트입니다. 먼저 추가한 후, 적용하세요.")
        }

        const applyFeedbackPrompt = async () => {
            console.log(currentPrompt.id);
            await api(true).put("/feedback-prompts/application", {
                id: currentPrompt.id,
            });
            setAppliedPromptTitle(currentPrompt.title);

            // prompts 상태에서 applied 업데이트
            setPrompts((prev) =>
                prev.map((item) =>
                    item.id === currentPrompt.id
                        ? { ...item, applied: true }
                        : { ...item, applied: false }
                )
            );
        };

        const applyEditPrompt = async () => {
            console.log(currentPrompt.id);
            await api(true).put("/edit-prompts/application", {
                id: currentPrompt.id,
            });
            setAppliedPromptTitle(currentPrompt.title);

            // prompts 상태에서 applied 업데이트
            setPrompts((prev) =>
                prev.map((item) =>
                    item.id === currentPrompt.id
                        ? { ...item, applied: true }
                        : { ...item, applied: false }
                )
            );
        };

        if (activeTab === "edits") {
            try {
                applyEditPrompt();
            } catch (err) {
                printErrorInfo(err);
            }
        } else if (activeTab === "feedbacks") {
            try {
                applyFeedbackPrompt();
            } catch (err) {
                printErrorInfo(err);
            }
        }
    };

    const onClickList = (id: number) => {

        const readEditPropmt = async () => {
            const res = await api(true).post("/edit-prompts/datail", {
                id
            })
            setCurrentPrompt(res.data);

        }

        const readFeedbackPropmt = async () => {
            const res = await api(true).post("/feedback-prompts/datail", {
                id
            })
            setCurrentPrompt(res.data);
        }


        if (activeTab == "edits") {
            try {
                readEditPropmt();
            } catch (err) {
                printErrorInfo(err);
            }
        }

        else if (activeTab == "feedbacks") {
            try {
                readFeedbackPropmt();
            } catch (err) {
                printErrorInfo(err);
            }
        }
    }

    const onClickDeleteButton = async (id: number) => {
        const deleteFeedbackPrompt = async () => {
            await api(true).post("/feedback-prompts/deletion", { id });

            setPrompts((prev) => {
                const found = prev.find((item) => item.id === id);
                if (found?.applied === true) {
                    setAppliedPromptTitle("");
                }
                return prev.filter((item) => item.id !== id);
            });
        };

        const deleteEditPrompt = async () => {
            await api(true).post("/edit-prompts/deletion", { id });

            setPrompts((prev) => {
                const found = prev.find((item) => item.id === id);
                if (found?.applied === true) {
                    setAppliedPromptTitle("");
                }
                return prev.filter((item) => item.id !== id);
            });
        };

        try {
            if (activeTab === "edits") {
                await deleteEditPrompt();
            } else if (activeTab === "feedbacks") {
                await deleteFeedbackPrompt();
            }
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const onClickUpdateButton = () => {

        if (currentPrompt.id == -1) {
            alert("아직 추가되지 않은 프롬프트입니다. 먼저 추가한 후, 수정하세요.")
        }
        const updateFeedbackPrompt = async () => {
            api(true).put("/feedback-prompts", currentPrompt)
        }
        const updateEditPrompt = async () => {
            api(true).put("/edit-prompts", currentPrompt)
        }

        if (activeTab == "edits") {
            try {
                updateEditPrompt();
            } catch (err) {
                printErrorInfo(err);
            }
        }

        else if (activeTab == "feedbacks") {
            try {
                updateFeedbackPrompt();
            } catch (err) {
                printErrorInfo(err);
            }
        }
    }



    return (
        <div className="space-y-6">
            <h1 className="text-3xl font-bold text-[#EE57CD]">프롬프트 설정</h1>

            <Tabs value={activeTab} onValueChange={setActiveTab}>
                <TabsList className="grid w-full max-w-md grid-cols-2">
                    <TabsTrigger value="edits">첨삭 프롬프트</TabsTrigger>
                    <TabsTrigger value="feedbacks">피드백 프롬프트</TabsTrigger>
                </TabsList>

                <TabsContent value="edits" className="space-y-4">
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
                                        <Button className="bg-[#EE57CD] hover:bg-[#d33eb3]" onClick={onClickApplyButton}>
                                            적용
                                        </Button>
                                        <Button className="bg-[#EE57CD] hover:bg-[#d33eb3]" onClick={onClickAddButton}>
                                            추가
                                        </Button>
                                        <Button onClick={onClickUpdateButton} disabled={currentPrompt.id == -1}>
                                            수정
                                        </Button>
                                    </div>
                                </div>

                                <div className="space-y-4">
                                    <div className="font-medium text-[#EE57CD]">현재 적용 중: {appliedPromptTitle}</div>
                                    <div className="space-y-2">
                                        <h3 className="font-medium text-[#666666]">프롬프트 리스트</h3>
                                        <div className="space-y-2 max-h-[400px] overflow-y-auto">
                                            {prompts.map((prompt) => (
                                                <div className="flex">
                                                    <div
                                                        key={prompt.id}
                                                        className={`w-[291px] p-3 border rounded-md cursor-pointer flex justify-between items-center ${prompt.applied ? "bg-[#FFE6FB]" : ""
                                                            }`}
                                                        onClick={() => { onClickList(prompt.id) }}
                                                    >
                                                        <div>
                                                            <div className="font-medium text-[#666666]">{prompt.title}</div>
                                                            {prompt.applied && <div className="text-xs text-[#EE57CD] mt-1">적용 중</div>}
                                                        </div>
                                                    </div>
                                                    <div className="flex justify-center items-center">
                                                        <Button
                                                            variant="ghost"
                                                            size="icon"
                                                            onClick={() => { onClickDeleteButton(prompt.id) }}
                                                        >
                                                            <Trash className="h-4 w-4 text-[#EE57CD]" />
                                                        </Button>
                                                    </div>
                                                </div>
                                            ))}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </CardContent>
                    </Card>
                </TabsContent>

                <TabsContent value="feedbacks" className="space-y-4">
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
                                        <Button className="bg-[#EE57CD] hover:bg-[#d33eb3]" onClick={onClickApplyButton}>
                                            적용
                                        </Button>
                                        <Button className="bg-[#EE57CD] hover:bg-[#d33eb3]" onClick={onClickAddButton}>
                                            추가
                                        </Button>
                                        <Button onClick={onClickUpdateButton} disabled={currentPrompt.id == -1}>
                                            수정
                                        </Button>
                                    </div>
                                </div>

                                <div className="space-y-4">
                                    <div className="font-medium text-[#EE57CD]">현재 적용 중: {appliedPromptTitle}</div>
                                    <div className="space-y-2">
                                        <h3 className="font-medium text-[#666666]">프롬프트 리스트</h3>
                                        <div className="space-y-2 max-h-[400px] overflow-y-auto">
                                            {prompts.map((prompt) => (
                                                <div className="flex">
                                                    <div
                                                        key={prompt.id}
                                                        className={`w-[291px] p-3 border rounded-md cursor-pointer flex justify-between items-center ${prompt.applied ? "bg-[#FFE6FB]" : ""
                                                            }`}
                                                        onClick={() => { onClickList(prompt.id) }}
                                                    >
                                                        <div>
                                                            <div className="font-medium text-[#666666]">{prompt.title}</div>
                                                            {prompt.applied && <div className="text-xs text-[#EE57CD] mt-1">적용 중</div>}
                                                        </div>
                                                    </div>
                                                    <div className="flex justify-center items-center">
                                                        <Button
                                                            variant="ghost"
                                                            size="icon"
                                                            onClick={() => { onClickDeleteButton(prompt.id) }}
                                                        >
                                                            <Trash className="h-4 w-4 text-[#EE57CD]" />
                                                        </Button>
                                                    </div>
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
