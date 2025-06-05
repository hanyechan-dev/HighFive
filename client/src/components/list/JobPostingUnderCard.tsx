
import BuildingIcon from "../../icons/BuildingIcon";
import CompanyTypeIcon from "../../icons/CompanyTypeIcon";
import JobIcon from "../../icons/JobIcon";
import MapPinIcon from "../../icons/MapPinIcon";
import Badge from "../badge/Badge";
import GraduationCapIcon from "../../icons/GraduationCapIcon";

interface JobPostingUnderCardProps {
    id: number;
    title: string;
    companyName: string;
    companyType: string;
    job: string;
    workLocation: string;
    careerType: string;
    educationLevel: string;
    similarityScore: number | null;
    createdDate: string;
    onClick: (id: number) => void;
}
const JobPostingUnderCard = ({
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
}: JobPostingUnderCardProps) => {

    let scoreText = '';
    if (typeof similarityScore === 'number') {
        scoreText = similarityScore + '%';
    }

    return (

        <div className="w-[320px] cursor-pointer transition-all hover:shadow-lg hover:scale-[1.03] m-1" onClick={() => onClick(id)}>
            <div className="text-base font-roboto border border-b-0 border-gray-300 rounded-t-lg w-[320px] flex flex-col space-y-1.5 p-6 py-4 bg-gradient-to-r from-[#EE57CD]/40 to-transparent">
                <div>
                    <span className="font-semibold truncate w-60 block">{title}</span>
                </div>
                <div className="flex items-center">
                    <BuildingIcon className="mr-3" />
                    <span className="text-[#666666]">{companyName}</span>
                </div>
            </div>

            <div className="text-base font-roboto border border-t-0 border-gray-300 rounded-b-lg w-[320px] flex flex-col space-y-1.5 p-6 p-4">
                <div className="grid grid-cols-1 gap-2 text-sm text-[#666666]">
                    <div className="flex items-center">
                        <CompanyTypeIcon className="mr-3" />
                        <span className="min-w-[70px] font-medium">기업분류:</span>
                        <span>{companyType}</span>
                    </div>

                    <div className="flex items-center">
                        <JobIcon className="mr-3" />
                        <span className="min-w-[70px] font-medium">모집부문:</span>
                        <span>{job}</span>
                    </div>

                    <div className="flex items-center mb-2">
                        <MapPinIcon className="mr-3" />
                        <span className="min-w-[70px] font-medium">근무지:</span>
                        <span>{workLocation}</span>
                    </div>

                    <div className="flex items-center space-x-2">
                        <Badge text={careerType} />
                        <Badge icon={<GraduationCapIcon />} text={educationLevel} />
                    </div>


                </div>

            </div>
        </div>
    );
}

export default JobPostingUnderCard;

