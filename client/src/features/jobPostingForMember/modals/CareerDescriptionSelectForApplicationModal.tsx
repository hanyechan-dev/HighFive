import { ExternalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import LoadingSpinner from "../../../common/components/loading/LoadingSpinner";
import ModalTitle from "../../../common/components/title/ModalTitle";
import ApplicationSelect from "../components/ApplicationSelect";
import CareerDescriptionOutput from "../components/CareerDescriptionOutput";
import { useJobPostingForMemberController } from "../customHooks/useJobPostingForMemberController";

interface CareerDescriptionSelectForApplicationModalProps {
    isShowModalNumber1Loading: boolean
    isCDLoading: boolean
}
const CareerDescriptionSelectForApplicationModal = ({ isShowModalNumber1Loading, isCDLoading }: CareerDescriptionSelectForApplicationModalProps) => {

    const {
        setShowModalNumber,
        careerDescriptionSummaryDtos,
        clickedCareerDescriptionId,
        setClickedCareerDescriptionId,
        careerDescriptionResponseDto,
    } = useJobPostingForMemberController();


    const onClickNext = () => {
        setShowModalNumber(2);
    }

    const onClickPrev = () => {
        setShowModalNumber(0);
    }



    return (
        <>
            <ModalTitle title="경력기술서 선택" />
            {isShowModalNumber1Loading ? (<LoadingSpinner message="경력기술서 리스트를 불러오는 중..." size="lg" color="theme" />) : (
                <>
                    <ExternalBox>
                        {careerDescriptionSummaryDtos.length > 0 ? (careerDescriptionSummaryDtos.map(careerDescriptionSummaryDto => (
                            <div key={careerDescriptionSummaryDto.id}>
                                <ApplicationSelect
                                    careerOrCoverLetterSummaryDto={careerDescriptionSummaryDto}
                                    isClicked={clickedCareerDescriptionId === careerDescriptionSummaryDto.id}
                                    setIsClicked={setClickedCareerDescriptionId}
                                />
                                {clickedCareerDescriptionId !== -1 && (
                                    <>
                                        {isCDLoading ? (
                                            <LoadingSpinner message="경력기술서를 불러오는 중..." size="lg" color="theme" />
                                        ) : (
                                            <CareerDescriptionOutput careerDescriptionResponseDto={careerDescriptionResponseDto} />
                                        )}
                                    </>
                                )}

                            </div>
                        ))):(<EmptyState title={"작성한 경력기술서가 없습니다."} text={""} />)}
                    </ExternalBox>

                    <div className="flex justify-end mr-6">
                        <Button color={"white"} size={"s"} disabled={false} text={"이전"} type={"button"} onClick={onClickPrev} />
                        <Button color={"theme"} size={"m"} disabled={false} text={"저장 및 다음"} type={"button"} onClick={onClickNext} />
                    </div>
                </>
            )}
        </>
    );

};

export default CareerDescriptionSelectForApplicationModal;