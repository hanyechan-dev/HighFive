import { useRef, useEffect } from "react";

interface TextAreaProps {
    label : string;
    placeholder : string;
    disabled : boolean;
    value : string;
    setValue: (value: string) => void
}

function TextArea({
    label,
    placeholder,
    disabled,
    value,
    setValue,
}: TextAreaProps) {

    const textareaRef = useRef<HTMLTextAreaElement>(null);

    const defaultSetting = 'w-[720px] min-h-[100px] text-base font-roboto rounded-lg border border-gray-300 mb-6 px-3 py-2 break-words resize-none'
    const labelSetting = 'font-roboto text-base mb-2 inline-block'

    useEffect(() => {
    const el = textareaRef.current;
    if (el) {
      el.style.height = 'auto'; // 초기화
      el.style.height = `${el.scrollHeight+2}px`; // 내용 기반 높이 재설정
    }
  }, [value]);

    return (
        <div>
            <label className={labelSetting}>{label}</label>
            <br />
            <textarea
            ref={textareaRef} 
            className={`${defaultSetting}` }
            placeholder={placeholder} 
            disabled={disabled} 
            value ={value} 
            onChange={(e: React.ChangeEvent<HTMLTextAreaElement>) => setValue(e.target.value)} />
        </div>
    );
}

export default TextArea;