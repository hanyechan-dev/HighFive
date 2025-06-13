
import { ExternalBox } from "../../common/components/box/Box";
import Button from "../../common/components/button/Button";
import ModalTitle from "../../common/components/title/ModalTitle";
import type { CareerDescriptionSummaryDto } from "./RequestProps";

import RequestSelect from "./RequestSelect";
import TargetInfo from "./TargetInfo";


interface CareerDescriptionSelectModalPrpos {
    targetJob: string;
    targetCompanyName: string;
    setShowModalNumber: (showModalNumber: number) => void
    careerDescriptionSummaryDtos: CareerDescriptionSummaryDto[]
    clickedCareerDescriptionId: number;
    setClickedCareerDescriptionId: (id: number) => void
}

const CareerDescriptionSelectModal = ({
    targetJob,
    targetCompanyName,
    setShowModalNumber,
    careerDescriptionSummaryDtos,
    clickedCareerDescriptionId,
    setClickedCareerDescriptionId,
}: CareerDescriptionSelectModalPrpos) => {


    const onClickNext = () => {
        setShowModalNumber(3);
    }

    const onClickPrev = () => {
        setShowModalNumber(1);
    }



    return (
        <>
            <ModalTitle title="경력기술서 선택" />
            <TargetInfo targetCompanyName={targetCompanyName} targetJob={targetJob} />
            <ExternalBox>
                {careerDescriptionSummaryDtos.map(careerDescriptionSummaryDto =>
                (<RequestSelect
                    key={careerDescriptionSummaryDto.id}
                    careerOrCoverLetter={careerDescriptionSummaryDto}
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

export default CareerDescriptionSelectModal;