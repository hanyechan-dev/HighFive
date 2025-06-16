import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import type { CareerDescriptionResponseDto } from "../props/JobPostingForMemberProps";


interface CareerDescriptionOutputProps {
    careerDescriptionResponseDto : CareerDescriptionResponseDto;
}

const CareerDescriptionOutput = ({careerDescriptionResponseDto} : CareerDescriptionOutputProps) => {
    return (
        <>
            <ModalTitle title={careerDescriptionResponseDto.title} />
            {careerDescriptionResponseDto.contents.map((content) =>
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
export default CareerDescriptionOutput;