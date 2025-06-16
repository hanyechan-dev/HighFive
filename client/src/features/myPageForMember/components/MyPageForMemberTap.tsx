
interface MyPageForMemberTapProps {
    checkedText: string;
    setCheckedText: (value: string) => void
};

const colorClass = 'text-black';

const checkedColorClass = 'bg-semi_theme text-theme';

const defaultSetting = 'w-[476px] h-[42px] text-base font-roboto rounded-lg ml-6';


const MyPageForMemberTapList = [
    {
        label: '회원정보',
        value: 'memberInfo'
    },
    {
        label: '지원서',
        value: 'resume'
    },
    {
        label: '지원서',
        value: '지원서'
    },
    {
        label: '지원서',
        value: '지원서'
    },
]



const MyPageForMemberTap = ({
    checkedText,
    setCheckedText,
}: MyPageForMemberTapProps) => {
    return (
        <div className={defaultSetting}>
            sa
        </div>
    )
}

export default MyPageForMemberTap
