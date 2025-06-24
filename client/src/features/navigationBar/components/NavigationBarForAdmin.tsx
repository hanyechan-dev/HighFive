import NavigationBarItem from "./NavigationBarItem"


const NavigationBarForAdmin = () => {
    return (
        <>
            <NavigationBarItem title={"관리자 페이지"} to={"/admin"} />
            <NavigationBarItem title={"커뮤니티"} to={"/community"} />
        </>
    )
}

export default NavigationBarForAdmin;