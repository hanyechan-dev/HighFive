
import { printErrorInfo } from "../../../../common/utils/ErrorUtil";
import { createCareerApi, createCareerDescriptionApi, createCertificationApi, createCoverLetterApi, createEducationApi, createLanguageTestApi, deleteCareerApi, deleteCertificationApi, deleteEducationApi, deleteLanguageTestApi, readCareerDescriptionApi, readCareerDescriptionsApi, readCareersApi, readCertificationsApi, readCoverLettersApi, readEducationsApi, readLanguageTestsApi, updateCareerApi, updateCareerDescriptionApi, updateCertificationApi, updateCoverLetterApi, updateEducationApi, updateLanguageTestApi } from "../../apis/MyPageForMemberApi";
import type { CareerCreateDto, CareerDescriptionCreateDto, CareerDescriptionResponseDto, CareerDescriptionSummaryDto, CareerDescriptionUpdateDto, CareerResponseDto, CareerUpdateDto, CertificationCreateDto, CertificationResponseDto, CertificationUpdateDto, CoverLetterCreateDto, CoverLetterResponseDto, CoverLetterSummaryDto, CoverLetterUpdateDto, EducationCreateDto, EducationResponseDto, EducationUpdateDto, LanguageTestCreateDto, LanguageTestResponseDto, LanguageTestUpdateDto } from "../../props/myPageForMemberProps";
import { useDocumentTabController } from "./useDocumentTabController";

export const useDocumentTabApi = () => {
    const {
        resume,
        setResume,
        careerDescriptionSummaryDtos,
        setCareerDescriptionSummaryDtos,
        setCareerDescriptionResponseDto,
        coverLetterSummaryDtos,
        setCoverLetterSummaryDtos,
        setCoverLetterResponseDto,
    } = useDocumentTabController();

    const readResume = async () => {
        const educationResponseDtos: EducationResponseDto[] = (await readEducationsApi()).data.content;
        const careerResponseDtos: CareerResponseDto[] = (await readCareersApi()).data.content;
        const certificationResponseDtos: CertificationResponseDto[] = (await readCertificationsApi()).data.content;
        const languageTestResponseDtos: LanguageTestResponseDto[] = (await readLanguageTestsApi()).data.content;
        setResume({
            educationResponseDtos,
            careerResponseDtos,
            certificationResponseDtos,
            languageTestResponseDtos,
        });
    };

    const readCareerDescriptionSummaryDtos = async () => {
        try {
            const careerDescriptionSummaryDtos: CareerDescriptionSummaryDto[] = (await readCareerDescriptionsApi()).data.content;
            setCareerDescriptionSummaryDtos(careerDescriptionSummaryDtos);
        } catch (err) {
            printErrorInfo(err);
        }
    };


    const readCareerDescriptionResponseDto = async (id : number) => {
        try {
            const careerDescriptionResponseDto: CareerDescriptionResponseDto = (await readCareerDescriptionApi(id)).data.content;
            setCareerDescriptionResponseDto(careerDescriptionResponseDto);
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const readCoverLetterSummaryDtos = async () => {
        try {
            const coverLetterSummaryDtos: CoverLetterSummaryDto[] = (await readCoverLettersApi()).data.content;
            setCoverLetterSummaryDtos(coverLetterSummaryDtos);
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const readCoverLetterResponseDto = async () => {
        try {
            const coverLetterResponseDto: CoverLetterResponseDto = (await readCoverLettersApi()).data.content;
            setCoverLetterResponseDto(coverLetterResponseDto);
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const createEducation = async (educationCreateDto: EducationCreateDto) => {
        try {
            const educationResponseDto: EducationResponseDto = (await createEducationApi(educationCreateDto)).data.content;
            setResume({
                ...resume,
                educationResponseDtos: [...resume.educationResponseDtos, educationResponseDto],
            });
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const updateEducation = async (educationUpdateDto: EducationUpdateDto) => {
        try {
            const responseDto: EducationResponseDto = (await updateEducationApi(educationUpdateDto)).data.content;
            setResume({
                ...resume,
                educationResponseDtos: resume.educationResponseDtos.map((educationResponseDto) => educationResponseDto.id === responseDto.id ? responseDto : educationResponseDto),
            });
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const deleteEducation = async (id: number) => {
        try {
            await deleteEducationApi(id);
            setResume({
                ...resume,
                educationResponseDtos: resume.educationResponseDtos.filter(dto => dto.id !== id),
            });
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const createCareer = async (careerCreateDto: CareerCreateDto) => {
        const careerResponseDto: CareerResponseDto = (await createCareerApi(careerCreateDto)).data.content;
        setResume({
            ...resume,
            careerResponseDtos: [...resume.careerResponseDtos, careerResponseDto],
        });
    };

    const updateCareer = async (careerUpdateDto: CareerUpdateDto) => {
        const responseDto: CareerResponseDto = (await updateCareerApi(careerUpdateDto)).data.content;
        setResume({
            ...resume,
            careerResponseDtos: resume.careerResponseDtos.map((careerResponseDto) => careerResponseDto.id === responseDto.id ? responseDto : careerResponseDto),
        });
    };

    const deleteCareer = async (id: number) => {
        try {
            await deleteCareerApi(id);
            setResume({
                ...resume,
                careerResponseDtos: resume.careerResponseDtos.filter(dto => dto.id !== id),
            });
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const createCertification = async (certificationCreateDto: CertificationCreateDto) => {
        const certificationResponseDto: CertificationResponseDto = (await createCertificationApi(certificationCreateDto)).data.content;
        setResume({
            ...resume,
            certificationResponseDtos: [...resume.certificationResponseDtos, certificationResponseDto],
        });
    };

    const updateCertification = async (certificationUpdateDto: CertificationUpdateDto) => {
        const responseDto: CertificationResponseDto = (await updateCertificationApi(certificationUpdateDto)).data.content;
        setResume({
            ...resume,
            certificationResponseDtos: resume.certificationResponseDtos.map((certificationResponseDto) => certificationResponseDto.id === responseDto.id ? responseDto : certificationResponseDto),
        });
    };

    const deleteCertification = async (id: number) => {
        try {
            await deleteCertificationApi(id);
            setResume({
                ...resume,
                certificationResponseDtos: resume.certificationResponseDtos.filter(dto => dto.id !== id),
            });
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const createLanguageTest = async (languageTestCreateDto: LanguageTestCreateDto) => {
        const languageTestResponseDto: LanguageTestResponseDto = (await createLanguageTestApi(languageTestCreateDto)).data.content;
        setResume({
            ...resume,
            languageTestResponseDtos: [...resume.languageTestResponseDtos, languageTestResponseDto],
        });
    };

    const updateLanguageTest = async (languageTestUpdateDto: LanguageTestUpdateDto) => {
        const responseDto: LanguageTestResponseDto = (await updateLanguageTestApi(languageTestUpdateDto)).data.content;
        setResume({
            ...resume,
            languageTestResponseDtos: resume.languageTestResponseDtos.map((languageTestResponseDto) => languageTestResponseDto.id === responseDto.id ? responseDto : languageTestResponseDto),
        });
    };

    const deleteLanguageTest = async (id: number) => {
        try {
            await deleteLanguageTestApi(id);
            setResume({
                ...resume,
                languageTestResponseDtos: resume.languageTestResponseDtos.filter(dto => dto.id !== id),
            });
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const createCareerDescription = async (careerDescriptionCreateDto: CareerDescriptionCreateDto) => {
        const careerDescriptionResponseDto: CareerDescriptionResponseDto = (await createCareerDescriptionApi(careerDescriptionCreateDto)).data.content;
        setCareerDescriptionResponseDto(careerDescriptionResponseDto);
        setCareerDescriptionSummaryDtos([
            ...careerDescriptionSummaryDtos,
            {
                id: careerDescriptionResponseDto.id,
                title: careerDescriptionResponseDto.title,
                createdDate: careerDescriptionResponseDto.createdDate,
            },
        ]);
    };

    const updateCareerDescription = async (careerDescriptionUpdateDto: CareerDescriptionUpdateDto) => {
        const careerDescriptionResponseDto: CareerDescriptionResponseDto = (await updateCareerDescriptionApi(careerDescriptionUpdateDto)).data.content;
        setCareerDescriptionResponseDto(careerDescriptionResponseDto);
        setCareerDescriptionSummaryDtos(
            careerDescriptionSummaryDtos.map((careerDescriptionSummaryDto) => careerDescriptionSummaryDto.id === careerDescriptionUpdateDto.id ?
                {
                    id: careerDescriptionResponseDto.id,
                    title: careerDescriptionResponseDto.title,
                    createdDate: careerDescriptionResponseDto.createdDate,
                }
                : careerDescriptionSummaryDto),
        );
    };

    const createCoverLetter = async (coverLetterCreateDto: CoverLetterCreateDto) => {
        const coverLetterResponseDto: CoverLetterResponseDto = (await createCoverLetterApi(coverLetterCreateDto)).data.content;
        setCoverLetterResponseDto(coverLetterResponseDto);
        setCoverLetterSummaryDtos([
            ...coverLetterSummaryDtos,
            {
                id: coverLetterResponseDto.id,
                title: coverLetterResponseDto.title,
                createdDate: coverLetterResponseDto.createdDate,
            },
        ]);
    };

    const updateCoverLetter = async (coverLetterUpdateDto: CoverLetterUpdateDto) => {
        const coverLetterResponseDto: CoverLetterResponseDto = (await updateCoverLetterApi(coverLetterUpdateDto)).data.content;
        setCoverLetterResponseDto(coverLetterResponseDto);
        setCoverLetterSummaryDtos(
            coverLetterSummaryDtos.map((coverLetterSummaryDto) => coverLetterSummaryDto.id === coverLetterUpdateDto.id ?
                {
                    id: coverLetterResponseDto.id,
                    title: coverLetterResponseDto.title,
                    createdDate: coverLetterResponseDto.createdDate,
                }
                : coverLetterSummaryDto),
        );
    };


    return {
        readResume,
        readCareerDescriptionSummaryDtos,
        readCareerDescriptionResponseDto,
        readCoverLetterSummaryDtos,
        readCoverLetterResponseDto,
        createEducation,
        updateEducation,
        deleteEducation,
        createCareer,
        updateCareer,
        deleteCareer,
        createCertification,
        updateCertification,
        deleteCertification,
        createLanguageTest,
        updateLanguageTest,
        deleteLanguageTest,
        createCareerDescription,
        updateCareerDescription,
        createCoverLetter,
        updateCoverLetter,
    }
}

