import Button from "../../../common/components/button/Button";
import EmptyState from "../../../common/components/emptyState/EmptyState";
import type { companyInfoProps, consultantInfoProps, memberInfoProps, UserInfoUnion } from "../union/UserInfoUnion";

interface UserListProps {
  userType: '일반회원' | '기업회원' | '컨설턴트회원';
  memberList: UserInfoUnion[];
}

interface ListItemProps {
  userType: '일반회원' | '기업회원' | '컨설턴트회원';
  memberInfo?: memberInfoProps;
  companyInfo?: companyInfoProps;
  consultantInfo?: consultantInfoProps;
}

const ListItem = ({
  userType,
  memberInfo,
  companyInfo,
  consultantInfo
}: ListItemProps) => {
  let email, name, nickname, companyName, phone, address, createdDate;

  if (userType === '일반회원' && memberInfo) {
    email = memberInfo.userManagementSummaryDto.email;
    name = memberInfo.userManagementSummaryDto.name;
    nickname = memberInfo.nickName;
    phone = memberInfo.userManagementSummaryDto.phone;
    address = memberInfo.userManagementSummaryDto.address;
    createdDate = memberInfo.userManagementSummaryDto.createdDate;
  } else if (userType === '기업회원' && companyInfo) {
    email = companyInfo.userManagementSummaryDto.email;
    name = companyInfo.userManagementSummaryDto.name;
    companyName = companyInfo.companyName;
    phone = companyInfo.userManagementSummaryDto.phone;
    address = companyInfo.userManagementSummaryDto.address;
    createdDate = companyInfo.userManagementSummaryDto.createdDate;
  } else if (userType === '컨설턴트회원' && consultantInfo) {
    email = consultantInfo.userManagementSummaryDto.email;
    name = consultantInfo.userManagementSummaryDto.name;
    phone = consultantInfo.userManagementSummaryDto.phone;
    address = consultantInfo.userManagementSummaryDto.address;
    createdDate = consultantInfo.userManagementSummaryDto.createdDate;
  }

  return (
    <div className="grid grid-cols-7 items-center px-4 py-3 border-b text-sm font-roboto">
      <div className="px-5">{email}</div>
      <div className="px-10">{name}</div>
      <div className="px-10">
        {userType === "일반회원" && nickname}
        {userType === "기업회원" && companyName}
        {userType === "컨설턴트회원" && "-"}
      </div>
      <div className="px-5">{phone}</div>
      <div className="px-4">{address}</div>
      <div className="px-8">{createdDate}</div>
      <div className="flex justify-center pt-6 mr-24">
        <Button color={"theme"} size={"s"} disabled={false} text={"상세정보보기"} type={"button"} />
      </div>
    </div>
  );
};

const UserList = ({ userType, memberList }: UserListProps) => {
  const output = userType === '일반회원' ? '닉네임' :
                 userType === '기업회원' ? '기업명' : '';

  return (
    <div className="w-[1500px] border border-gray-300 rounded-xl shadow-sm overflow-hidden text-sm">
      <div className="grid grid-cols-7 bg-gray-100 text-gray-700 font-roboto px-4 py-2 border-b">
        <div className="px-16">이메일</div>
        <div className="px-11">이름</div>
        <div className="px-10">{output}</div>
        <div className="px-12">연락처</div>
        <div className="px-10">주소</div>
        <div className="px-11">가입일</div>
        <div className="px-12">상세</div>
      </div>

      {memberList && memberList
        .filter(user => user.userType === userType)
        .map(user => {
          if (user.userType === "일반회원") {
            return (
              <ListItem
                key={user.userManagementSummaryDto.id}
                userType="일반회원"
                memberInfo={user}
              />
            );
          } else if (user.userType === "기업회원") {
            return (
              <ListItem
                key={user.userManagementSummaryDto.id}
                userType="기업회원"
                companyInfo={user}
              />
            );
          } else if (user.userType === "컨설턴트회원") {
            return (
              <ListItem
                key={user.userManagementSummaryDto.id}
                userType="컨설턴트회원"
                consultantInfo={user}
              />
            );
          }
        })}
    </div>
  );
};

export default UserList;
