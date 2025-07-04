const Footer = () => {
    return (
        <div className="w-full bg-[#1f2937] h-[300px]  pt-[60px] text-white font-roboto">
            <div className="w-[1452px] grid grid-cols-6 mx-auto">
                <div></div>

                <div>
                    <div className="text-lg mb-6">JobPrize</div>
                    <div className="text-gray-400 text-sm">취업과 채용을 위한 최고의 플랫폼</div>
                </div>
                <div>
                    <div className="text-lg mb-6">서비스</div>
                    <div className="text-gray-400 text-sm">채용 공고</div>
                    <div className="text-gray-400 text-sm">인재풀 관리</div>
                    <div className="text-gray-400 text-sm">AI 기반 컨설팅</div>
                </div>
                <div>
                    <div className="text-lg mb-6">회사</div>
                    <div className="text-gray-400 text-sm">회사 소개</div>
                    <div className="text-gray-400 text-sm">이용 약관</div>
                    <div className="text-gray-400 text-sm">개인정보처리방침</div>
                </div>
                <div>
                    <div className="text-lg mb-6">문의</div>
                    <div className="text-gray-400 text-sm">contact@jobprize.com</div>
                    <div className="text-gray-400 text-sm">02-123-4567</div>
                </div>
                <div></div>

            </div>
            <div className="border-b mt-10 w-[1500px] mx-auto border-gray-600"></div>
            <div className="mt-10 text-center text-gray-400">© 2023 JobPrize. All rights reserved.</div>
        </div>
    )
}

export default Footer;