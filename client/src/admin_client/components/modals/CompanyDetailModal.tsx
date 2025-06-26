import { useEffect, useState } from "react";
import Button from "../../../common/components/button/Button";
import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal";
import { companyDetailClick } from "../features/UserPageClick";

interface CompanyDetailData {
  email: string;
  name: string;
  phone: string;
  address: string;
  isSubscribed: boolean;
  genderType: string;
  birthDate: string;
  createdDate: string;
  companyName: string;
}

interface CompanyDetailProps {
  id: number;
  onClose: () => void;
}

const CompanyDetail = ({ id, onClose }: CompanyDetailProps) => {
  const [companydata, setCompanyData] = useState<CompanyDetailData | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await companyDetailClick(id);
        setCompanyData(res.data.content);
      } catch (err) {
        console.error("기업 상세 조회 실패", err);
      }
    };

    fetchData();
  }, [id]);

  if (!companydata) return null;

  return (
    <CommonModal size="m" onClose={onClose}>
      <ModalTitle title={"회원 상세 정보"} />
      <div className="ml-6 space-y-3 font-roboto">
        <div className="mb-3 font-semibold">회사명: {companydata.companyName}</div>
        <div className="mb-3 font-semibold">이메일: {companydata.email}</div>
        <div className="mb-3 font-semibold">이름: {companydata.name}</div>
        <div className="mb-3 font-semibold">연락처: {companydata.phone}</div>
        <div className="mb-3 font-semibold">주소: {companydata.address}</div>
        <div className="mb-3 font-semibold">구독 여부: {companydata.isSubscribed ? "예" : "아니오"}</div>
        <div className="mb-3 font-semibold">성별: {companydata.genderType}</div>
        <div className="mb-3 font-semibold">생일: {companydata.birthDate}</div>
        <div className="mb-3 font-semibold">가입일: {companydata.createdDate}</div>

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

export default CompanyDetail;
