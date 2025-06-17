interface CommonPageProps {
    children: React.ReactNode
}

const CommonPage = ({children}: CommonPageProps) => {
    return (
        <div className="flex justify-center items-center min-h-screen">
            <div className="w-[1500px]">
                {children}
            </div>
        </div>
    );
  };

export default CommonPage;
