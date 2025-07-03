import { Outlet, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

interface SubscriptionProtectedRouterProps {
    isSubscribe: boolean | null;
}

const SubscriptionProtectedRouter = ({ isSubscribe }: SubscriptionProtectedRouterProps) => {
    const navigate = useNavigate();
    const [checked, setChecked] = useState(false);
    const [shouldRedirect, setShouldRedirect] = useState(false);

    useEffect(() => {
        if (isSubscribe === false && !checked) {
            const result = window.confirm("구독이 필요한 서비스입니다. 구독페이지로 이동하시겠습니까?");
            if (result) {
                navigate("/subscription/plans");
            } else {
                setShouldRedirect(true);
            }
            setChecked(true);
        }
    }, [isSubscribe, checked, navigate]);

    if (isSubscribe === false && shouldRedirect) {
        // 구독 안 한다고 한 경우 화면 차단
        return null;
    }

    if (isSubscribe === false && !checked) {
        // 아직 confirm 중이므로 아무것도 렌더하지 않음
        return null;
    }

    return <Outlet />;
};

export default SubscriptionProtectedRouter;