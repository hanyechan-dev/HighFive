import { useEffect, useState } from "react";
import { userTypeEnum } from "../../../common/enum/Enum";
import RadioButton from "../../../common/components/button/RadioButton";
import ModalTitle from "../../../common/components/title/ModalTitle";
import UserApprovalList from "../list/UserApprovalList";
import { companyAndConsultantApprovalClick, companyAndConsultantRejectionClick } from "../features/UserPageClick";
import UserList from "../list/UserManagementList";
import UserManagementList from "../list/UserManagementList";
import type { consultantInfoProps, memberInfoProps } from "../users/UserInfo";
import type { UserInfoUnion } from "../union/UserInfoUnion";

interface UserInfoProps {
    id: number;
    email: string;
    name: string;
    phone: string;
    address: string;
    createdDate: string;
}

interface MemberInfoProps {
    userInfo: UserInfoProps;
    nickName: string;
} 

interface companyInfoProps {
    userInfo: UserInfoProps;
}


const UserManagementPage = () => {

    const approvalUserTypes = userTypeEnum.filter(item => item.value !== "일반회원");
    const [approvalType, setApprovalType] = useState(approvalUserTypes[0].value);
   const [userType, setUserType] = useState<"일반회원" | "기업회원" | "컨설턴트회원">(userTypeEnum[0].value as "일반회원" | "기업회원" | "컨설턴트회원");
    const [pageMode, setPageMode] = useState("가입 승인");
    const managementOptions = [{ label: "회원 조회", value: "회원 조회" }, { label: "가입 승인", value: "가입 승인" }];
    const [selectedIds, setSelectedIds] = useState<number[]>([]);
    const [members, setMembers] = useState<UserInfoUnion[]>([]);

    useEffect(() => {
        const approve = async () => {
            try {
                await companyAndConsultantApprovalClick(selectedIds);
            } catch (err) {
                console.error(err);
            }
        };
        approve();
    }, []);


    const companyAndConsultantApproval = (id: number) => {

        const click = async () => {
            try {
                await companyAndConsultantApprovalClick([id]);
            } catch (err) {
                console.error(err);
            }
        }
        click();
    }

    const companyAndConsultantRejection = (id: number) => {

        const click = async () => {
            try {
                await companyAndConsultantRejectionClick([id]);
            } catch (err) {
                console.error(err);
            }
        }
        click();
    }

    return (
        <div className="bg-white min-h-screen">

            {pageMode === "가입 승인" && (
                <div className="mb-[2px]">
                    <RadioButton
                        name=""
                        textList={approvalUserTypes}
                        checkedText={approvalType}
                        setCheckedText={setApprovalType}
                    />
                </div>
            )}
            {pageMode === "회원 조회" && (
                <UserManagementList userType={userType} memberList={members} />
            )}
        </div>
    );
};

export default UserManagementPage;

