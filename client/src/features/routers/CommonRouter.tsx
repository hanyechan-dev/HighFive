import { Navigate, Route, Routes } from "react-router-dom";
import Layout from "../layout/Layout";
import PostPage from "../post/pages/PostPage";
import MainPage from "../../common/pages/MainPage";

interface CommonRouterProps {
    userType: string
}

const CommonRouter = ({ userType }: CommonRouterProps) => {
    return (
        <Routes>
            <Route element={<Layout userType={userType} />}>
                <Route path="/" element={<MainPage />} />
                <Route
                    path="/community"
                    element={<PostPage />}
                />
            </Route>
        </Routes>
    );
};

export default CommonRouter;