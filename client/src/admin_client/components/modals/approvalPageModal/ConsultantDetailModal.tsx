import { useState, useEffect } from "react";
import ModalTitle from "../../../../common/components/title/ModalTitle";
import CommonModal from "../../../../common/modals/CommonModal";
import { consultantDetailClick } from "../../features/UserPageClick";


interface ConsultantDetailData {
  email: string;
  name: string;
  phone: string;
  address: string;
  genderType: string;
  birthDate: string;
  createdDate: string;
}

interface ConsultantDetailModalProps {
  id: number;
  onClose: () => void;
}

const ConsultantDetailModal = ({ id, onClose }: ConsultantDetailModalProps) => {
  const [consultantdata, setConsultantData] = useState<ConsultantDetailData | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await consultantDetailClick(id);
        setConsultantData(res.data.userManagementDetailDto);
      } catch (err) {
        console.error(err);
      }
    };

    fetchData();
  }, [id]);

  if (!consultantdata) return null;

  return (
    <CommonModal size="m" onClose={onClose}>
      <ModalTitle title={"회원 상세 정보"} />
      <div className="ml-6 space-y-3 mb-6 font-roboto">
        <div className="mb-3 font-semibold">이메일: {consultantdata.email}</div>
        <div className="mb-3 font-semibold">이름: {consultantdata.name}</div>
        <div className="mb-3 font-semibold">연락처: {consultantdata.phone}</div>
        <div className="mb-3 font-semibold">주소: {consultantdata.address}</div>
        <div className="mb-3 font-semibold">가입일 : {consultantdata.genderType}</div>
        <div className="mb-3 font-semibold">생일 : {consultantdata.birthDate}</div>
        <div className="mb-3 font-semibold">성별 : {consultantdata.createdDate}</div>
      </div>
    </CommonModal>
  );
};

export default ConsultantDetailModal;
