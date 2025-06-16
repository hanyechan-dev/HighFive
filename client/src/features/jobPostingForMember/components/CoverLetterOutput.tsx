import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import type { CoverLetterResponseDto } from "../../myPageForMember/props/myPageForMemberProps";


interface CoverLetterOutputProps {
    coverLetterResponseDto : CoverLetterResponseDto;
}

const CoverLetterOutput = ({coverLetterResponseDto} : CoverLetterOutputProps) => {
    return (
        <>
            <ModalTitle title={coverLetterResponseDto.title} />
            {coverLetterResponseDto.contents.map((content) =>
            (<TextArea
                key={content.id}
                label={content.item}
                placeholder={""}
                disabled={true}
                value={content.content}
                setValue={() => { }}
                size={"ml"} />))}
        </>
    )

}
export default CoverLetterOutput;