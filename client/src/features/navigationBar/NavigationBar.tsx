import NavigationBarItem from "./components/NavigationBarItem";


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