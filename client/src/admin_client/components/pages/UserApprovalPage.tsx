import { useState } from "react";
import Button from "../../../common/components/button/Button";
import RadioButton from "../../../common/components/button/RadioButton";
import { userTypeEnum } from "../../../common/enum/Enum";
import { approvalClick, rejectionClick } from "../features/UserPageClick";
import UserApprovalList from "../list/UserApprovalList";
import CompanyDetail from "../modals/approvalPageModal/CompanyDetailModal";
import ConsultantDetailModal from "../modals/approvalPageModal/ConsultantDetailModal";
import RadioButton2 from "../../../common/components/button/RadioButton2";

const UserApprovalPage = () => {
    const approvalUserTypes = userTypeEnum.filter(
        (item) => item.value !== "일반회원"
    );
    const [approvalType, setApprovalType] = useState<"기업회원" | "컨설턴트회원">("기업회원");
    const [selectedIds, setSelectedIds] = useState<number[]>([]);
    const [isDetailOpen, setIsDetailOpen] = useState(false);
    const [selectedId, setSelectedId] = useState<number>(-1);
    const [listReset, setListReset] = useState(0);


    const handleSelect = (id: number, checked: boolean) => {
        setSelectedIds(prev => checked ? [...prev, id] : prev.filter(i => i !== id));
    };

    const handleApprove = async () => {
        if (selectedIds.length === 0) return;
        try {
            await approvalClick(selectedIds);
            setSelectedIds([]);
            setListReset(prev => prev + 1);
            alert("승인이 완료되었습니다.");
        } catch (err) {
            console.error(err);
        }
    };

    const handleReject = async () => {
        if (selectedIds.length === 0) return;
        try {
            await rejectionClick(selectedIds);
            setSelectedIds([]);
            setListReset(prev => prev + 1);
            alert("거절이 완료되었습니다.");
        } catch (err) {
            console.error(err);
        }
    };

    const handleDetailClick = (id: number) => {
        setSelectedId(id);
        setIsDetailOpen(true);
    };

    const closeModal = () => {
        setIsDetailOpen(false);
        setSelectedId(-1);
    };

    return (
        <>
            <div className="flex justify-between">
                <div >
                    <RadioButton2
                        name=""
                        textList={approvalUserTypes}
                        checkedText={approvalType}
                        setCheckedText={(value) => setApprovalType(value as "기업회원" | "컨설턴트회원")} itemNumber={2}                    />
                </div>
                <div className="mb-[-24px]">
                    <Button color={"theme"} size={"s"} disabled={false} text={"가입 승인"} type={"button"} onClick={handleApprove} />
                    <Button color={"action"} size={"s"} disabled={false} text={"거절"} type={"button"} onClick={handleReject} />
                </div>
            </div>
            <UserApprovalList
                userType={approvalType}
                onSelect={handleSelect}
                onDetailClick={handleDetailClick}
                listReset={listReset}
            />
            {isDetailOpen && selectedId !== -1 && (
                approvalType === "기업회원" ? (
                    <CompanyDetail id={selectedId} onClose={closeModal} />
                ) : (
                    <ConsultantDetailModal id={selectedId} onClose={closeModal} />
                )
            )}
        </>
    );
};

export default UserApprovalPage;
