

const defaultSetting =
    'w-[1452px] h-[75px] text-base font-roboto rounded-t-lg border border-b-0 border-gray-300 px-3 py-[13px] flex items-center mx-[24px] mt-[24px]';
const itemSetting = 'shrink-0 text-left mr-5';
const sizeClass = {
    s: 'w-[100px]',
    m: 'w-[150px]',
    l: 'w-[450px]',
};

const JobPostingSummaryTitle = () => {


    return (
        <div className={defaultSetting}>
            <span className={`${sizeClass.s} ${itemSetting}`}>기업명</span>
            <span className={`${sizeClass.s} ${itemSetting}`}>기업분류</span>
            <span className={`${sizeClass.l} ${itemSetting}`}>공고명</span>
            <span className={`${sizeClass.s} ${itemSetting}`}>모집부문</span>
            <span className={`${sizeClass.s} ${itemSetting}`}>근무지</span>
            <span className={`${sizeClass.m} ${itemSetting}`}>경력구분</span>
            <span className={`${sizeClass.s} ${itemSetting}`}>학력</span>
            <span className={`${sizeClass.s} ${itemSetting}`}>등록일</span>
            <span className={`${sizeClass.s} ${itemSetting} text-theme` }>유사도</span>

            
        </div>
    );
}

export default JobPostingSummaryTitle;