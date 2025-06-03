import { useState } from "react";

interface InputProps {
    label : string;
    placeholder : string;
    size : 's' | 'm' | 'l';
    disabled : boolean;
    type : 'text' | 'password' | 'email';
    value : string;
    onChange : (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const sizeClass = {
    s: 'w-[130px] h-[42px]',
    m: 'w-[300px] h-[42px]',
    l: 'w-[720px] h-[42px]',
};

function Input({
    label,
    placeholder,
    size,
    disabled,
    type,
    value,
    onChange,
}: InputProps) {

    const defaultSetting = 'text-base font-roboto font-semibold rounded-lg border border-gray-300'

    const [value, setValue] = useState('');


    return (
        <div>
            <label>{label}</label>
            <input className={`${sizeClass[size]} ${defaultSetting}`} placeholder={placeholder} disabled={disabled} type = {type} value ={value} onChange={(e) => setValue(e.target.value)} />
        </div>
    );
}

export default Input;