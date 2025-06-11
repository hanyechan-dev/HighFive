import { useState } from "react"
import LoginModal from "./LoginModal";
import SignUpModal from "./SignUpModal";

const AuthModal = () => {
    const [showLoginModal, setShowLoginModal] = useState(true);
    const [showSignUpModal, setShowSignUpModal] = useState(false);
    const [kakaoEmail, setKakaoEmail] = useState("");

    const onClose = () => {
        setShowLoginModal(false);
        setShowSignUpModal(false);
    };

    const onSwitchToSignUp = () => {
        setShowLoginModal(false);
        setShowSignUpModal(true);
    };

    return (
        <>
            {showLoginModal && (
                <LoginModal setKakaoEmail={setKakaoEmail} onClose={onClose} onSwitchToSignUp={onSwitchToSignUp}
                />
            )}

            {showSignUpModal && (
                <SignUpModal kakaoEmail={kakaoEmail} onClose={onClose} />
            )}
        </>
    );
};

export default AuthModal;
