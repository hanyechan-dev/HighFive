
import { useState } from "react";
import EmptyState from "../../../../common/components/emptyState/EmptyState";
import CertificationCreate from "../../components/CertificationCreate";
import CertificationInfoForMyPage from "../../components/CertificationInfoForMyPage";
import { useDocumentTabController } from "../../customHooks/DocumentTab/useDocumentTabController";
import Button from "../../../../common/components/button/Button";


const CertificationTab = () => {


    const [isAddCertificationMode, setIsAddCertificationMode] = useState(false);

    const {
        resume
    } = useDocumentTabController();

    const { certificationResponseDtos } = resume


    const onclickAddCertification = () => {
        setIsAddCertificationMode(true);
    }

    return (
        <div>
            {certificationResponseDtos.length > 0 ? certificationResponseDtos.map(certificationResponseDto => (
                <CertificationInfoForMyPage
                    key={certificationResponseDto.id}
                    certificationResponseDto={certificationResponseDto}
                />
            )) : (!isAddCertificationMode && <EmptyState title={"등록된 자격증이 없습니다."} text={"자격증을 등록해주세요."} />)}

            {isAddCertificationMode && <CertificationCreate setIsAddCertificationMode={setIsAddCertificationMode} />}

            <div className="flex justify-end mr-6">
                <Button
                    text="추가"
                    size="s"
                    color="theme"
                    onClick={onclickAddCertification}
                    type="button" disabled={isAddCertificationMode} />
            </div>
        </div>

    )

}

export default CertificationTab;