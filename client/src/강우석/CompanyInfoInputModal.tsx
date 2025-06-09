import { useState, type ChangeEvent } from "react"

import { companyInfoInputModalApi } from "./CompanyApi.tsx";
import CommonModal from "../common/modals/CommonModal";
import ModalTitle from "../common/components/title/ModalTitle.tsx";
import Input from "../common/components/input/Input";
import Button from "../common/components/button/Button.tsx";
import TextArea from "../common/components/input/TextArea.tsx";
import RadioButton from "../common/components/button/RadioButton.tsx";
import { companyTypeEnum } from "../common/enum/Enum.tsx";
import ImageOutputArea from "../common/components/ImageOutputArea.tsx";

const CompanyInfoInputModal = () => {
    const [showModal, setShowModal] = useState(true);
    const [companyName, setCompanyName] = useState('');
    const [businessNumber, setBusinessNumber] = useState('');
    const [representativeName, setRepresentativeName] = useState('');
    const [companyAddress, setCompanyAddress] = useState('');
    const [companyPhone, setCompanyPhone] = useState('');
    const [industry, setIndustry] = useState('');
    const [employeeCount, setEmployeeCount] = useState('');
    const [establishedDate, setEstablishedDate] = useState('');
    const [introduction, setIntroduction] = useState('');
    const [logoImage, setLogoImage] = useState<File | null>(null);
    const [logoPreviewUrl, setLogoPreviewUrl] = useState<string | null>(null);
    const [checkedCompanyType, setCheckedCompanyType] = useState(companyTypeEnum[0].value)
    const onClose = () => { setShowModal(false) };

    const handleLogoImageChange = (event: ChangeEvent<HTMLInputElement>) => {
        const selectedFile = event.target.files ? event.target.files[0] : null;

        if (selectedFile) {
            setLogoImage(selectedFile);
            setLogoPreviewUrl(URL.createObjectURL(selectedFile)); 
        } else {
            setLogoImage(null);
            setLogoPreviewUrl(null);
        }
    };
    
    const companyInfoInput = async () => {
        try {
            if (logoImage) {
                await companyInfoInputModalApi(
                    companyName,
                    businessNumber,
                    representativeName,
                    companyAddress,
                    companyPhone,
                    industry,
                    employeeCount,
                    establishedDate,
                    introduction,
                    logoImage
                );
            } else {
                await companyInfoInputModalApi(
                    companyName,
                    businessNumber,
                    representativeName,
                    companyAddress,
                    companyPhone,
                    industry,
                    employeeCount,
                    establishedDate,
                    introduction,
                    // @ts-expect-error: logoImage is required, but passing null for no image
                    null
                );
            }
           
            onClose();

        } catch (err) {
            console.error('입력 실패:', err);
        }
    };

    if (!showModal) return null

    return (

        <CommonModal size="m" onClose={() => { }} >
            <ModalTitle title={'기업 추가정보 입력'} />
            <RadioButton
                name={"회사 유형"}
                textList={companyTypeEnum}
                checkedText={checkedCompanyType}
                setCheckedText={setCheckedCompanyType} />

            <Input
                label={'회사명'}
                placeholder={'회사명을 입력해주세요.'}
                size={'m'}
                disabled={false}
                type={'text'}
                value={companyName}
                setValue={setCompanyName}
            />
            <Input
                label={'사업자 번호'}
                placeholder={'사업자 번호를 입력해주세요.'}
                size={'m'}
                disabled={false}
                type={'text'}
                value={businessNumber}
                setValue={setBusinessNumber}
            />
            <Input
                label={'대표자명'}
                placeholder={'대표자명을 입력해주세요.'}
                size={'m'}
                disabled={false}
                type={'text'}
                value={representativeName}
                setValue={setRepresentativeName}
            />
            <Input
                label={'회사 주소'}
                placeholder={'회사 주소를 입력해주세요.'}
                size={'m'}
                disabled={false}
                type={'text'}
                value={companyAddress}
                setValue={setCompanyAddress}
            />
            <Input
                label={'회사 전화번호'}
                placeholder={'회사 전화번호를 입력해주세요.'}
                size={'m'}
                disabled={false}
                type={'text'}
                value={companyPhone}
                setValue={setCompanyPhone}
            />
            <Input
                label={'산업 분야'}
                placeholder={'산업 분야를 입력해주세요.'}
                size={'m'}
                disabled={false}
                type={'text'}
                value={industry}
                setValue={setIndustry}
            />
            <Input
                label={'직원 수'}
                placeholder={'직원 수를 입력해주세요.'}
                size={'m'}
                disabled={false}
                type={'text'}
                value={employeeCount}
                setValue={setEmployeeCount}
            />
            <Input
                label={'설립일'}
                placeholder={'설립일을 입력해주세요. (YYYY-MM-DD)'}
                size={'m'}
                disabled={false}
                type={'date'}
                value={establishedDate}
                setValue={setEstablishedDate}
            />
            <TextArea
                size={"m"}
                label={"회사소개"}
                placeholder={""}
                disabled={false}
                value={introduction}
                setValue={setIntroduction} />

            <div className="mb-6">
                    <label htmlFor="logo-upload" className="font-roboto text-base mb-2 inline-block ml-[24px]">회사 로고 (선택 사항):</label> 
                    <input
                        id="logo-upload"
                        type="file"
                        accept="image/*"
                        onChange={handleLogoImageChange}
                        className="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-full file:border-0 file:text-sm file:font-semibold file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100 ml-[24px]" 
                    />
                    {logoPreviewUrl && (
                        <ImageOutputArea size={"m"} imageUrl={logoPreviewUrl} />
                        // <div className="mt-4 p-4 border border-gray-200 rounded-md bg-gray-50 ml-[24px] mr-[24px]"> 
                        //     <h4 className="text-md font-semibold mb-2"></h4>
                        //     <img
                        //         src={logoPreviewUrl}
                        //         alt="선택된 로고"
                        //         className="max-w-xs max-h-36 object-contain block mb-4 border border-gray-300 rounded-sm"
                        //     />
                            
                        // </div>
                    )}
                </div>
            <Button
                color={"theme"}
                size={"s"}
                disabled={false}
                text={"저장"}
                type={"button"}
                onClick={() => companyInfoInput()} />

        </ CommonModal>
    );

};

export default CompanyInfoInputModal;
