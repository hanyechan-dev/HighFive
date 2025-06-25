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
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import { mockMembers } from "../../common/mockData/CompanyMockData";

// AI 추천 mock 데이터 예시
const aiRecommended: MemberPoolSummary[] = [
    { id: 1, name: "김인재", job: "프론트엔드 개발자", hasCareer: true, similarityScore: 95, educationLevel: "고등학교 졸업", genderType: "남성", birthDate: "1992-01-01" },
    { id: 2, name: "이개발", job: "백엔드 개발자", hasCareer: true, similarityScore: 92, educationLevel: "석사", genderType: "여성", birthDate: "1990-05-10" },
    { id: 3, name: "박디자인", job: "UI/UX 디자이너", hasCareer: false, similarityScore: 88, educationLevel: "대졸", genderType: "여성", birthDate: "1995-09-15" },
    { id: 4, name: "최기획", job: "프로덕트 매니저", hasCareer: true, similarityScore: 85, educationLevel: "대졸", genderType: "남성", birthDate: "1988-12-20" },
];

const MemberPoolPage = () => {
    const filter = useSelector((state: RootState) => state.memberPoolFilter.filter);

    const [members, setMembers] = useState<MemberPoolSummary[]>(mockMembers);

    const [totalElements, setTotalElements] = useState(0);
    const [selectedId, setSelectedId] = useState<number | null>(null);
    const [isDetailModalOpen, setIsDetailModalOpen] = useState(false);
    const [isFilterModalOpen, setIsFilterModalOpen] = useState(false);

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
                if (res && res.data) {
                    setMembers(res.data.content);   // Page 객체의 content 배열 사용
                    setTotalElements(res.data.totalElements);  // 실제 전체 요소 수 사용
                } else {
                    // API 응답이 없으면 mock 데이터 사용
                    setMembers(mockMembers);
                    setTotalElements(mockMembers.length);
                }
            } catch (err) {
                printErrorInfo(err);
                // 에러 시에도 mock 데이터 사용
                setMembers(mockMembers);
                setTotalElements(mockMembers.length);
            }
        };
        fetchMembers();
    }, [clickedPage, filter]);

    const handleMemberClick = (id: number) => {
        setIsDetailModalOpen(true)
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
                            onClick={() => setIsFilterModalOpen(true)}
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
                isOpen={isDetailModalOpen}
                onClose={() => setIsDetailModalOpen(false)}
                memberId={selectedId}
            />}
            <MemberPoolFilterModal
                isOpen={isFilterModalOpen}
                onClose={() => setIsFilterModalOpen(false)}
            />
        </CommonPage>
    );
};

export default MemberPoolPage;