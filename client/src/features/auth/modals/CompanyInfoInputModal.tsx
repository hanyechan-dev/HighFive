import { useState, type ChangeEvent } from "react"
import ModalTitle from "../../../common/components/title/ModalTitle.tsx";
import Input from "../../../common/components/input/Input.tsx";
import Button from "../../../common/components/button/Button.tsx";
import TextArea from "../../../common/components/input/TextArea.tsx";
import RadioButton from "../../../common/components/button/RadioButton.tsx";
import { companyTypeEnum } from "../../../common/enum/Enum.tsx";
import ImageOutputArea from "../../../common/components/image/ImageOutputArea.tsx";
import DaumPostcode from 'react-daum-postcode';

interface AddressData {
    roadAddress: string;
}


interface CompanyInfoInputModalProps {
    companyInfoInput: (companyName: string,
        businessNumber: string,
        representativeName: string,
        type: string,
        companyAddress: string,
        companyPhone: string,
        industry: string,
        employeeCount: string,
        establishedDate: string,
        introduction: string,
        logoImageFile: File | null) => Promise<boolean>;
    onClose: () => void;
}

const CompanyInfoInputModal = ({ companyInfoInput, onClose }: CompanyInfoInputModalProps) => {
    const [companyName, setCompanyName] = useState('');
    const [businessNumber, setBusinessNumber] = useState('');
    const [representativeName, setRepresentativeName] = useState('');
    const [companyAddress, setCompanyAddress] = useState('');
    const [companyPhone, setCompanyPhone] = useState('');
    const [industry, setIndustry] = useState('');
    const [employeeCount, setEmployeeCount] = useState('');
    const [establishedDate, setEstablishedDate] = useState('');
    const [introduction, setIntroduction] = useState('');
    const [logoImageFile, setLogoImageFile] = useState<File | null>(null);
    const [logoPreviewUrl, setLogoPreviewUrl] = useState<string | null>(null);
    const [type, setCheckedCompanyType] = useState(companyTypeEnum[0].value)
    const [showAddressModal, setShowAddressModal] = useState(false);

    const handleLogoImageChange = (event: ChangeEvent<HTMLInputElement>) => {
        const selectedFile = event.target.files ? event.target.files[0] : null;

        if (selectedFile) {
            setLogoImageFile(selectedFile);
            setLogoPreviewUrl(URL.createObjectURL(selectedFile));
        } else {
            setLogoImageFile(null);
            setLogoPreviewUrl(null);
        }
    };

    const onClickSaveButton = async () => {
        const result = await companyInfoInput(companyName,
            businessNumber,
            representativeName,
            type,
            companyAddress,
            companyPhone,
            industry,
            employeeCount,
            establishedDate,
            introduction,
            logoImageFile);

        if(result){
            onClose();
        }
        
    }

    const onClickAddressButton = () => {
        if (showAddressModal) {
            setShowAddressModal(false);
        } else {
            setShowAddressModal(true);
        }

    }

    const onCompleteAddress = (data: AddressData) => {
        setCompanyAddress(data.roadAddress);

    }

    const onCloseAddress = (state: string) => {
        if (state === 'FORCE_CLOSE') {
            setShowAddressModal(false);
        } else if (state === 'COMPLETE_CLOSE') {
            setShowAddressModal(false);
        }
    };



    return (
        <>

            <ModalTitle title={'기업 추가정보 입력'} />
            <RadioButton
                name={"회사 유형"}
                textList={companyTypeEnum}
                checkedText={type}
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

            <Button color={"white"} size={"l"} disabled={false} text={"주소검색"} onClick={onClickAddressButton} type={"button"} />

            {showAddressModal && <DaumPostcode className="mb-6" style={{ height: "445px" }} onComplete={onCompleteAddress} onClose={onCloseAddress} />}

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
                <label htmlFor="logo-upload" className="font-roboto text-base mb-2 inline-block ml-[24px]">회사 로고</label>
                <input
                    id="logo-upload"
                    type="file"
                    accept="image/*"
                    onChange={handleLogoImageChange}
                    className="font-roboto block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-lg file:border-0 file:text-sm file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100 mb-6 ml-[24px]"
                />
                {logoPreviewUrl && (
                    <ImageOutputArea size={"m"} imageUrl={logoPreviewUrl} />
                )}
            </div>
            <Button
                color={"theme"}
                size={"l"}
                disabled={false}
                text={"저장"}
                type={"button"}
                onClick={onClickSaveButton} />

        </>
    );

};

export default CompanyInfoInputModal;
