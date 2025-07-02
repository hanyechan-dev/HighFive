import { useEffect, useState } from "react";
import Button from "../../../common/components/button/Button";
import Pagination from "../../../common/components/pagination/Pagination";
import type { CompanyManagementSummaryDto, ConsultantManagementSummaryDto } from "../props/UserManagementProps";
import { usePagination } from "../../../common/customHooks/usePagination";
import { companysWatingSummaryList, consultantsWatingSummaryList } from "../apis/UserManagementApi";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import EmptyState from "../../../common/components/emptyState/EmptyState";

interface approvalListItemProps {
    userType: "기업회원" | "컨설턴트회원";
    companyManagementSummaryDto?: CompanyManagementSummaryDto
    consultantManagementSummaryDto?: ConsultantManagementSummaryDto;
    onSelect: (id: number, checked: boolean) => void;
    onDetailClick: (id: number) => void;

}

const ListItem = ({
    userType,
    companyManagementSummaryDto,
    consultantManagementSummaryDto,
    onSelect,
    onDetailClick,
}: approvalListItemProps) => {
    let email = "", name = "", phone = "", address = "", createdDate = "", companyName = "";
    let id: number | null = null;

    if (userType === "기업회원" && companyManagementSummaryDto) {
        const { userManagementSummaryDto } = companyManagementSummaryDto;
        id = userManagementSummaryDto.id;
        email = userManagementSummaryDto.email;
        name = userManagementSummaryDto.name;
        companyName = companyManagementSummaryDto.companyName;
        phone = userManagementSummaryDto.phone;
        address = userManagementSummaryDto.address;
        createdDate = userManagementSummaryDto.createdDate;
    }

    if (userType === "컨설턴트회원" && consultantManagementSummaryDto) {
        const { userManagementSummaryDto } = consultantManagementSummaryDto;
        id = userManagementSummaryDto.id;
        email = userManagementSummaryDto.email;
        name = userManagementSummaryDto.name;
        phone = userManagementSummaryDto.phone;
        address = userManagementSummaryDto.address;
        createdDate = userManagementSummaryDto.createdDate;
    }

    return (
        <div className="grid grid-cols-8 items-center px-4 py-3 border-b text-sm font-roboto">
            <div className="px-6">
                <input type="checkbox" onChange={(e) => onSelect(id as number, e.target.checked)} />
            </div>
            <div className="mr-1">{email}</div>
            <div className="px-10">{name}</div>
            <div className="px-10">{userType === "기업회원" ? companyName : "-"}</div>
            <div className="px-5">{phone}</div>
            <div className="px-4">{address}</div>
            <div className="px-8">{createdDate}</div>
            <div className="flex justify-center pt-6">
                <Button
                    color={"theme"}
                    size={"s"}
                    disabled={false}
                    text={"상세"}
                    type={"button"}
                    onClick={function (): void {
                        onDetailClick(id as number);
                    }}
                />
            </div>
        </div>
    );
};


















interface UserListProps {
    userType: "기업회원" | "컨설턴트회원";
    onSelect: (id: number, checked: boolean) => void;
    onDetailClick: (id: number) => void;
    listReset: number;
}

const elementsPerPage = 10;
const pagesPerBlock = 10;

const UserApprovalList = ({ userType, onSelect, onDetailClick: handleDetail, listReset }: UserListProps) => {
    const [companyList, setCompanyList] = useState<CompanyManagementSummaryDto[]>([]);
    const [consultantList, setConsultantList] = useState<ConsultantManagementSummaryDto[]>([]);
    const [totalElements, setTotalElements] = useState(0);

    const {
        clickedPage,
        pageBlockIndex,
        lastPage,
        lastPageBlockIndex,
        onClickFirst,
        onClickPrev,
        onClickNext,
        onClickLast,
        setClickedPage,
    } = usePagination({
        totalElements: totalElements,
        elementsPerPage: elementsPerPage,
        pagesPerBlock: pagesPerBlock,
    });

    useEffect(() => {
        const fetchData = async () => {
            try {
                if (userType === "기업회원") {

                    const res = await companysWatingSummaryList(clickedPage - 1, elementsPerPage);

                    setCompanyList(res.data.content);
                    setTotalElements(res.data.totalElements);

                } else if (userType === "컨설턴트회원") {
                    const res = await consultantsWatingSummaryList(clickedPage - 1, elementsPerPage);
                    console.log("컨설턴트 승인 리스트 응답:", res.data);
                    setConsultantList(res.data.content);
                    setTotalElements(res.data.totalElements);
                }
            } catch (err) {
                printErrorInfo(err);
            }
        };

        fetchData();

    }, [userType, clickedPage, listReset]);



    return (
        <>
            <div className="w-[1452px] border border-gray-300 rounded-xl font-roboto shadow-sm overflow-hidden text-sm">
                <div className="grid grid-cols-8 bg-gray-100 text-gray-700 font-roboto px-4 py-2 border-b">
                    <div className="px-4">선택</div>
                    <div className="px-10">이메일</div>
                    <div className="px-11">이름</div>
                    <div className="px-10">{userType === "기업회원" ? "기업명" : "-"}</div>
                    <div className="px-12">연락처</div>
                    <div className="px-10">주소</div>
                    <div className="px-11">신청일</div>
                    <div className="pl-[75px]">상세</div>
                </div>

                {userType === "기업회원" && (
                    companyList.length > 0 ? (
                        companyList.map(company => (
                            <ListItem
                                key={company.userManagementSummaryDto.id}
                                userType="기업회원"
                                companyManagementSummaryDto={company}
                                onSelect={onSelect}
                                onDetailClick={handleDetail}
                            />
                        ))
                    ) : (
                        <EmptyState title={"조회된 기업회원이 없습니다."} text={""} />
                    )
                )}

                {userType === "컨설턴트회원" && (
                    consultantList.length > 0 ? (
                        consultantList.map(consultant => (
                            <ListItem
                                key={consultant.userManagementSummaryDto.id}
                                userType="컨설턴트회원"
                                consultantManagementSummaryDto={consultant}
                                onSelect={onSelect}
                                onDetailClick={handleDetail}
                            />
                        ))
                    ) : (
                        <EmptyState title={"조회된 컨설턴트회원이 없습니다."} text={""} />
                    )
                )}
            </div>

            <div className="flex justify-center">
                <Pagination
                    currentPageBlockIndex={pageBlockIndex}
                    lastPageBlockIndex={lastPageBlockIndex}
                    pagesPerBlock={pagesPerBlock}
                    lastPage={lastPage}
                    clickedPage={clickedPage}
                    onClickFirst={onClickFirst}
                    onClickPrev={onClickPrev}
                    onClickNext={onClickNext}
                    onClickLast={onClickLast}
                    onClickPage={setClickedPage}
                />
            </div>


        </>
    );
};

export default UserApprovalList;
