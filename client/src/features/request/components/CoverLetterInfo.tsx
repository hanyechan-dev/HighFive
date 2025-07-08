import { InternalBox } from "../../../common/components/box/Box";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import TextArea from "../../../common/components/input/TextArea";
import ModalTitle from "../../../common/components/title/ModalTitle";
import type { CoverLetterResponseDto } from "../../myPageForMember/props/myPageForMemberProps";


interface CoverLetterInfoProps {
    coverLetterResponseDto: CoverLetterResponseDto | undefined
}

const CoverLetterInfo = ({ coverLetterResponseDto }: CoverLetterInfoProps) => {

    if (coverLetterResponseDto) {
        return (
            <>
                <ModalTitle title={coverLetterResponseDto.title} />
                <InternalBox>
                    {coverLetterResponseDto.contents.map((content) =>
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
                    <EmptyState title={"해당 지원서에 자기소개서를 첨부하지 않았습니다."} text={""} />
                </InternalBox>
            </>
        )
    }


};

export default CoverLetterInfo;