import { useState, useEffect, useCallback } from "react";
import AuthModal from "../../features/auth/modals/AuthModal";
import { AuthProvider } from "../../features/auth/contexts/AuthProvider";

// 고급진 SVG 아이콘들
const AIIcon = () => (
  <svg className="w-16 h-16 text-theme" fill="currentColor" viewBox="0 0 24 24">
    <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15l-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z"/>
    <path d="M12 6c-3.31 0-6 2.69-6 6s2.69 6 6 6 6-2.69 6-6-2.69-6-6-6zm0 10c-2.21 0-4-1.79-4-4s1.79-4 4-4 4 1.79 4 4-1.79 4-4 4z"/>
  </svg>
);

const DocumentIcon = () => (
  <svg className="w-16 h-16 text-theme" fill="currentColor" viewBox="0 0 24 24">
    <path d="M14 2H6c-1.1 0-1.99.9-1.99 2L4 20c0 1.1.89 2 2 2h12c1.1 0 2-.9 2-2V8l-6-6zm2 16H8v-2h8v2zm0-4H8v-2h8v2zm-3-5V3.5L18.5 9H13z"/>
  </svg>
);

const PeopleIcon = () => (
  <svg className="w-16 h-16 text-theme" fill="currentColor" viewBox="0 0 24 24">
    <path d="M16 4c0-1.11.89-2 2-2s2 .89 2 2-.89 2-2 2-2-.89-2-2-2zm4 18v-6h2.5l-2.54-7.63A1.5 1.5 0 0 0 18.54 8H17c-.8 0-1.54.37-2.01 1l-1.7 2.26V16h-1.5v6H20zM12.5 11.5c.83 0 1.5-.67 1.5-1.5s-.67-1.5-1.5-1.5S11 9.17 11 10s.67 1.5 1.5 1.5zM5.5 6c1.11 0 2-.89 2-2s-.89-2-2-2-2 .89-2 2 .89 2 2 2zm2 16v-7H9V9c0-1.1-.9-2-2-2H4c-1.1 0-2 .9-2 2v6.5h1.5V22h4z"/>
  </svg>
);

// 고급진 아바타 아이콘들
const UserAvatar1 = () => (
  <svg className="w-12 h-12 text-white" fill="currentColor" viewBox="0 0 24 24">
    <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
  </svg>
);

const UserAvatar2 = () => (
  <svg className="w-12 h-12 text-white" fill="currentColor" viewBox="0 0 24 24">
    <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
    <circle cx="12" cy="8" r="2"/>
  </svg>
);

const UserAvatar3 = () => (
  <svg className="w-12 h-12 text-white" fill="currentColor" viewBox="0 0 24 24">
    <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
    <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 3c1.66 0 3 1.34 3 3s-1.34 3-3 3-3-1.34-3-3 1.34-3 3-3zm0 14.2c-2.5 0-4.71-1.28-6-3.22.03-1.99 4-3.08 6-3.08 1.99 0 5.97 1.09 6 3.08-1.29 1.94-3.5 3.22-6 3.22z"/>
  </svg>
);

const testimonials = [
  {
    text: "AI 첨삭과 컨설턴트의 피드백을 통해 이력서의 완성도가 크게 높아졌습니다. 덕분에 원하는 기업에 합격할 수 있었어요!",
    name: "정지현",
    role: "취준생",
    avatar: <UserAvatar1 />
  },
  {
    text: "AI 추천 인재 덕분에 우리 회사에 딱 맞는 인재를 빠르게 찾을 수 있었습니다. 채용 과정의 효율성이 크게 향상됐어요.",
    name: "이상식",
    role: "ABC 인사담당자",
    avatar: <UserAvatar2 />
  },
  {
    text: "컨설턴트의 전문 피드백과 AI의 첨삭이 결합된 점이 인상적입니다. 취업 준비생과 기업 모두에게 강력히 추천합니다!",
    name: "백예린",
    role: "전문 컨설턴트",
    avatar: <UserAvatar3 />
  }
];

// Throttle 함수
const throttle = (func: Function, limit: number) => {
  let inThrottle: boolean;
  return function(this: any, ...args: any[]) {
    if (!inThrottle) {
      func.apply(this, args);
      inThrottle = true;
      setTimeout(() => inThrottle = false, limit);
    }
  }
};

const MainPage = () => {
  const [testimonialIdx, setTestimonialIdx] = useState(0);
  const [showAuthModal, setShowAuthModal] = useState(false);
  const [mousePosition, setMousePosition] = useState({ x: 0, y: 0 });
  const [scrollY, setScrollY] = useState(0);
  const [isButtonPressed, setIsButtonPressed] = useState(false);

  // 슬라이더 효과 (자동 전환)
  useEffect(() => {
    const timer = setInterval(() => {
      setTestimonialIdx((idx) => (idx + 1) % testimonials.length);
    }, 4000);
    return () => clearInterval(timer);
  }, []);

  // 스크롤 위치 추적
  useEffect(() => {
    const handleScroll = () => {
      setScrollY(window.scrollY);
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  // 최적화된 마우스 움직임 추적
  const throttledMouseMove = useCallback(
    throttle((e: MouseEvent) => {
      setMousePosition({ x: e.clientX, y: e.clientY });
    }, 16),
    []
  );

  useEffect(() => {
    window.addEventListener('mousemove', throttledMouseMove);
    return () => window.removeEventListener('mousemove', throttledMouseMove);
  }, [throttledMouseMove]);

  const onClickAuth = () => {
    setShowAuthModal(true);
  };

  const handleButtonPress = () => {
    setIsButtonPressed(true);
    setTimeout(() => setIsButtonPressed(false), 150);
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 via-white to-blue-50 font-roboto relative overflow-x-hidden">
      {/* 플로팅 배경 요소들 - 스크롤 기반 애니메이션 */}
      <div className="fixed inset-0 pointer-events-none z-0">
        <div 
          className="absolute top-20 left-10 w-24 h-24 bg-gradient-to-r from-theme/20 to-purple-400/20 rounded-full opacity-30 animate-float-slow blur-sm"
          style={{ transform: `translateY(${scrollY * 0.1}px)` }}
        ></div>
        <div 
          className="absolute top-40 right-20 w-20 h-20 bg-gradient-to-r from-blue-400/20 to-cyan-400/20 rounded-full opacity-30 animate-float-medium blur-sm"
          style={{ transform: `translateY(${scrollY * -0.05}px)` }}
        ></div>
        <div 
          className="absolute bottom-40 left-1/4 w-32 h-32 bg-gradient-to-r from-theme/15 to-pink-400/15 rounded-full opacity-25 animate-float-fast blur-sm"
          style={{ transform: `translateY(${scrollY * 0.08}px)` }}
        ></div>
        <div 
          className="absolute bottom-20 right-1/3 w-16 h-16 bg-gradient-to-r from-emerald-400/20 to-teal-400/20 rounded-full opacity-30 animate-float-slow blur-sm"
          style={{ transform: `translateY(${scrollY * -0.03}px)` }}
        ></div>
      </div>

      {/* Glassmorphism & Gradient Hero */}
      <section className="relative pt-36 pb-32 px-4 text-center bg-gradient-to-br from-slate-100/60 via-white/90 to-blue-50/60 overflow-hidden">
        <div 
          className="absolute -top-40 -left-40 w-[600px] h-[600px] bg-gradient-to-r from-theme/20 to-purple-400/20 rounded-full blur-3xl z-0 animate-pulse-slow"
          style={{ transform: `translateY(${scrollY * 0.2}px)` }}
        ></div>
        <div 
          className="absolute -bottom-40 -right-40 w-[600px] h-[600px] bg-gradient-to-r from-blue-400/20 to-cyan-400/20 rounded-full blur-3xl z-0 animate-pulse-slow"
          style={{ transform: `translateY(${scrollY * -0.15}px)` }}
        ></div>
        <div className="relative z-10">
          <h1 
            className="text-6xl md:text-7xl font-black mb-8 bg-gradient-to-r from-slate-800 via-theme to-slate-700 bg-clip-text text-transparent drop-shadow-lg animate-fade-in animate-gradient-shift animate-theme-shimmer tracking-tight"
            style={{ transform: `translateY(${scrollY * 0.1}px)` }}
          >
            AI와 컨설턴트가 함께하는<br />
            <span className="bg-gradient-to-r from-theme to-pink-500 bg-clip-text text-transparent">채용플랫폼</span>
          </h1>
          <p 
            className="text-xl md:text-2xl text-slate-600 mb-12 max-w-3xl mx-auto animate-fade-in delay-100 leading-relaxed font-light"
            style={{ transform: `translateY(${scrollY * 0.05}px)` }}
          >
            JobPrize는 AI 기술과 전문 컨설턴트의 피드백을 통해 구직자와 기업을 연결하는 혁신적인 채용 플랫폼입니다.<br />
            이력서 첨삭부터 맞춤형 채용 공고까지, 취업과 채용의 모든 과정을 지원합니다.
          </p>
          <div className="flex justify-center items-center mb-8 animate-fade-in delay-200">
            <button 
              className={`relative bg-gradient-to-r from-theme to-pink-500 text-white font-bold py-5 px-16 rounded-3xl text-xl shadow-2xl transition-all duration-300 animate-pulse-subtle group overflow-hidden ${isButtonPressed ? 'scale-95' : 'hover:scale-105 hover:shadow-theme/40'}`}
              onClick={() => {
                onClickAuth();
                handleButtonPress();
              }}
            >
              <span className="relative z-10">서비스 이용하기</span>
              <div className="absolute inset-0 bg-gradient-to-r from-pink-500 to-theme opacity-0 group-hover:opacity-100 transition-opacity duration-300"></div>
              <div className="absolute inset-0 bg-white/20 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-700"></div>
              {/* 리플 효과 */}
              <div className="absolute inset-0 bg-white/30 rounded-full scale-0 group-active:scale-100 transition-transform duration-150 origin-center"></div>
            </button>
          </div>
        </div>
      </section>

      {/* 주요 서비스 - 3D 카드 + 뉴모피즘 */}
      <section className="py-32 px-4 bg-transparent">
        <h2 
          className="text-5xl font-black text-center mb-6 tracking-tight animate-fade-in bg-gradient-to-r from-slate-800 to-slate-600 bg-clip-text text-transparent"
          style={{ transform: `translateY(${Math.max(0, scrollY - 400) * 0.1}px)` }}
        >
          주요 서비스
        </h2>
        <p 
          className="text-center text-slate-500 mb-16 animate-fade-in delay-100 text-lg font-light"
          style={{ transform: `translateY(${Math.max(0, scrollY - 400) * 0.05}px)` }}
        >
          JobPrize의 다양한 서비스를 통해 취업과 채용의 효율성을 높이세요.
        </p>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-12 max-w-7xl mx-auto">
          <div 
            className="relative bg-white/90 backdrop-blur-2xl rounded-3xl p-12 flex flex-col items-center border border-white/60 hover:scale-105 hover:-translate-y-3 transition-all duration-500 group cursor-pointer animate-fade-in delay-200 shadow-neumorphism hover:shadow-neumorphism-hover"
            style={{
              transform: `perspective(1000px) rotateY(${(mousePosition.x - window.innerWidth / 2) * 0.01}deg) rotateX(${(mousePosition.y - window.innerHeight / 2) * 0.01}deg) translateY(${Math.max(0, scrollY - 500) * 0.1}px)`
            }}
          >
            <div className="mb-8 group-hover:scale-110 group-hover:rotate-12 transition-all duration-300 animate-bounce-subtle">
              <AIIcon />
            </div>
            <div className="font-black text-2xl mb-4 bg-gradient-to-r from-theme to-pink-500 bg-clip-text text-transparent">AI + 컨설턴트 첨삭</div>
            <div className="text-slate-600 text-center leading-relaxed text-lg font-light">AI가 1차 첨삭을 제공하고, 전문 컨설턴트가 최종 검토하여 취업 성공에 한 걸음 더 가까워지세요.</div>
          </div>
          <div 
            className="relative bg-white/90 backdrop-blur-2xl rounded-3xl p-12 flex flex-col items-center border border-white/60 hover:scale-105 hover:-translate-y-3 transition-all duration-500 group cursor-pointer animate-fade-in delay-300 shadow-neumorphism hover:shadow-neumorphism-hover"
            style={{
              transform: `perspective(1000px) rotateY(${(mousePosition.x - window.innerWidth / 2) * 0.01}deg) rotateX(${(mousePosition.y - window.innerHeight / 2) * 0.01}deg) translateY(${Math.max(0, scrollY - 500) * 0.1}px)`
            }}
          >
            <div className="mb-8 group-hover:scale-110 group-hover:rotate-12 transition-all duration-300 animate-bounce-subtle delay-100">
              <DocumentIcon />
            </div>
            <div className="font-black text-2xl mb-4 bg-gradient-to-r from-theme to-pink-500 bg-clip-text text-transparent">맞춤형 채용 공고</div>
            <div className="text-slate-600 text-center leading-relaxed text-lg font-light">AI가 분석한 맞춤 포지션을 바탕으로 적합한 채용 공고를 추천합니다. 원하는 정보로 쉽고 빠르게 확인하세요.</div>
          </div>
          <div 
            className="relative bg-white/90 backdrop-blur-2xl rounded-3xl p-12 flex flex-col items-center border border-white/60 hover:scale-105 hover:-translate-y-3 transition-all duration-500 group cursor-pointer animate-fade-in delay-400 shadow-neumorphism hover:shadow-neumorphism-hover"
            style={{
              transform: `perspective(1000px) rotateY(${(mousePosition.x - window.innerWidth / 2) * 0.01}deg) rotateX(${(mousePosition.y - window.innerHeight / 2) * 0.01}deg) translateY(${Math.max(0, scrollY - 500) * 0.1}px)`
            }}
          >
            <div className="mb-8 group-hover:scale-110 group-hover:rotate-12 transition-all duration-300 animate-bounce-subtle delay-200">
              <PeopleIcon />
            </div>
            <div className="font-black text-2xl mb-4 bg-gradient-to-r from-theme to-pink-500 bg-clip-text text-transparent">인재풀 관리</div>
            <div className="text-slate-600 text-center leading-relaxed text-lg font-light">기업은 AI와 인사 컨설턴트를 통해 적합한 인재를 빠르게 찾고, 효율적으로 관리할 수 있습니다.</div>
          </div>
        </div>
      </section>

      {/* 이용자 후기 - 3D 슬라이더 */}
      <section className="py-32 px-4 bg-gradient-to-br from-slate-50/60 via-white/90 to-blue-50/60 relative overflow-hidden">
        <h2 
          className="text-5xl font-black text-center mb-6 animate-fade-in bg-gradient-to-r from-slate-800 to-slate-600 bg-clip-text text-transparent"
          style={{ transform: `translateY(${Math.max(0, scrollY - 800) * 0.1}px)` }}
        >
          이용자 후기
        </h2>
        <p 
          className="text-center text-slate-500 mb-16 animate-fade-in delay-100 text-lg font-light"
          style={{ transform: `translateY(${Math.max(0, scrollY - 800) * 0.05}px)` }}
        >
          JobPrize를 통해 성공적인 취업과 채용을 경험한 이용자들의 후기입니다.
        </p>
        <div className="flex justify-center items-center">
          <div className="relative w-full max-w-2xl">
            <div 
              className="bg-white/90 backdrop-blur-2xl rounded-3xl shadow-2xl p-12 flex flex-col items-center border border-white/60 animate-fade-in delay-200 transition-all duration-700 hover:shadow-neumorphism-hover"
              style={{
                transform: `perspective(1000px) rotateY(${(mousePosition.x - window.innerWidth / 2) * 0.005}deg) rotateX(${(mousePosition.y - window.innerHeight / 2) * 0.005}deg) translateY(${Math.max(0, scrollY - 900) * 0.1}px)`
              }}
            >
              <div className="w-20 h-20 rounded-full bg-gradient-to-r from-theme/20 to-pink-400/20 flex items-center justify-center mb-8 animate-bounce-subtle">
                {testimonials[testimonialIdx].avatar}
              </div>
              <div className="text-slate-700 text-center mb-8 text-xl font-light leading-relaxed italic">"{testimonials[testimonialIdx].text}"</div>
              <div className="text-lg text-slate-500 font-semibold bg-gradient-to-r from-theme to-pink-500 bg-clip-text text-transparent">{testimonials[testimonialIdx].name} · {testimonials[testimonialIdx].role}</div>
            </div>
            {/* 슬라이더 점 */}
            <div className="flex justify-center gap-4 mt-10">
              {testimonials.map((_, idx) => (
                <button
                  key={idx}
                  className={`w-5 h-5 rounded-full transition-all duration-300 hover:scale-125 ${idx === testimonialIdx ? 'bg-gradient-to-r from-theme to-pink-500 shadow-lg' : 'bg-slate-300 hover:bg-slate-400'}`}
                  onClick={() => setTestimonialIdx(idx)}
                  aria-label={`후기 ${idx + 1}번 보기`}
                />
              ))}
            </div>
          </div>
        </div>
      </section>

      {/* 통계 섹션 - 뉴모피즘 카드 */}
      <section className="py-24 px-4 bg-gradient-to-r from-theme/10 to-pink-100/20 text-center">
        <h3 
          className="text-4xl font-black mb-10 animate-fade-in bg-gradient-to-r from-slate-800 to-slate-600 bg-clip-text text-transparent"
          style={{ transform: `translateY(${Math.max(0, scrollY - 1200) * 0.1}px)` }}
        >
          JobPrize와 함께하는 이유
        </h3>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-10 max-w-5xl mx-auto animate-fade-in delay-100">
          <div 
            className="bg-white/90 backdrop-blur-2xl rounded-3xl p-10 border border-white/60 shadow-neumorphism hover:shadow-neumorphism-hover transition-all duration-300 hover:scale-105 group"
            style={{ transform: `translateY(${Math.max(0, scrollY - 1200) * 0.1}px)` }}
          >
            <div className="text-5xl font-black bg-gradient-to-r from-theme to-pink-500 bg-clip-text text-transparent mb-4 group-hover:scale-110 transition-transform duration-300">98%</div>
            <div className="text-slate-600 font-medium text-lg">이력서 첨삭 만족도</div>
          </div>
          <div 
            className="bg-white/90 backdrop-blur-2xl rounded-3xl p-10 border border-white/60 shadow-neumorphism hover:shadow-neumorphism-hover transition-all duration-300 hover:scale-105 group"
            style={{ transform: `translateY(${Math.max(0, scrollY - 1200) * 0.1}px)` }}
          >
            <div className="text-5xl font-black bg-gradient-to-r from-theme to-pink-500 bg-clip-text text-transparent mb-4 group-hover:scale-110 transition-transform duration-300">3배</div>
            <div className="text-slate-600 font-medium text-lg">채용 성공률 향상</div>
          </div>
          <div 
            className="bg-white/90 backdrop-blur-2xl rounded-3xl p-10 border border-white/60 shadow-neumorphism hover:shadow-neumorphism-hover transition-all duration-300 hover:scale-105 group"
            style={{ transform: `translateY(${Math.max(0, scrollY - 1200) * 0.1}px)` }}
          >
            <div className="text-5xl font-black bg-gradient-to-r from-theme to-pink-500 bg-clip-text text-transparent mb-4 group-hover:scale-110 transition-transform duration-300">24시간</div>
            <div className="text-slate-600 font-medium text-lg">AI 피드백 제공</div>
          </div>
        </div>
      </section>

      {/* 푸터 */}
      <footer className="py-12 px-4 bg-slate-100/80 text-center text-slate-500 text-base mt-8 animate-fade-in delay-200">
        &copy; {new Date().getFullYear()} JobPrize. All rights reserved. | 이용약관 | 개인정보처리방침 | 고객센터
      </footer>

      {/* 인증 모달 */}
      {showAuthModal && (
        <AuthProvider>
          <AuthModal onClose={() => setShowAuthModal(false)} />
        </AuthProvider>
      )}

      {/* 애니메이션 키프레임 */}
      <style>{`
        .animate-fade-in { animation: fadeInUp 1s both; }
        .animate-fade-in.delay-100 { animation-delay: 0.1s; }
        .animate-fade-in.delay-200 { animation-delay: 0.2s; }
        .animate-fade-in.delay-300 { animation-delay: 0.3s; }
        .animate-fade-in.delay-400 { animation-delay: 0.4s; }
        
        .animate-float-slow { animation: float 6s ease-in-out infinite; }
        .animate-float-medium { animation: float 4s ease-in-out infinite; }
        .animate-float-fast { animation: float 3s ease-in-out infinite; }
        
        .animate-pulse-slow { animation: pulse 4s ease-in-out infinite; }
        .animate-pulse-subtle { animation: pulse 2s ease-in-out infinite; }
        
        .animate-bounce-subtle { animation: bounce 2s ease-in-out infinite; }
        .animate-bounce-subtle.delay-100 { animation-delay: 0.5s; }
        .animate-bounce-subtle.delay-200 { animation-delay: 1s; }
        
        .animate-gradient-shift { 
          background-size: 200% 200%;
          animation: gradientShift 3s ease infinite;
        }
        
        .animate-theme-shimmer {
          background: linear-gradient(90deg, #EE57CD, #ec4899, #EE57CD);
          background-size: 200% 200%;
          animation: themeShimmer 4s ease-in-out infinite;
          background-clip: text;
          -webkit-background-clip: text;
          -webkit-text-fill-color: transparent;
        }
        
        .shadow-neumorphism {
          box-shadow: 25px 25px 75px #d1d9e6, -25px -25px 75px #ffffff;
        }
        
        .shadow-neumorphism-hover {
          box-shadow: 35px 35px 100px #c1c9d6, -35px -35px 100px #ffffff;
        }
        
        @keyframes fadeInUp {
          from { opacity: 0; transform: translateY(40px); }
          to { opacity: 1; transform: translateY(0); }
        }
        
        @keyframes float {
          0%, 100% { transform: translateY(0px); }
          50% { transform: translateY(-20px); }
        }
        
        @keyframes gradientShift {
          0% { background-position: 0% 50%; }
          50% { background-position: 100% 50%; }
          100% { background-position: 0% 50%; }
        }
        
        @keyframes themeShimmer {
          0% { background-position: -200% 0; }
          100% { background-position: 200% 0; }
        }
        
        /* 접근성 개선 */
        @media (prefers-reduced-motion: reduce) {
          .animate-* { animation: none !important; }
          * { transition: none !important; }
        }
      `}</style>
    </div>
  );
};

export default MainPage; 