

interface ButtonProps {
  color : 'theme' | 'kakao' | 'action';
  size : 's' | 'm' | 'l';
  disabled : boolean;
  text : string;
  onClick : () => void;
  type : 'button' | 'submit';
}

const colorClass = {
  theme: 'bg-theme text-white hover:brightness-90',
  kakao: 'bg-kakao text-black hover:brightness-90',
  action: 'bg-action text-white hover:brightness-95'
};

const disabledColorClass = {
  theme: 'bg-theme text-white hover:none cursor-not-allowed brightness-50',
  kakao: 'bg-kakao text-black hover:none cursor-not-allowed brightness-50',
  action: 'bg-action text-white hover:none cursor-not-allowed brightness-50',
}

const sizeClass = {
  s: 'w-[95px] h-[42px]',
  m: 'w-[125px] h-[42px]',
  l: 'w-[390px] h-[42px]',
};



function Button({
  color,
  size,
  text,
  disabled,
  onClick,
  type,
}: ButtonProps) {

  const colorStyle = disabled ? disabledColorClass[color] : colorClass[color];
  const defaultSetting = 'text-base font-roboto rounded-lg';

  return (
    <button
      className={`${colorStyle} ${sizeClass[size]} ${defaultSetting}`}
      disabled={disabled}
      onClick={onClick}
      type={type}
    >
      {text}
    </button>
  );
}

export default Button;
