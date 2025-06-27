import NavigationBarItem from "./NavigationBarItem"

const companyMemberPoolDropDown = [
    { title: "적합한 인재 찾기", to: "/member-pool" },
    { title: "채용 제안 현황", to: "/proposal" },
]

const companyApplicationDropDown = [
    { title: "지원자 관리", to: "/job-postings/applications" },
    { title: "합격자 관리", to: "/job-postings/passes" },
]


const NavigationBarForCompany = () => {
    return (
        <>
            <NavigationBarItem title={"인재풀"} to={"/member-pool"} dropDown={companyMemberPoolDropDown} />
            <NavigationBarItem title={"채용 공고 관리"} to={"/job-postings/management"}  />
            <NavigationBarItem title={"지원자"} to={"/job-postings/applications"} dropDown={companyApplicationDropDown}  />
            <NavigationBarItem title={"일정 관리"} to={"/schedule"}  />
            <NavigationBarItem title={"구독"} to={"/subscription"}  />
            <NavigationBarItem title={"커뮤니티"} to={"/community"}  />
        </>
    )
}

export default NavigationBarForCompany;