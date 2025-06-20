import Button from "../../../common/components/button/Button";

interface UserInfoProps {
  id: number;
  email: string;
  name: string;
  phone: string;
  address: string;
  createdDate: string;
}

interface consultantInfoProps {
  userManagementSummaryDto: UserInfoProps;
}

interface companyInfoProps {
  userManagementSummaryDto: UserInfoProps;
  companyName: string;
}

interface approvalListItemProps {
  approvalType: "WATTING" | "APPROVED" | "REJECTED";
  userType: "기업회원" | "컨설턴트회원";
  companyInfo?: companyInfoProps;
  consultantInfo?: consultantInfoProps;
}

// 리스트 아이템 컴포넌트
const ListItem = ({
  userType,
  companyInfo,
  consultantInfo,
}: approvalListItemProps) => {
  let email = "";
  let name = "";
  let companyName = "";
  let phone = "";
  let address = "";
  let createdDate = "";

  if (userType === "기업회원" && companyInfo) {
    const { userManagementSummaryDto } = companyInfo;
    email = userManagementSummaryDto.email;
    name = userManagementSummaryDto.name;
    companyName = companyInfo.companyName;
    phone = userManagementSummaryDto.phone;
    address = userManagementSummaryDto.address;
    createdDate = userManagementSummaryDto.createdDate;
  } else if (userType === "컨설턴트회원" && consultantInfo) {
    const { userManagementSummaryDto } = consultantInfo;
    email = userManagementSummaryDto.email;
    name = userManagementSummaryDto.name;
    phone = userManagementSummaryDto.phone;
    address = userManagementSummaryDto.address;
    createdDate = userManagementSummaryDto.createdDate;
  }

  return (
    <div className="grid grid-cols-7 items-center px-4 py-3 border-b text-sm font-roboto">
      <div className="px-5">{email}</div>
      <div className="px-10">{name}</div>
      <div className="px-10">
        {userType === "기업회원" ? companyName : "-"}
      </div>
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
        />
      </div>
    </div>
  );
};

// 샘플 유저 데이터
const userList = [
  {
    userType: "컨설턴트회원",
    userManagementSummaryDto: {
      email: "user2@example.com",
      name: "홍길동",
      phone: "010-2222-2222",
      address: "서울시 서초구",
      createdDate: "2023-01-05",
      id: 2,
    },
  },
  {
    userType: "기업회원",
    companyName: "정우무역",
    userManagementSummaryDto: {
      email: "user3@example.com",
      name: "정우",
      phone: "010-3333-3333",
      address: "서울시 마포구",
      createdDate: "2023-01-10",
      id: 3,
    },
  },
  {
    userType: "기업회원",
    companyName: "민수컴퍼니",
    userManagementSummaryDto: {
      email: "user5@example.com",
      name: "최민수",
      phone: "010-5555-5555",
      address: "서울시 중구",
      createdDate: "2023-02-05",
      id: 5,
    },
  },
  {
    userType: "컨설턴트회원",
    userManagementSummaryDto: {
      email: "user6@example.com",
      name: "한지현",
      phone: "010-6666-6666",
      address: "서울시 용산구",
      createdDate: "2023-02-10",
      id: 6,
    },
  },
  {
    userType: "기업회원",
    companyName: "하늘식품",
    userManagementSummaryDto: {
      email: "user7@example.com",
      name: "박하늘",
      phone: "010-7777-7777",
      address: "서울시 강서구",
      createdDate: "2023-03-01",
      id: 7,
    },
  },
  {
    userType: "컨설턴트회원",
    userManagementSummaryDto: {
      email: "user9@example.com",
      name: "이도윤",
      phone: "010-9999-9999",
      address: "서울시 송파구",
      createdDate: "2023-03-10",
      id: 9,
    },
  },
];

interface UserListProps {
  userType: string;
}

// 전체 유저 리스트 컴포넌트
const UserApprovalList = ({ userType }: UserListProps) => {
  const output = userType === "기업회원" ? "기업명" : "-";

  return (
    <div className="w-[1452px] border border-gray-300 rounded-xl font-roboto shadow-sm overflow-hidden text-sm">
      {/* 헤더 */}
      <div className="grid grid-cols-7 bg-gray-100 text-gray-700 font-roboto px-4 py-2 border-b">
        <div className="px-16">이메일</div>
        <div className="px-11">이름</div>
        <div className="px-10">{output}</div>
        <div className="px-12">연락처</div>
        <div className="px-10">주소</div>
        <div className="px-11">가입일</div>
      </div>

      {/* 데이터 행 */}
      {userList && userList
        .filter((user) => user.userType === userType)
        .map((user) => {
          if (user.userType === "기업회원") {
            const companyData: companyInfoProps = {
              companyName: user.companyName ?? "",
              userManagementSummaryDto: user.userManagementSummaryDto,
            };
            return (
              <ListItem
                key={companyData.userManagementSummaryDto.id}
                userType="기업회원"
                companyInfo={companyData}
                approvalType="WATTING"
              />
            );
          } else if (user.userType === "컨설턴트회원") {
            const consultantData: consultantInfoProps = {
              userManagementSummaryDto: user.userManagementSummaryDto,
            };
            return (
              <ListItem
                key={consultantData.userManagementSummaryDto.id}
                userType="컨설턴트회원"
                consultantInfo={consultantData}
                approvalType="WATTING"
              />
            );
          } else {
            return null;
          }
        })}

    </div>
  );
};

export default UserApprovalList;
