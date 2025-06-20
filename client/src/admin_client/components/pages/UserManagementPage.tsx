import { useEffect, useState } from "react";
import RadioButton from "../../../common/components/button/RadioButton";
import ModalTitle from "../../../common/components/title/ModalTitle";
import { userTypeEnum } from "../../../common/enum/Enum";
import { companyPageClick, companysApproveButtonClick, consultantPageClick, consultantsApproveButtonClick, memberPageClick } from "../features/UserPageClick";
import Pagination from "../../../common/components/pagination/Pagination";
import { usePagination } from "../../../common/coustomHooks/usePagination";
import CommonPage from "../../../common/pages/CommonPage";
import UserApprovalPage from "./UserApprovalPage";

interface UserInfoProps {
    id: number;
    email: string;
    name: string;
    phone: string;
    address: string;
    createdDate: string;
}

interface MemberInfoProps {
    userInfo: UserInfoProps;
    nickName: string;
}

interface companyInfoProps {
    userInfo: UserInfoProps;
}

type PageMode = '회원 조회' | '가입 승인';

const elementsPerPage = 10;
const pagesPerBlock = 10;

const UserManagementPage = () => {
    const [userType, setUserType] = useState(userTypeEnum[0].value);
    const [pageMode, setPageMode] = useState<PageMode>("회원 조회");
    const [members, setMembers] = useState([]);
    const [totalElements, setTotalElements] = useState<number>(1);

    const managementOptions: { label: string; value: PageMode }[] = [
        { label: "회원 조회", value: "회원 조회" },
        { label: "가입 승인", value: "가입 승인" }
    ];

    const {
        clickedPage,
        pageBlockIndex,
        lastPage,
        lastPageBlockIndex,
        onClickFirst,
        onClickPrev,
        onClickNext,
        onClickLast,
        setClickedPage,
    } = usePagination({
        totalElements: totalElements,
        elementsPerPage: elementsPerPage,
        pagesPerBlock: pagesPerBlock,
    });

    useEffect(() => {

        const fetchData = async () => {

            if (pageMode === '회원 조회') {
                if (userType === '일반회원') {
                    try {
                        const res = await memberPageClick(clickedPage, elementsPerPage);
                        setMembers(res.data.content);
                        setTotalElements(res.data.totalElements);
                    } catch (err) {
                        console.error(err);
                    }

                }
                else if (userType === '기업회원') {
                    try {
                        const res = await companyPageClick(clickedPage, elementsPerPage);
                        setMembers(res.data.content);
                        setTotalElements(res.data.totalElements);
                    } catch (err) {
                        console.error(err);
                    }
                }
                else if (userType === '컨설턴트회원') {
                    try {
                        const res = await consultantPageClick(clickedPage, elementsPerPage);
                        setMembers(res.data.content);
                        setTotalElements(res.data.totalElements);
                    } catch (err) {
                        console.error(err);
                    }
                }

            }
            if (pageMode === '가입 승인') {
                if (userType === '기업회원') {
                    try {
                        const res = await companysApproveButtonClick(clickedPage, elementsPerPage);
                        setMembers(res.data.content);
                        setTotalElements(res.data.totalElements);
                    } catch (err) {
                        console.error(err);
                    }
                }
            }
            else if(userType === '컨설턴트회원'){
                try{
                    const res = await consultantsApproveButtonClick(clickedPage, elementsPerPage);
                    setMembers(res.data.content);
                    setTotalElements(res.data.totalElements);
                } catch (err) {
                    console.error(err);
                }
            }
        }
        fetchData();

    }, [pageMode, userType, clickedPage]);

    return (

        <CommonPage >
            <div className="bg-white min-h-screen">
                <div className="text-theme mt-2 font-roboto">
                    <ModalTitle title="회원 관리" />
                </div>

                <div className="mb-[1px] ">
                    <RadioButton
                        name=""
                        textList={managementOptions}
                        checkedText={pageMode}
                        setCheckedText={setPageMode as unknown as (value: string) => void}
                    />
                </div>

                {pageMode === "회원 조회" && (
                    <div className="mb-[1px]">
                        <RadioButton
                            name=""
                            textList={userTypeEnum}
                            checkedText={userType}
                            setCheckedText={setUserType}
                        />
                    </div>
                )}

                {pageMode === "가입 승인" && (
                    <UserApprovalPage />
                )}
                <div className="flex justify-center mt-6">

                    <Pagination currentPageBlockIndex={pageBlockIndex}
                        lastPageBlockIndex={lastPageBlockIndex}
                        pagesPerBlock={pagesPerBlock}
                        lastPage={lastPage}
                        clickedPage={clickedPage}
                        onClickFirst={onClickFirst}
                        onClickPrev={onClickPrev}
                        onClickNext={onClickNext}
                        onClickLast={onClickLast}
                        onClickPage={setClickedPage} />
                </div>
            </div>
        </CommonPage>
    );
};

export default UserManagementPage;