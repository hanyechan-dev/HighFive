import TextArea from "../../../common/components/input/TextArea"
import { InternalBox } from "../../../common/components/box/Box"
import ModalTitle from "../../../common/components/title/ModalTitle";
import type { CareerDescriptionResponseDto } from "../../myPageForMember/props/myPageForMemberProps";

interface CareerDescriptionInfoProps{
    careerDescriptionResponseDto : CareerDescriptionResponseDto
}

const CareerDescriptionInfo = ({careerDescriptionResponseDto
}: CareerDescriptionInfoProps) => {


    return (
        <>
            <ModalTitle title={careerDescriptionResponseDto.title} />
            <InternalBox>
                {careerDescriptionResponseDto.contents.map((content) =>
                (<TextArea 
                    key={content.id}
                    label={content.item}
                    placeholder={""}
                    disabled={true}
                    value={content.content}
                    setValue={() => { }}
                    size={"ibl"} />))}
            </InternalBox>
        </>

    )
};

export default CareerDescriptionInfo;