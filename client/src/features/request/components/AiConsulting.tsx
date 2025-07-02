
import { ExternalBox } from "../../../common/components/box/Box";
import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import type { AiConsultingContentResponseDto } from "../props/RequestProps";

interface AiConsultingProps {
    aiConsultingContentResponseDtos : AiConsultingContentResponseDto[];
    consultingType : string;
}

const AiConsulting = ({aiConsultingContentResponseDtos, consultingType} : AiConsultingProps) => {



    return (
        <>
            <ModalTitle title={consultingType === "첨삭" ? 'AI 첨삭' : 'AI 피드백'} />
            <ExternalBox color="semi_theme">
                {aiConsultingContentResponseDtos.map((aiConsultingContentResponseDto) =>
                (<TextArea 
                    key={aiConsultingContentResponseDto.id}
                    label={aiConsultingContentResponseDto.item}
                    placeholder={""}
                    disabled={true}
                    value={aiConsultingContentResponseDto.content}
                    setValue={() => { }}
                    size={"ml"} />))}
            </ExternalBox>
        </>
    );

};

export default AiConsulting;