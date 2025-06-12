import type { CareerDescriptionOrCoverLetterSummaryProps } from "../../common/props/MemberProps";

interface RequestSelectProps{
    careerOrCoverLetter : CareerDescriptionOrCoverLetterSummaryProps
    isClicked : boolean; 
    setIsClicked : (id : number) => void
}

const defaultSetting = "w-[904px] h-[42px] border border-gray-300 rounded-lg p-[7px] pl-6 ml-[24px] font-roboto flex mb-6 cursor-pointer"
const unClickSetting = "hover:bg-semi_theme"
const clickedSetting = "bg-theme text-white"

const RequestSelect = ({careerOrCoverLetter, isClicked , setIsClicked} : RequestSelectProps) =>{
    const onClick = () =>{
        if(isClicked){
            setIsClicked(-1)
        }else{
            setIsClicked(careerOrCoverLetter.id)
        }
    }


    return(
        <div className={`${defaultSetting} ${isClicked?clickedSetting:unClickSetting } justify-between items-center`} onClick={onClick}>
            <div className="w-[700px] truncate overflow-hidden">
                {careerOrCoverLetter.title}
            </div>

            <div className={`${isClicked?'text-white/70':'text-gray-500'} mr-2 `}>
                {careerOrCoverLetter.createdDate}
            </div>
        </div>
    );
}

export default RequestSelect;