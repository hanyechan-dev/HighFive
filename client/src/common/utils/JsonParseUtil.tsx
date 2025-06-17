import type { Resume } from "../../features/jobPostingForMember/props/JobPostingForMemberProps";
import type { CareerDescriptionResponseDto, CoverLetterResponseDto } from "../../features/myPageForMember/props/myPageForMemberProps";


export const parseStringToJson = (resumeJson: string, careerDescriptionJson: string, coverLetterJson: string,) => {
    const resume = JSON.parse(resumeJson) as Resume;
    const educationResponseDtos = resume.educationResponseDtos;
    const careerResponseDtos = resume.careerResponseDtos;
    const certificationResponseDtos = resume.certificationResponseDtos;
    const languageTestResponseDtos = resume.languageTestResponseDtos;
    const parsedCareerDescriptionJson = JSON.parse(careerDescriptionJson);
    const careerDescriptionResponseDto = parsedCareerDescriptionJson.careerDescriptionResponseDto as CareerDescriptionResponseDto
    const parsedCoverLetterJson = JSON.parse(coverLetterJson);
    const coverLetterResponseDto = parsedCoverLetterJson.coverLetterResponseDto as CoverLetterResponseDto;

    return{
        educationResponseDtos,
        careerResponseDtos,
        certificationResponseDtos,
        languageTestResponseDtos,
        careerDescriptionResponseDto,
        coverLetterResponseDto
    }
}