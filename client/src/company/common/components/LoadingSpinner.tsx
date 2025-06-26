interface LoadingSpinnerProps {
  message?: string;
  size?: 'sm' | 'md' | 'lg';
  color?: 'pink' | 'theme' | 'gray';
}

const LoadingSpinner = ({ 
  message = "로딩 중...", 
  size = 'md',
  color = 'pink'
}: LoadingSpinnerProps) => {
  const sizeClasses = {
    sm: 'h-6 w-6',
    md: 'h-8 w-8',
    lg: 'h-12 w-12'
  };

  const colorClasses = {
    pink: 'border-pink-500',
    theme: 'border-theme',
    gray: 'border-gray-500'
  };

  return (
    <div className="flex justify-center items-center py-12">
      <div className={`animate-spin rounded-full ${sizeClasses[size]} border-b-2 ${colorClasses[color]}`}></div>
      <span className="ml-3 text-gray-600">{message}</span>
    </div>
  );
};

export default LoadingSpinner; 