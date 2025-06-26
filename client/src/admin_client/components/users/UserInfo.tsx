export interface userInfoProps{
    id: number;
    email : String;
    name : String;
    phone: String;
    address : String;
    createdDate : String;
}

export interface memberInfoProps{
    userManagementSummaryDto : userInfoProps;
    nickName : string;
}

export interface companyInfoProps{
    companyName : string;
    userManagementSummaryDto : userInfoProps;
}

export interface consultantInfoProps{
    userManagementSummaryDto : userInfoProps;
}