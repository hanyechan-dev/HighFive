interface RadioButtonProps {
    name: string;
    textList: string[];
    checkedText: string;
    setCheckedText: (value: string) => void
};

const colorClass = 'text-black';

const checkedColorClass = 'bg-semi_theme text-theme';

const defaultSetting = 'w-[130px] h-[42px] text-base font-roboto rounded-lg';

const labelSetting = 'font-roboto text-base mb-2 inline-block';


const RadioButton = ({
    name,
    textList,
    checkedText,
    setCheckedText
}: RadioButtonProps) => {



    return (<>
        <div className={labelSetting}>{name}</div>
        <br />
        {textList.map(text => <button
            className={`${checkedText == text ? checkedColorClass : colorClass} ${defaultSetting}`} value={text} name={name}
            onClick={() => setCheckedText(text)}
            type={'button'}
        >
            {text}
        </button>)}
    </>
    );
};

export default RadioButton;

