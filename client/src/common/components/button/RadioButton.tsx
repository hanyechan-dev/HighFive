interface RadioButtonProps {
    name: string;
    textList: {label: string; value: string}[];
    checkedText: string;
    setCheckedText: (value: string) => void
    size? : "document" | "externalResume" | "internalResume"
};

const colorClass = 'text-black';

const checkedColorClass = 'bg-semi_theme text-theme';

const defaultSetting = 'w-[154px] h-[42px] text-base font-roboto rounded-lg mb-6';

const labelSetting = 'font-roboto text-base mb-2 inline-block ml-[24px]';

const sizeClass = {
    document: 'w-[317px] h-[42px]',
    externalResume: 'w-[238px] h-[42px]',
    internalResume: 'w-[226px] h-[42px]'
}


const RadioButton = ({
    name,
    textList,
    checkedText,
    setCheckedText,
    size
}: RadioButtonProps) => {



    return (<>
        <div className={labelSetting}>{name}</div>
        <br />
        {textList.map((text,index) => <button
            className={`${checkedText == text.value ? checkedColorClass : colorClass} ${defaultSetting} ${size ? sizeClass[size] : ''} ${index === 0 ? 'ml-6' : ''}`} value={text.value} name={name}
            onClick={() => setCheckedText(text.value)}
            type={'button'}
        >
            {text.label}
        </button>)}
    </>
    );
};

export default RadioButton;

