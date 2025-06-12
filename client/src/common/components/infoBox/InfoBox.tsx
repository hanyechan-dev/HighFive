interface InfoBoxProps {
    label: string;
    value: string;
}

const defaultSetting = 'h-[48px] text-center mr-[24px] mb-[24px] text-lg text-[#333] font-roboto flex flex-col items-center justify-center'
const labelSetting = 'font-semibold'

const InfoBox = ({ label, value }: InfoBoxProps) => {
    return (
        <div className={defaultSetting}>
            <div className={labelSetting}>
                {label}
            </div>
            <div className="text-[#666]">{value}</div>
        </div>
    );
};

export default InfoBox;