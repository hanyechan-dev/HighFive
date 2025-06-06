const MapPin = (props: React.SVGProps<SVGSVGElement>) => (
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
    {/* 핀 외곽: 밑부분이 뾰족한 모양 */}
    <path
      d="
        M10 2
        C6 2 2 6 2 10
        C2 13 5 15 10 19
        C15 15 18 13 18 10
        C18 6 14 2 10 2
        Z
      "
      fill="none"
    />
    {/* 핀 중심 원 */}
    <circle cx="10" cy="9" r="2" fill="none" />
  </svg>
);

export default MapPin;
