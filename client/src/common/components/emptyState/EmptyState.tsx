import SearchIcon from "../../icons/searchIcon"

interface EmptyStateProps {
    title: string;
    text: string;
}


const EmptyState = ({title, text }: EmptyStateProps) => {
    return (
        <div className={`flex flex-col items-center justify-center py-16 px-4 `}>
            <div className="flex flex-col items-center">
                {/* 애니메이션 요소 */}
                <div className="relative mb-8">
                    {/* 메인 원형 배경 */}
                    <div className="w-32 h-32 rounded-full bg-gradient-to-br from-theme to-semi_theme flex items-center justify-center shadow-lg relative overflow-hidden">
                        {/* 배경 패턴 */}
                        <div className="absolute inset-0">
                            <div className="absolute top-4 left-4 w-6 h-6 bg-white/10 rounded-full"></div>
                            <div className="absolute bottom-6 right-6 w-8 h-8 bg-white/10 rounded-full"></div>
                            <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-16 h-16 border border-white/20 rounded-full"></div>
                        </div>

                        {/* 아이콘 */}
                        <div className="relative animate-bounce">
                            <SearchIcon />
                        </div>
                    </div>

                    {/* 장식용 원형들 - 원 안에 배치 */}
                    <div className="absolute -top-2 -left-2 w-6 h-6 rounded-full bg-[#EE57CD]/30 animate-float"></div>
                    <div className="absolute top-1/2 -right-4 w-8 h-8 rounded-full bg-[#EE57CD]/20 animate-float-delay"></div>
                    <div className="absolute -bottom-3 left-1/2 w-5 h-5 rounded-full bg-[#EE57CD]/30 animate-float-slow"></div>
                </div>

                {/* 텍스트 콘텐츠 */}
                <div className="text-center">
                    <h3 className="text-xl font-bold font-roboto text-gray-800 mb-2">{title}</h3>
                    <p className="text-gray-500 mb-6 font-roboto">{text}</p>
                </div>
            </div>
        </div>
    )
}

export default EmptyState;