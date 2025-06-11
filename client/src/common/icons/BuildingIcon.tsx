const BuildingIcon = (props: React.SVGProps<SVGSVGElement>) => (
  <svg
    width="20"
    height="20"
    viewBox="0 0 20 20"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    {...props}
  >
    {/* 왼쪽 작은 건물 */}
    <rect
      x="1"
      y="7"
      width="6"
      height="12"
      stroke="black"
      fill="none"
      strokeWidth="1.5"
    />
    {/* 오른쪽 큰 건물 */}
    <rect
      x="11"
      y="2"
      width="8"
      height="17"
      stroke="black"
      fill="none"
      strokeWidth="1.5"
    />
    {/* 간단한 창문 */}
    <rect x="3" y="9" width="2" height="2" stroke="black" fill="none" strokeWidth="1" />
    <rect x="13" y="4" width="2" height="2" stroke="black" fill="none" strokeWidth="1" />
  </svg>
);


export default BuildingIcon;