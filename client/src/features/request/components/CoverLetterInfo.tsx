import { ExternalBox } from "../../../common/components/box/Box";
import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import type { CoverLetterResponseDto } from "../../myPageForMember/props/myPageForMemberProps";


interface CoverLetterInfoProps{
    coverLetterResponseDto: CoverLetterResponseDto
}

const CoverLetterInfo = ( { coverLetterResponseDto } : CoverLetterInfoProps) => {


    return (
        <>
            <ModalTitle title={coverLetterResponseDto.title} />
            <ExternalBox>
                {coverLetterResponseDto.contents.map((content) =>
                (<TextArea
                    key={content.id}
                    label={content.item}
                    placeholder={""}
                    disabled={true}
                    value={content.content}
                    setValue={() => { }}
                    size={"ml"} />))}
            </ExternalBox>
        </>

    )
};

export default CoverLetterInfo;