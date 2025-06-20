// src/admin_client/components/users/UserInfo.ts

export interface UserInfoBase {
  userManagementSummaryDto: {
    id: number;
    email: string;
    name: string;
    phone: string;
    address: string;
    createdDate: string;
  };
}

export interface memberInfoProps extends UserInfoBase {
  userType: "일반회원";
  nickName: string;
}

export interface companyInfoProps extends UserInfoBase {
  userType: "기업회원";
  companyName: string;
}

export interface consultantInfoProps extends UserInfoBase {
  userType: "컨설턴트회원";
}

export type UserInfoUnion = memberInfoProps | companyInfoProps | consultantInfoProps;

