


import JobPostingMainCard from './components/list/JobPostingMainCard';
import JobPostingUnderCard from './components/list/JobPostingUnderCard';


function App() {

    const mockData = {
        id: 1,
        title: '백엔드 개발자 공개채용 삼성에서 인재를 모십니다 많은 지원 바랍니다. ',
        companyName: '삼성전자',
        companyType: '대기업',
        job: '백엔드',
        workLocation: '서울 관악구',
        careerType: '경력 3년차 이상',
        educationLevel: '전문학사',
        similarityScore: 87,
        createdDate: '2025-05-05'
    };

    return (
        <>
            
            <JobPostingMainCard {...mockData} onClick={function (id: number): void {
                throw new Error('Function not implemented.');
            }} />

            <JobPostingUnderCard {...mockData} onClick={function (id: number): void {
                throw new Error('Function not implemented.');
            }} />



        </>
    )
}

export default App
