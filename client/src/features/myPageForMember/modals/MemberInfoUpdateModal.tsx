import ModalTitle from "../../../common/components/title/ModalTitle";
import CommonModal from "../../../common/modals/CommonModal"
import RadioButton from "../../../common/components/button/RadioButton";
import MyPageBasicInfoUpdate from "../components/MyPageBasicInfoUpdate";
import NicknameUpdate from "../components/NicknameUpdate";
import PasswordUpdate from "../components/PasswordUpdate";
import { useMemberInfoTabController } from "../customHooks/MemberInfoTab/useMemberInfoTabController";
import { useMemberInfoTabApi } from "../customHooks/MemberInfoTab/useMemberInfoTabApi";


const memberInfoUpdateTextList = [
    { label: "기본정보", value: "기본정보" },
    { label: "닉네임", value: "닉네임" },
    { label: "비밀번호", value: "비밀번호" }
]



const MemberInfoUpdateModal = () => {




    const {
        showModalType,
        setShowModalType,
        setShowModal,
    } = useMemberInfoTabController();

    const {updateMyPage, updateNickName, updatePassword} = useMemberInfoTabApi();

    const onClose = () => {
        setShowModal(false);
    }





    return (
        <CommonModal size="m" onClose={onClose}>
            <ModalTitle title={`${showModalType} 수정`} />
            <RadioButton name={""} textList={memberInfoUpdateTextList} checkedText={showModalType} setCheckedText={setShowModalType} />
            {showModalType === "기본정보" && <MyPageBasicInfoUpdate setShowModal={setShowModal} onUpdate={updateMyPage} />}
            {showModalType === "닉네임" && <NicknameUpdate setShowModal={setShowModal} onUpdate={updateNickName} />}
            {showModalType === "비밀번호" && <PasswordUpdate setShowModal={setShowModal} onUpdate={updatePassword} />}

        </CommonModal>
    )
}

export default MemberInfoUpdateModal
