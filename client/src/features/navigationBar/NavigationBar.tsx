import NavigationBarForAdmin from "./components/NavigationBarForAdmin";
import NavigationBarForCompany from "./components/NavigationBarForCompany";
import NavigationBarForMember from "./components/NavigationBarForMember";



interface NavigationBarProps {
    userType : string
} 

const NavigationBar = ({userType} : NavigationBarProps) => {
    
    return (
        <div className="flex justify-start w-full h-[50px] items-center font-roboto bg-gray-100">
            <div className="flex w-[1500px] mx-auto">
                {userType === "일반회원" && <NavigationBarForMember />}
                {userType === "기업회원" && <NavigationBarForCompany />}
                {userType === "관리자" && <NavigationBarForAdmin />}
            </div>
        </div>
    )

}

export default NavigationBar;
