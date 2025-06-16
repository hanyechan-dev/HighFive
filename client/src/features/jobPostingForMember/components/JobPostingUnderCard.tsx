import Badge from "../../../common/components/badge/Badge";
import BuildingIcon from "../../../common/icons/BuildingIcon";
import CalenderIcon from "../../../common/icons/CalenderIcon";
import CareerTypeIcon from "../../../common/icons/CareerTypeIcon";
import CompanyTypeIcon from "../../../common/icons/CompanyTypeIcon";
import GraduationCapIcon from "../../../common/icons/GraduationCapIcon";
import JobIcon from "../../../common/icons/JobIcon";
import MapPinIcon from "../../../common/icons/MapPinIcon";
import type { JobPostingUnderCardDto } from "../props/JobPostingForMemberProps";



interface JobPostingUnderCardProps {
    jobPostingUnderCardDto : JobPostingUnderCardDto;
    onClick: (id: number) => void;
}
const JobPostingUnderCard = ({
    jobPostingUnderCardDto,
    onClick
}: JobPostingUnderCardProps) => {
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
    } = jobPostingUnderCardDto


    return (

        <div className="w-[345px]  cursor-pointer transition-all hover:shadow-[0_6px_20px_rgba(238,87,205,0.3)] hover:scale-[1.03] ml-[24px] mb-[24px]" onClick={() => onClick(id)}>
            <div className="text-base font-roboto border border-b-0 border-gray-300 rounded-t-lg w-[345px] h-[87px] flex flex-col space-y-1.5 p-6 py-4 bg-gradient-to-r from-[#EE57CD]/40 to-transparent">
                <div>
                    <div className="flex justify-start gap-7">
                        <span className="font-semibold truncate w-60 block">{title}</span>
                        <div className=" text-theme">{similarityScore}%</div>
                    </div>
                    
                </div>
                <div className="flex items-center">
                    <BuildingIcon className="mr-3" />
                    <span className="text-[#666666]">{companyName}</span>
                </div>
            </div>

            <div className="text-base font-roboto border border-t-0 border-gray-300 rounded-b-lg w-[345px] flex flex-col space-y-1.5 p-6">
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

                    <div className="flex items-center">
                        <MapPinIcon className="mr-3" />
                        <span className="min-w-[70px] font-medium">근무지:</span>
                        <span>{workLocation}</span>
                    </div>

                    <div className="flex items-center mb-2">
                        <CalenderIcon className="mr-3" />
                        <span className="min-w-[70px] font-medium">등록일:</span>
                        <span>{createdDate}</span>
                    </div>

                    <div className="flex items-center space-x-2">
                        <Badge icon={<CareerTypeIcon />} text={careerType} />
                        <Badge icon={<GraduationCapIcon />} text={educationLevel} />
                    </div>


                </div>

            </div>
        </div>
    );
}

export default JobPostingUnderCard;

