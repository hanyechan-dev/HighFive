import type { ReactNode } from "react";

interface ModalTitleProps {
    icon?: ReactNode;
    title: string;
}

const defaultSetting = 'font-semibold font-roboto text-2xl m-[24px]';

const ModalTitle = ({
    icon,
    title
}: ModalTitleProps) => {



    return (
        <>
            <div className="flex items-center">
                {icon && <span className="mr-2">{icon}</span>}
                <span className={defaultSetting}>{title}</span>
            </div>
        </>
    );
}


export default ModalTitle;