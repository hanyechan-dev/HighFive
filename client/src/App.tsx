
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
    id: 1,
    title: '백엔드 개발자 공개채용 삼성에서 인재를 모십니다 많은 지원 바랍니다.',
    companyName: '삼성전자',
    companyType: '대기업',
    job: '백엔드',
    workLocation: '서울 관악구',
    careerType: '경력 3년차 이상상',
    educationLevel: '전문학사',
    similarityScore: 87,
    createdDate: '2025-05-05'
};

    const userType = ['일반회원', '기업회원', '컨설턴트회원'];
    const [checkedText, setcheckedText] = useState(userType[0]);

    const onClick = (id: number) => {
        console.log(id);
    };


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

            <ContentOfList {...mockData} onClick={onClick}/>



        </>
    )
}

export default App
