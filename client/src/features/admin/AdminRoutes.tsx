import { Route } from "react-router-dom";
import AdminMainPage from "./AdminMainPage";

const AdminRoutes = () => {
  <>
    <Route path="/admin" element={<AdminMainPage />} />
  </>
}

export default AdminRoutes;