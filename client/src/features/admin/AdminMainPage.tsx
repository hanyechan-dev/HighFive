import { Users, BarChart2, MessageSquare, Settings } from "lucide-react"
import { Link } from "react-router-dom";

const AdminMainPage = () => {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-white p-4">
      <div className="mb-12 flex justify-center">
        <img
          src="https://hebbkx1anhila5yf.public.blob.vercel-storage.com/JobPrize-cHdxkqNeY40gq2CeVWre7EOapfpTOv.png"
          alt="JobPrize"
          width={300}
          height={100}
        />
      </div>

      <div className="grid grid-cols-2 gap-4 w-full max-w-3xl">
        {/* 1사분면: 회원 관리 */}
        <Link to="/member">
          <div className="h-80 flex flex-col items-center justify-center bg-white border border-gray-200 rounded-tl-lg p-6 hover:bg-[#FFE6FB] transition-colors">
          <Users className="h-16 w-16 text-[#EE57CD] mb-4" />
          <h2 className="text-xl font-semibold text-[#666666]">회원 관리</h2>
          </div>
        </Link>

        {/* 2사분면: 컨설팅 */}
        <Link to="/consulting">
          <div className="h-80 flex flex-col items-center justify-center bg-white border border-gray-200 rounded-tl-lg p-6 hover:bg-[#FFE6FB] transition-colors">
          <MessageSquare className="h-16 w-16 text-[#EE57CD] mb-4" />
          <h2 className="text-xl font-semibold text-[#666666]">컨설팅</h2>
          </div>
        </Link>

        {/* 3사분면: 서비스 통계 */}
        <Link to="/service">
          <div className="h-80 flex flex-col items-center justify-center bg-white border border-gray-200 rounded-tl-lg p-6 hover:bg-[#FFE6FB] transition-colors">
          <BarChart2 className="h-16 w-16 text-[#EE57CD] mb-4" />
          <h2 className="text-xl font-semibold text-[#666666]">서비스 통계</h2>
          </div>
        </Link>

        {/* 4사분면: 프롬프트 설정 */}
        <Link to="/prompt">
          <div className="h-80 flex flex-col items-center justify-center bg-white border border-gray-200 rounded-tl-lg p-6 hover:bg-[#FFE6FB] transition-colors">
          <Settings className="h-16 w-16 text-[#EE57CD] mb-4" />
          <h2 className="text-xl font-semibold text-[#666666]">프롬프트 설정</h2>
          </div>
        </Link>
      </div>
    </div>
  )
}

export default AdminMainPage;