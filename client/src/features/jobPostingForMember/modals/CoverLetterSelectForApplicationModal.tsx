import { ExternalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import LoadingSpinner from "../../../common/components/loading/LoadingSpinner";
import ModalTitle from "../../../common/components/title/ModalTitle";
import ApplicationSelect from "../components/ApplicationSelect";
import CoverLetterOutput from "../components/CoverLetterOutput";
import { useJobPostingForMemberController } from "../customHooks/useJobPostingForMemberController";

interface CoverLetterSelectForApplicationModalProps {
    isShowModalNumber2Loading: boolean
    isCLLoading: boolean
}

const CoverLetterSelectForApplicationModal = ({ isShowModalNumber2Loading, isCLLoading }: CoverLetterSelectForApplicationModalProps) => {

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
            {isShowModalNumber2Loading ? (<LoadingSpinner message="자기소개서 리스트를 불러오는 중..." size="lg" color="theme" />) : (
                <>
                    <ExternalBox>
                        {coverLetterSummaryDtos.map(coverLetterSummaryDto => (
                            <div key={coverLetterSummaryDto.id}>
                                <ApplicationSelect
                                    careerOrCoverLetterSummaryDto={coverLetterSummaryDto}
                                    isClicked={clickedCoverLetterId === coverLetterSummaryDto.id}
                                    setIsClicked={setClickedCoverLetterId}
                                />
                                {clickedCoverLetterId !== -1 && (
                                    <>
                                        {isCLLoading ? (
                                            <LoadingSpinner message="자기소개서를 불러오는 중..." size="lg" color="theme" />
                                        ) : (
                                            <CoverLetterOutput coverLetterResponseDto={coverLetterResponseDto} />
                                        )}
                                    </>
                                )}
                            </div>
                        ))}
                    </ExternalBox>

                    <div className="flex justify-end mr-6">
                        <Button color={"white"} size={"s"} disabled={false} text={"이전"} type={"button"} onClick={onClickPrev} />
                        <Button color={"theme"} size={"m"} disabled={showModalNumber === 3} text={"지원하기"} type={"button"} onClick={onClickNext} />
                    </div>
                </>
            )}
        </>
    );

};

export default CoverLetterSelectForApplicationModal;