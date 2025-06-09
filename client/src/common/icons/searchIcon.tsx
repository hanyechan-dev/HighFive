const SearchIcon: React.FC<React.SVGProps<SVGSVGElement>> = (props) => {
  // 기본 20×20, 필요하면 props 로 크기 조절
  const size = props.width || props.height || 20;

  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width={size}
      height={size}
      viewBox="0 0 20 20"
      fill="none"
      stroke="currentColor"
      strokeWidth={2}
      strokeLinecap="round"
      strokeLinejoin="round"
      {...props}
    >
      {/* 원래 11,11에 반지름 8 이었으니
          20×20에 맞춰 10,10에 반지름 8 로 배치 */}
      <circle cx="9" cy="9" r="7" />
      {/* 대각선도 20×20 기준으로 */}
      <path d="M20 20 L15 15" />
    </svg>
  );
};

export default SearchIcon;