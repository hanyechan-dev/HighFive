interface InfoItemProps {
    label: string
    value?: string | number
  }
  
  export function InfoItem({ label, value }: InfoItemProps) {
    if (!value && value !== 0) return null
  
    return (
      <div className="border border-gray-200 rounded-md p-3">
        <div className="text-gray-500 text-sm mb-1">{label}</div>
        <div className="font-medium">{value}</div>
      </div>
    )
  }
  