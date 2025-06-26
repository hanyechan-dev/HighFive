import { useState } from "react";
import CommonPage from "../../../common/pages/CommonPage";
import MyInfoTab from "./MyInfoTab";
import CompanyInfoTab from "./CompanyInfoTab";
import PaymentTab from "./PaymentTab";
import PageTitle from "../../../common/components/title/PageTitle";

const TABS = [
  { label: "내 정보", value: "my" },
  { label: "기업 정보", value: "company" },
  { label: "결제내역", value: "payment" },
];

const MyPage = () => {
  const [selectedTab, setSelectedTab] = useState("my");

  return (
    <CommonPage>
      <PageTitle title="마이페이지" />
      <div className="flex mt-6">
        {/* 사이드바 탭 */}
        <div>
          {TABS.map((tab) => (
            <button
              key={tab.value}
              className={`${
                selectedTab === tab.value 
                  ? 'bg-semi_theme text-theme' 
                  : 'text-black'
              } w-[260px] h-[42px] text-base font-roboto rounded-lg ml-6 flex justify-start px-5 py-[10px]`}
              onClick={() => setSelectedTab(tab.value)}
              type="button"
            >
              {tab.label}
            </button>
          ))}
        </div>

        {/* 메인 콘텐츠 영역 */}
        <div className="flex-1 ml-6">
          {selectedTab === "my" && <MyInfoTab />}
          {selectedTab === "company" && <CompanyInfoTab />}
          {selectedTab === "payment" && <PaymentTab />}
        </div>
      </div>
    </CommonPage>
  );
};

export default MyPage; 