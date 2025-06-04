interface ContentProps {
    id: number;
    title : string;
    companyName : string;
    companyType: string;
    job : string;
    workLocation : string;
    careerType : string;
    educationLevel : string; 
    similarityScore : number;
    createdDate : string; 
}

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
    createdDate
}: ContentProps) {

    const defaultSetting = 'w-[1500px] h-[50px] text-base font-roboto border-t border-gray-300 px-3 grid grid-cols-8 items-center'

    return (
        <div className={defaultSetting} key={id}>
            <span className="w-[200px] text-center">{companyName}</span>
            <span className="w-[150px] text-center">{companyType}</span>
            <span className="w-[300px] text-center">{title}</span>
            <span className="w-[150px] text-center">{job}</span>
            <span className="w-[150px] text-center">{workLocation}</span>
            <span className="w-[100px] text-center">{careerType}</span>
            <span className="w-[100px] text-center">{educationLevel}</span>
            <span className="w-[150px] text-center">{createdDate}</span>
            <span className="w-[150px] text-center">{similarityScore}</span>
        </div>
    );
}

export default ContentOfList;