import { useState, useEffect } from 'react';
import AuthModal from '../../features/auth/modals/AuthModal';
import { AuthProvider } from '../../features/auth/contexts/AuthProvider';

// 깔끔한 SVG 아이콘들
const AIIcon = () => (
  <svg className="w-12 h-12 text-theme" fill="currentColor" viewBox="0 0 24 24">
    <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15l-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z" />
  </svg>
);

const DocumentIcon = () => (
  <svg className="w-12 h-12 text-theme" fill="currentColor" viewBox="0 0 24 24">
    <path d="M14 2H6c-1.1 0-1.99.9-1.99 2L4 20c0 1.1.89 2 2 2h12c1.1 0 2-.9 2-2V8l-6-6zm2 16H8v-2h8v2zm0-4H8v-2h8v2zm-3-5V3.5L18.5 9H13z" />
  </svg>
);

const SearchIcon = () => (
  <svg className="w-12 h-12 text-theme" fill="currentColor" viewBox="0 0 24 24">
    <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z" />
  </svg>
);

// 깔끔한 아바타 아이콘들
const UserAvatar1 = () => (
  <svg className="w-10 h-10 text-white" fill="currentColor" viewBox="0 0 24 24">
    <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z" />
  </svg>
);

const UserAvatar2 = () => (
  <svg className="w-10 h-10 text-white" fill="currentColor" viewBox="0 0 24 24">
    <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z" />
  </svg>
);

const UserAvatar3 = () => (
  <svg className="w-10 h-10 text-white" fill="currentColor" viewBox="0 0 24 24">
    <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z" />
  </svg>
);

const testimonials = [
  {
    text: 'AI 첨삭과 컨설턴트의 피드백을 통해 이력서의 완성도가 크게 높아졌습니다. 덕분에 원하는 기업에 합격할 수 있었어요!',
    name: '정지현',
    role: '취준생',
    avatar: <UserAvatar1 />,
  },
  {
    text: 'AI 추천 인재 덕분에 우리 회사에 딱 맞는 인재를 빠르게 찾을 수 있었습니다. 채용 과정의 효율성이 크게 향상됐어요.',
    name: '이상식',
    role: 'ABC 인사담당자',
    avatar: <UserAvatar2 />,
  },
  {
    text: '컨설턴트의 전문 피드백과 AI의 첨삭이 결합된 점이 인상적입니다. 취업 준비생과 기업 모두에게 강력히 추천합니다!',
    name: '백예린',
    role: '전문 컨설턴트',
    avatar: <UserAvatar3 />,
  },
];

// 스타일 태그로 애니메이션 추가
const LogoAnimationStyle = () => (
  <style>{`
    @keyframes roll-bounce-in {
      0% {
        transform: translateX(-200px) rotate(-360deg) scale(1);
        opacity: 0;
        box-shadow: 0 8px 32px 0 rgba(232, 79, 207, 0);
      }
      60% {
        opacity: 1;
        box-shadow: 0 8px 32px 0 rgba(232, 79, 207, 0.15);
      }
      80% {
        transform: translateX(0) rotate(0deg) scale(1.15);
        box-shadow: 0 12px 36px 0 rgba(232, 79, 207, 0.25);
      }
      100% {
        transform: translateX(0) rotate(0deg) scale(1);
        opacity: 1;
        box-shadow: 0 8px 32px 0 rgba(232, 79, 207, 0.18);
      }
    }
  `}</style>
);

const MainPage = () => {
  const [testimonialIdx, setTestimonialIdx] = useState(0);

  // 슬라이더 효과 (자동 전환)
  useEffect(() => {
    const timer = setInterval(() => {
      setTestimonialIdx((idx) => (idx + 1) % testimonials.length);
    }, 5000);
    return () => clearInterval(timer);
  }, []);



  return (
    <div className="bg-white font-roboto antialiased">
      {/* Hero Section */}
      <section className="relative pt-32 pb-6 px-6 text-center bg-gradient-to-b from-slate-50 to-white">
        <LogoAnimationStyle />
        <div className="max-w-4xl mx-auto">
          {/* 텍스트 로고 애니메이션 */}
          <div className="flex justify-center items-center mb-10 select-none">
            <span
              className="inline-flex items-center justify-center mr-2"
              style={{
                width: 80,
                height: 80,
                borderRadius: 20,
                border: '6px solid #e84fcf',
                background: '#fff',
                color: '#e84fcf',
                fontWeight: 800,
                fontSize: 56,
                fontFamily: 'Inter, Arial, Helvetica, sans-serif',
                boxSizing: 'border-box',
                animation: 'roll-bounce-in 1.3s cubic-bezier(0.68, -0.55, 0.27, 1.55)',
              }}
            >
              J
            </span>
            <span
              className="text-[60px] font-bold"
              style={{
                color: '#e84fcf',
                letterSpacing: '-2px',
                fontFamily: 'Inter, Arial, Helvetica, sans-serif',
              }}
            >
              obPrize
            </span>
          </div>
          <h1 className="text-5xl md:text-6xl font-bold mb-8 text-slate-900 leading-tight">
            AI와 컨설턴트가 함께하는
            <br />
            <span className="text-theme">채용플랫폼</span>
          </h1>
          <p className="text-xl text-slate-600 mb-12 leading-relaxed max-w-3xl mx-auto">
            JobPrize는 AI 기술과 전문 컨설턴트의 피드백을 통해
            <br />
            구직자와 기업을 연결하는 혁신적인 채용 플랫폼입니다.
            <br />
            이력서 첨삭부터 맞춤형 채용 공고까지, 취업과 채용의 모든 과정을 지원합니다.
          </p>

        </div>
      </section>

      {/* 주요 서비스 */}
      <section className="py-24 px-6 bg-gray-100">
        <div className="max-w-6xl mx-auto">
          <h2 className="text-4xl font-bold text-center mb-4 text-slate-900">주요 서비스</h2>
          <p className="text-center text-slate-600 mb-16 text-lg">
            JobPrize의 다양한 서비스를 통해 취업과 채용의 효율성을 높이세요.
          </p>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-12">
            <div className="text-center group">
              <div className="w-20 h-20 mx-auto mb-6 bg-slate-100 rounded-2xl flex items-center justify-center group-hover:bg-theme/10 transition-colors duration-300">
                <AIIcon />
              </div>
              <h3 className="text-2xl font-bold mb-4 text-slate-900">AI + 컨설턴트 첨삭</h3>
              <p className="text-slate-600 leading-relaxed">
                AI가 1차 첨삭을 제공하고, 전문 컨설턴트가<br />
                최종 검토하여 취업 성공에 한 걸음 더
                가까워지세요.
              </p>
            </div>
            <div className="text-center group">
              <div className="w-20 h-20 mx-auto mb-6 bg-slate-100 rounded-2xl flex items-center justify-center group-hover:bg-theme/10 transition-colors duration-300">
                <DocumentIcon />
              </div>
              <h3 className="text-2xl font-bold mb-4 text-slate-900">맞춤형 채용 공고</h3>
              <p className="text-slate-600 leading-relaxed">
                AI가 분석한 맞춤 포지션을 바탕으로 <br />적합한
                채용 공고를 추천합니다.<br />
                 원하는 정보로 쉽고
                빠르게 확인하세요.
              </p>
            </div>
            <div className="text-center group">
              <div className="w-20 h-20 mx-auto mb-6 bg-slate-100 rounded-2xl flex items-center justify-center group-hover:bg-theme/10 transition-colors duration-300">
                <SearchIcon />
              </div>
              <h3 className="text-2xl font-bold mb-4 text-slate-900">인재 검색</h3>
              <p className="text-slate-600 leading-relaxed">
                기업은 AI와 인사 컨설턴트를 통해 <br />적합한 인재를 빠르게 찾고,<br />효율적으로 관리할 수
                있습니다.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* 이용자 후기 */}
      <section className="py-24 px-6 bg-white">
        <div className="max-w-4xl mx-auto">
          <h2 className="text-4xl font-bold text-center mb-4 text-slate-900">이용자 후기</h2>
          <p className="text-center text-slate-600 mb-16 text-lg">
            JobPrize를 통해 성공적인 취업과 채용을 경험한 이용자들의 후기입니다.
          </p>
          <div className="bg-white rounded-2xl shadow-lg p-12 text-center">
            <div className="w-16 h-16 mx-auto mb-8 bg-theme/10 rounded-full flex items-center justify-center">
              {testimonials[testimonialIdx].avatar}
            </div>
            <p className="text-slate-700 mb-8 text-lg leading-relaxed italic">
              "{testimonials[testimonialIdx].text}"
            </p>
            <div className="text-slate-900 font-semibold">
              {testimonials[testimonialIdx].name} · {testimonials[testimonialIdx].role}
            </div>
            {/* 슬라이더 점 */}
            <div className="flex justify-center gap-3 mt-8">
              {testimonials.map((_, idx) => (
                <button
                  key={idx}
                  className={`w-3 h-3 rounded-full transition-all duration-300 ${idx === testimonialIdx ? 'bg-theme' : 'bg-slate-300 hover:bg-slate-400'
                    }`}
                  onClick={() => setTestimonialIdx(idx)}
                  aria-label={`후기 ${idx + 1}번 보기`}
                />
              ))}
            </div>
          </div>
        </div>
      </section>

      {/* 통계 섹션 */}
      <section className="py-24 px-6 bg-white text-center max-h-[400px] overflow-y-auto">
        <div className="max-w-4xl mx-auto">
          <h3 className="text-4xl font-bold mb-12 text-slate-900">JobPrize와 함께하는 이유</h3>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-12">
            <div>
              <div className="text-5xl font-bold text-theme mb-4">98%</div>
              <div className="text-slate-600 font-medium text-lg">이력서 첨삭 만족도</div>
            </div>
            <div>
              <div className="text-5xl font-bold text-theme mb-4">3배</div>
              <div className="text-slate-600 font-medium text-lg">채용 성공률 향상</div>
            </div>
            <div>
              <div className="text-5xl font-bold text-theme mb-4">24시간</div>
              <div className="text-slate-600 font-medium text-lg">AI 피드백 제공</div>
            </div>
          </div>
        </div>
      </section>



    </div>
  );
};

export default MainPage;
