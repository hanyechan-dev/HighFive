import { ExternalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import ModalTitle from "../../../common/components/title/ModalTitle";
import ApplicationSelect from "../components/ApplicationSelect";
import CoverLetterOutput from "../components/CoverLetterOutput";
import { useJobPostingForMemberController } from "../customHooks/useJobPostingForMemberController";



const CoverLetterSelectForApplicationModal = () => {

    const {
        showModalNumber,
        setShowModalNumber,
        coverLetterSummaryDtos,
        clickedCoverLetterId,
        setClickedCoverLetterId,
        coverLetterResponseDto,
    } = useJobPostingForMemberController();


    const onClickNext = () => {
        setShowModalNumber(3);
    }

    const onClickPrev = () => {
        setShowModalNumber(1);
    }



    return (
        <>
            <ModalTitle title="자기소개서 선택" />
            <ExternalBox>
                {coverLetterSummaryDtos.map(coverLetterSummaryDto => (
                    <div key={coverLetterSummaryDto.id}>
                        <ApplicationSelect
                            careerOrCoverLetterSummaryDto={coverLetterSummaryDto}
                            isClicked={clickedCoverLetterId === coverLetterSummaryDto.id}
                            setIsClicked={setClickedCoverLetterId}
                        />
                        {clickedCoverLetterId !== -1 &&  <CoverLetterOutput coverLetterResponseDto={coverLetterResponseDto} />}
                    </div>
                ))}
            </ExternalBox>

            <div className="flex justify-end mr-6">
                <Button color={"white"} size={"s"} disabled={false} text={"이전"} type={"button"} onClick={onClickPrev} />
                <Button color={"theme"} size={"m"} disabled={showModalNumber === 3} text={"지원하기"} type={"button"} onClick={onClickNext} />
            </div>
        </>
    );

};

export default CoverLetterSelectForApplicationModal;