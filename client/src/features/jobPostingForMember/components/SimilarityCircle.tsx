interface SimilarityCircleProps {
  similarityScore: number; // 0 ~ 100
  size?: number; // optional, default 80
}

const SimilarityCircle = ({ similarityScore, size = 80 }: SimilarityCircleProps) => {
  const radius = size / 2 - 5;
  const circumference = 2 * Math.PI * radius;
  const offset = circumference - (similarityScore / 100) * circumference;

  return (
    <svg width={size} height={size} >
      <circle
        cx={size / 2}
        cy={size / 2}
        r={radius}
        stroke="#e5e7eb" // gray-200
        strokeWidth="4"
        fill="none"
      />
      <circle
        cx={size / 2}
        cy={size / 2}
        r={radius}
        stroke="#EE57CD" // blue-500
        strokeWidth="4"
        fill="none"
        strokeDasharray={circumference}
        strokeDashoffset={offset}
        strokeLinecap="round"
        transform={`rotate(-90 ${size / 2} ${size / 2})`} // 진행만 90도 회전
      />
     <text
        x="50%"
        y="50%"
        textAnchor="middle"
        dominantBaseline="middle"
        fontSize={size * 0.25}
        fill="#EE57CD"
      >
        {similarityScore}%
      </text>
    </svg>
  );
};

export default SimilarityCircle;