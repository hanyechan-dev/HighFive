interface SelectProps {
    label: string;
    options: {label: string; value: string}[];
    size: 's' | 'm' | 'pbm' | 'bibm' | 'l';
    disabled: boolean;
    value: string;
    setValue: (value: string) => void

}

const sizeClass = {
    s: 'w-[220px] h-[42px]',
    m: 'w-[464px] h-[42px]',
    pbm : 'w-[548px] h-[42px]',
    bibm : 'w-[500px] h-[42px]',
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
        <div>
            <label className={labelSetting}>{label}</label>
            <br />
            <select className={`${sizeClass[size]} ${defaultSetting}`}
                disabled={disabled}
                value={value}
                onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setValue(e.target.value)}>
                <option value="" disabled>{label}을 선택해주세요</option>
                {options.map((opt) => (
                    <option key={opt.value} value={opt.value}>
                        {opt.label}
                    </option>
                ))}
            </select>
        </div>
    );
}

export default Select;