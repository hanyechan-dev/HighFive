
import { printErrorInfo } from "../../../../common/utils/ErrorUtil";
import { createCareerApi, createCareerDescriptionApi, createCertificationApi, createCoverLetterApi, createEducationApi, createLanguageTestApi, deleteCareerApi, deleteCareerDescriptionApi, deleteCertificationApi, deleteCoverLetterApi, deleteEducationApi, deleteLanguageTestApi, readCareerDescriptionApi, readCareerDescriptionsApi, readCareersApi, readCertificationsApi, readCoverLetterApi, readCoverLettersApi, readEducationsApi, readLanguageTestsApi, updateCareerApi, updateCareerDescriptionApi, updateCertificationApi, updateCoverLetterApi, updateEducationApi, updateLanguageTestApi } from "../../apis/MyPageForMemberApi";
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
        const educationResponseDtos: EducationResponseDto[] = (await readEducationsApi()).data;
        const careerResponseDtos: CareerResponseDto[] = (await readCareersApi()).data;
        const certificationResponseDtos: CertificationResponseDto[] = (await readCertificationsApi()).data;
        const languageTestResponseDtos: LanguageTestResponseDto[] = (await readLanguageTestsApi()).data;

        setResume({
            educationResponseDtos,
            careerResponseDtos,
            certificationResponseDtos,
            languageTestResponseDtos,
        });
    };

    const createEducation = async (educationCreateDto: EducationCreateDto) => {
        try {
            const educationResponseDto: EducationResponseDto = (await createEducationApi(educationCreateDto)).data;
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
            const responseDto: EducationResponseDto = (await updateEducationApi(educationUpdateDto)).data;
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
        const careerResponseDto: CareerResponseDto = (await createCareerApi(careerCreateDto)).data;
        setResume({
            ...resume,
            careerResponseDtos: [...resume.careerResponseDtos, careerResponseDto],
        });
    };

    const updateCareer = async (careerUpdateDto: CareerUpdateDto) => {
        const responseDto: CareerResponseDto = (await updateCareerApi(careerUpdateDto)).data;
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
        const certificationResponseDto: CertificationResponseDto = (await createCertificationApi(certificationCreateDto)).data;
        setResume({
            ...resume,
            certificationResponseDtos: [...resume.certificationResponseDtos, certificationResponseDto],
        });
    };

    const updateCertification = async (certificationUpdateDto: CertificationUpdateDto) => {
        const responseDto: CertificationResponseDto = (await updateCertificationApi(certificationUpdateDto)).data;
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
        const languageTestResponseDto: LanguageTestResponseDto = (await createLanguageTestApi(languageTestCreateDto)).data;
        setResume({
            ...resume,
            languageTestResponseDtos: [...resume.languageTestResponseDtos, languageTestResponseDto],
        });
    };

    const updateLanguageTest = async (languageTestUpdateDto: LanguageTestUpdateDto) => {
        const responseDto: LanguageTestResponseDto = (await updateLanguageTestApi(languageTestUpdateDto)).data;
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
        const careerDescriptionResponseDto: CareerDescriptionResponseDto = (await createCareerDescriptionApi(careerDescriptionCreateDto)).data;
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

    const readCareerDescriptionSummaryDtos = async () => {
        try {
            const careerDescriptionSummaryDtos: CareerDescriptionSummaryDto[] = (await readCareerDescriptionsApi()).data;
            setCareerDescriptionSummaryDtos(careerDescriptionSummaryDtos);
        } catch (err) {
            printErrorInfo(err);
        }
    };


    const readCareerDescriptionResponseDto = async (id: number) => {
        try {
            const careerDescriptionResponseDto: CareerDescriptionResponseDto = (await readCareerDescriptionApi(id)).data;
            setCareerDescriptionResponseDto(careerDescriptionResponseDto);
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const updateCareerDescription = async (careerDescriptionUpdateDto: CareerDescriptionUpdateDto) => {
        const careerDescriptionResponseDto: CareerDescriptionResponseDto = (await updateCareerDescriptionApi(careerDescriptionUpdateDto)).data;
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

    const deleteCareerDescription = async (id: number) => {
        try {
            await deleteCareerDescriptionApi(id);
            careerDescriptionSummaryDtos.filter(dto => dto.id !== id);
        } catch (err) {
            printErrorInfo(err);
        }
    }

    const createCoverLetter = async (coverLetterCreateDto: CoverLetterCreateDto) => {
        const coverLetterResponseDto: CoverLetterResponseDto = (await createCoverLetterApi(coverLetterCreateDto)).data;
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

    const readCoverLetterSummaryDtos = async () => {
        try {
            const coverLetterSummaryDtos: CoverLetterSummaryDto[] = (await readCoverLettersApi()).data;
            setCoverLetterSummaryDtos(coverLetterSummaryDtos);
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const readCoverLetterResponseDto = async (id: number) => {
        try {
            const coverLetterResponseDto: CoverLetterResponseDto = (await readCoverLetterApi(id)).data;
            setCoverLetterResponseDto(coverLetterResponseDto);
        } catch (err) {
            printErrorInfo(err);
        }
    };


    const updateCoverLetter = async (coverLetterUpdateDto: CoverLetterUpdateDto) => {
        const coverLetterResponseDto: CoverLetterResponseDto = (await updateCoverLetterApi(coverLetterUpdateDto)).data;
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

    const deleteCoverLetter = async (id: number) => {
        try {
            await deleteCoverLetterApi(id);
            coverLetterSummaryDtos.filter(dto => dto.id !== id);
        } catch (err) {
            printErrorInfo(err);
        }
    }






    return {
        readResume,
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
        readCareerDescriptionSummaryDtos,
        readCareerDescriptionResponseDto,
        updateCareerDescription,
        deleteCareerDescription,
        createCoverLetter,
        readCoverLetterSummaryDtos,
        readCoverLetterResponseDto,
        updateCoverLetter,
        deleteCoverLetter
    }
}

