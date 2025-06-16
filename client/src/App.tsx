import { BrowserRouter } from 'react-router-dom';
import TempPage from './chat/TempPage';
import Button from './common/components/button/Button';
import AdminMainPage from './features/admin/AdminMainPage';
import SignUpModal from './features/auth/SignUpModal';

const App = () => {
    return (
        // <TempPage />
    <BrowserRouter>
        <AdminMainPage />
    </BrowserRouter>
    )
};

export default App;