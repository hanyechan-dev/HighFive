import { ExternalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import ModalTitle from "../../../common/components/title/ModalTitle";
import ApplicationSelect from "../components/ApplicationSelect";
import CareerDescriptionOutput from "../components/CareerDescriptionOutput";
import { useJobPostingForMemberController } from "../customHooks/useJobPostingForMemberController";



const CareerDescriptionSelectForApplicationModal = () => {

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
            <ExternalBox>
                {careerDescriptionSummaryDtos.map(careerDescriptionSummaryDto => (
                    <div key={careerDescriptionSummaryDto.id}>
                        <ApplicationSelect
                            careerOrCoverLetterSummaryDto={careerDescriptionSummaryDto}
                            isClicked={clickedCareerDescriptionId === careerDescriptionSummaryDto.id}
                            setIsClicked={setClickedCareerDescriptionId}
                        />
                        {clickedCareerDescriptionId !== -1 &&  <CareerDescriptionOutput careerDescriptionResponseDto={careerDescriptionResponseDto} />}
                    </div>
                ))}
            </ExternalBox>

            <div className="flex justify-end mr-6">
                <Button color={"white"} size={"s"} disabled={false} text={"이전"} type={"button"} onClick={onClickPrev} />
                <Button color={"theme"} size={"m"} disabled={false} text={"저장 및 다음"} type={"button"} onClick={onClickNext} />
            </div>
        </>
    );

};

export default CareerDescriptionSelectForApplicationModal;