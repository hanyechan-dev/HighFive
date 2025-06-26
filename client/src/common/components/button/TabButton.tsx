"use client"

interface TabButtonProps {
  label: string
  isActive: boolean
  onClick: () => void
  variant?: 'main' | 'sub';
}

export function TabButton({ label, isActive, onClick, variant = 'sub' }: TabButtonProps) {
  const styles = {
    main: "px-6 py-3 text-lg font-semibold w-[317px] font-roboto",
    sub: "px-8 py-2 text-base rounded-lg font-medium w-[238px]  font-roboto",
  };
  
  return (
    <button
      onClick={onClick}
      className={`
        transition-colors font-roboto
        ${styles[variant]}
        ${isActive ? "bg-pink-100 text-pink-500" : "bg-transparent text-gray-400 hover:bg-gray-100"}
      `}
    >
      {label}
    </button>
  )
} 