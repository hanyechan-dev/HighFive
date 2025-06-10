interface AdminCardProps {
  icon: React.ReactNode;
  label: string;
}

const AdminCard = ({
  icon,
  label
}: AdminCardProps) => {
  return (
    <div className="flex flex-col items-center">

    <div className="rounded-xl border border-gray-300 p-6 w-[400px] h-[370px] flex flex-col items-center justify-center 
  hover:border-theme hover:border-2 hover:scale-110 transition-all duration-200 cursor-pointer text-base font-roboto">
  

        {icon}
        <div className="text-xl font-semibold text-gray-700">{label}</div>
      </div>
    </div>
  );
};

export default AdminCard;
