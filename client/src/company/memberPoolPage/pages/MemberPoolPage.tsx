import { useState, useEffect } from "react";
import Button from "../../../common/components/button/Button";
import Pagination from "../../../common/components/pagination/Pagination";
import CommonPage from "../../../common/pages/CommonPage";
import MemberPoolSummaryRow from "../components/MemberPoolSummaryRow";
import MemberPoolDetailModal from "../modals/MemberPoolDetailModal";
import MemberPoolFilterModal from "../modals/MemberPoolFilterModal";
import { MemberPoolPageApi } from "../apis/MemberPoolApi";
import type { MemberPoolSummary } from "../props/MemberPoolProps";
import { useSelector } from "react-redux";
import type { RootState } from "../../../common/store/store";
import MemberPoolCard from "../components/MemberPoolCard";
import { usePagination } from "../../../common/customHooks/usePagination";
import PageTitle from "../../common/components/PageTitle";
import MemberPoolListHeader from "../components/MemberPoolListHeader";

// AI 추천 mock 데이터 예시
const aiRecommended: MemberPoolSummary[] = [
    { id: 1, name: "김인재", job: "프론트엔드 개발자", hasCareer: true, similarityScore: 95, educationLevel: "고등학교 졸업", genderType: "남성", birthDate: "1992-01-01" },
    { id: 2, name: "이개발", job: "백엔드 개발자", hasCareer: true, similarityScore: 92, educationLevel: "석사", genderType: "여성", birthDate: "1990-05-10" },
    { id: 3, name: "박디자인", job: "UI/UX 디자이너", hasCareer: false, similarityScore: 88, educationLevel: "대졸", genderType: "여성", birthDate: "1995-09-15" },
    { id: 4, name: "최기획", job: "프로덕트 매니저", hasCareer: true, similarityScore: 85, educationLevel: "대졸", genderType: "남성", birthDate: "1988-12-20" },
];




const mockMemberPoolSummary : MemberPoolSummary[] =[
        {
            id: 1,
            name: "김철수",
            job: "프론트엔드 개발자",
            hasCareer: true,
            similarityScore: 95,
            educationLevel: "대졸",
            genderType: "남성",
            birthDate: "1990-05-15"
        },
        {
            id: 2,
            name: "이영희",
            job: "백엔드 개발자",
            hasCareer: false,
            similarityScore: 88,
            educationLevel: "대졸",
            genderType: "여성",
            birthDate: "1992-08-23"
        },
        {
            id: 3,
            name: "박지민",
            job: "UI/UX 디자이너",
            hasCareer: true,
            similarityScore: 92,
            educationLevel: "대졸",
            genderType: "남성",
            birthDate: "1995-03-10"
        },
        {
            id: 4,
            name: "정민수",
            job: "데이터 사이언티스트",
            hasCareer: true,
            similarityScore: 85,
            educationLevel: "석사",
            genderType: "남성",
            birthDate: "1988-11-30"
        },
        {
            id: 5,
            name: "최유진",
            job: "프로덕트 매니저",
            hasCareer: false,
            similarityScore: 90,
            educationLevel: "대졸",
            genderType: "여성",
            birthDate: "1993-07-18"
        },
        {
            id: 6,
            name: "강다은",
            job: "모바일 개발자",
            hasCareer: true,
            similarityScore: 87,
            educationLevel: "대졸",
            genderType: "여성",
            birthDate: "1991-02-14"
        },
        {
            id: 7,
            name: "윤서연",
            job: "DevOps 엔지니어",
            hasCareer: true,
            similarityScore: 93,
            educationLevel: "석사",
            genderType: "여성",
            birthDate: "1989-09-22"
        },
        {
            id: 8,
            name: "임준호",
            job: "AI 엔지니어",
            hasCareer: false,
            similarityScore: 89,
            educationLevel: "대졸",
            genderType: "남성",
            birthDate: "1994-12-01"
        },
        {
            id: 9,
            name: "한소희",
            job: "보안 엔지니어",
            hasCareer: true,
            similarityScore: 91,
            educationLevel: "석사",
            genderType: "여성",
            birthDate: "1990-03-27"
        },
        {
            id: 10,
            name: "송민재",
            job: "클라우드 아키텍트",
            hasCareer: true,
            similarityScore: 94,
            educationLevel: "대졸",
            genderType: "남성",
            birthDate: "1987-06-05"
        }
    ]


























export default function MemberPoolPage() {

    const filter = useSelector((state: RootState) => state.memberPoolFilter.filter);

    const [members, setMembers] = useState<MemberPoolSummary[]>(mockMemberPoolSummary);

    const [totalElements, setTotalElements] = useState(0);
    const [selectedId, setSelectedId] = useState<number | null>(null);
    const [isDetailOpen, setDetailOpen] = useState(false);
    const [isFilterOpen, setFilterOpen] = useState(false);

    const {
        clickedPage,
        setClickedPage,
        lastPage,
        pageBlockIndex,
        lastPageBlockIndex,
        onClickFirst,
        onClickPrev,
        onClickNext,
        onClickLast,
    } = usePagination({
        totalElements,
        elementsPerPage: 10,
        pagesPerBlock: 10,
    });

    useEffect(() => {
        const fetchMembers = async () => {
            try {
                const res = await MemberPoolPageApi(filter, clickedPage);
                if (res) {
                    setMembers(res);
                    // API 응답이 있으면 전체 개수로 설정 (실제로는 API에서 totalElements를 받아야 함)
                    setTotalElements(mockMemberPoolSummary.length);
                } else {
                    // API 응답이 없으면 mock 데이터 사용
                    setMembers(mockMemberPoolSummary);
                    setTotalElements(mockMemberPoolSummary.length);
                }
            } catch (err) {
                console.error(err);
                // 에러 시에도 mock 데이터 사용
                setMembers(mockMemberPoolSummary);
                setTotalElements(mockMemberPoolSummary.length);
            }
        };
        fetchMembers();
    }, [clickedPage, filter]);



    const handleMemberClick = (id: number) => {
        setDetailOpen(true)
        setSelectedId(id);
    };

    return (
        <CommonPage>
            <div className="w-[1452px] mx-auto px-0 font-roboto">
                {/* 헤더 섹션 */}
                <div className="flex items-center justify-between mb-8">
                    <PageTitle
                        title="인재풀페이지"
                        description="AI 기반으로 추천된 인재들을 확인해보세요"
                    />
                </div>
                {/* AI 인재 추천 섹션 */}
                <div className="mb-12">
                    <div className="flex items-center gap-3 mb-6">
                        <h2 className="text-xl font-semibold">AI 인재 추천</h2>
                        <div className="h-px flex-1 bg-gradient-to-r from-gray-200 to-transparent"></div>
                    </div>
                    <div className="grid grid-cols-4 gap-4">
                        {aiRecommended.map(member => (
                            <MemberPoolCard
                                onClick={handleMemberClick}
                                key={member.id}
                                member={member}
                            />
                        ))}
                    </div>
                </div>
                {/* 인재 리스트 섹션 */}
                <div>
                    <div className="flex items-center justify-between mb-6">
                        <div className="flex items-center gap-3">
                            <div className="h-px flex-1 bg-gradient-to-r from-gray-200 to-transparent"></div>
                        </div>
                    </div>
                    <div className="flex justify-end">
                        <Button
                            color="theme"
                            size="s"
                            disabled={false}
                            text="필터"
                            type="button"
                            onClick={() => setFilterOpen(true)}
                        />
                    </div>
                    {/* 헤더 */}
                    <MemberPoolListHeader />
                    {/* 리스트 */}
                    <div>
                        {members.map((member) => (
                            <MemberPoolSummaryRow
                                key={member.id}
                                member={member}
                                onClick={handleMemberClick}
                            />
                        ))}
                    </div>
                </div>
                {/* 페이지네이션 */}
                <div className="mt-8 flex justify-center">
                    <Pagination
                        currentPageBlockIndex={pageBlockIndex}
                        lastPageBlockIndex={lastPageBlockIndex}
                        pagesPerBlock={10}
                        lastPage={lastPage}
                        clickedPage={clickedPage}
                        onClickFirst={onClickFirst}
                        onClickPrev={onClickPrev}
                        onClickNext={onClickNext}
                        onClickLast={onClickLast}
                        onClickPage={setClickedPage}
                    />
                </div>
            </div>
            {selectedId && < MemberPoolDetailModal
                isOpen={isDetailOpen}
                onClose={() => setDetailOpen(false)}
                memberId={selectedId}
            />}
            <MemberPoolFilterModal
                isOpen={isFilterOpen}
                onClose={() => setFilterOpen(false)}
            />
        </CommonPage>
    );
}
