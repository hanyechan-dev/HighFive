


// w-[1452px]     text-center text-sm 




const titleDefaultSetting =
    'w-[1452px] border-b  px-3 py-4 text-sm font-semibold text-[#666] font-roboto rounded-t-xl bg-gray-100 flex items-center ml-[24px] mt-[24px]';
const titleItemDefaultSetting = 'shrink-0 text-left mr-5';

const sizeClass = {
    s: 'w-[100px]',
    m: 'w-[150px]',
    l: 'w-[450px]',
};



const JobPostingListTop = () => {



    return (
                <div className={titleDefaultSetting}>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>기업명</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>기업분류</span>
                    <span className={`${sizeClass.l} ${titleItemDefaultSetting}`}>공고명</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>모집부문</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>근무지</span>
                    <span className={`${sizeClass.m} ${titleItemDefaultSetting}`}>경력구분</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>학력</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting}`}>등록일</span>
                    <span className={`${sizeClass.s} ${titleItemDefaultSetting} text-theme pl-2`}>유사도</span>
                </div>

    );
}

export default JobPostingListTop;

