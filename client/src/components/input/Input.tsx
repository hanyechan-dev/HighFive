interface InputProps {
    label : string;
    placeholder : string;
    size : 's' | 'm' | 'l';
    disabled : boolean;
    type : 'text' | 'password' | 'email';
    value : string;
    setValue: (value: string) => void
}

const sizeClass = {
    s: 'w-[130px] h-[42px]',
    m: 'w-[390px] h-[42px]',
    l: 'w-[720px] h-[42px]',
};

function Input({
    label,
    placeholder,
    size,
    disabled,
    type,
    value,
    setValue,
}: InputProps) {

    const defaultSetting = 'text-base font-roboto rounded-lg border border-gray-300 mb-6 px-3'
    const labelSetting = 'font-roboto text-base mb-2 inline-block'

    return (
        <div>
            <label className={labelSetting}>{label}</label>
            <br />
            <input className={`${sizeClass[size]} ${defaultSetting}`} 
            placeholder={placeholder} 
            disabled={disabled} 
            type = {type} 
            value ={value} 
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setValue(e.target.value)} />
        </div>
    );
}

export default Input;