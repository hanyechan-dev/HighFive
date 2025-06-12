import TempPage from './chat/TempPage';
import Button from './common/components/button/Button';
import SignUpModal from './features/auth/SignUpModal';

const handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    console.log('버튼이 클릭되었습니다.', e);
};

const App = () => {
    return (
        <div>
            <h1>버튼 예제</h1>
            <Button
                color="theme"
                size="m"
                text="클릭하세요!"
                disabled={false}
                onClick={handleClick}
                type="button"
            />
        </div>
        // <TempPage />
    )
};

export default App;