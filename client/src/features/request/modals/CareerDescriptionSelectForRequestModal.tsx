import { ExternalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import ModalTitle from "../../../common/components/title/ModalTitle";
import BasicInfo from "../components/BasicInfo";

import RequestSelect from "../components/RequestSelect";
import { useRequestController } from "../customHooks/useRequestController";



const CareerDescriptionSelectForRequestModal = () => {

    const {
        targetJob,
        targetCompanyName,
        setShowModalNumber,
        careerDescriptionSummaryDtos,
        clickedCareerDescriptionId,
        setClickedCareerDescriptionId,
    } = useRequestController();


    const onClickNext = () => {
        setShowModalNumber(3);
    }

    const onClickPrev = () => {
        setShowModalNumber(1);
    }



    return (
        <>
            <ModalTitle title="경력기술서 선택" />
            <BasicInfo targetCompanyName={targetCompanyName} targetJob={targetJob} />
            <ExternalBox>
                {careerDescriptionSummaryDtos.map(careerDescriptionSummaryDto =>
                (<RequestSelect
                    key={careerDescriptionSummaryDto.id}
                    careerOrCoverLetterSummaryDto={careerDescriptionSummaryDto}
                    isClicked={clickedCareerDescriptionId === careerDescriptionSummaryDto.id}
                    setIsClicked={setClickedCareerDescriptionId} />
                ))}
            </ExternalBox>



            <div className="flex justify-end mr-6">
                <Button color={"white"} size={"s"} disabled={false} text={"이전"} type={"button"} onClick={onClickPrev} />
                <Button color={"theme"} size={"m"} disabled={false} text={"저장 및 다음"} type={"button"} onClick={onClickNext} />
            </div>
        </>
    );

};

export default CareerDescriptionSelectForRequestModal;