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
        py-2 text-center rounded-md transition-colors
        ${isActive ? "bg-pink-100 text-pink-600 font-medium" : "bg-gray-100 text-gray-600 hover:bg-gray-200"}
        focus:outline-none focus:ring-2 focus:ring-pink-500 focus:ring-offset-1
      `}
    >
      {label}
    </button>
  )
}
