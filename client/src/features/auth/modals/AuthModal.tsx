import LoginModal from "./LoginModal";
import SignUpModal from "./SignUpModal";
import { useAuthController } from "../customHooks/useAuthController";
import { useAuthApi } from "../customHooks/useAuthApi";
import NicknameInputModal from "./NicknameInputModal";
import CommonModal from "../../../common/modals/CommonModal";
import CompanyInfoInputModal from "./CompanyInfoInputModal";


interface AuthModalProps {
    onClose: () => void;
}

const AuthModal = ({ onClose }: AuthModalProps) => {

    const { showModalType, setShowModalType, kakaoEmail, isKakao } = useAuthController();

    const { login, kakaoLogin, nicknameInput, signUp, companyInfoInput } = useAuthApi();

    const onCloseModal = () => {
        setShowModalType("logIn");
        onClose();
    }



    return (
            <CommonModal size={"m"} onClose={onCloseModal}>
                {showModalType === "logIn" && (
                    <LoginModal setShowModalType={setShowModalType} login={login} kakaoLogin={kakaoLogin} onClose={onClose} />
                )}
                {showModalType === "signUp" && (
                    <SignUpModal isKakao={isKakao} kakaoEmail={kakaoEmail} setShowModalType={setShowModalType} signUp={signUp} onClose={onCloseModal} />
                )}
                {showModalType === "nicknameInput" && (
                    <NicknameInputModal nicknameInput={nicknameInput} onClose={onCloseModal} />
                )}
                {showModalType === "companyInfoInput" && (
                    <CompanyInfoInputModal companyInfoInput={companyInfoInput} onClose={onCloseModal} />
            )}
            </CommonModal>
    );
};

export default AuthModal;
