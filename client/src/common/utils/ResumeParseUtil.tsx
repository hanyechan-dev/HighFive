import type {
  EducationResponseDto,
  CareerResponseDto,
  CertificationResponseDto,
  LanguageTestResponseDto,
  CareerDescriptionResponseDto,
  CoverLetterResponseDto
} from "../../features/myPageForMember/props/myPageForMemberProps";
import { parseStringToJson } from "./JsonParseUtil";

// JsonParseUtil의 결과 타입
interface ParsedJsonResult {
  educationResponseDtos: EducationResponseDto[];
  careerResponseDtos: CareerResponseDto[];
  certificationResponseDtos: CertificationResponseDto[];
  languageTestResponseDtos: LanguageTestResponseDto[];
  careerDescriptionResponseDto: CareerDescriptionResponseDto;
  coverLetterResponseDto: CoverLetterResponseDto;
}

// 최종 파싱 결과 타입
interface ResumeParseResult {
  education: EducationInfo | null;
  career: CareerInfo | null;
  certification: CertificationInfo | null;
  languageTest: LanguageTestInfo | null;
  careerDescription: CareerDescriptionResponseDto;
  coverLetter: CoverLetterResponseDto;
}

interface EducationInfo {
  schoolName: string;
  educationLevel: string;
  major: string;
  gpa: string;
  location: string;
  enterDate: string;
  graduateDate: string;
}

interface CareerInfo {
  companyName: string;
  job: string;
  department: string;
  position: string;
  startDate: string;
  endDate: string;
}

interface CertificationInfo {
  certificationName: string;
  issuingOrg: string;
  grade: string;
  score: string;
  certificationNo: string;
  acquisitionDate: string;
}

interface LanguageTestInfo {
  languageType: string;
  testName: string;
  issuingOrg: string;
  grade: string;
  score: string;
  certificationNo: string;
  acquisitionDate: string;
}

// JSON 문자열들을 받아서 최종 파싱된 결과를 반환하는 메인 함수
export const parseResumeFromJsonStrings = (
  resumeJson: string, 
  careerDescriptionJson: string, 
  coverLetterJson: string
): ResumeParseResult => {
  const parsedData = parseStringToJson(resumeJson, careerDescriptionJson, coverLetterJson);
  return parseResumeFromParsedData(parsedData);
};

// 이미 파싱된 데이터를 받아서 최종 파싱된 결과를 반환하는 함수
export const parseResumeFromParsedData = (parsedData: ParsedJsonResult): ResumeParseResult => {
  const {
    educationResponseDtos,
    careerResponseDtos,
    certificationResponseDtos,
    languageTestResponseDtos,
    careerDescriptionResponseDto,
    coverLetterResponseDto
  } = parsedData;

  return {
    education: parseEducation(educationResponseDtos),
    career: parseCareer(careerResponseDtos),
    certification: parseCertification(certificationResponseDtos),
    languageTest: parseLanguageTest(languageTestResponseDtos),
    careerDescription: careerDescriptionResponseDto,
    coverLetter: coverLetterResponseDto
  };
};

// 학력 정보 파싱
const parseEducation = (educationResponseDtos: EducationResponseDto[]): EducationInfo | null => {
  if (!educationResponseDtos?.length) return null;
  
  const {
    schoolName,
    educationLevel,
    major,
    gpa,
    location,
    enterDate,
    graduateDate
  } = educationResponseDtos[0];

  return {
    schoolName: schoolName ?? "",
    educationLevel: educationLevel ?? "",
    major: major ?? "",
    gpa: gpa ?? "",
    location: location ?? "",
    enterDate: enterDate ?? "",
    graduateDate: graduateDate ?? ""
  };
};

// 경력 정보 파싱
const parseCareer = (careerResponseDtos: CareerResponseDto[]): CareerInfo | null => {
  if (!careerResponseDtos?.length) return null;

  const {
    companyName,
    job,
    department,
    position,
    startDate,
    endDate
  } = careerResponseDtos[0];

  return {
    companyName: companyName ?? "",
    job: job ?? "",
    department: department ?? "",
    position: position ?? "",
    startDate: startDate ?? "",
    endDate: endDate ?? ""
  };
};

// 자격증 정보 파싱
const parseCertification = (certificationResponseDtos: CertificationResponseDto[]): CertificationInfo | null => {
  if (!certificationResponseDtos?.length) return null;

  const {
    certificationName,
    issuingOrg,
    grade,
    score,
    certificationNo,
    acquisitionDate
  } = certificationResponseDtos[0];

  return {
    certificationName: certificationName ?? "",
    issuingOrg: issuingOrg ?? "",
    grade: grade ?? "",
    score: score ?? "",
    certificationNo: certificationNo ?? "",
    acquisitionDate: acquisitionDate ?? ""
  };
};

// 어학 정보 파싱
const parseLanguageTest = (languageTestResponseDtos: LanguageTestResponseDto[]): LanguageTestInfo | null => {
  if (!languageTestResponseDtos?.length) return null;

  const {
    languageType,
    testName,
    issuingOrg,
    grade,
    score,
    certificationNo,
    acquisitionDate
  } = languageTestResponseDtos[0];

  return {
    languageType: languageType ?? "",
    testName: testName ?? "",
    issuingOrg: issuingOrg ?? "",
    grade: grade ?? "",
    score: score ?? "",
    certificationNo: certificationNo ?? "",
    acquisitionDate: acquisitionDate ?? ""
  };
}; 