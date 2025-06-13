import TextArea from "../input/TextArea"
import { ExternalBox } from "../box/Box"
import ModalTitle from "../title/ModalTitle";
import type { careerDescriptionJson } from "../../../features/request/RequestProps";

interface CareerDescriptionInfoProps{
    careerDescription : careerDescriptionJson
}

const CareerDescriptionInfo = ({careerDescription
}: CareerDescriptionInfoProps) => {


    return (
        <>
            <ModalTitle title={careerDescription.title} />
            <ExternalBox>
                {careerDescription.contents.map((content) =>
                (<TextArea
                    label={content.item}
                    placeholder={""}
                    disabled={true}
                    value={content.content}
                    setValue={() => { }}
                    size={"ml"} />))}
            </ExternalBox>
        </>

    )
};

export default CareerDescriptionInfo;