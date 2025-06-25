import { useEffect, useState } from "react";
import RadioButton from "../../../common/components/button/RadioButton";
import ModalTitle from "../../../common/components/title/ModalTitle";
import Pagination from "../../../common/components/pagination/Pagination";
import { usePagination } from "../../../common/coustomHooks/usePagination";
import CommonPage from "../../../common/pages/CommonPage";
import UserApprovalPage from "./UserApprovalPage";
import {
  companyDetailClick,
  companyPageClick,
  consultantDetailClick,
  consultantPageClick,
  deactivationClick,
  memberDetailClick,
  memberPageClick,
} from "../features/UserPageClick";
import UserList from "../list/UserManagementList";
import type { UserInfoUnion } from "../union/UserInfoUnion";
import MemberDetail from "../modals/managementPageModal/MemberDetailModal";
import CompanyDetail from "../modals/managementPageModal/CompanyDetailModal";
import ConsultantDetail from "../modals/managementPageModal/ConsultantDetailModal";
import Button from "../../../common/components/button/Button";
import RadioButton2 from "../../../common/components/button/RadioButton2";

type UserType = "일반회원" | "기업회원" | "컨설턴트회원";
type PageMode = "회원 조회" | "가입 승인";

const userTypeEnum: { label: string; value: UserType }[] = [
  { label: "일반 회원", value: "일반회원" },
  { label: "기업 회원", value: "기업회원" },
  { label: "컨설턴트 회원", value: "컨설턴트회원" },
];

const elementsPerPage = 10;
const pagesPerBlock = 10;

const UserManagementPage = () => {
  const [userType, setUserType] = useState<UserType>(userTypeEnum[0].value);
  const [pageMode, setPageMode] = useState<PageMode>("회원 조회");
  const [members, setMembers] = useState<UserInfoUnion[]>([]);
  const [selectedIds, setSelectedIds] = useState<number[]>([]);
  const [selectedUserId, setSelectedUserId] = useState<number>(-1);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [totalElements, setTotalElements] = useState<number>(1);

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
    totalElements,
    elementsPerPage,
    pagesPerBlock,
  });

  const handleSelect = (id: number, checked: boolean) => {
    setSelectedIds(prev => checked ? [...prev, id] : prev.filter(i => i !== id));
  };

  const handleDelete = async () => {
    if (selectedIds.length === 0) return;

    try {
      console.log("🔥 삭제 요청할 ID 목록:", selectedIds);
      await deactivationClick(selectedIds);
      alert("회원 삭제가 완료되었습니다");

      setMembers(prevMembers =>
        prevMembers.filter(
          user => !selectedIds.includes(user.userManagementSummaryDto.id)
        )
      );

      setSelectedIds([]);
    } catch (err) {
      console.error("삭제 실패", err);
    }
  };

  const handleDetailClick = async (id: number) => {
    try {
      let res;
      if (userType === "일반회원") {
        res = await memberDetailClick(id);
      } else if (userType === "기업회원") {
        res = await companyDetailClick(id);
      } else {
        res = await consultantDetailClick(id);
      }
      if (res?.data) {
        setSelectedUserId(id);
        setIsModalOpen(true);
      }
    } catch (err) {
      console.error("상세 조회 실패", err);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      if (pageMode !== "회원 조회") return;
      try {
        let res;
        if (userType === "일반회원") {
          res = await memberPageClick(clickedPage - 1, elementsPerPage);
        } else if (userType === "기업회원") {
          res = await companyPageClick(clickedPage - 1, elementsPerPage);
        } else {
          res = await consultantPageClick(clickedPage - 1, elementsPerPage);
        }
        if (res) {
          setMembers(res.data.content);
          setTotalElements(res.data.totalElements);

          console.log("응답:", res);
        }
      } catch (err) {
        console.error(err);
      }
    };
    fetchData();
  }, [pageMode, userType, clickedPage]);

  const selectedUser = members.find(user => user.userManagementSummaryDto.id === selectedUserId);

  return (
    <CommonPage>
      <div className="bg-white min-h-screen">
        <div className="text-theme mt-2 font-roboto mb-[-24px]">
          <ModalTitle title="회원 관리" />
        </div>
        <div className="mb-[1px]">
          <RadioButton
            name=""
            textList={[
              { label: "회원 조회", value: "회원 조회" },
              { label: "가입 승인", value: "가입 승인" },
            ]}
            checkedText={pageMode}
            setCheckedText={setPageMode as unknown as (value: string) => void}
          />
        </div>

        {pageMode === "회원 조회" && (
          <>
            <div className="flex justify-between">
              <div>
                  <RadioButton2
                  name=""
                  textList={userTypeEnum}
                  checkedText={userType}
                  setCheckedText={(value: string) => {
                    if (value === "일반회원" ||
                      value === "기업회원" ||
                      value === "컨설턴트회원") {
                      setUserType(value);
                    }
                  } } itemNumber={3}                  />
              </div>
              <div className="mb-[-24px]">
                <Button color={"action"} size={"s"} disabled={false} text={"회원 삭제"} type={"button"} onClick={handleDelete} />
              </div>
            </div>
            <UserList
              userType={userType}
              memberList={members.filter(user => !user.userManagementSummaryDto.deletedDate)}
              onDetailClick={handleDetailClick}
              onSelect={handleSelect}
              selectedIds={selectedIds}
              listRest={0} />
            <div className="flex justify-center mt-8">
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
        )}

        {pageMode === "가입 승인" && <UserApprovalPage />}
        {isModalOpen && selectedUser && (
          userType === "일반회원" ? (
            <MemberDetail
              id={selectedUser.userManagementSummaryDto.id}
              onClose={() => {
                setIsModalOpen(false);
                setSelectedUserId(-1);
              }}
            />
          ) : userType === "기업회원" ? (
            <CompanyDetail
              id={selectedUser.userManagementSummaryDto.id}
              onClose={() => {
                setIsModalOpen(false);
                setSelectedUserId(-1);
              }}
            />
          ) : (
            <ConsultantDetail
              id={selectedUser.userManagementSummaryDto.id}
              onClose={() => {
                setIsModalOpen(false);
                setSelectedUserId(-1);
              }}
            />
          )
        )}


      </div>
    </CommonPage>
  );
};

export default UserManagementPage;
