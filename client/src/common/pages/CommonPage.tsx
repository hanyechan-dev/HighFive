interface CommonPageProps {
    children: React.ReactNode
}

const CommonPage = ({children}: CommonPageProps) => {
    return (
        <div className='w-[1500px] min-h-[800px] border p-6 overflow-y-auto'>
            {children}
        </div>
    );
};

export default CommonPage;
