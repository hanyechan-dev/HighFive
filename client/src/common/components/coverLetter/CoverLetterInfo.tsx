import TextArea from "../input/TextArea"
import { ExternalBox } from "../box/Box"
import type { coverLetterProps } from "../../props/AiConsultingProps";
import ModalTitle from "../title/ModalTitle";

interface CoverLetterInfoProps{
    coverLetter: coverLetterProps
}

const CoverLetterInfo = ( { coverLetter } : CoverLetterInfoProps) => {


    return (
        <>
            <ModalTitle title={coverLetter.title} />
            <ExternalBox>
                {coverLetter.contents.map((content) =>
                (<TextArea
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