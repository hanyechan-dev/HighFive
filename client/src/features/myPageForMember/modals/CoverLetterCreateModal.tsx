import { useState } from "react";
import Input from "../../../common/components/input/Input";
import ModalTitle from "../../../common/components/title/ModalTitle"
import { ExternalBox, InternalBox } from "../../../common/components/box/Box";
import TextArea from "../../../common/components/input/TextArea";
import Button from "../../../common/components/button/Button";
import type { CoverLetterContentCreateDto, CoverLetterCreateDto } from "../props/myPageForMemberProps";


type TempContent = CoverLetterContentCreateDto & { tempId: number };


interface CoverLetterCreateModalProps {
    onCreate: (coverLetterCreateDto: CoverLetterCreateDto) => void;
    setShowModalType: (modalType: string) => void
}

const CoverLetterDetailModal = ({
    onCreate,
    setShowModalType
}: CoverLetterCreateModalProps) => {

    const [createDto, setCreateDto] = useState<{ title: string; contents: TempContent[]; }>
        ({
            title: "",
            contents: [],
        });

    const setTitle = (newTitle: string) => {
        setCreateDto(prev => ({
            ...prev,
            title: newTitle
        }));
    };

    const setItem = (tempId: number, newItem: string) => {
        setCreateDto(prev => ({
            ...prev,
            contents: prev.contents.map((c) =>
                c.tempId === tempId ? { ...c, item: newItem } : c
            )
        }));
    };

    const setContent = (tempId: number, newContent: string) => {
        setCreateDto(prev => ({
            ...prev,
            contents: prev.contents.map((c) =>
                c.tempId === tempId ? { ...c, content: newContent } : c
            )
        }));
    };

    const onClickDeleteItem = (tempId: number) => {
        setCreateDto(prev => ({
            ...prev,
            contents: prev.contents.filter(c => c.tempId !== tempId)
        }));
    };

    const [isAddMode, setIsAddMode] = useState(false);
    const [newContent, setNewContent] = useState<CoverLetterContentCreateDto>({
        item: "",
        content: ""
    });

    const setNewItem = (val: string) => {
        setNewContent(prev => ({
            ...prev,
            item: val
        }));
    };

    const setNewContentValue = (val: string) => {
        setNewContent(prev => ({
            ...prev,
            content: val
        }));
    };

    const onClickConfirmButton = () => {
        setCreateDto(prev => ({
            ...prev,
            contents: [
                ...prev.contents,
                {
                    tempId: Date.now(), // 프론트용 임시 ID
                    item: newContent.item,
                    content: newContent.content
                }
            ]
        }));

        setNewContent({ item: "", content: "" });
        setIsAddMode(false);
    };

    const onClickSaveButton = () => {
        const dtoToSubmit: CoverLetterCreateDto = {
            title: createDto.title,
            contents: createDto.contents.map(({ item, content }) => ({
                item,
                content
            }))
        };

        onCreate(dtoToSubmit);
        setShowModalType("detail");
    };


    const onClickAddItemButton = () => {
        setIsAddMode(true)
    }



    return (
        <>
            <ModalTitle title="경력기술서 작성" />
            <Input label={"제목"} placeholder={""} size={"l"} disabled={false} type={"text"} value={createDto.title} setValue={setTitle} />
            <ExternalBox>
                {createDto.contents.map((content) => (
                    <div key={content.tempId}>
                        <InternalBox >
                            <div className="mt-[-24px]">
                                <Input label={""} placeholder={""} size={"ibl"} disabled={false} type={"text"} value={content.item} setValue={(val) => setItem(content.tempId, val)} />
                            </div>
                            <div className="mt-[-24px]">
                                <TextArea size={"ibl"} label={""} placeholder={""} disabled={false} value={content.content} setValue={(val) => setContent(content.tempId, val)} />
                            </div>
                            <div className="flex justify-end mr-6">
                                <Button color={"action"} size={"s"} disabled={false} text={"항목삭제"} type={"button"} onClick={() => { onClickDeleteItem(content.tempId) }} />
                            </div>

                        </InternalBox>
                    </div>
                ))}
                {isAddMode && (
                    <InternalBox >
                        <div className="mt-[-24px]">
                            <Input label={""} placeholder={""} size={"ibl"} disabled={false} type={"text"} value={newContent.item} setValue={setNewItem} />
                        </div>
                        <div className="mt-[-24px]">
                            <TextArea size={"ibl"} label={""} placeholder={""} disabled={false} value={newContent.content} setValue={setNewContentValue} />
                        </div>
                        <div className="flex justify-end mr-6">
                            <Button color={"white"} size={"s"} disabled={false} text={"확인"} type={"button"} onClick={onClickConfirmButton} />
                        </div>
                    </InternalBox>)}
            </ExternalBox>
            <div className="flex justify-end mr-6">

                {!isAddMode && (
                    <Button color={"white"} size={"s"} disabled={false} text={"항목 추가"} type={"button"} onClick={onClickAddItemButton} />
                )}
                <Button color={"theme"} size={"s"} disabled={false} text={"저장"} type={"button"} onClick={onClickSaveButton} />


            </div>
        </>
    )
}





export default CoverLetterDetailModal;


