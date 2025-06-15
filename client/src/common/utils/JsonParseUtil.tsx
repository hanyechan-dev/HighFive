import type { Resume, CoverLetterResponseDto, CareerDescriptionResponseDto } from "../../features/request/props/RequestProps";

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