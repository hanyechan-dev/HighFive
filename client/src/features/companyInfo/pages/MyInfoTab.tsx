import { useEffect, useState } from 'react';
import Input from '../../../common/components/input/Input';
import { PageBox } from '../../../common/components/box/Box';
import ModalTitle from '../../../common/components/title/ModalTitle';
import Button from '../../../common/components/button/Button';
import { getCompanyInfoApi } from '../apis/CompanyApi';
import { deactivateAccountApi } from '../../myPageForMember/apis/MyPageForMemberApi';
import { printErrorInfo } from '../../../common/utils/ErrorUtil';
import { store } from '../../../common/store/store';
import AuthUtil from '../../../common/utils/AuthUtil';
import CompanyInfoUpdateModal from '../modals/CompanyUserUpdateModal.tsx';

const MyInfoTab = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [birthDate, setBirthDate] = useState('');
  const [genderType, setGenderType] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    getCompanyInfoApi().then(res => {
      const data = res.data.myPageResponseDto;
      setName(data.name ?? '');
      setEmail(data.email ?? '');
      setBirthDate(data.birthDate ?? '');
      setGenderType(data.genderType ?? '');
      setPhone(data.phone ?? '');
      setAddress(data.address ?? '');
    }).catch(err =>{
        printErrorInfo(err);
    });
  }, []);

  const onClickDeactivateAccountButton = async () => {
    try {
      const accessToken = store.getState().auth.accessToken;

      if (!accessToken) {
        alert("로그인이 필요합니다.");
        return;
      }
      const id = AuthUtil.getIdFromToken(accessToken);
      if (id !== null) {
        await deactivateAccountApi(id);
        alert("회원탈퇴가 완료되었습니다.");
        // 로그아웃 처리나 홈페이지로 리다이렉트
      }
    } catch (err) {
      printErrorInfo(err);
    }
  };

  return (
    <>
      <PageBox>
        <div className="flex gap-3">
          <ModalTitle title="내 정보" />
          <div className="flex gap-[803px]">
            <Button color="action" size="s" disabled={false} text="회원탈퇴" type="button" onClick={onClickDeactivateAccountButton} />
          </div>
        </div>
        <div className="mt-[-12px] ml-6 mb-6 font-roboto text-[#888]">
          내 기본 정보를 확인할 수 있습니다.
        </div>
        <div className="border-b w-[1120px] ml-6 mb-6"></div>
        <div className="flex">
          <Input label="이름" placeholder="" size="pbm" disabled type="text" value={name} setValue={setName} />
          <Input label="이메일" placeholder="" size="pbm" disabled type="email" value={email} setValue={setEmail} />
        </div>
        <div className="flex">
          <Input label="생년월일" placeholder="" size="pbm" disabled type="text" value={birthDate} setValue={setBirthDate} />
          <Input label="성별" placeholder="" size="pbm" disabled type="text" value={genderType} setValue={setGenderType} />
        </div>
        <div className="flex">
          <Input label="전화번호" placeholder="" size="pbm" disabled type="text" value={phone} setValue={setPhone} />
        </div>
        <Input label="주소" placeholder="" size="pbl" disabled type="text" value={address} setValue={setAddress} />
        <div className="flex justify-end mr-6">
          <Button color="theme" size="m" disabled={false} text="회원정보 수정" type="button" onClick={() => setShowModal(true)} />
        </div>
      </PageBox>

      {showModal && <CompanyInfoUpdateModal setShowModal={setShowModal} />}
    </>
  );
};

export default MyInfoTab; 