/**
 * 생년월일로부터 나이를 계산하는 함수
 * @param birthDate - 생년월일 (YYYY-MM-DD 형식)
 * @returns 나이 (number)
 */
export function getAge(birthDate: string): number {
  const today = new Date();
  const birth = new Date(birthDate);
  let age = today.getFullYear() - birth.getFullYear();
  const m = today.getMonth() - birth.getMonth();
  if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) {
    age--;
  }
  return age;
} 