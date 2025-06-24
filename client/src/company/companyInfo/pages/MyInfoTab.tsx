import { useEffect, useState } from 'react';
import Input from '../../../common/components/input/Input';
import { getCompanyInfoApi } from '../apis/CompanyApi';

const MyInfoTab = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [birthDate, setBirthDate] = useState('');
  const [genderType, setGenderType] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');

  useEffect(() => {
    getCompanyInfoApi().then(res => {
      const data = res.data.myPageResponseDto;
      setName(data.name ?? '');
      setEmail(data.email ?? '');
      setBirthDate(data.birthDate ?? '');
      setGenderType(data.genderType ?? '');
      setPhone(data.phone ?? '');
      setAddress(data.address ?? '');
    });
  }, []);

  return (
    <div className="w-full max-w-[500px] mx-auto flex flex-col gap-4">
      <Input label="이름" placeholder="이름" size="m" disabled type="text" value={name} setValue={setName} />
      <Input label="이메일" placeholder="이메일" size="m" disabled type="email" value={email} setValue={setEmail} />
      <Input label="생년월일" placeholder="생년월일" size="m" disabled type="text" value={birthDate} setValue={setBirthDate} />
      <Input label="성별" placeholder="성별" size="m" disabled type="text" value={genderType} setValue={setGenderType} />
      <Input label="전화번호" placeholder="전화번호" size="m" disabled type="text" value={phone} setValue={setPhone} />
      <Input label="주소" placeholder="주소" size="m" disabled type="text" value={address} setValue={setAddress} />
    </div>
  );
};

export default MyInfoTab; 