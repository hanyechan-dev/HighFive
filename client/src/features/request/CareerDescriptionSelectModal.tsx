
import { ExternalBox } from "../../common/components/box/Box";
import Button from "../../common/components/button/Button";
import ModalTitle from "../../common/components/title/ModalTitle";
import type { careerDescriptionOrCoverLetterSummaryProps } from "../../common/props/AiConsultingProps";

import RequestSelect from "./RequestSelect";
import TargetInfo from "./TargetInfo";


interface CareerDescriptionSelectModalPrpos {
    targetJob: string;
    targetCompanyName: string;
    setShowModalNumber: (showModalNumber: number) => void
    careerDescriptions: careerDescriptionOrCoverLetterSummaryProps[]
    clickedCareerDescriptionId: number;
    setClickedCareerDescriptionId: (id: number) => void
}

const CareerDescriptionSelectModal = ({
    targetJob,
    targetCompanyName,
    setShowModalNumber,
    careerDescriptions,
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
                {careerDescriptions.map(careerDescription =>
                (<RequestSelect
                    key={careerDescription.id}
                    careerOrCoverLetter={careerDescription}
                    isClicked={clickedCareerDescriptionId === careerDescription.id}
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