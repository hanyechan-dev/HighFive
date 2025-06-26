import { useEffect, useState } from "react";
import Button from "../../../common/components/button/Button";
import { companysWatingSummaryList, consultantsWatingSummaryList } from "../features/UserPageClick";
import type { companyInfoProps, consultantInfoProps } from "../union/UserInfoUnion";
import Pagination from "../../../common/components/pagination/Pagination";
import { usePagination } from "../../../common/coustomHooks/usePagination";

interface approvalListItemProps {
  userType: "기업회원" | "컨설턴트회원";
  companyInfo?: companyInfoProps;
  consultantInfo?: consultantInfoProps;
  onSelect: (id: number, checked: boolean) => void;
  onDetailClick: (id: number) => void;

}

const ListItem = ({
  userType,
  companyInfo,
  consultantInfo,
  onSelect,
  onDetailClick,
}: approvalListItemProps) => {
  let email = "", name = "", phone = "", address = "", createdDate = "", companyName = "";
  let id: number | null = null;

  if (userType === "기업회원" && companyInfo) {
    const { userManagementSummaryDto } = companyInfo;
    id = userManagementSummaryDto.id;
    email = userManagementSummaryDto.email;
    name = userManagementSummaryDto.name;
    companyName = companyInfo.companyName;
    phone = userManagementSummaryDto.phone;
    address = userManagementSummaryDto.address;
    createdDate = userManagementSummaryDto.createdDate;
  }

  if (userType === "컨설턴트회원" && consultantInfo) {
    const { userManagementSummaryDto } = consultantInfo;
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

const UserApprovalList = ({ userType, onSelect, onDetailClick: handleDetail,  listReset }: UserListProps) => {
  const [companyList, setCompanyList] = useState<companyInfoProps[]>([]);
  const [consultantList, setConsultantList] = useState<consultantInfoProps[]>([]);
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
        console.error("회원 목록 조회 실패:", err);
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
          <div className="px-4"> </div>
        </div>

        {userType === "기업회원" &&
          companyList.map(company => (
            <ListItem
              key={company.userManagementSummaryDto.id}
              userType="기업회원"
              companyInfo={company}
              onSelect={onSelect}
              onDetailClick={handleDetail}
            />
          ))}

        {userType === "컨설턴트회원" &&
          consultantList.map(consultant => (
            <ListItem
              key={consultant.userManagementSummaryDto.id}
              userType="컨설턴트회원"
              consultantInfo={consultant}
              onSelect={onSelect}
              onDetailClick={handleDetail}
            />
          ))}
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
