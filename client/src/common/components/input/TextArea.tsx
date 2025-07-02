import { useRef, useEffect } from "react";

interface TextAreaProps {
    size : 'm' | 'ibl' | 'ml' | 'l' | 'mibl' | 'pbl';
    label: string;
    placeholder: string;
    disabled: boolean;
    value: string;
    setValue: (value: string) => void
};

const sizeClass = {
    m: 'w-[464px]',
    ibl :'w-[854px] h-[42px]',
    ml:'w-[904px]',
    l: 'w-[952px]',
    mibl: 'w-[976px]',
    pbl: 'w-[1120px]'
}

const defaultSetting = 'min-h-[100px] text-base font-roboto rounded-lg border border-gray-300 ml-[24px] mb-6 px-3 py-2 break-words resize-none';

const labelSetting = 'font-roboto text-base mb-2 inline-block ml-[24px]';



const TextArea = ({
    size,
    label,
    placeholder,
    disabled,
    value,
    setValue,
}: TextAreaProps) => {


    console.log("RAW VALUE:", value);
    console.log("JSON STRINGIFIED:", JSON.stringify(value));



    const textareaRef = useRef<HTMLTextAreaElement>(null);

    useEffect(() => {
        const el = textareaRef.current;
        if (el) {
            el.style.height = 'auto'; // 초기화
            el.style.height = `${el.scrollHeight + 3}px`; // 내용 기반 높이 재설정
        }
    }, [value]);


    return (
        <div>
            <label className={labelSetting}>{label}</label>
            <br />
            <textarea
                ref={textareaRef}
                className={`${sizeClass[size]} ${defaultSetting}`}
                placeholder={placeholder}
                disabled={disabled}
                value={value}
                onChange={(e: React.ChangeEvent<HTMLTextAreaElement>) => setValue(e.target.value)} />
        </div>
    );
}

export default TextArea;