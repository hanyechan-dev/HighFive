import { useEffect, useState } from "react";
import Input from "../../../common/components/input/Input";
import ModalTitle from "../../../common/components/title/ModalTitle"
import { ExternalBox, InternalBox } from "../../../common/components/box/Box";
import TextArea from "../../../common/components/input/TextArea";
import Button from "../../../common/components/button/Button";
import type { CoverLetterContentCreateDto, CoverLetterResponseDto, CoverLetterUpdateDto } from "../props/myPageForMemberProps";

const convertToUpdateDto = (responseDto: CoverLetterResponseDto): CoverLetterUpdateDto => {
    return {
        id: responseDto.id,
        title: responseDto.title,
        contents: responseDto.contents.map((c) => ({
            item: c.item,
            content: c.content
        }))
    };
};


interface CoverLetterUpdateModalProps {
    coverLetterResponseDto: CoverLetterResponseDto;
    onUpdate: (coverLetterUpdateDto: CoverLetterUpdateDto) => void;
    setShowModalType: (modalType: string) => void
}

const CoverLetterUpdateModal = ({
    coverLetterResponseDto,
    onUpdate,
    setShowModalType,
}: CoverLetterUpdateModalProps) => {

    const [responseDto, setResponseDto] = useState<CoverLetterResponseDto>(coverLetterResponseDto);

    useEffect(() => {
        setResponseDto(coverLetterResponseDto);
    }, [coverLetterResponseDto]);

    const setTitle = (newTitle: string) => {
        setResponseDto(prev => ({
            ...prev,
            title: newTitle
        }));
    };

    const setItem = (id: number, newItem: string) => {
        setResponseDto(prev => ({
            ...prev,
            contents: prev.contents.map((c) =>
                c.id === id ? { ...c, item: newItem } : c
            )
        }));
    };

    const setContent = (id: number, newContent: string) => {
        setResponseDto(prev => ({
            ...prev,
            contents: prev.contents.map((c) =>
                c.id === id ? { ...c, content: newContent } : c
            )
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

    const onClickDeleteItem = (id: number) => {
        setResponseDto(prev => ({
            ...prev,
            contents: prev.contents.filter(c => c.id !== id)
        }));
    };

    const onClickAddItemButton = () => {
        setIsAddMode(true)
    }


    const onClickSaveButton = () => {
        const updateDto = convertToUpdateDto(responseDto)
        onUpdate(updateDto);
        setShowModalType("detail")
    }

    const onClickConfirmButton = () => {
        setResponseDto(prev => ({
            ...prev,
            contents: [
                ...prev.contents,
                {
                    id: Date.now(), // 프론트 임시 ID
                    item: newContent.item,
                    content: newContent.content
                }
            ]
        }));
        setNewContent({ item: "", content: "" });
        setIsAddMode(false);
    };

    const onClickCancelButton = () => {
        setResponseDto(coverLetterResponseDto); // 초기화
        setIsAddMode(false);
        setNewContent({ item: "", content: "" });
        setShowModalType("detail"); // 상세 보기 모드로 전환
    };





    return (
        <>
            <ModalTitle title="경력기술서 상세보기" />
            <Input label={"제목"} placeholder={""} size={"l"} disabled={false} type={"text"} value={responseDto.title} setValue={setTitle} />
            <ExternalBox>
                {responseDto.contents.map((content) => (
                    <div key={content.id}>
                        <InternalBox >
                            <div className="mt-[-24px]">
                                <Input label={""} placeholder={""} size={"ibl"} disabled={false} type={"text"} value={content.item} setValue={(val) => setItem(content.id, val)} />
                            </div>
                            <div className="mt-[-24px]">
                                <TextArea size={"ibl"} label={""} placeholder={""} disabled={false} value={content.content} setValue={(val) => setContent(content.id, val)} />
                            </div>
                            <div className="flex justify-end mr-6">
                                <Button color={"action"} size={"s"} disabled={false} text={"항목삭제"} type={"button"} onClick={() => { onClickDeleteItem(content.id) }} />
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
                        <div>
                            <Button color={"white"} size={"s"} disabled={false} text={"확인"} type={"button"} onClick={onClickConfirmButton} />
                        </div>
                    </InternalBox>)}
            </ExternalBox>
            <div className="flex justify-end mr-6">
                <Button color={"action"} size={"s"} disabled={false} text={"취소"} type={"button"} onClick={onClickCancelButton} />
                {!isAddMode && (
                    <Button color={"white"} size={"s"} disabled={false} text={"항목 추가"} type={"button"} onClick={onClickAddItemButton} />
                )}
                <Button color={"theme"} size={"s"} disabled={false} text={"저장"} type={"button"} onClick={onClickSaveButton} />
            </div>
        </>
    )
}

export default CoverLetterUpdateModal;


