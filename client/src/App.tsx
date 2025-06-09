import { useEffect, useState } from "react";
import JobPostingMainCard from "./common/components/list/JobPostingMainCard"
import JobPostingUnderCard from "./common/components/list/JobPostingUnderCard"
import Pagenation from "./common/components/pagination/Pagination"

import CommonPage from "./common/pages/CommonPage"
import Pagination from "./common/components/pagination/Pagination";
import { getLastPage, getLastPageBlockIndex } from "./common/utils/PaginationUtil";
import { usePagination } from "./common/coustomHooks/usePagination";
import SignUpModal from "./features/auth/SignUpModal";
import JobPostingFilterModal from "./features/jobPostingForMember/JobPostingFilterModal";







function App() {

    const pagesPerBlock = 10;
    const elementsPerPage = 10;
    const totalElements = 1545;



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




    return (
        <>  
        <JobPostingFilterModal />

            {/* <SignUpModal /> */}


            {/* <Pagination currentPageBlockIndex={pageBlockIndex}
                lastPageBlockIndex={lastPageBlockIndex}
                pagesPerBlock={pagesPerBlock}
                lastPage={lastPage}
                clickedPage={clickedPage}
                onClickFirst={onClickFirst}
                onClickPrev={onClickPrev}
                onClickNext={onClickNext}
                onClickLast={onClickLast}
                onClickPage={setClickedPage} /> */}

            {/* <CommonPage>
                <div className="flex">
                    <JobPostingMainCard />
                    <JobPostingMainCard />
                    <JobPostingMainCard />
                    <JobPostingMainCard />
                </div>

                <div className="flex">
                    <JobPostingUnderCard />
                    <JobPostingUnderCard />
                    <JobPostingUnderCard />
                    <JobPostingUnderCard />
                </div>
                
                <div className="flex">
                    <JobPostingUnderCard />
                    <JobPostingUnderCard />
                    <JobPostingUnderCard />
                    <JobPostingUnderCard />
                </div>


            </CommonPage> */}
        </>
    )

}

export default App
