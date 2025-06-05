import type { ReactNode } from "react";

interface ModalTitleProps {
    icon?: ReactNode;
    title: string;
}

const defaultSetting = 'font-semibold font-roboto text-2xl';

const ModalTitle = ({
    icon,
    title
}: ModalTitleProps) => {



    return (
        <>
            <div className="flex items-center  mb-5">
                {icon && <span className="mr-2">{icon}</span>}
                <span className={defaultSetting}>{title}</span>
            </div>
        </>
    );
}


export default ModalTitle;