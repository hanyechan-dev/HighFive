import { useState, useEffect } from "react";
import { companyDetailClick } from "../apis/UserManagementApi";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import CommonModal from "../../../common/modals/CommonModal";
import ModalTitle from "../../../common/components/title/ModalTitle";



const companyTypeEnum: { label: string; value: string }[] = [
    { label: "대기업", value: "LARGE" },
    { label: "중소기업", value: "SMALL" }
];

type CompanyType = typeof companyTypeEnum[number]['value'];

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
                const data = res.data;

                const detailData: CompanyDetailData = {
                    industry: data.industry,
                    companyName: data.companyName,
                    representativeName: data.representativeName,
                    businessNumber: data.businessNumber,
                    companyAddress: data.companyAddress,
                    companyPhone: data.companyPhone,
                    companyType: data.type,
                    employeeCount: data.employeeCount,
                    introduction: data.introduction
                };

                setCompanyData(detailData);
            } catch (err) {
                printErrorInfo(err)
            }
        };

        fetchData();
    }, [id]);


    if (!companydata) return null;

    return (
        <CommonModal size="m" onClose={onClose}>
            <ModalTitle title={"회원 상세 정보"} />
            <div className="ml-6 mb-6 space-y-3 font-roboto">
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
