import Button from "../../../common/components/button/Button";
import type { CompanyManagementSummaryDto, ConsultantManagementSummaryDto, MemberManagementSummaryDto } from "../props/UserManagementProps";



interface ListItemProps {
    userType: '일반회원' | '기업회원' | '컨설턴트회원';
    memberManagementSummaryDto?: MemberManagementSummaryDto;
    companyManagementSummaryDto?: CompanyManagementSummaryDto;
    consultantManagementSummaryDto?: ConsultantManagementSummaryDto;
    onDetailClick: (id: number) => void;
    onSelect: (id: number, checked: boolean) => void;
    selectedIds: number[];
}

const ListItem = ({
    userType,
    memberManagementSummaryDto,
    companyManagementSummaryDto,
    consultantManagementSummaryDto,
    onDetailClick,
    onSelect,
    selectedIds
}: ListItemProps) => {
    let id: number | undefined;
    let email = "", name = "", nickname = "", companyName = "", phone = "", address = "", createdDate = "";

    if (userType === '일반회원' && memberManagementSummaryDto?.userManagementSummaryDto) {
        id = memberManagementSummaryDto.userManagementSummaryDto.id;
        email = memberManagementSummaryDto.userManagementSummaryDto.email;
        name = memberManagementSummaryDto.userManagementSummaryDto.name;
        nickname = memberManagementSummaryDto.nickName;
        phone = memberManagementSummaryDto.userManagementSummaryDto.phone;
        address = memberManagementSummaryDto.userManagementSummaryDto.address;
        createdDate = memberManagementSummaryDto.userManagementSummaryDto.createdDate;

    } else if (userType === '기업회원' && companyManagementSummaryDto?.userManagementSummaryDto) {
        id = companyManagementSummaryDto.userManagementSummaryDto.id;
        email = companyManagementSummaryDto.userManagementSummaryDto.email;
        name = companyManagementSummaryDto.userManagementSummaryDto.name;
        companyName = companyManagementSummaryDto.companyName;
        phone = companyManagementSummaryDto.userManagementSummaryDto.phone;
        address = companyManagementSummaryDto.userManagementSummaryDto.address;
        createdDate = companyManagementSummaryDto.userManagementSummaryDto.createdDate;

    } else if (userType === '컨설턴트회원' && consultantManagementSummaryDto?.userManagementSummaryDto) {
        id = consultantManagementSummaryDto.userManagementSummaryDto.id;
        email = consultantManagementSummaryDto.userManagementSummaryDto.email;
        name = consultantManagementSummaryDto.userManagementSummaryDto.name;
        phone = consultantManagementSummaryDto.userManagementSummaryDto.phone;
        address = consultantManagementSummaryDto.userManagementSummaryDto.address;
        createdDate = consultantManagementSummaryDto.userManagementSummaryDto.createdDate;
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




















interface UserListProps {
    userType: '일반회원' | '기업회원' | '컨설턴트회원';
    memberList: MemberManagementSummaryDto[] | CompanyManagementSummaryDto[] | ConsultantManagementSummaryDto[]
    onDetailClick: (id: number) => void;
    onSelect: (id: number, checked: boolean) => void;
    selectedIds: number[];
    listRest: number;
}



const UserList = ({ userType, memberList, onDetailClick, onSelect, selectedIds, }: UserListProps) => {

    let output;

    if (userType === '일반회원') {
        output = '닉네임';
    }
    else if(userType === '기업회원'){
        output = '기업명';
    }
    else{
        output =''
    }

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

            {memberList.map(user => {
                if (userType === "일반회원") {
                    return (
                        <ListItem
                            key={user.userManagementSummaryDto?.id}
                            userType="일반회원"
                            memberManagementSummaryDto={user as MemberManagementSummaryDto}
                            onDetailClick={onDetailClick}
                            onSelect={onSelect}
                            selectedIds={selectedIds}/>
                    );
                } else if (userType === "기업회원") {
                    return (
                        <ListItem
                            key={user.userManagementSummaryDto?.id}
                            userType="기업회원"
                            companyManagementSummaryDto={user as CompanyManagementSummaryDto}
                            onDetailClick={onDetailClick}
                            onSelect={onSelect}
                            selectedIds={selectedIds}/>
                    );
                } else if (userType === "컨설턴트회원") {
                    return (
                        <ListItem
                            key={user.userManagementSummaryDto?.id}
                            userType="컨설턴트회원"
                            consultantManagementSummaryDto={user as ConsultantManagementSummaryDto}
                            onDetailClick={onDetailClick}
                            onSelect={onSelect}
                            selectedIds={selectedIds}/>
                    );
                } else {
                    return null;
                }
            })}
        </div>
    );
};

export default UserList;