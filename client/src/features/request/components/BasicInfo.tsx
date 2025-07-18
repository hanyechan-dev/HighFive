import InfoBox from "../../../common/components/infoBox/InfoBox";


interface TargetInfoProps{
    targetCompanyName : string;
    targetJob : string;
}


const BasicInfo = ({targetCompanyName, targetJob} : TargetInfoProps) => {

    return (
        <div className="flex justify-start ml-12 gap-12">
            <InfoBox label={"지원 기업"} value={targetCompanyName} />
            <InfoBox label={"지원 부문"} value={targetJob} />
        </div>

    );


}
export default BasicInfo;

