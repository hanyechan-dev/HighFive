import { useNavigate } from "react-router-dom";
import AdminCard from "../../../common/components/cards/AdminCard";
import BarChartIcon from "../../../common/icons/BarChartIcon";
import ConsultingIcon from "../../../common/icons/ConsultingIcon";
import PeopleIcon from "../../../common/icons/PeopleIcon";
import SettingsIcon from "../../../common/icons/SettingIcon";
import { api } from "../../../common/Axios";

function AdminPage() {
    const navigate = useNavigate();

    const onClickGetSimilarity =()=>{
        api(true).post("/admin/similarity");
    }

    const onClickGetMemberVector = () =>{
        api(true).post("/admin/member-vector")
    }

    const onClickGetJobPostingVector = () =>{
        api(true).post("/admin/job-posting-vector")
    }

    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-white pb-[40px]">
            <img src="/jobPrize.png" className="w-[360px] h-auto mb-6" />

            <div className="grid grid-cols-2 gap-6 mb-8">
                
                <div onClick={() => navigate("/dashboard/member")}>
                    <AdminCard icon={<PeopleIcon />} label={"회원관리"} />
                </div>
                 <div onClick={() => navigate("/dashboard/consulting")}>
                    <AdminCard icon={<ConsultingIcon />} label={"컨설팅"} />
                </div>
                <div onClick={() => navigate("/dashboard/service")}>
                    <AdminCard icon={<BarChartIcon />} label={"서비스 통계"} />
                </div>
                <div onClick={() => navigate("/dashboard/prompt")}>
                    <AdminCard icon={<SettingsIcon />} label={"프롬프트 설정"} />
                </div>
            </div>

            <button className="px-6 py-2 rounded-lg cursor-pointer w-[630px] text-center mb-6 border" onClick={onClickGetMemberVector}>
                멤버 벡터 구하기
            </button>

            <button className="px-6 py-2 rounded-lg cursor-pointer w-[630px] text-center mb-6 border" onClick={onClickGetJobPostingVector}>
                잡포스팅 벡터 구하기
            </button>

            <button className="px-6 py-2 rounded-lg cursor-pointer w-[630px] text-center mb-6 border" onClick={onClickGetSimilarity}>
                유사도 구하기
            </button>
        </div>
    );
}

export default AdminPage;
