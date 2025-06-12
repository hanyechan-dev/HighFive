import InfoBox from "../../common/components/infoBox/InfoBox";

interface TargetInfoProps{
    targetCompanyName : string;
    targetJob : string;
}


const TargetInfo = ({targetCompanyName, targetJob} : TargetInfoProps) => {

    return (
        <div className="flex justify-center gap-12">
            <InfoBox label={"지원 기업"} value={targetCompanyName} />
            <InfoBox label={"지원 부문"} value={targetJob} />
        </div>

    );


}
export default TargetInfo;

