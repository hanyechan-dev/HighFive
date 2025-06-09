import CloseIcon from "../icons/CloseIcon";

interface CommonModalProps {
    size: 's' | 'm' | 'l';
    children: React.ReactNode
    onClose: () => void;
}

const sizeClass = {
    s: 'w-[268px]',
    m: 'w-[512px]',
    l: 'w-[1000px]'
};


const CommonModal = ({ size, onClose, children }: CommonModalProps) => {

    const raw = sizeClass[size].match(/\d+/)?.[0] ?? "0";
    const ml = parseInt(raw, 10) - 30;
    return (
        <div
            className="fixed inset-0 bg-black/50 z-50 flex items-center justify-center"
        >
            <div
                className={`bg-white rounded-lg border border-gray-300 ${sizeClass[size]}`}
            >
                <CloseIcon onClick={onClose} className='cursor-pointer mt-[10px] mb-0' style={{ marginLeft: ml }}/>
                {children}
            </div>
        </div>
    );
};

export default CommonModal;
