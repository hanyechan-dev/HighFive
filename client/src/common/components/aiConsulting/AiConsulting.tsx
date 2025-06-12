import type { aiContentsProps } from "../../props/AiConsultingProps";
import { ExternalBox } from "../box/Box";
import TextArea from "../input/TextArea";
import ModalTitle from "../title/ModalTitle";

interface AiConsultingProps {
    aiContents : aiContentsProps;
    consultingType : "첨삭" | "피드백";
}

const AiConsulting = ({aiContents, consultingType} : AiConsultingProps) => {

    return (
        <>
            <ModalTitle title={consultingType === "첨삭" ? 'AI 첨삭' : 'AI 피드백'} />
            <ExternalBox color="semi_theme">
                {aiContents.aiContents.map((aiContent) =>
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

export default AiConsulting;