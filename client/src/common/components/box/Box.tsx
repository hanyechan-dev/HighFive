interface BoxProps {
    children: React.ReactNode;
    color?: 'semi_theme'
}

export const ExternalBox = ({ children, color }: BoxProps) => {



    return (
        <div className={` ${color == 'semi_theme' ? 'bg-semi_theme' : ''} w-[952px] min-h-[100px] rounded-lg border border-gray-300 mb-6 ml-[24px] pt-[24px]`}>
            {children}
        </div>
    );
}

export const InternalBox = ({ children, color }: BoxProps) => {
    return (
        <div className={` ${color == 'semi_theme' ? 'bg-semi_theme' : ''}w-[904px] min-h-[100px] rounded-lg border border-gray-300 mb-6 ml-[24px] pt-[24px]`}>
            {children}
        </div>
    );
}

