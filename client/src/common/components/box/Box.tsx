import { Children } from "react";

interface BoxProps {
    children : React.ReactNode;
}

const Box = ({children}:BoxProps) => {
    return (
        <div className="w-[952px] min-h-[100px] rounded-lg border border-gray-300 mb-6 ml-[24px] pt-8">
            {children}
        </div>
    );
}

export default Box