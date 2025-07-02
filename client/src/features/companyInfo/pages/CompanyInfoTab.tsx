import { useState, useEffect } from 'react';
import type { ChangeEvent } from 'react';
import Input from '../../../common/components/input/Input';
import TextArea from '../../../common/components/input/TextArea';
import Button from '../../../common/components/button/Button';
import Select from '../../../common/components/input/Select';
import { PageBox } from '../../../common/components/box/Box';
import ModalTitle from '../../../common/components/title/ModalTitle';
import { companyTypeEnum } from '../../../common/enum/Enum';
import { getCompanyInfoApi, updateCompanyInfoApi } from '../apis/CompanyApi';
import { printErrorInfo } from '../../../common/utils/ErrorUtil';
import ImagePreviewArea from '../../../common/components/image/ImagePreviewArea';
import ImageOutputArea from '../../../common/components/image/ImageOutputArea';

const CompanyInfoTab = () => {
    const [companyName, setCompanyName] = useState('');
    const [businessNumber, setBusinessNumber] = useState('');
    const [representativeName, setRepresentativeName] = useState('');
    const [establishedDate, setEstablishedDate] = useState('');
    const [employeeCount, setEmployeeCount] = useState('');
    const [industry, setIndustry] = useState('');
    const [address, setAddress] = useState('');
    const [phone, setPhone] = useState('');
    const [introduction, setIntroduction] = useState('');
    const [companyType, setCompanyType] = useState('');
    const [logoPreviewUrl, setLogoPreviewUrl] = useState<string | null>(null);
    const [logoUrl, setLogoUrl] = useState<string>("");
    const [logoFile, setLogoFile] = useState<File | null>(null);
    const [loading, setLoading] = useState(false);
    const [isPreview, setIsPreview] = useState(false);

    useEffect(() => {
        getCompanyInfoApi().then(res => {
            const data = res.data.companyResponseDto;
            console.log("---------------")
            console.log(data)
            console.log("---------------")
            setCompanyName(data.companyName ?? '');
            setBusinessNumber(data.businessNumber ?? '');
            setRepresentativeName(data.representativeName ?? '');
            setEstablishedDate(data.establishedDate ?? '');
            setEmployeeCount(data.employeeCount?.toString() ?? '');
            setIndustry(data.industry ?? '');
            setAddress(data.companyAddress ?? '');
            setPhone(data.companyPhone ?? '');
            setIntroduction(data.introduction ?? '');
            setCompanyType(data.companyType ?? '');
            setLogoUrl(data.imageUrl);
        })
            .catch(err => {
                printErrorInfo(err);
            })

    }, []);

    const handleLogoChange = (e: ChangeEvent<HTMLInputElement>) => {
        setIsPreview(true)
        const file = e.target.files?.[0] || null;
        setLogoFile(file);
        setLogoPreviewUrl(file ? URL.createObjectURL(file) : null);
    };

    const handleSave = async () => {
        setLoading(true);
        try {
            await updateCompanyInfoApi(
                companyName,
                businessNumber,
                representativeName,
                companyType,
                address,
                phone,
                industry,
                employeeCount,
                establishedDate,
                introduction,
                logoFile
            );
            alert('저장되었습니다.');
        } catch (err) {
            printErrorInfo(err);
        } finally {
            setLoading(false);
        }
    };

    return (
        <PageBox>
            <ModalTitle title="기업 정보" />
            <div className="mt-[-12px] ml-6 mb-6 font-roboto text-[#888]">
                기업의 기본 정보를 확인하고 관리할 수 있습니다.
            </div>
            <div className="border-b w-[1120px] ml-6 mb-6"></div>
            <div className="flex">
                <Input label="기업명" placeholder="기업명을 입력하세요" size="pbm" disabled={false} type="text" value={companyName} setValue={setCompanyName} />
                <Input label="대표자명" placeholder="대표자명을 입력하세요" size="pbm" disabled={false} type="text" value={representativeName} setValue={setRepresentativeName} />
            </div>
            <div className="flex">
                <Input label="사업자등록번호" placeholder="사업자등록번호 입력" size="pbm" disabled={false} type="text" value={businessNumber} setValue={setBusinessNumber} />
                <Input label="설립일" placeholder="설립일 입력" size="pbm" disabled={false} type="date" value={establishedDate} setValue={setEstablishedDate} />
            </div>
            <div className="flex">
                <Input label="직원수" placeholder="직원수 입력" size="pbm" disabled={false} type="text" value={employeeCount} setValue={setEmployeeCount} />
                <Input label="업종" placeholder="업종 입력" size="pbm" disabled={false} type="text" value={industry} setValue={setIndustry} />
            </div>
            <div className="flex">
                <Input label="주소" placeholder="주소 입력" size="pbm" disabled={false} type="text" value={address} setValue={setAddress} />
                <Select label="회사 유형" options={companyTypeEnum} size="pbm" disabled={false} value={companyType} setValue={setCompanyType} />
            </div>
            <div className="flex">
                <Input label="기업 전화" placeholder="전화번호 입력" size="pbm" disabled={false} type="text" value={phone} setValue={setPhone} />
            </div>
            <TextArea size="pbl" label="기업 소개" placeholder="기업 소개를 입력하세요" disabled={false} value={introduction} setValue={setIntroduction} />
            <div className="mb-6">
                <label className="font-roboto text-base mb-2 inline-block ml-[24px]">기업 로고</label>
                <input type="file" accept="image/*" onChange={handleLogoChange} className="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-full file:border-0 file:text-sm file:font-semibold file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100 ml-[24px] mb-6" />
                
                {isPreview && logoPreviewUrl ? (
                    <div className="mt-4 ml-[24px]">
                        <ImagePreviewArea size="m" imageUrl={logoPreviewUrl} />
                    </div>
                ):(<div className="mt-4 ml-[24px]">
                        <ImageOutputArea size="m" imageUrl={logoUrl} />
                    </div>)}
            </div>
            <div className="flex justify-end mr-6">
                <Button color="theme" size="m" disabled={loading} text={loading ? '저장 중...' : '저장'} type="button" onClick={handleSave} />
            </div>
        </PageBox>
    );
};

export default CompanyInfoTab; 