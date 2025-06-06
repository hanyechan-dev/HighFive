interface SelectProps {
    label: string;
    options: string[];
    size: 's' | 'm' | 'l';
    disabled: boolean;
    value: string;
    setValue: (value: string) => void

}

const sizeClass = {
    s: 'w-[220px] h-[42px]',
    m: 'w-[464px] h-[42px]',
    l: 'w-[952px] h-[42px]',
};

const defaultSetting = 'text-base font-roboto rounded-lg border border-gray-300 mb-6 ml-[24px] px-3';

const labelSetting = 'font-roboto text-base mb-2 inline-block ml-[24px]';

const Select = ({
    label,
    options,
    size,
    disabled,
    value,
    setValue }: SelectProps) => {


    return (
        <span>
            <label className={labelSetting}>{label}</label>
            <br />
            <select className={`${sizeClass[size]} ${defaultSetting}`}
                disabled={disabled}
                value={value}
                onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setValue(e.target.value)}>
                <option value="" disabled>{label}을 선택해주세요</option>
                {options.map((opt) => (
                    <option key={opt} value={opt}>
                        {opt}
                    </option>
                ))}
            </select>
        </span>
    );
}

export default Select;