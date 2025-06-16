import { useState } from "react";
import { ExternalBox } from "../../../common/components/box/Box";
import Button from "../../../common/components/button/Button";
import ModalTitle from "../../../common/components/title/ModalTitle";
import type { consultingTypeEnum } from "../../../common/enum/Enum";
import BasicInfo from "../components/BasicInfo";
import RequestSelect from "../components/RequestSelect";
import { useRequestController } from "../customHooks/useRequestController";




interface CoverLetterSelectForRequestModalProps {
    consultingType: consultingTypeEnum
}



const CoverLetterSelectForRequestModal = ({ consultingType
}: CoverLetterSelectForRequestModalProps) => {

    const {
        targetJob,
        targetCompanyName,
        setShowModalNumber,
        coverLetterSummaryDtos,
        clickedCoverLetterId,
        setClickedCoverLetterId,
    } = useRequestController();



    const [showAlert, setShowAlert] = useState(false);



    const onClickNext = () => {

        if (clickedCoverLetterId === -1) {
            setShowAlert(true);
        } else {
            setShowModalNumber(4); // 상위에서 유즈이펙트로 4가 되면 API 호출
        }
    }

    const onClickPrev = () => {
        setShowModalNumber(2);
    }



    return (
        <>
            <ModalTitle title="자기소개서 선택" />
            <BasicInfo targetCompanyName={targetCompanyName} targetJob={targetJob} />
            <ExternalBox>
                {coverLetterSummaryDtos.map(coverLetterSummaryDto =>
                (<RequestSelect
                    key={coverLetterSummaryDto.id}
                    careerOrCoverLetterSummaryDto={coverLetterSummaryDto}
                    isClicked={clickedCoverLetterId === coverLetterSummaryDto.id}
                    setIsClicked={setClickedCoverLetterId} />
                ))}
            </ExternalBox>
            {showAlert && (<div className="flex justify-end pr-6 pb-6 text-sm font-roboto text-blue-400">자기소개서를 입력해주세요</div>)}
            <div className="flex justify-end mr-6">
                <Button color={"white"} size={"s"} disabled={false} text={"이전"} type={"button"} onClick={onClickPrev} />
                <Button color={"theme"} size={"m"} disabled={false} text={`AI ${consultingType} 요청`} type={"button"} onClick={onClickNext} />
            </div>
        </>
    );

};

export default CoverLetterSelectForRequestModal;