import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal";
import RadioButton from "../../../common/components/button/RadioButton";
import MyPageBasicInfoUpdate from "../../myPageForMember/components/MyPageBasicInfoUpdate";
import PasswordUpdate from "../../myPageForMember/components/PasswordUpdate";
import { updateMyPageApi, updatePasswordApi } from "../../myPageForMember/apis/MyPageForMemberApi";
import type { MyPageUpdateDto, PasswordUpdateDto } from "../../myPageForMember/props/myPageForMemberProps";
import { printErrorInfo } from "../../../common/utils/ErrorUtil";
import { useState } from "react";

const companyInfoUpdateTextList = [
    { label: "기본정보", value: "기본정보" },
    { label: "비밀번호", value: "비밀번호" }
];

interface CompanyInfoUpdateModalProps {
    setShowModal: (showModal: boolean) => void;
}

const CompanyInfoUpdateModal = ({ setShowModal }: CompanyInfoUpdateModalProps) => {
    const [showModalType, setShowModalType] = useState("기본정보");

    const onClose = () => {
        setShowModal(false);
    };

    const updateMyPage = async (myPageUpdateDto: MyPageUpdateDto) => {
        try {
            await updateMyPageApi(myPageUpdateDto);
            alert("기본정보가 수정되었습니다.");
        } catch (err) {
            printErrorInfo(err);
        }
    };

    const updatePassword = async (passwordUpdateDto: PasswordUpdateDto) => {
        try {
            await updatePasswordApi(passwordUpdateDto);
            alert("비밀번호가 수정되었습니다.");
        } catch (err) {
            printErrorInfo(err);
        }
    };

    return (
        <CommonModal size="m" onClose={onClose}>
            <ModalTitle title={`${showModalType} 수정`} />
            <RadioButton 
                name={""} 
                textList={companyInfoUpdateTextList} 
                checkedText={showModalType} 
                setCheckedText={setShowModalType} 
            />
            {showModalType === "기본정보" && (
                <MyPageBasicInfoUpdate 
                    setShowModal={setShowModal} 
                    onUpdate={updateMyPage} 
                />
            )}
            {showModalType === "비밀번호" && (
                <PasswordUpdate 
                    setShowModal={setShowModal} 
                    onUpdate={updatePassword} 
                />
            )}
        </CommonModal>
    );
};

export default CompanyInfoUpdateModal; 