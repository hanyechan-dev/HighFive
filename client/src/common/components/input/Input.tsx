interface InputProps {
    label: string;
    placeholder: string;
    size: 'ibs' | 'ebs' | 's' | 'ibm' |'ebm'| 'm' | 'ibl' |'ebl' | 'l';
    disabled: boolean;
    type: 'text' | 'password' | 'email' | 'date' ;
    value: string;
    setValue: (value: string) => void
}

const sizeClass = {
    ibs: 'w-[195.5px] h-[42px]',
    ebs : 'w-[208px] h-[42px]',
    s: 'w-[220px] h-[42px]',
    ibm :'w-[415px] h-[42px]',
    ebm: 'w-[439px] h-[42px]',
    m: 'w-[464px] h-[42px]',
    ibl :'w-[854px] h-[42px]',
    ebl: 'w-[904px] h-[42px]',
    l: 'w-[952px] h-[42px]',
};

const defaultSetting = 'text-base font-roboto rounded-lg border border-gray-300 mb-6 ml-[24px] px-3';

const labelSetting = 'font-roboto text-base mb-2 inline-block ml-[24px]';

const Input = ({
    label,
    placeholder,
    size, 
    disabled, 
    type, 
    value, 
    setValue }: InputProps) => {


    return (
        <div>
            <label className={labelSetting}>{label}</label>
            <br />
            <input className={`${sizeClass[size]} ${defaultSetting}`}
                placeholder={placeholder}
                disabled={disabled}
                type={type}
                value={value}
                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setValue(e.target.value)} />
        </div>
    );
}

export default Input;