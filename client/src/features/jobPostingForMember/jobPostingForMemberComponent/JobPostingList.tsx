
import { useEffect, useState } from "react";
import Pagination from "../../../common/components/pagination/Pagination";
import { usePagination } from "../../../common/coustomHooks/usePagination";
import { JobPostingListForMemberApi } from "../JobPostingForMemberApi";
import { useSelector } from "react-redux";
import type { RootState } from "../../../common/store/store";
import EmptyState from "../../../common/components/emptyState/EmptyState";

interface JobPostingProps {
    id: number;
    title: string;
    companyName: string;
    companyType: string;
    job: string;
    workLocation: string;
    careerType: string;
    educationLevel: string;
    similarityScore: number | null;
    createdDate: string;
}

const pagesPerBlock = 10;
const elementsPerPage = 10;






const titleDefaultSetting =
    'w-[1452px] h-[75px] text-base font-roboto rounded-t-lg border border-b-0 border-gray-300 px-3 py-[13px] flex items-center mx-[24px] mt-[24px]';
const titleItemDefaultSetting = 'shrink-0 text-left mr-5';

const listDefaultSetting =
    'w-[1452px] h-[75px] text-base font-roboto border border-gray-300 px-3 py-[13px] flex items-center hover:bg-semi_theme hover:cursor-pointer truncate mx-[24px]';
const listItemDefaultSetting = 'shrink-0 text-left mr-5';
const similarityScoreSetting = 'text-theme pl-3'
const sizeClass = {
    s: 'w-[100px]',
    m: 'w-[150px]',
    l: 'w-[450px]',
};



const JobPostingList = () => {

    const { careerType, educationLevel, workLocation, job, salary } =
        useSelector((state: RootState) => state.jobPostingFilter.filter);

    const [jobPostingList, setJobPostingList] = useState<JobPostingProps[]>([]);
    const [totalElements, setTotalElements] = useState<number>(0);

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

    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await JobPostingListForMemberApi(clickedPage);
                setJobPostingList(res.data.content);
                setTotalElements(res.data.totalElements);
            } catch (err) {
                console.error(err);
            }
        };

        fetchData();
    }, [clickedPage,
        careerType,
        educationLevel,
        workLocation,
        job,
        salary]);

    const onClick = (id: number) => {

    }


    return (
        <>
            <div className="flex justify-center">
                <div className={titleDefaultSetting}>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>기업명</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>기업분류</span>
                    <span className={`${sizeClass.l} ${titleItemDefaultSetting}`}>공고명</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>모집부문</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>근무지</span>
                    <span className={`${sizeClass.m} ${titleItemDefaultSetting}`}>경력구분</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>학력</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>등록일</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting} text-theme`}>유사도</span>


                </div>
            </div>


            {jobPostingList.length === 0 ?
                <div className="flex justify-center">
                    <div className="w-[1452px] rounded-b-lg border border-t-0 border-gray-300">
                        <EmptyState title={"공고를 불러오지 못하였습니다."} text={"회원가입 또는 로그인을 해주십시오."} />
                    </div>
                </div>
                :
                jobPostingList.map(jobPosting => (
                    <div key={jobPosting.id} className="flex justify-center">
                        <div className={listDefaultSetting} onClick={() => onClick(jobPosting.id)}>
                            <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{jobPosting.companyName}</span>
                            <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{jobPosting.companyType}</span>
                            <span className={`${sizeClass.l} ${listItemDefaultSetting}`}>{jobPosting.title}</span>
                            <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{jobPosting.job}</span>
                            <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{jobPosting.workLocation}</span>
                            <span className={`${sizeClass.m} ${listItemDefaultSetting}`}>{jobPosting.careerType}</span>
                            <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{jobPosting.educationLevel}</span>
                            <span className={`${sizeClass.s} ${listItemDefaultSetting}`}>{jobPosting.createdDate}</span>
                            {jobPosting.similarityScore !== null && (
                                <span className={`${sizeClass.s} ${listItemDefaultSetting} ${similarityScoreSetting}`}>
                                    {jobPosting.similarityScore}%
                                </span>
                            )}
                        </div>
                    </div>
                ))}

            <div className="flex justify-center">


                <Pagination currentPageBlockIndex={pageBlockIndex}
                    lastPageBlockIndex={lastPageBlockIndex}
                    pagesPerBlock={pagesPerBlock}
                    lastPage={lastPage}
                    clickedPage={clickedPage}
                    onClickFirst={onClickFirst}
                    onClickPrev={onClickPrev}
                    onClickNext={onClickNext}
                    onClickLast={onClickLast}
                    onClickPage={setClickedPage} />
            </div>

        </>
    );
}

export default JobPostingList;

