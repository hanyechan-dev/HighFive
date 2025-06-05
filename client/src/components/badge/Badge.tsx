interface BadgeProps {
    icon?: React.ReactNode;
    text: string;
}

const defaultSetting = 'w-[100px] flex items-center rounded-full border pl-2.5 py-1 text-xs font-semibold bg-[#EE57CD]/10 text-[#666666] border-[#EE57CD]/30';

const Badge = ({
    icon,
    text }: BadgeProps) => (
    <div className={defaultSetting}>
        {icon && <span className="mr-3">{icon}</span>}
        <span>{text}</span>
    </div>
);

export default Badge;