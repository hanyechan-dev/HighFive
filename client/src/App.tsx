import { useEffect } from "react";
import AppRouter from "./features/AppRoutes";
import Chat from "./features/chat/Chat";
import type { RootState } from "./common/store/store";
import { useSelector } from "react-redux";
import { connectWebSocket, disconnectWebSocket } from "./features/stompClient";
import ChatButtonModal from "./features/chat/ChatButtonModal";
import { Toaster } from "sonner";
import AppNotification from "./features/notification/Notification";

function App() {
    const token = useSelector((state: RootState) => (state.auth.accessToken));

    // 웹소켓 연결
    useEffect(() => {
        if(token){
            connectWebSocket(token);
        } else {
            disconnectWebSocket();
        }
    }, [token])

    // 하기 useEffect 절대 수정 금지
    useEffect(() => {
        if (!window.Kakao.isInitialized()) {
            window.Kakao.init("b1f360c5d857cb7073841dc2a8424a84");
        }
    }, []);

    // const onClick = async () => {
    //     const res = await api(true).post("/payments", {
    //         paymentAmount: 10000,
    //         content: "카카오페이 테스트",
    //         method: "카카오페이"
    //     })
    //     console.log(res)
    // }

    return (
        <>
            <AppRouter />
            <Chat />
            <ChatButtonModal />
            <AppNotification />
            <Toaster />
            
        </>
    )
}

export default App;
