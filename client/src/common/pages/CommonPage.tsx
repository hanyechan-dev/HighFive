interface CommonPageProps {
    children: React.ReactNode
}



const CommonPage = ({children}: CommonPageProps) => {
    return (

        <div className='w-[1500px] border'>
            {children}
        </div>

    );
  };

export default CommonPage;
