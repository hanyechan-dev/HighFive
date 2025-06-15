import TextArea from "../../../common/components/input/TextArea"
import { ExternalBox } from "../../../common/components/box/Box"
import ModalTitle from "../../../common/components/title/ModalTitle";
import type { CareerDescriptionResponseDto } from "../props/RequestProps";

interface CareerDescriptionInfoProps{
    careerDescriptionResponseDto : CareerDescriptionResponseDto
}

const CareerDescriptionInfo = ({careerDescriptionResponseDto
}: CareerDescriptionInfoProps) => {


    return (
        <>
            <ModalTitle title={careerDescriptionResponseDto.title} />
            <ExternalBox>
                {careerDescriptionResponseDto.contents.map((content) =>
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

export default CareerDescriptionInfo;