import { jwtDecode, type JwtPayload } from "jwt-decode";

interface MyJwtPayload extends JwtPayload {
  userType: string; // 커스텀 클레임 타입 정의
}

// 토큰에서 ID 획득
const getIdFromToken = (token: string | null): number | null => {
  if(!token) return null;
  try {
    const decodePayload = jwtDecode(token); // 토큰 복호화
    const id = Number(decodePayload.sub); // ID 추출
    return id ?? null;
  } catch (error) {
    console.log("토큰으로부터 ID 획득 실패: ", error);
    return null;
  }
}

// 토큰으로 UserType 획득
const getUserTypeFromToken = (token: string | null): string | null => {
  if(!token) return null;
  try {
    const decodePayload = jwtDecode<MyJwtPayload>(token);
    const userType = String(decodePayload.userType);
    return userType ?? null;
  } catch (error) {
    console.log("토큰으로부터 UserType 획득 실패: ", error);
    return null;
  }
}

export default { getIdFromToken, getUserTypeFromToken };