import { useState } from "react";
import Input from "../../../common/components/input/Input";
import ModalTitle from "../../../common/components/title/ModalTitle"
import CommonModal from "../../../common/modals/CommonModal"
import type { CareerDescriptionResponseDto, CoverLetterResponseDto } from "../props/myPageForMemberProps";
import { ExternalBox, InternalBox } from "../../../common/components/box/Box";
import TextArea from "../../../common/components/input/TextArea";
import Button from "../../../common/components/button/Button";


interface CDAndCLDetailModalProps {
    responseDto : CareerDescriptionResponseDto | CoverLetterResponseDto;
    documentType: "경력기술서" | "자기소개서";
    onClose: () => void;
    onClickDelete: () => void;
        
}

const CDAndCLDetailModal = ({ responseDto, documentType, onClose, onClickDelete }: CDAndCLDetailModalProps) => {

    const [isDetailMode, setIsDetailMode] = useState(true);

    const onClickUpdate = () => {
        setIsDetailMode(false);
    };

    const onClickSave = () => {
        setIsDetailMode(true);
    };

    return (
        <CommonModal size="l" onClose={onClose}>
            <ModalTitle title={`${documentType} 상세보기`} />
            <Input label={"제목"} placeholder={""} size={"l"} disabled={isDetailMode} type={"text"} value={responseDto.title} setValue={function (value: string): void {
                throw new Error("Function not implemented.");
            } } />
            <ExternalBox>
                {responseDto.contents.map((content, index) => (
                    <InternalBox key={index}>
                    <div className="mt-[-24px]">
                    <Input label={""} placeholder={""} size={"ibl"} disabled={isDetailMode} type={"text"} value={content.item} setValue={function (value: string): void {
                        throw new Error("Function not implemented.");
                    } } />
                    </div>
                    <div className="mt-[-24px]">
                        <TextArea size={"ibl"} label={""} placeholder={""} disabled={isDetailMode} value={content.content} setValue={function (value: string): void {
                            throw new Error("Function not implemented.");
                        } } />
                    </div>
                    </InternalBox>
                ))}
            </ExternalBox>
            <div className="flex justify-end mr-6">
                <Button color={"action"} size={"s"} disabled={false} text={"삭제"} type={"button"} onClick={onClickDelete}/>
                {isDetailMode ? (
                    <Button color={"theme"} size={"s"} disabled={false} text={"수정"} type={"button"} onClick={onClickUpdate}/>
                ) : (
                    <Button color={"theme"} size={"s"} disabled={false} text={"저장"} type={"button"} onClick={onClickSave}/>
                )}
            </div>
        </CommonModal>
    )
}

export default CDAndCLDetailModal