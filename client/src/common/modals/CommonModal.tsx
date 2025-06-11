import { useEffect, useRef, useState } from "react";
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

    const containerRef = useRef<HTMLDivElement>(null);
    const [extraWidth, setExtraWidth] = useState(0);

    useEffect(() => {
        const el = containerRef.current;
        if (!el) return;
        // 스크롤이 필요한지, 스크롤바 너비
        const hasVScroll = el.scrollHeight > el.clientHeight;
        if (hasVScroll) {
            const scrollbarWidth = el.offsetWidth - el.clientWidth;
            setExtraWidth(scrollbarWidth);
        }
    }, [children]);

    const raw = sizeClass[size].match(/\d+/)?.[0] ?? "0";

    return (
        <div
            className="fixed inset-0 bg-black/50 z-50 flex items-center justify-center"
        >
            <div
                className={`max-h-[800px] overflow-y-auto overflow-x-hidden bg-white rounded-lg border border-gray-300`}
                style={{ width: `${Number(raw) + extraWidth}px` }}
                ref={containerRef}
            >
                <div className="flex justify-end">
                <CloseIcon onClick={onClose} className='cursor-pointer mt-[10px] mb-0 mr-[10px]' />
                </div>
                {children}
            </div>
        </div>
    );
};

export default CommonModal;
