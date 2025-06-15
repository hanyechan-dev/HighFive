
import type { JobPostingSummaryForMemberDto } from "../props/JobPostingForMemberProps";

interface JobPostingProps {
    jobPostingSummaryForMemberDto: JobPostingSummaryForMemberDto;
    onClick: (id: number) => void
}

const listDefaultSetting =
    'w-[1452px] h-[75px] text-base font-roboto border-b px-3 py-3 flex items-center hover:bg-semi_theme hover:cursor-pointer truncate mx-[24px]';
const listItemDefaultSetting = 'shrink-0 text-left mr-5';
const similarityScoreSetting = 'text-theme pl-3'
const sizeClass = {
    s: 'w-[100px]',
    m: 'w-[150px]',
    l: 'w-[450px]',
};



const JobPostingListItem = ({ jobPostingSummaryForMemberDto, onClick }: JobPostingProps) => {
    const {
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
    } = jobPostingSummaryForMemberDto;

    return (
        <>


            <div className={listDefaultSetting} onClick={() => onClick(id)}>
                <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{companyName}</span>
                <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{companyType}</span>
                <span className={`${sizeClass.l} ${listItemDefaultSetting}`}>{title}</span>
                <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{job}</span>
                <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{workLocation}</span>
                <span className={`${sizeClass.m} ${listItemDefaultSetting}`}>{careerType}</span>
                <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{educationLevel}</span>
                <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{createdDate}</span>
                {similarityScore !== null && (
                    <span className={`${sizeClass.s} ${listItemDefaultSetting} ${similarityScoreSetting}`}>
                        {similarityScore}%
                    </span>
                )}
            </div>


        </>
    );
}

export default JobPostingListItem;

