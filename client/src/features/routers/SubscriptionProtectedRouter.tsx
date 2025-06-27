import { Outlet, useNavigate } from "react-router-dom";

interface SubscriptionProtectedRouterProps{
    isSubscribe : boolean | null
}

const SubscriptionProtectedRouter = ({isSubscribe }: SubscriptionProtectedRouterProps) => {
    const navigate = useNavigate();

    if (!isSubscribe) {
        const result = window.confirm("구독이 필요한 서비스입니다. 구독페이지로 이동하시겠습니까?");
        if (result) {
            navigate("/subscription/plans");
        }
        else {
            return null;
        }
    }

    return <Outlet />;

};

export default SubscriptionProtectedRouter;
