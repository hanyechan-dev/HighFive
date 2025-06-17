
interface MyPageTapProps {
    textList: string[];
    checkedText: string;
    setCheckedText: (value: string) => void
};

const colorClass = 'text-black';

const checkedColorClass = 'bg-semi_theme text-theme';

const defaultSetting = 'w-[260px] h-[42px] text-base font-roboto rounded-lg ml-6 flex justify-start px-5 py-[10px]';


const MyPageTap = ({
    textList,
    checkedText,
    setCheckedText,
}: MyPageTapProps) => {
    return (
        <>
            <div>
                {textList.map((text, index) => <button key={index}
                    className={`${checkedText == text ? checkedColorClass : colorClass} ${defaultSetting}  `} value={text}
                    onClick={() => setCheckedText(text)}
                    type={'button'}
                >
                    {text}
                </button>)}
            </div >
        </>

    )
};

export default MyPageTap
