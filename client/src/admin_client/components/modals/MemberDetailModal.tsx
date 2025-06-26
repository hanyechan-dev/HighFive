import { useEffect, useState } from "react";
import Button from "../../../common/components/button/Button";
import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal";
import { memberDetailClick } from "../features/UserPageClick";

interface MemberDetailData {
    email: string;
    name: string;
    phone: string;
    address: string;
    isSubscribed: boolean;
    genderType: string;
    birthDate: string;
    createdDate: string;
    nickName: string;
}

interface MemberDetailProps {
    id: number;
    onClose: () => void;
}
const MemberDetail = ({ id, onClose }: MemberDetailProps) => {
    const [memberdata, setMemberdata] = useState<MemberDetailData | null>(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await memberDetailClick(id);
                setMemberdata(res.data.content);
            } catch (err) {
                console.error(err);
            }
        };

        fetchData();
    }, [id]);

    if (!memberdata) return null;

    return (
        <CommonModal size="m" onClose={onClose}>
            <ModalTitle title={"회원 상세 정보"} />
            <div className="ml-6 space-y-3 font-roboto">
                <div className="mb-3 font-semibold">닉네임: {memberdata.nickName}</div>
                <div className="mb-3 font-semibold">이메일: {memberdata.email}</div>
                <div className="flex justify-start gap-2">
                    <div className="mb-3 font-semibold">이름:</div>
                    <div>{memberdata.name}</div>
                </div>
                <div className="mb-3 font-semibold">연락처: {memberdata.phone}</div>
                <div className="mb-3 font-semibold">주소: {memberdata.address}</div>
                <div className="mb-3 font-semibold">구독 여부: {memberdata.isSubscribed ? "예" : "아니오"}</div>
                <div className="mb-3 font-semibold">성별: {memberdata.genderType}</div>
                <div className="mb-3 font-semibold">생일: {memberdata.birthDate}</div>
                <div className="mb-3 font-semibold">가입일: {memberdata.createdDate}</div>

                <div className="flex justify-end mr-[15px]">
                    <Button
                        color={"theme"}
                        size={"s"}
                        disabled={false}
                        text={"닫기"}
                        type={"button"}
                        onClick={onClose}
                    />
                </div>
            </div>
        </CommonModal>
    );
};

export default MemberDetail;
