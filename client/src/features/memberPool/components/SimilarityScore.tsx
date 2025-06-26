import { useEffect, useRef, useState } from 'react';
import type { FC } from 'react';

interface SimilarityScoreProps {
  score: number; // 0~100
  size?: number; // px, 기본값 56
  color?: string; // progress bar color (Tailwind class or hex)
  bgColor?: string; // background color
  className?: string;
  active?: boolean; // 외부에서 hover 상태 제어
}

const SimilarityScore: FC<SimilarityScoreProps> = ({ score, size = 56, color = '#EE57CD', className = '', active }) => {
  const [progress, setProgress] = useState(0);
  const [isHovered, setIsHovered] = useState(false);
  const requestRef = useRef<number>();
  const duration = 50;
  const radius = (size / 2) - 6;
  const circumference = 2 * Math.PI * radius;

  // 실제로 사용할 hover 상태: active prop이 있으면 그걸, 아니면 내부 state
  const show = typeof active === 'boolean' ? active : isHovered;

  useEffect(() => {
    if (!show) {
      setProgress(0);
      return;
    }
    const start = performance.now();
    const animate = (now: number) => {
      const elapsed = now - start;
      const t = Math.min(elapsed / duration, 1);
      const end = score * 1.26;
      const next = t * end;
      setProgress(next);
      if (t < 1) {
        requestRef.current = requestAnimationFrame(animate);
      }
    };
    requestRef.current = requestAnimationFrame(animate);
    return () => { if (requestRef.current) cancelAnimationFrame(requestRef.current); };
  }, [show, score]);

  // color가 hex 코드(#)면 stroke, 아니면 className
  const isHex = color?.startsWith('#');

  return (
    <div
      className={`relative flex items-center justify-center select-none ${className}`}
      style={{ width: size, height: size }}
      onMouseEnter={typeof active === 'undefined' ? () => setIsHovered(true) : undefined}
      onMouseLeave={typeof active === 'undefined' ? () => setIsHovered(false) : undefined}
    >
      {/* 숫자(수치)는 flex로 완전 중앙 정렬, 연한 회색(text-gray-700) */}
      <div className="absolute inset-0 flex items-center justify-center z-20 pointer-events-none">
        <span
          className="text-lg font-semibold text-gray-700 pt-[2px]"
          style={{
            fontSize: size / 4,
            fontWeight: 600,
            
          }}
        >
          {score}%
        </span>
      </div>
      <div className={`absolute inset-0 flex items-center justify-center transition-all duration-500 ${show ? 'opacity-100 scale-110' : 'opacity-0'}`} style={{ zIndex: 10 }}>
        <svg width={size} height={size} viewBox={`0 0 ${size} ${size}`} className="w-full h-full">
          {/* 배경 회색 원 <circle> 제거 */}
          {isHex ? (
            <circle
              strokeWidth={size/14}
              strokeDasharray={circumference}
              strokeDashoffset={circumference - progress}
              strokeLinecap="round"
              stroke={color}
              fill="transparent"
              r={radius}
              cx={size/2}
              cy={size/2}
              style={{ transition: "stroke-dashoffset 0.2s" }}
            />
          ) : (
            <circle
              strokeWidth={size/14}
              strokeDasharray={circumference}
              strokeDashoffset={circumference - progress}
              strokeLinecap="round"
              className={color}
              fill="transparent"
              r={radius}
              cx={size/2}
              cy={size/2}
              style={{ transition: "stroke-dashoffset 0.2s" }}
            />
          )}
        </svg>
      </div>
    </div>
  );
};

export default SimilarityScore; 