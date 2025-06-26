import Button from "../../../common/components/button/Button";
import type { companyInfoProps, consultantInfoProps, memberInfoProps, UserInfoUnion } from "../union/UserInfoUnion";

interface UserListProps {
  userType: '일반회원' | '기업회원' | '컨설턴트회원';
  memberList: UserInfoUnion[];
  onDetailClick: (id: number) => void;
  onSelect: (id: number, checked: boolean) => void;
  selectedIds: number[];
  listRest: number;
}

interface ListItemProps {
  userType: '일반회원' | '기업회원' | '컨설턴트회원';
  memberInfo?: memberInfoProps;
  companyInfo?: companyInfoProps;
  consultantInfo?: consultantInfoProps;
  onDetailClick: (id: number) => void;
  onSelect: (id: number, checked: boolean) => void;
  selectedIds: number[];
  listRest : number
}

const ListItem = ({
  userType,
  memberInfo,
  companyInfo,
  consultantInfo,
  onDetailClick,
  onSelect,
  selectedIds
}: ListItemProps) => {
  let id: number | undefined;
  let email = "", name = "", nickname = "", companyName = "", phone = "", address = "", createdDate = "";

  if (userType === '일반회원' && memberInfo?.userManagementSummaryDto) {
    id = memberInfo.userManagementSummaryDto.id;
    email = memberInfo.userManagementSummaryDto.email;
    name = memberInfo.userManagementSummaryDto.name;
    nickname = memberInfo.nickName;
    phone = memberInfo.userManagementSummaryDto.phone;
    address = memberInfo.userManagementSummaryDto.address;
    createdDate = memberInfo.userManagementSummaryDto.createdDate;

  } else if (userType === '기업회원' && companyInfo?.userManagementSummaryDto) {
    id = companyInfo.userManagementSummaryDto.id;
    email = companyInfo.userManagementSummaryDto.email;
    name = companyInfo.userManagementSummaryDto.name;
    companyName = companyInfo.companyName;
    phone = companyInfo.userManagementSummaryDto.phone;
    address = companyInfo.userManagementSummaryDto.address;
    createdDate = companyInfo.userManagementSummaryDto.createdDate;

  } else if (userType === '컨설턴트회원' && consultantInfo?.userManagementSummaryDto) {
    id = consultantInfo.userManagementSummaryDto.id;
    email = consultantInfo.userManagementSummaryDto.email;
    name = consultantInfo.userManagementSummaryDto.name;
    phone = consultantInfo.userManagementSummaryDto.phone;
    address = consultantInfo.userManagementSummaryDto.address;
    createdDate = consultantInfo.userManagementSummaryDto.createdDate;
  }

  if (id === undefined) return null;

  return (
    <div className="grid grid-cols-8 items-center py-3 border-b text-sm font-roboto">
      <div className="px-10">
       <input type="checkbox" checked={selectedIds.includes(id!)} onChange={(e) => onSelect(id!, e.target.checked)}
/>

      </div>
      <div className="">{email}</div>
      <div className="px-9">{name}</div>
      <div className="">
        {userType === "일반회원" && nickname}
        {userType === "기업회원" && companyName}
        {userType === "컨설턴트회원" && "-"}
      </div>
      <div className="ml-[-15px]">{phone}</div>
      <div className="pr-1">{address}</div>
      <div className="px-8">{createdDate}</div>
      <div className="flex justify-center pt-6 mr-24">
        <Button
          color={"theme"}
          size={"s"}
          disabled={false}
          text={"상세"}
          type={"button"}
          onClick={function (): void {
            onDetailClick(id!);
          }}
        />
      </div>
    </div>
  );
};

const UserList = ({ userType, memberList, onDetailClick, onSelect ,selectedIds,}: UserListProps) => {
  const output = userType === '일반회원' ? '닉네임' :
    userType === '기업회원' ? '기업명' : '';

  const filteredList = memberList.filter(user =>
  !user.userManagementSummaryDto?.deletedDate
);


  return (
    <div className="w-[1452px] border border-gray-300 rounded-xl shadow-sm overflow-hidden text-sm">
      <div className="grid grid-cols-8 bg-gray-100 text-gray-700 font-roboto px-4 py-2 border-b">
        <div className="px-4">선택</div>
        <div className="px-10">이메일</div>
        <div className="px-8">이름</div>
        <div className="px-2">{output}</div>
        <div className="px-2">연락처</div>
        <div className="px-16">주소</div>
        <div className="px-11">가입일</div>
        <div className="px-10">상세</div>
      </div>

      {filteredList.map(user => {
        if (userType === "일반회원") {
          return (
            <ListItem
              key={user.userManagementSummaryDto?.id}
              userType="일반회원"
              memberInfo={user as memberInfoProps}
              onDetailClick={onDetailClick}
              onSelect={onSelect}
              selectedIds={selectedIds} 
              listRest={0}            />
          );
        } else if (userType === "기업회원") {
          return (
            <ListItem
              key={user.userManagementSummaryDto?.id}
              userType="기업회원"
              companyInfo={user as companyInfoProps}
              onDetailClick={onDetailClick}
              onSelect={onSelect}
              selectedIds={selectedIds} listRest={0} />
          );
        } else if (userType === "컨설턴트회원") {
          return (
            <ListItem
              key={user.userManagementSummaryDto?.id}
              userType="컨설턴트회원"
              consultantInfo={user as consultantInfoProps}
              onDetailClick={onDetailClick}
              onSelect={onSelect}
              selectedIds={selectedIds} listRest={0} />
          );
        } else {
          return null;
        }
      })}
    </div>
  );
};

export default UserList;