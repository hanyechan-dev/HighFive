interface JobPostingSummaryProps {
    id:number;
    title: string;
    companyName: string;
    companyType: string;
    job: string;
    workLocation: string;
    careerType: string;
    educationLevel: string;
    similarityScore: number | null | string;
    createdDate: string;
    onClick: (id:number) => void;
}

const defaultSetting =
    'm-5 w-[1500px] h-[75px] text-base font-roboto border-t border-b border-gray-300 px-3 py-[13px] flex items-center hover:bg-semi_theme hover:cursor-pointer truncate';
const itemSetting = 'shrink-0 text-left mr-5';
const similarityScoreSetting = 'text-theme text-center';
const sizeClass = {
    s: 'w-[100px]',
    m: 'w-[150px]',
    l: 'w-[500px]',
};

function JobPostingSummary({
    id,
    title,
    companyName,
    companyType,
    job,
    workLocation,
    careerType,
    educationLevel,
    similarityScore,
    createdDate,
    onClick
}: JobPostingSummaryProps) {

    let scoreText = '';
    if (similarityScore != null) {
        scoreText = similarityScore + '%';
    }

    return (
        <div className={defaultSetting} onClick={()=>onClick(id)}>
            <span className={`${sizeClass.s} ${itemSetting}`}>{companyName}</span>
            <span className={`${sizeClass.s} ${itemSetting}`}>{companyType}</span>
            <span className={`${sizeClass.l} ${itemSetting}`}>{title}</span>
            <span className={`${sizeClass.s} ${itemSetting}`}>{job}</span>
            <span className={`${sizeClass.s} ${itemSetting}`}>{workLocation}</span>
            <span className={`${sizeClass.m} ${itemSetting}`}>{careerType}</span>
            <span className={`${sizeClass.s} ${itemSetting}`}>{educationLevel}</span>
            <span className={`${sizeClass.s} ${itemSetting}`}>{createdDate}</span>
            {similarityScore !== null && (
                <span className={`${sizeClass.s} ${itemSetting} ${similarityScoreSetting}`}>
                    {scoreText}
                </span>
            )}
        </div>
    );
}

export default JobPostingSummary;