import { jwtDecode, type JwtPayload } from "jwt-decode";
import { useSelector } from "react-redux";

interface MyJwtPayload extends JwtPayload {
  userType: string; // 커스텀 클레임 타입 정의
}

// 토큰에서 ID 획득
const getIdFromToken = (token: string | null): number | null => {
  if (!token) return null;
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
  if (!token) return null;
  try {
    const decodePayload = jwtDecode<MyJwtPayload>(token);
    const userType = String(decodePayload.userType);
    return userType ?? null;
  } catch (error) {
    console.log("토큰으로부터 UserType 획득 실패: ", error);
    return null;
  }
}

const isLogin = (token:string): boolean => {
  if (!token) return false;

  try {
    const decodedPayload = jwtDecode<JwtPayload>(token);
    const now = Math.floor(Date.now() / 1000); // 현재 시간 (초 단위)
    const isExpired = decodedPayload.exp !== undefined && decodedPayload.exp < now;
    return !isExpired;
  } catch (error) {
    console.error("JWT 디코딩 실패:", error);
    return false;
  }

};


export default { getIdFromToken, getUserTypeFromToken, isLogin };