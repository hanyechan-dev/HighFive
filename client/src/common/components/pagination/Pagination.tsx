interface PaginationProps {
    currentPageBlockIndex: number, // 몇번째 페이지 블록인가
    lastPageBlockIndex : number,
    pagesPerBlock: number, // 한블록에 몇개의 페이지가 들어가느냐
    lastPage:number
    clickedPage: number,
    onClickFirst: () => void;
    onClickPrev: () => void,
    onClickNext: () => void,
    onClickLast: () => void;
    onClickPage: (page: number) => void,
}

const defaultSetting = 'w-[32px] h-[32px] rounded-lg border text-base font-roboto flex items-center justify-center m-2 cursor-pointer'
const unclickedPageSetting = 'hover:bg-semi_theme'
const clickedPageSetting = 'bg-theme text-white'
const emptyButton = <div className="w-[32px] h-[32px] m-2"></div>;

const Pagination = ({
    currentPageBlockIndex,
    lastPageBlockIndex,
    pagesPerBlock,
    lastPage,
    clickedPage,
    onClickFirst,
    onClickPrev,
    onClickNext,
    onClickLast,
    onClickPage
}: PaginationProps) => {

    const startPage = currentPageBlockIndex * pagesPerBlock + 1;
    const endPage = startPage + pagesPerBlock - 1; // inclusive

    const pageNumbers = Array.from(
        { length: endPage - startPage + 1 },
        (_, idx) => startPage + idx
    );


    return (
        <div className="flex">

            {/* 최초 버튼 */}
            {currentPageBlockIndex == 0 ? (emptyButton)
                :
                (<div onClick={onClickFirst} className={`${defaultSetting} ${unclickedPageSetting}`}>
                    {'<<'}
                </div>)}


            {/* 이전 버튼 */}
            {currentPageBlockIndex == 0 ? (emptyButton)
                :
                (<div onClick={onClickPrev} className={`${defaultSetting} ${unclickedPageSetting}`}>
                    {'<'}
                </div>)}

            {/* 페이지 숫자들 */}
            {pageNumbers.filter((page) => page <= lastPage).map((page) => (
                <div
                    key={page}
                    className={`${defaultSetting} ${page === clickedPage ? clickedPageSetting : unclickedPageSetting}`}
                    onClick={() => onClickPage(page)}
                >
                    <div className="relative top-[-2px]">
                        {page}
                    </div>
                    
                </div>
            ))}

            {/* 다음 버튼 */}

            {currentPageBlockIndex >= lastPageBlockIndex ? (emptyButton)
                :
                (<div onClick={onClickNext} className={`${defaultSetting} ${unclickedPageSetting}`}>
                    {'>'}
                </div>)}

            {/* 마지막 버튼 */}

            {currentPageBlockIndex >= lastPageBlockIndex ? (emptyButton)
                :
                (<div onClick={onClickLast} className={`${defaultSetting} ${unclickedPageSetting}`}>
                    {'>>'}
                </div>)}

        </div>
    );
};

export default Pagination;