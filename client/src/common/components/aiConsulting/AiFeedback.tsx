import type { aiContentsProps } from "../../props/AiConsultingProps";
import { ExternalBox } from "../box/Box";
import TextArea from "../input/TextArea";
import ModalTitle from "../title/ModalTitle";

const AiFeedback = ({aiContents} : aiContentsProps) => {

    return (
        <>
            <ModalTitle title='AI 피드백' />
            <ExternalBox color="semi_theme">
                {aiContents.map((aiContent) =>
                (<TextArea label={aiContent.item}
                    placeholder={""}
                    disabled={true}
                    value={aiContent.content}
                    setValue={() => { }}
                    size={"ml"} />))}
            </ExternalBox>
        </>
    );

};

export default AiFeedback;