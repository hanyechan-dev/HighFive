"use client"

interface TabButtonProps {
  label: string
  isActive: boolean
  onClick: () => void
}

export function TabButton({ label, isActive, onClick }: TabButtonProps) {
  return (
    <button
      onClick={onClick}
      className={`
        px-8 py-2 text-base rounded-lg font-medium transition-colors
        w-[238px]
        ${isActive ? "bg-pink-100 text-pink-500" : "bg-transparent text-gray-400 hover:bg-gray-100"}
      `}
    >
      {label}
    </button>
  )
}
