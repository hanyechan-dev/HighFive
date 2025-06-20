import AdminCard from "../../../common/components/cards/AdminCard";
import BarChartIcon from "../../../common/icons/BarChartIcon";
import ConsultingIcon from "../../../common/icons/ConsultingIcon";
import PeopleIcon from "../../../common/icons/PeopleIcon";
import SettingsIcon from "../../../common/icons/SettingIcon";
import { memberPageClick } from "../features/UserPageClick";


 const userPageClick = () => {

        const pageClick = async () => {
            try {
                const res = await memberPageClick(1,10);
                setMembers(res.data.content);
            } catch (err) {
                console.error(err);
            }
        }
        pageClick();
    }
    
function App() {
    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-white pb-[40px]">

            <img src="/jobPrize.png" className="w-[360px] h-auto mb-6" />

            <div className="grid grid-cols-2 gap-6 mb-8">
                <AdminCard icon={<PeopleIcon />} label={"회원관리"} />
                <AdminCard icon={<ConsultingIcon />} label={"컨설팅"} />
                <AdminCard icon={<BarChartIcon />} label={"서비스 통계"} />
                <AdminCard icon={<SettingsIcon />} label={"프롬프트 설정"} />
            </div>

            <button className="px-6 py-2 rounded-lg cursor-pointer w-[730px] text-center">
            </button>
        </div>
    );
}

export default App;
function setMembers(content: any) {
    throw new Error("Function not implemented.");
}

