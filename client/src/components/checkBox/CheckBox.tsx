interface CheckboxProps {
    checked: boolean;
    setChecked: (value: boolean) => void
}

function Checkbox({
    checked,
    setChecked }
    : CheckboxProps) {

    const defaultSetting ="w-5 h-5 appearance-none rounded border border-theme checked:bg-theme"

  return (
    <input
      className={defaultSetting}
      type="checkbox"
      checked={checked}
      onChange={(e) => setChecked(e.target.checked)}
    />
  );
}

export default Checkbox;