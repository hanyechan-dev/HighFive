
import Button from './components/button/Button'
import RadioButton from './components/button/RadioButton';
import Input from './components/input/Input'
import { useState } from 'react'
import ModalTitle from './components/title/ModalTitle';
import TextArea from './components/input/TextArea';
import Checkbox from './components/checkBox/CheckBox';
import ContentOfList from './components/list/ContentOfList';

function App() {

    const [value, setValue] = useState('');
    const [value1, setValue1] = useState('');
    const [value2, setValue2] = useState('');
    const [checked, setChecked] = useState(false);

    const mockData = {
        id : 1,
        기업명 : '삼성전자',
        기업형태 : '대기업',
        공고명 : '백엔드 개발자 공개채용',
        모집부문 : '백엔드',
        근무지 : '서울',
        경력 : '신입',
        학력 : '대졸',
        등록일 : '2025-05-05'};


    const userType = ['일반회원', '기업회원', '컨설턴트회원'];
    const [checkedText, setcheckedText] = useState(userType[0]);


    return (
        <>
            <ModalTitle title={'회원 가입'} />
            
            <RadioButton name={'회원유형'} textList={userType} checkedText={checkedText} setCheckedText={setcheckedText} />
            <Input label={'이메일'} placeholder={'example@gmail.com'} size={'m'} disabled={false} type={'text'} value={value} setValue={setValue} />
            <Input label={'비밀번호'} placeholder={'비밀번호'} size={'m'} disabled={false} type={'password'} value={value1} setValue={setValue1} />

            <TextArea label={'내용'} placeholder={'공고 내용'} disabled={false} value={value2} setValue={setValue2}/>


            <Button color={'theme'} size={'l'} disabled={false} text={'버튼'} onClick={function (): void {
                throw new Error('Function not implemented.');
            }} type={'button'} />

            <Checkbox checked={checked} setChecked={setChecked} />

            <ContentOfList {...mockData}/>
            <ContentOfList id={0} />
            <ContentOfList id={0} />
            <ContentOfList id={0} />


        </>
    )
}

export default App
