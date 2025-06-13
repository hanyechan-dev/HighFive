import TextArea from "../input/TextArea"
import { ExternalBox } from "../box/Box"
import ModalTitle from "../title/ModalTitle";
import type { coverLetterJson } from "../../../features/request/RequestProps";

interface CoverLetterInfoProps{
    coverLetter: coverLetterJson
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