import type { Resume } from "../../features/jobPostingForMember/props/JobPostingForMemberProps";
import type { CareerDescriptionResponseDto, CoverLetterResponseDto } from "../../features/myPageForMember/props/myPageForMemberProps";

export const parseStringToJson = (
  resumeJson: string | null | undefined,
  careerDescriptionJson: string | null | undefined,
  coverLetterJson: string | null | undefined,
) => {
  let resume: Resume = {
    educationResponseDtos: [],
    careerResponseDtos: [],
    certificationResponseDtos: [],
    languageTestResponseDtos: [],
  };

  if (resumeJson && resumeJson.trim() !== "") {
    try {
      resume = JSON.parse(resumeJson) as Resume;
    } catch (e) {
      console.error("Resume JSON parse error:", e, resumeJson);
    }
  }

  let careerDescriptionResponseDto: CareerDescriptionResponseDto | undefined = undefined;
  if (careerDescriptionJson && careerDescriptionJson.trim() !== "") {
    try {
      const parsed = JSON.parse(careerDescriptionJson);
      careerDescriptionResponseDto = parsed?.careerDescriptionResponseDto as CareerDescriptionResponseDto | undefined;
    } catch (e) {
      console.error("CareerDescription JSON parse error:", e, careerDescriptionJson);
    }
  }

  let coverLetterResponseDto: CoverLetterResponseDto | undefined = undefined;
  if (coverLetterJson && coverLetterJson.trim() !== "") {
    try {
      const parsed = JSON.parse(coverLetterJson);
      coverLetterResponseDto = parsed?.coverLetterResponseDto as CoverLetterResponseDto | undefined;
    } catch (e) {
      console.error("CoverLetter JSON parse error:", e, coverLetterJson);
    }
  }

  return {
    educationResponseDtos: resume.educationResponseDtos,
    careerResponseDtos: resume.careerResponseDtos,
    certificationResponseDtos: resume.certificationResponseDtos,
    languageTestResponseDtos: resume.languageTestResponseDtos,
    careerDescriptionResponseDto,
    coverLetterResponseDto,
  };
};
