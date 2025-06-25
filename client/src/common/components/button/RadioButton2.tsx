interface RadioButtonProps {
    name: string;
    textList: {label: string; value: string}[];
    checkedText: string;
    setCheckedText: (value: string) => void
    itemNumber : number
};

const colorClass = 'text-gray-500';

const checkedColorClass = 'bg-white text-gray-800';

const defaultSetting = 'w-[154px] h-[42px] text-base font-roboto rounded-lg';


const RadioButton = ({
    name,
    textList,
    checkedText,
    setCheckedText,
    itemNumber
}: RadioButtonProps) => {

    let wSize;
    if(itemNumber === 3){
        wSize='w-[470px]'
    }
    else if(itemNumber === 2){
        wSize='w-[316px]'
    }



    return (
    <div className={`bg-gray-200 ${wSize} h-[50px] flex items-center justify-center mb-1 rounded-lg`}>
        {textList.map((text, index) => <button key={index}
            className={`${checkedText == text.value ? checkedColorClass : colorClass} ${defaultSetting}`} value={text.value} name={name}
            onClick={() => setCheckedText(text.value)}
            type={'button'}
        >
            {text.label}
        </button>)}
    </div>
    );
};

export default RadioButton;

