import { api } from "../../common/Axios.tsx";

export const loginApi = (
  email: string,
  password: string

) => {
  return api(false).post('/auth/token', {
    email,
    password
  });
};

export const SignUpApi = (
  email: string,
  password: string,
  name: string,
  birthDate: string,
  genderType: string,
  phone: string,
  address: string,
  type: string

) => {
  return api(false).post('/users', {
    email,
    password,
    name,
    birthDate,
    genderType,
    phone,
    address,
    type
  });
};