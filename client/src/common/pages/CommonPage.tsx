interface CommonPageProps {
    children: React.ReactNode
}

const CommonPage = ({children}: CommonPageProps) => {
    return (

            <div className="w-[1500px] mx-auto">
                {children}
            </div>

    );
  };

export default CommonPage;
