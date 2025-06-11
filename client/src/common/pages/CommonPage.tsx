interface CommonPageProps {
    children: React.ReactNode
}



const CommonPage = ({children}: CommonPageProps) => {
    return (

        <div className='w-[1500px] border p-6'>
            {children}
        </div>

    );
  };

export default CommonPage;
