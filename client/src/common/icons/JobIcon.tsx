const JobIcon = (props: React.SVGProps<SVGSVGElement>) => (
    <svg
        width="20"
        height="20"
        viewBox="0 0 20 20"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
        stroke="black"
        strokeWidth="1.5"
        strokeLinecap="round"
        strokeLinejoin="round"
        {...props}
    >
        <rect x="4" y="3" width="12" height="14" rx="1" />
        <path d="M8 3V1h4v2" />
        <line x1="7" y1="8" x2="13" y2="8" />
        <line x1="7" y1="11" x2="13" y2="11" />
        <line x1="7" y1="14" x2="11" y2="14" />
    </svg>
);

export default JobIcon;
