import { useState } from "react";

import { useDocumentTabController } from "../../customHooks/DocumentTab/useDocumentTabController";

import EmptyState from "../../../../common/components/emptyState/EmptyState";
import LanguageTestCreate from "../../components/LanguageTestCreate";
import LanguageTestInfoForMyPage from "../../components/LanguageTestInfoForMyPage";
import Button from "../../../../common/components/button/Button";

const LanguageTestTab = () => {

    
    const [isAddLanguageTestMode, setIsAddLanguageTestMode] = useState(false);

    const {
        resume
    } = useDocumentTabController();

    const { languageTestResponseDtos } = resume

    
    const onclickAddLanguageTest = () => {
        setIsAddLanguageTestMode(true);
    }

    return (
        <div>
            {languageTestResponseDtos.length > 0 ? languageTestResponseDtos.map(languageTestResponseDto => (
                <LanguageTestInfoForMyPage
                    key={languageTestResponseDto.id}
                    languageTestResponseDto={languageTestResponseDto}
                />
            )) : (!isAddLanguageTestMode && <EmptyState title={"등록된 어학 시험이 없습니다."} text={"어학 시험을 등록해주세요."} />)}

            {isAddLanguageTestMode && <LanguageTestCreate setIsLanguageTestMode={setIsAddLanguageTestMode} />}

            <div className="flex justify-end mr-6">
                <Button
                    text="추가"
                    size="s"
                    color="theme"
                    onClick={onclickAddLanguageTest}
                    type="button" disabled={isAddLanguageTestMode} />
            </div>
        </div>

    )

}

export default LanguageTestTab;