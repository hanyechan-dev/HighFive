import { useState, useEffect } from "react";
import ModalTitle from "../../../../common/components/title/ModalTitle";
import CommonModal from "../../../../common/modals/CommonModal";
import { companyDetailClick } from "../../features/UserPageClick";
import type { CompanyType } from "../managementPageModal/CompanyDetailModal";

export const companyTypeEnum: { label: string; value: string }[] = [
  { label: "대기업", value: "LARGE" },
  { label: "중소기업", value: "SMALL" }
];

interface CompanyDetailData {
  industry: string;
  companyName: string;
  representativeName: string,
  businessNumber: string;
  companyAddress: string;
  companyPhone: string;
  companyType: CompanyType;
  employeeCount: number;
  introduction: string;
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
      const dto = res.data;

      const detailData: CompanyDetailData = {
        industry: dto.industry,
        companyName: dto.companyName,
        representativeName: dto.representativeName,
        businessNumber: dto.businessNumber,
        companyAddress: dto.companyAddress,
        companyPhone: dto.companyPhone,
        companyType: dto.type,
        employeeCount: dto.employeeCount,
        introduction: dto.introduction
      };

      setCompanyData(detailData);
    } catch (err) {
      console.error(err);
    }
  };

  fetchData();
}, [id]);

  if (!companydata) return null;

  return (
    <CommonModal size="m" onClose={onClose}>
      <ModalTitle title={"회원 상세 정보"} />
      <div className="ml-6 space-y-3 mb-6 font-roboto">
        <div className="mb-3 font-semibold">산업 : {companydata.industry}</div>
        <div className="mb-3 font-semibold">기업명 : {companydata.companyName}</div>
        <div className="mb-3 font-semibold">대표명 : {companydata.representativeName}</div>
        <div className="mb-3 font-semibold">사업자 번호 : {companydata.businessNumber}</div>
        <div className="mb-3 font-semibold">회사 주소 : {companydata.companyAddress}</div>
        <div className="mb-3 font-semibold">기업 유형 : {companydata.companyType}</div>
        <div className="mb-3 font-semibold">직원 수 : {companydata.employeeCount}</div>
        <div className="mb-3 font-semibold">회사 소개 : {companydata.introduction}</div>
      </div>
    </CommonModal>
  );
};

export default CompanyDetail;
