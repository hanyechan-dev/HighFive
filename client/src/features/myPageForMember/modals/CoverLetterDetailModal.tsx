
import Input from "../../../common/components/input/Input";
import ModalTitle from "../../../common/components/title/ModalTitle"
import { ExternalBox, InternalBox } from "../../../common/components/box/Box";
import TextArea from "../../../common/components/input/TextArea";
import Button from "../../../common/components/button/Button";
import type { CoverLetterResponseDto } from "../props/myPageForMemberProps";

interface CoverLetterDetailModalProps {
    coverLetterResponseDto: CoverLetterResponseDto;
    setShowModalType : ( modalType : string ) => void
    setShowModal : (showModal : boolean) => void
    onClickDelete : (id : number) => void
}

const CoverLetterDetailModal = ({
    coverLetterResponseDto,
    setShowModalType,
    setShowModal,
    onClickDelete
}: CoverLetterDetailModalProps) => {

    const { title, contents } = coverLetterResponseDto

    const onClickDeleteButton =() =>{
        onClickDelete(coverLetterResponseDto.id)
        setShowModal(false);
    }

    const onClickUpdateButton = () =>{
        setShowModalType("update")
    }


    return (
        <>
            <ModalTitle title="자기소개서 상세보기" />
            <Input label={"제목"} placeholder={""} size={"l"} disabled={true} type={"text"} value={title} setValue={() => { }} />
            <ExternalBox>
                {contents.map((content) => (
                        <InternalBox key={content.id}>
                            <div className="mt-[-24px]">
                                <Input label={""} placeholder={""} size={"ibl"} disabled={true} type={"text"} value={content.item} setValue={() => { }} />
                            </div>
                            <div className="mt-[-24px]">
                                <TextArea size={"ibl"} label={""} placeholder={""} disabled={true} value={content.content} setValue={() => { }} />
                            </div>
                        </InternalBox>
                ))}
            </ExternalBox>
            <div className="flex justify-end mr-6">
                    <Button color={"action"} size={"s"} disabled={false} text={"삭제"} type={"button"} onClick={onClickDeleteButton} />
                    <Button color={"theme"} size={"s"} disabled={false} text={"수정"} type={"button"} onClick={onClickUpdateButton} />
            </div>
        </>
    )
}

export default CoverLetterDetailModal;


