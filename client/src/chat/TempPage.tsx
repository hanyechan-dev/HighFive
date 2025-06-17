import CommonPage from "../common/pages/CommonPage"
import Chat from "./Chat";
import TempMember from "./TempMember";

const TempPage = () => {
    return(
        <CommonPage>
            <TempMember />
            <Chat />
        </CommonPage>
    )
}

export default TempPage;