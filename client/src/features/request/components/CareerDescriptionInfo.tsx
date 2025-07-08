import TextArea from "../../../common/components/input/TextArea"
import { InternalBox } from "../../../common/components/box/Box"
import ModalTitle from "../../../common/components/title/ModalTitle";
import type { CareerDescriptionResponseDto } from "../../myPageForMember/props/myPageForMemberProps";
import EmptyState from "../../../common/components/emptyState/EmptyState";

interface CareerDescriptionInfoProps {
    careerDescriptionResponseDto: CareerDescriptionResponseDto | undefined
}

const CareerDescriptionInfo = ({ careerDescriptionResponseDto
}: CareerDescriptionInfoProps) => {


    if (careerDescriptionResponseDto != undefined) {
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
    }
    else {
        return (
            <>
                <ModalTitle title={""} />
                <InternalBox>
                    <EmptyState title={"해당 지원서에 경력기술서를 첨부하지 않았습니다."} text={""} />
                </InternalBox>
            </>
        )
    }
};

export default CareerDescriptionInfo;