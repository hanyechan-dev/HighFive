import NavigationBarItem from "./components/NavigationBarItem";

const companyMemberPoolDropDown = [
    {title : "적합한 인재 찾기", to : "/member-pool"},
    {title : "채용 제안 현황", to : "/proposal"},
]

const companyApplicationDropDown = [
    {title : "지원자 관리", to : "/job-postings/applications"},
    {title : "합격자 관리", to : "/job-postings/passes"},
]

interface NavigationBar 

const NavigationBar = () => {



    return (
        <div className="flex justify-start w-[1920px] h-[50px] items-center font-roboto bg-gray-100">
            <div className="flex ml-[210px]">
                <NavigationBarItem title={"채용 공고"} to={"/job-postings"} />
                <NavigationBarItem title={"AI + 컨설턴트 피드백"} to={"/feedbacks"} />
                <NavigationBarItem title={"AI + 컨설턴트 첨삭"} to={"/edits"} />
                <NavigationBarItem title={"구독"} to={"/subscribes"} />
                <NavigationBarItem title={"커뮤니티"} to={"/community"} />
            </div>
        </div>
    )

}

export default NavigationBar;


{/* <div className="flex ml-[210px]">
                <NavigationBarItem title={"인재풀"} to={"/member-pool"} dropDown={companyMemberPoolDropDown}/>
                <NavigationBarItem title={"채용 공고 관리"} to={"/job-postings/management"} />
                <NavigationBarItem title={"지원자"} to={"/job-postings/applications"}  dropDown={companyApplicationDropDown}/>
                <NavigationBarItem title={"일정 관리"} to={"/schedule"} />
                <NavigationBarItem title={"구독"} to={"/subscription"} />
                <NavigationBarItem title={"커뮤니티"} to={"/community"} />
            </div> */}