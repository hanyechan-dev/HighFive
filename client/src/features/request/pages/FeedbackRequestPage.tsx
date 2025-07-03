import { useState, useEffect } from "react";
import Button from "../../../common/components/button/Button";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import Pagination from "../../../common/components/pagination/Pagination";
import CommonPage from "../../../common/pages/CommonPage";

import RequestListItem from "../components/RequestListItem";
import RequestListTop from "../components/RequestListTop";
import RequestDetailModal from "../modals/RequestDetailModal";
import RequestModal from "../modals/RequestModal";

import { printErrorInfo } from "../../../common/utils/ErrorUtil";

import type { RequestDetailDto, RequestSummaryDto } from "../props/RequestProps";
import { useRequestController } from "../customHooks/useRequestController";

import { readRequestsApi, readRequestApi, createRequestApi } from "../apis/RequestApi";
import { readCareerDescriptionsApi, readCareersApi, readCertificationsApi, readCoverLettersApi, readEducationsApi, readLanguageTestsApi } from "../../myPageForMember/apis/MyPageForMemberApi";
import type { EducationResponseDto, CareerResponseDto, CertificationResponseDto, LanguageTestResponseDto } from "../../myPageForMember/props/myPageForMemberProps";
import { usePagination } from "../../../common/customHooks/usePagination";
import PageTitle from "../../../common/components/title/PageTitle";
import LoadingSpinner from "../../../common/components/loading/LoadingSpinner";

const consultingType = '피드백'
const elementsPerPage = 10;
const pagesPerBlock = 10;





const FeedbackRequestPage = () => {

    const [totalElements, setTotalElements] = useState(0);
    const [isRequestDetailDtoLoading ,setIsRequestDetailDtoLoading] = useState(true)


    // 페이지 내 전역 상태 선언부
    const {
        targetJob,
        targetCompanyName,
        clickedCareerDescriptionId,
        clickedCoverLetterId,
        showModalNumber,
        requestSummaryDtos,
        isRequestSummaryDtosLoading,
        setIsRequestSummaryDtosLoading,
        showRequestModal,
        showRequestDetailModal,
        setTargetJob,
        setTargetCompanyName,
        setClickedCareerDescriptionId,
        setClickedCoverLetterId,
        setShowModalNumber,
        setResume,
        setIsResumeLoading,
        setCareerDescriptionSummaryDtos,
        setIsCareerDescriptionSummaryDtosLoading,
        setCoverLetterSummaryDtos,
        setIsCoverLetterSummaryDtosLoading,
        setRequestSummaryDtos,
        setShowRequestModal,
        setShowRequestDetailModal,
        setRequestDetailDto,
        requestDetailDto
    } = useRequestController();


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

    // 페이지 내 상태 관리부
    const onClickRequestModal = () => {
        setShowModalNumber(0)
        setShowRequestModal(true);
    }


    // 페이지 내 API 호출부
    useEffect(() => {
        const fetchData = async () => {
            setIsRequestSummaryDtosLoading(true);
            try {
                const res = await readRequestsApi(clickedPage - 1, elementsPerPage, consultingType);
                const totalElements = res.data.totalElements as number
                setTotalElements(totalElements);
                const requestSummaryDtos = res.data.content as RequestSummaryDto[];
                setRequestSummaryDtos(requestSummaryDtos);

            }
            catch (err) {
                printErrorInfo(err)
            }
            finally {
                setIsRequestSummaryDtosLoading(false);
            }
        }



        fetchData();



    }, [clickedPage]);




    const onClickRequestDetailModal = (id: number) => {
        setShowRequestDetailModal(true);
        setIsRequestDetailDtoLoading(true);

        const fetchData = async () => {
            try {
                const res = await readRequestApi(id);
                const requestDetailDto = res.data as RequestDetailDto;
                setRequestDetailDto(requestDetailDto);
                
            } catch (err) {
                printErrorInfo(err);
            } finally {
                setIsRequestDetailDtoLoading(false);
            }
        };

        fetchData();
    };

    useEffect(() => {

        if (showModalNumber === 1) {

            const fetchData = async () => {
                setIsResumeLoading(true);
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
                    setIsResumeLoading(false);
                }

            }


            fetchData();



        } else if (showModalNumber === 2) {

            const fetchData = async () => {
                setIsCareerDescriptionSummaryDtosLoading(true);
                try {
                    const careerDescriptionSummaryDtos = (await readCareerDescriptionsApi()).data;
                    setCareerDescriptionSummaryDtos(careerDescriptionSummaryDtos);
                } catch (err) {
                    printErrorInfo(err);
                } finally {
                    setIsCareerDescriptionSummaryDtosLoading(false);
                }

            }

            fetchData();

        } else if (showModalNumber === 3) {
            const fetchData = async () => {
                setIsCoverLetterSummaryDtosLoading(true);
                try {
                    const coverLetterSummaryDtos = (await readCoverLettersApi()).data;
                    setCoverLetterSummaryDtos(coverLetterSummaryDtos);
                } catch (err) {
                    printErrorInfo(err);
                }
                finally {
                    setIsCoverLetterSummaryDtosLoading(false);
                }
            }

            fetchData();


        } else if (showModalNumber === 4) {

            const post = async () => {
                setShowRequestDetailModal(true);
                setIsRequestDetailDtoLoading(true);
                try {
                    const res = await createRequestApi(
                        targetJob,
                        targetCompanyName,
                        consultingType,
                        clickedCoverLetterId,
                        clickedCareerDescriptionId
                    );
                    const requestDetailDto = res.data as RequestDetailDto;
                    setRequestDetailDto(requestDetailDto);
                    
                } catch (err) {
                    printErrorInfo(err);
                } finally {
                    setIsRequestDetailDtoLoading(false);
                }
            };
            post();
            setTargetJob('');
            setTargetCompanyName('');
            setClickedCareerDescriptionId(-1);
            setClickedCoverLetterId(-1);

        }
    }, [showModalNumber]);


    return (
        <>


            <CommonPage>
                <PageTitle
                    title={`AI + 컨설턴트 ${consultingType}`}
                    description="회원님이 요청하신 피드백을 확인할 수 있습니다." />
                <div className="flex justify-end mr-6 mb-[-24px]">
                    <Button color={"theme"} size={"m"} disabled={false} text={`새 ${consultingType} 요청하기`} type={"button"} onClick={onClickRequestModal} />
                </div>
                {isRequestSummaryDtosLoading ? (<LoadingSpinner message="피드백을 불러오는 중입니다..." />) : (
                    <>
                        <RequestListTop />
                        <div className="mb-3">
                            {requestSummaryDtos.length > 0 ? requestSummaryDtos.map(requestSummaryDto =>
                                <RequestListItem requestSummaryDto={requestSummaryDto} key={requestSummaryDto.id} onClick={() => onClickRequestDetailModal(requestSummaryDto.id)} />
                            ) : <EmptyState title={"요청 내역이 없습니다."} text={"요청을 등록해주세요."} />}
                        </div>

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

            {showRequestModal && <RequestModal consultingType={consultingType} />}

            {showRequestDetailModal && (
                <RequestDetailModal
                    isRequestDetailDtoLoading={isRequestDetailDtoLoading}
                    requestDetailDto={requestDetailDto}
                    setShowRequestDetailModal={setShowRequestDetailModal} />
            )}

        </>
    )
};

export default FeedbackRequestPage;
