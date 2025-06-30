export interface UserManagementDetailDto {
    email: string
    name: string
    phone: string
    address: string
    isSubscribed: boolean
    genderType: string
    LocalDate: string
    createdDate: string
}

export interface MemberManagementDetailDto {
    nickName: string
    userManagementDetailDto: UserManagementDetailDto
}

export interface CompanyManagementDetailDto {
    industry: string
    companyName: string
    representativeName: string
    businessNumber: string
    companyAddress: string
    companyPhone: string
    introduction: string
    companyType: string
    employeeCount: number
    establishedDate: string
    userManagementDetailDto: UserManagementDetailDto
}

export interface ConsultantManagementDetailDto {
    userManagementDetailDto: UserManagementDetailDto
}


export interface UserManagementSummaryDto {
    id: number
    email: string
    name: string
    phone: string
    address: string
    createdDate: string
}

export interface UserItem {
  userManagementSummaryDto: UserManagementSummaryDto;
  approvalState: "APPROVED" | "WAITING" | "REJECTED";
}


export interface CompanyManagementSummaryDto {
  companyName: string;
  userManagementSummaryDto: UserManagementSummaryDto;
  approvalState: "APPROVED" | "WAITING" | "REJECTED";
}

export interface ConsultantManagementSummaryDto {
  userManagementSummaryDto: UserManagementSummaryDto;
  approvalState: "APPROVED" | "WAITING" | "REJECTED";
}

export interface MemberManagementSummaryDto {
  nickName: string;
  userManagementSummaryDto: UserManagementSummaryDto;
  approvalState: "APPROVED" | "WAITING" | "REJECTED";
}


