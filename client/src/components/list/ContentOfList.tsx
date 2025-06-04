interface ContentProps {
    id:number;
    title: string;
    companyName: string;
    companyType: string;
    job: string;
    workLocation: string;
    careerType: string;
    educationLevel: string;
    similarityScore: number | null;
    createdDate: string;
    onClick: (id:number) => void;
}

const defaultSetting =
    'w-[1500px] h-[50px] text-base font-roboto border-t border-gray-300 px-3 py-[13px] flex items-center hover:bg-semi_theme';
const itemSetting = 'shrink-0 text-left mr-5';
const similarityScoreSetting = 'w-[50px] h-[30px] bg-theme text-white border border-theme rounded-lg px-2 text-center';
const sizeClass = {
    s: 'w-[100px]',
    m: 'w-[150px]',
    l: 'w-[500px]',
};

function ContentOfList({
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
}: ContentProps) {

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

export default ContentOfList;