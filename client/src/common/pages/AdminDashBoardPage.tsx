
import AdminCard from "../../common/components/cards/AdminCard";
import BarChartIcon from "../../common/icons/BarChartIcon";
import ConsultingIcon from "../../common/icons/ConsultingIcon";
import PeopleIcon from "../../common/icons/PeopleIcon";
import SettingIcon from "../../common/icons/SettingIcon";

const AdminDashBoardPage = () => {

    return (
        <div className="relative w-screen h-screen bg-white">
            <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 flex flex-col items-center">
                <img src="/jobPrize.png" alt="JobPrize Logo" className="h-28 mb-12" />
                <div className="grid grid-cols-2 grid-rows-2 gap-10 mb-[100px]">
                    <AdminCard label="회원관리" icon={<PeopleIcon />} />
                    <AdminCard label="컨설팅" icon={<ConsultingIcon />} />
                    <AdminCard label="통계" icon={<BarChartIcon />} />
                    <AdminCard label="프롬프트 설정" icon={<SettingIcon />} />
                </div>
            </div>
        </ div>

    )

}

export default AdminDashBoardPage;

