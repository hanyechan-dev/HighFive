import { useEffect, useRef, useState } from "react";
import CloseIcon from "../icons/CloseIcon";

interface CommonModalProps {
    size: "s" | "m" | "l";
    children: React.ReactNode;
    onClose: () => void;
}

const sizeClass = {
    s: "w-[268px]",
    m: "w-[512px]",
    l: "w-[1000px]",
};

const CommonModal = ({ size, onClose, children }: CommonModalProps) => {
    const containerRef = useRef<HTMLDivElement>(null);
    const [extraWidth, setExtraWidth] = useState(0);
    const [isClosing, setIsClosing] = useState(false);

    useEffect(() => {
        const el = containerRef.current;
        if (!el) return;
        const hasVScroll = el.scrollHeight > 800;
        const scrollbarWidth = el.offsetWidth - el.clientWidth;
        setExtraWidth(hasVScroll ? scrollbarWidth : 0);
    }, [children]);

    const handleClose = () => {
        setIsClosing(true);
        setTimeout(() => {
            onClose();
        }, 300); // popOut 애니메이션 끝난 뒤 onClose 호출
    };

    const raw = sizeClass[size].match(/\d+/)?.[0] ?? "0";

    return (
        <div className="fixed inset-0 bg-black/50 z-50 flex items-center justify-center">
            <div
                ref={containerRef}
                className="max-h-[800px] overflow-y-auto overflow-x-hidden bg-white rounded-lg border border-gray-300"
                style={{
                    width: `${Number(raw) + extraWidth}px`,
                    animation: `${isClosing ? "popOut" : "popIn"} 300ms ease-out forwards`,
                }}
            >
                <div className="flex justify-end">
                    <CloseIcon
                        onClick={handleClose}
                        className="cursor-pointer mt-[10px] mb-0 mr-[10px]"
                    />
                </div>
                {children}
            </div>
        </div>
    );
};

export default CommonModal;