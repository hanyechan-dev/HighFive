import { useEffect, useState } from "react";
import CommonPage from "../../../common/pages/CommonPage";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import { CreateApplicationApi, JobPostingDetailApi, JobPostingListForMemberApi, JobPostingMainCardForMemberApi, JobPostingUnderCardForMemberApi } from "../apis/JobPostingForMemberApi";
import JobPostingMainCard from "../components/JobPostingMainCard";
import { useJobPostingForMemberController } from "../customHooks/useJobPostingForMemberController";
import type { CareerResponseDto, CertificationResponseDto, EducationResponseDto, JobPostingForMemberResponseDto, JobPostingMainCardDto, JobPostingSummaryForMemberDto, JobPostingUnderCardDto, LanguageTestResponseDto } from "../props/JobPostingForMemberProps";
import { usePagination } from "../../../common/coustomHooks/usePagination";
import JobPostingUnderCard from "../components/JobPostingUnderCard";
import Pagination from "../../../common/components/pagination/Pagination";
import JobPostingListTop from "../components/JobPostingListTop";
import JobPostingListItem from "../components/JobPostingListItem";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import Button from "../../../common/components/button/Button";
import JobPostingFilterModal from "../modals/JobPostingFilterModal";
import JobPostingDetailForMemberModal from "../modals/JobPostingDetailForMemberModal";
import ApplicationModal from "../modals/ApplicationModal";
import { readEducationsApi, readCareersApi, readCertificationsApi, readLanguageTestsApi, readCareerDescriptionsApi, readCoverLettersApi } from "../../member/MemberApi";


const elementsPerPage = 10;
const pagesPerBlock = 10;

const JobPostingForMemberPage = () => {

    const [totalElements, setTotalElements] = useState(0);

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
            try {
                const resMain = await JobPostingMainCardForMemberApi();
                const jobPostingMainCardDtos = resMain.data.content as JobPostingMainCardDto[];
                setJobPostingMainCardDtos(jobPostingMainCardDtos);

                const resUnder = await JobPostingUnderCardForMemberApi();
                const jobPostingUnderCardDtos = resUnder.data.content as JobPostingUnderCardDto[];
                setJobPostingUnderCardDtos(jobPostingUnderCardDtos);


            }
            catch (err) {
                printErrorInfo(err)
            }
        }

        fetchData();

    }, []);


    useEffect(() => {
        const fetchData = async () => {
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
        }

        fetchData();

    }, [clickedPage]);

    useEffect(() => {


        if (showModalNumber == 0) {
            const fetchData = async () => {

                try {
                    const educationResponseDtos = (await readEducationsApi()).data.content as EducationResponseDto[];
                    const careerResponseDtos = (await readCareersApi()).data.content as CareerResponseDto[];
                    const certificationResponseDtos = (await readCertificationsApi()).data.content as CertificationResponseDto[];
                    const languageTestResponseDtos = (await readLanguageTestsApi()).data.content as LanguageTestResponseDto[];
                    setResume({ educationResponseDtos, careerResponseDtos, certificationResponseDtos, languageTestResponseDtos })
                } catch (err) {
                    printErrorInfo(err);
                }
            }
            fetchData();

        }
        else if (showModalNumber == 1) {
            const fetchData = async () => {

                try {
                    const careerDescriptionSummaryDtos = (await readCareerDescriptionsApi()).data.content;
                    setCareerDescriptionSummaryDtos(careerDescriptionSummaryDtos);
                } catch (err) {
                    printErrorInfo(err);
                }
            }

            fetchData();

        }
        else if (showModalNumber == 2) {
            const fetchData = async () => {

                try {
                    const coverLetterSummaryDtos = (await readCoverLettersApi()).data.content;
                    setCoverLetterSummaryDtos(coverLetterSummaryDtos);
                } catch (err) {
                    printErrorInfo(err);
                }
            }

            fetchData();

        }
        else if (showModalNumber == 3) {
            const post = async () => {

                try {
                    await CreateApplicationApi(clickedJobPostingId, clickedCoverLetterId, clickedCareerDescriptionId)
                } catch (err) {
                    printErrorInfo(err);
                }
            }
            post();
        }


    }, [showModalNumber]);



    const onClickJobPostingDetailModal = (id: number) => {
        const fetchData = async () => {

            try {
                const res = await JobPostingDetailApi(id);
                const jobPostingForMemberResponseDto = res.data.content as JobPostingForMemberResponseDto;
                setJobPostingForMemberResponseDto(jobPostingForMemberResponseDto);
            }
            catch (err) {
                printErrorInfo(err)
            }
        }

        fetchData();
        setShowJobPostingForMemberDetailModal(true)
        setClickedJobPostingId(id);
    }




    return (
        <>
            <CommonPage >
                <div className="flex mt-2">
                    {jobPostingMainCardDtos.map(jobPostingMainCardDto => (
                        <div key={jobPostingMainCardDto.id}>
                            <JobPostingMainCard
                                jobPostingMainCardDto={jobPostingMainCardDto}
                                onClick={onClickJobPostingDetailModal} />
                        </div>

                    ))}
                </div>

                <div className="flex mt-2">
                    {jobPostingUnderCardDtos.map(jobPostingUnderCardDto => (
                        <div key={jobPostingUnderCardDto.id}>
                            <JobPostingUnderCard
                                jobPostingUnderCardDto={jobPostingUnderCardDto}
                                onClick={onClickJobPostingDetailModal} />
                        </div>

                    ))}
                </div>

                <div className="flex justify-end mr-6">
                    <Button color={"theme"} size={"m"} disabled={false} text={"채용공고 필터"} type={"button"} onClick={onClickJobPostingFilterModal} />
                </div>

                <JobPostingListTop />
                {jobPostingForMemberSummaryDtos.length > 0 ? jobPostingForMemberSummaryDtos.map(jobPostingForMemberSummaryDto =>
                    <JobPostingListItem
                        key={jobPostingForMemberSummaryDto.id}
                        jobPostingSummaryForMemberDto={jobPostingForMemberSummaryDto}
                        onClick={onClickJobPostingDetailModal} />
                ) : <EmptyState title={"등록된 공고가 없습니다."} text={"회원가입 및 로그인을 해주세요"} />
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


            </CommonPage>

            {showJobPostingFilterModal && <JobPostingFilterModal />}
            {showJobPostingForMemberDetailModal && <JobPostingDetailForMemberModal />}
            {showApplicationModal && <ApplicationModal />}


        </>
    );

}

export default JobPostingForMemberPage