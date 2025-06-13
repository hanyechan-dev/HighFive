import { ExternalBox } from "../../common/components/box/Box";
import TextArea from "../../common/components/input/TextArea";
import ModalTitle from "../../common/components/title/ModalTitle";
import type { ConsultantConsultingContentResponseDto } from "./RequestProps";


interface ConsultantConsultingProps {
    consultantConsultingContentResponseDtos : ConsultantConsultingContentResponseDto[];
    consultingType : string;
}

const ConsultantConsulting = ({consultantConsultingContentResponseDtos, consultingType} : ConsultantConsultingProps) => {

    return (
        <>
            <ModalTitle title={consultingType === "첨삭" ? '컨설턴트 첨삭' : '컨설턴트 피드백'} />
            <ExternalBox>
                {consultantConsultingContentResponseDtos.map((consultantConsultingContentResponseDto) =>
                (<TextArea key={consultantConsultingContentResponseDto.id}
                    label={consultantConsultingContentResponseDto.item}
                    placeholder={""}
                    disabled={true}
                    value={consultantConsultingContentResponseDto.content}
                    setValue={() => { }}
                    size={"ml"} />))}
            </ExternalBox>
        </>
    );

};

export default ConsultantConsulting;