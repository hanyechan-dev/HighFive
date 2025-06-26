import { useState, useEffect } from "react";
import { memberDetailClick } from "../apis/UserManagementApi";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal";


interface UserManagementDetailDto {
  email: string;
  name: string;
  phone: string;
  address: string;
  subscribed: boolean;
  genderType: string;
  birthDate: string;
  createdDate: string;
}

interface MemberDetailData {
  nickName: string;
  userManagementDetailDto: UserManagementDetailDto;
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
        setMemberdata(res.data);

      } catch (err) {
        printErrorInfo(err);
      }
    };

    fetchData();
  }, [id]);

  if (!memberdata) return null;

  const detail = memberdata.userManagementDetailDto;

  return (
    <CommonModal size="m" onClose={onClose}>
      <ModalTitle title={"회원 상세 정보"} />
      <div className="ml-6 space-y-3 mb-6 font-roboto">
        <div className="mb-3 font-semibold">이메일 : {detail.email}</div>
        <div className="mb-3 font-semibold">이름 : {detail.name}</div>
        <div className="mb-3 font-semibold">닉네임 : {memberdata.nickName}</div>
        <div className="mb-3 font-semibold">연락처 : {detail.phone}</div>
        <div className="mb-3 font-semibold">주소 : {detail.address}</div>
        <div className="mb-3 font-semibold">구독 여부 : {detail.subscribed ? "예" : "아니오"}</div>
        <div className="mb-3 font-semibold">성별 : {detail.genderType}</div>
        <div className="mb-3 font-semibold">생일 : {detail.birthDate}</div>
        <div className="mb-3 font-semibold">가입일 : {detail.createdDate}</div>
      </div>
    </CommonModal>
  );
};

export default MemberDetail;
