import type { CareerDescriptionSummaryDto, CoverLetterSummaryDto } from "../props/RequestProps";




interface RequestSelectProps {
    careerOrCoverLetterSummaryDto: CareerDescriptionSummaryDto | CoverLetterSummaryDto
    isClicked: boolean;
    setIsClicked: (id: number) => void
}

const defaultSetting = "w-[904px] h-[42px] border border-gray-300 rounded-lg p-[7px] pl-6 ml-[24px] font-roboto flex mb-6 cursor-pointer"
const unClickSetting = "hover:bg-semi_theme"
const clickedSetting = "bg-theme text-white"

const RequestSelect = ({ careerOrCoverLetterSummaryDto, isClicked, setIsClicked }: RequestSelectProps) => {
    const {
        id,
        title,
        createdDate,
    } = careerOrCoverLetterSummaryDto



    const onClick = () => {
        if (isClicked) {
            setIsClicked(-1)
        } else {
            setIsClicked(id)
        }
    }


    return (
        <div className={`${defaultSetting} ${isClicked ? clickedSetting : unClickSetting} justify-between items-center`} onClick={onClick}>
            <div className="w-[700px] truncate overflow-hidden">
                {title}
            </div>

            <div className={`${isClicked ? 'text-white/70' : 'text-gray-500'} mr-2 `}>
                {createdDate}
            </div>
        </div>
    );
}

export default RequestSelect;