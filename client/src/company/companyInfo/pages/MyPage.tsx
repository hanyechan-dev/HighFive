import { useState } from "react";
import CommonPage from "../../../common/pages/CommonPage";
import PageTitle from "../../common/components/PageTitle";
import MyInfoTab from "./MyInfoTab";
import CompanyInfoTab from "./CompanyInfoTab";
import PaymentTab from "./PaymentTab";
import { TabButton } from "../../common/components/TabButton";

const TABS = [
  { label: "내 정보", value: "my" },
  { label: "기업 정보", value: "company" },
  { label: "결제내역", value: "payment" },
];

const MyPage = () => {
  const [selectedTab, setSelectedTab] = useState("my");

  return (
    <CommonPage>
      <div className="font-roboto">
      <PageTitle title="마이페이지" description="내 정보 및 기업 정보를 관리하세요" />
      <div className="flex justify-center mb-8">
        {TABS.map(tab => (
          <TabButton
            key={tab.value}
            label={tab.label}
            isActive={selectedTab === tab.value}
            onClick={() => setSelectedTab(tab.value)}
            variant="sub"
          />
        ))}
      </div>
      <div className="w-full max-w-[1070px] mx-auto px-4">
        <div className="border-b border-gray-200 mb-8" />
        {selectedTab === "my" && (
          <>
            
            <MyInfoTab />
          </>
        )}
        {selectedTab === "company" && (
          <>
           
            <CompanyInfoTab />
          </>
        )}
        {selectedTab === "payment" && (
          <>
            <PaymentTab />
          </>
        )}
      </div>
      </div>
    </CommonPage>
  );
};

export default MyPage; 