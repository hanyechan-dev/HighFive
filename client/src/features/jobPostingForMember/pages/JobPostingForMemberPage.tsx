import { useEffect, useState } from "react";
import CommonPage from "../../../common/pages/CommonPage";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import { CreateApplicationApi, JobPostingDetailApi, JobPostingListForMemberApi, JobPostingMainCardForMemberApi, JobPostingUnderCardForMemberApi, ReadMyCareerDescriptionApi, ReadMyCoverLetterApi } from "../apis/JobPostingForMemberApi";
import JobPostingMainCard from "../components/JobPostingMainCard";
import { useJobPostingForMemberController } from "../customHooks/useJobPostingForMemberController";

import JobPostingUnderCard from "../components/JobPostingUnderCard";
import Pagination from "../../../common/components/pagination/Pagination";
import JobPostingListTop from "../components/JobPostingListTop";
import JobPostingListItem from "../components/JobPostingListItem";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import Button from "../../../common/components/button/Button";
import JobPostingFilterModal from "../modals/JobPostingFilterModal";
import JobPostingDetailForMemberModal from "../modals/JobPostingDetailForMemberModal";
import ApplicationModal from "../modals/ApplicationModal";
import { readEducationsApi, readCareersApi, readCertificationsApi, readLanguageTestsApi, readCareerDescriptionsApi, readCoverLettersApi } from "../../myPageForMember/apis/MyPageForMemberApi";
import type { EducationResponseDto, CareerResponseDto, CertificationResponseDto, LanguageTestResponseDto } from "../../myPageForMember/props/myPageForMemberProps";
import type { JobPostingMainCardDto, JobPostingUnderCardDto, JobPostingSummaryForMemberDto, JobPostingForMemberResponseDto } from "../props/JobPostingForMemberProps";
import { useSelector } from "react-redux";
import type { RootState } from "../../../common/store/store";
import { usePagination } from "../../../common/customHooks/usePagination";
import PageTitle from "../../../common/components/title/PageTitle";
import LoadingSpinner from "../../../common/components/loading/LoadingSpinner";




const elementsPerPage = 10;
const pagesPerBlock = 10;

const JobPostingForMemberPage = () => {

    const [totalElements, setTotalElements] = useState(0);
    const filter = useSelector((state: RootState) => state.jobPostingFilter.filter);
    const [isPageLoding, setIsPageLoading] = useState(true);
    const [isShowModalNumber0Loading, setIsShowModalNumber0Loading] = useState(true);
    const [isShowModalNumber1Loading, setIsShowModalNumber1Loading] = useState(true);
    const [isShowModalNumber2Loading, setIsShowModalNumber2Loading] = useState(true);
    const [isCLLoading, setIsCLLoading] = useState(true);
    const [isCDLoading, setIsCDLoading] = useState(true);
    const [isJobPostingModalLoading, setIsJobPostingModalLoading] = useState(true);

    const {
        jobPostingMainCardDtos,
        setJobPostingMainCardDtos,
        jobPostingUnderCardDtos,
        setJobPostingUnderCardDtos,
        jobPostingForMemberSummaryDtos,
        setJobPostingForMemberSummaryDtos,
        showJobPostingFilterModal,
        setShowJobPostingFilterModal,
        clickedJobPostingId,
        setClickedJobPostingId,
        showJobPostingForMemberDetailModal,
        setShowJobPostingForMemberDetailModal,
        setJobPostingForMemberResponseDto,
        showApplicationModal,
        showModalNumber,
        setResume,
        clickedCareerDescriptionId,
        setCareerDescriptionSummaryDtos,
        clickedCoverLetterId,
        setCoverLetterSummaryDtos,
        setCoverLetterResponseDto,
        setCareerDescriptionResponseDto,
        setShowApplicationModal,
        setShowModalNumber,
        setClickedCareerDescriptionId,
        setClickedCoverLetterId
    } = useJobPostingForMemberController();

    const {
        clickedPage,
        pageBlockIndex,
        lastPage,
        lastPageBlockIndex,
        onClickFirst,
        onClickPrev,
        onClickNext,
        onClickLast,
        setClickedPage,
    } = usePagination({
        totalElements: totalElements,
        elementsPerPage: elementsPerPage,
        pagesPerBlock: pagesPerBlock,
    });


    const onClickJobPostingFilterModal = () => {
        setShowJobPostingFilterModal(true);
    }

    useEffect(() => {
        const fetchData = async () => {
            setIsPageLoading(true)
            try {

                const resMain = await JobPostingMainCardForMemberApi();
                const jobPostingMainCardDtos = resMain.data as JobPostingMainCardDto[];
                console.log(jobPostingMainCardDtos)
                setJobPostingMainCardDtos(jobPostingMainCardDtos);

                const resUnder = await JobPostingUnderCardForMemberApi();
                const jobPostingUnderCardDtos = resUnder.data as JobPostingUnderCardDto[];
                setJobPostingUnderCardDtos(jobPostingUnderCardDtos);


            }
            catch (err) {
                printErrorInfo(err)
            }
            finally {
                setIsPageLoading(false)
            }
        }

        fetchData();

    }, []);


    useEffect(() => {
        const fetchData = async () => {
            setIsPageLoading(true)
            try {
                const res = await JobPostingListForMemberApi(clickedPage - 1, elementsPerPage);
                const totalElements = res.data.totalElements as number
                setTotalElements(totalElements);
                const jobPostingSummaryForMemberDtos = res.data.content as JobPostingSummaryForMemberDto[];
                setJobPostingForMemberSummaryDtos(jobPostingSummaryForMemberDtos);

            }
            catch (err) {
                printErrorInfo(err)
            }
            finally {
                setIsPageLoading(false)
            }
        }

        fetchData();

    }, [clickedPage, filter]);

    useEffect(() => {


        if (showModalNumber == 0) {
            const fetchData = async () => {
                setIsShowModalNumber0Loading(true)
                try {
                    const educationResponseDtos = (await readEducationsApi()).data as EducationResponseDto[];
                    const careerResponseDtos = (await readCareersApi()).data as CareerResponseDto[];
                    const certificationResponseDtos = (await readCertificationsApi()).data as CertificationResponseDto[];
                    const languageTestResponseDtos = (await readLanguageTestsApi()).data as LanguageTestResponseDto[];
                    setResume({ educationResponseDtos, careerResponseDtos, certificationResponseDtos, languageTestResponseDtos })
                } catch (err) {
                    printErrorInfo(err);
                }
                finally {
                    setIsShowModalNumber0Loading(false)
                }
            }
            fetchData();

        }
        else if (showModalNumber == 1) {
            const fetchData = async () => {
                setIsShowModalNumber1Loading(true)
                try {
                    const careerDescriptionSummaryDtos = (await readCareerDescriptionsApi()).data;
                    setCareerDescriptionSummaryDtos(careerDescriptionSummaryDtos);
                } catch (err) {
                    printErrorInfo(err);
                }
                finally {
                    setIsShowModalNumber1Loading(false)
                }
            }

            fetchData();

        }
        else if (showModalNumber == 2) {
            const fetchData = async () => {
                setIsShowModalNumber2Loading(true)
                try {
                    const coverLetterSummaryDtos = (await readCoverLettersApi()).data;
                    setCoverLetterSummaryDtos(coverLetterSummaryDtos);
                } catch (err) {
                    printErrorInfo(err);
                }
                finally {
                    setIsShowModalNumber2Loading(false)
                }
            }

            fetchData();

        }
        else if (showModalNumber == 3) {
            const post = async () => {
                try {
                    await CreateApplicationApi(
                        {
                            jobPostingId: clickedJobPostingId,
                            coverLetterId: clickedCoverLetterId,
                            careerDescriptionId: clickedCareerDescriptionId
                        }
                    )

                } catch (err) {
                    printErrorInfo(err);
                }
            }
            post();
            setShowApplicationModal(false)
            setShowModalNumber(-1)
            setClickedCareerDescriptionId(-1)
            setClickedCoverLetterId(-1)
        }


    }, [showModalNumber]);

    useEffect(() => {
        const fetchData = async () => {
            setIsCLLoading(true)
            try {
                const coverLetterResponseDto = (await ReadMyCoverLetterApi(clickedCoverLetterId)).data;
                setCoverLetterResponseDto(coverLetterResponseDto);
            } catch (err) {
                printErrorInfo(err);
            }
            finally {
                setIsCLLoading(false)
            }
        }


        if (clickedCoverLetterId != -1) {
            fetchData();
        }

    }, [clickedCoverLetterId])

    useEffect(() => {
        const fetchData = async () => {
            setIsCDLoading(true)
            try {
                const careerDescriptionResponseDto = (await ReadMyCareerDescriptionApi(clickedCareerDescriptionId)).data;
                setCareerDescriptionResponseDto(careerDescriptionResponseDto);
            } catch (err) {
                printErrorInfo(err);
            }
            finally {
                setIsCDLoading(false)
            }
        }


        if (clickedCareerDescriptionId != -1) {
            fetchData();
        }

    }, [clickedCareerDescriptionId])



    const onClickJobPostingDetailModal = (id: number) => {
        const fetchData = async () => {
            setIsJobPostingModalLoading(true)
            try {
                const res = await JobPostingDetailApi(id);
                const jobPostingForMemberResponseDto = res.data as JobPostingForMemberResponseDto;
                setJobPostingForMemberResponseDto(jobPostingForMemberResponseDto);
            }
            catch (err) {
                printErrorInfo(err)
            }
            finally {
                setIsJobPostingModalLoading(false)
            }
        }

        fetchData();
        setShowJobPostingForMemberDetailModal(true)
        setClickedJobPostingId(id);
    }




    return (
        <>
            <CommonPage >
                <PageTitle
                    title="채용 공고"
                    description="회원님에게 알맞는 채용 공고를 확인하세요." />

                {isPageLoding ? (<LoadingSpinner message="채용 공고를 불러오는 중..." size="lg" color="theme" />) : (
                    <>
                        <div className="flex mt-2">


                            {jobPostingMainCardDtos.map(jobPostingMainCardDto => (
                                <div key={jobPostingMainCardDto.id}>
                                    <JobPostingMainCard
                                        jobPostingMainCardDto={jobPostingMainCardDto}
                                        onClick={onClickJobPostingDetailModal} />
                                </div>

                            ))}
                        </div>

                        <div className="grid grid-cols-4 gap-0 mt-2">
                            {jobPostingUnderCardDtos.map((jobPostingUnderCardDto) => (
                                <div key={jobPostingUnderCardDto.id}>
                                    <JobPostingUnderCard
                                        jobPostingUnderCardDto={jobPostingUnderCardDto}
                                        onClick={onClickJobPostingDetailModal}
                                    />
                                </div>
                            ))}
                        </div>

                        <div className="flex justify-end mb-[-24px] mr-6">
                            <Button color={"theme"} size={"m"} disabled={false} text={"채용공고 필터"} type={"button"} onClick={onClickJobPostingFilterModal} />
                        </div>

                        <JobPostingListTop />
                        {jobPostingForMemberSummaryDtos.length > 0 ? jobPostingForMemberSummaryDtos.map(jobPostingForMemberSummaryDto =>
                            <JobPostingListItem
                                key={jobPostingForMemberSummaryDto.id}
                                jobPostingSummaryForMemberDto={jobPostingForMemberSummaryDto}
                                onClick={onClickJobPostingDetailModal} />
                        ) : <EmptyState title={"등록된 공고가 없습니다."} text={""} />
                        }


                        <div className="flex justify-center">
                            <Pagination
                                currentPageBlockIndex={pageBlockIndex}
                                lastPageBlockIndex={lastPageBlockIndex}
                                pagesPerBlock={pagesPerBlock}
                                lastPage={lastPage}
                                clickedPage={clickedPage}
                                onClickFirst={onClickFirst}
                                onClickPrev={onClickPrev}
                                onClickNext={onClickNext}
                                onClickLast={onClickLast}
                                onClickPage={setClickedPage}
                            />
                        </div>
                    </>
                )}


            </CommonPage>

            {showJobPostingFilterModal && <JobPostingFilterModal />}
            {showJobPostingForMemberDetailModal && <JobPostingDetailForMemberModal
                isJobPostingModalLoading={isJobPostingModalLoading} />}
            {showApplicationModal && <ApplicationModal
                isShowModalNumber0Loading={isShowModalNumber0Loading}
                isShowModalNumber1Loading={isShowModalNumber1Loading}
                isShowModalNumber2Loading={isShowModalNumber2Loading}
                isCLLoading={isCLLoading}
                isCDLoading={isCDLoading} />}


        </>
    );

}

export default JobPostingForMemberPage