import Badge from "../../../common/components/badge/Badge";
import BuildingIcon from "../../../common/icons/BuildingIcon";
import CalenderIcon from "../../../common/icons/CalenderIcon";
import CareerTypeIcon from "../../../common/icons/CareerTypeIcon";
import CompanyTypeIcon from "../../../common/icons/CompanyTypeIcon";
import GraduationCapIcon from "../../../common/icons/GraduationCapIcon";
import JobIcon from "../../../common/icons/JobIcon";
import MapPinIcon from "../../../common/icons/MapPinIcon";
import type { JobPostingMainCardDto } from "../props/JobPostingForMemberProps";
import SimilarityCircle from "./SimilarityCircle";


interface JobPostingMainCardProps {
    jobPostingMainCardDto: JobPostingMainCardDto
    onClick: (id: number) => void;
}
const JobPostingMainCard = ({
    jobPostingMainCardDto,
    onClick
}: JobPostingMainCardProps) => {
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
        createdDate,
        imageUrl
    } = jobPostingMainCardDto


    return (
        <div
            className="rounded-lg bg-transparent group w-[345px] h-[578px] cursor-pointer hover:shadow-[0_6px_20px_rgba(238,87,205,0.3)] hover:scale-[1.03] transition-all ml-[24px] mb-5"
            onClick={() => onClick(id)}
        >
            {/* 헤더 */}
            <div className="relative h-[270px] overflow-hidden rounded-t-lg border border-b-0 border-gray-300">
                {/* 로고 */}
                <div className="absolute inset-0">
                    {/* 흐릿한 배경용 로고 */}
                    <img
                        src={`http://192.168.0.121:8090${imageUrl}`}
                        className="w-full h-full object-contain blur-2xl scale-125 opacity-100"
                    />

                    {/* 선명한 실제 로고 */}
                    <img
                        src={`http://192.168.0.121:8090${imageUrl}`}
                        className="absolute inset-0 w-full h-full object-contain p-4 opacity-100"
                    />
                </div>



            </div>
            {/* 끼워지는 정보 */}
            <div
                className="border-x border-gray-300 absolute bottom-50 left-0 w-[345px] h-[60px] px-4 py-1 text-center text-theme text-base font-roboto
                 opacity-0 group-hover:opacity-100 transition-opacity duration-300 z-10 bg-theme bg-opacity-10"
            >


                <div className="flex justify-start items-center mt-1">
                    <BuildingIcon className="mr-2" />
                    <span className="font-medium">{companyName}</span>
                </div>

                <div className="flex justify-start">
                    <div className="truncate">{title}</div>
                </div>
            </div>

            {/* 바디 */}
            <div className="transition-transform duration-300 group-hover:translate-y-[60px] bg-white border border-t-0 border-gray-300 rounded-b-lg">
                <div className="text-black text-base font-roboto  w-[345px] flex flex-col space-y-1.5 p-6">
                    <div className="grid grid-cols-1 gap-2 text-sm">

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

                        <div className="flex items-center justify-between">
                            <div className="space-y-2">
                                <Badge icon={<CareerTypeIcon />} text={careerType} />
                                <Badge icon={<GraduationCapIcon />} text={educationLevel} />
                            </div>
                            <SimilarityCircle similarityScore={similarityScore} />
                        </div>



                    </div>

                </div>
            </div>
        </div>
    );
}

export default JobPostingMainCard;





