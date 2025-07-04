import NavigationBarItem from "./NavigationBarItem"

const NavigationBarForMember = () => {
    return (
        <>
            <NavigationBarItem title={"채용 공고"} to={"/job-postings"}/>
            <NavigationBarItem title={"AI 피드백"} to={"/feedbacks"}/>
            <NavigationBarItem title={"AI 첨삭"} to={"/edits"}/>
            <NavigationBarItem title={"구독"} to={"/subscription/plans"}/>
            <NavigationBarItem title={"커뮤니티"} to={"/community"}/>
        </>
    )
}

export default NavigationBarForMember;