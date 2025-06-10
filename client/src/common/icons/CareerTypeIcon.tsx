const CareerTypeIcon = (props: React.SVGProps<SVGSVGElement>) => (
  <svg
    width="20"
    height="20"
    viewBox="0 0 20 20"
    stroke="currentColor"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    {...props}
  >
    {/* 가방 본체 */}
    <rect x="3" y="6" width="14" height="10" rx="1.5" strokeWidth="1" fill="none" />

    {/* 손잡이 */}
    <rect x="7" y="3.5" width="6" height="3" rx="0.8" strokeWidth="1" fill="none" />

    {/* 중간 분할선 */}
    <line x1="3" y1="11" x2="17" y2="11" strokeWidth="1" />
  </svg>
);

export default CareerTypeIcon;
