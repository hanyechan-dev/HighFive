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


const CommonModal = ({ size, onClose, children}: CommonModalProps) => {
    return (
      <div
        className="fixed inset-0 bg-black/50 z-50 flex items-center justify-center"
        onClick={onClose}
      >
        <div
          className={`bg-white rounded-lg border border-gray-300 ${sizeClass[size]}`}
          onClick={(e) => e.stopPropagation()} // 모달 내부 클릭은 닫힘 방지
        >
          {children}
        </div>
      </div>
    );
  };

export default CommonModal;
