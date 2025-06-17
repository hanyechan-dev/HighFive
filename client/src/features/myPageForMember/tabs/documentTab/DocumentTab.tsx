
import ResumeTab from "./ResumeTab";
import CareerDescriptionTab from "./CareerDescriptionTab";
import CoverLetterTab from "./CoverLetterTab";
import { useDocumentTabApi } from "../../customHooks/DocumentTab/useDocumentTabApi";
import { BigExternalBox, PageBox } from "../../../../common/components/box/Box";
import RadioButton from "../../../../common/components/button/RadioButton";
import ModalTitle from "../../../../common/components/title/ModalTitle";
import { useDocumentTabController } from "../../customHooks/DocumentTab/useDocumentTabController";

const documentTextList = [
    { label: "이력서", value: "이력서" },
    { label: "경력기술서", value: "경력기술서" },
    { label: "자기소개서", value: "자기소개서" }
]


const DocumentTab = () => {



    const {
        showDocumentTab,
        setShowDocumentTab,
    } = useDocumentTabController();

    const {
        readResume,
        readCareerDescriptionSummaryDtos,
        readCoverLetterSummaryDtos,
    } = useDocumentTabApi();

    const onClickSetChekedText = (text: string) => {
        switch (text) {
            case "이력서":
                readResume();
                break;
            case "경력기술서":
                readCareerDescriptionSummaryDtos();
                break;
            case "자기소개서":
                readCoverLetterSummaryDtos();
                break;
        }
        setShowDocumentTab(text);
    }


    return (
        <>
            <PageBox >
                <ModalTitle title={showDocumentTab} />
                <div className="mt-[-12px] ml-6 mb-6 font-roboto text-[#888]">
                    회원님의 이력서, 경력기술서, 자기소개서를 확인하고 관리할 수 있습니다.
                </div>
                <div className="border-b w-[1120px] ml-6 mb-6"></div>
                <RadioButton name={""} textList={documentTextList} checkedText={showDocumentTab} setCheckedText={onClickSetChekedText} size="bigExternalDocument" />
                <BigExternalBox>
                    {showDocumentTab === "이력서" && <ResumeTab />}
                    {showDocumentTab === "경력기술서" && <CareerDescriptionTab />}
                    {showDocumentTab === "자기소개서" && <CoverLetterTab />}





                </ BigExternalBox>

            </PageBox>
        </>
    )
};

export default DocumentTab;