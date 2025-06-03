
import Button from './components/button/Button'
import Input from './components/input/Input'

function App() {


  return (
    <>
      <Input label={''} placeholder={''} size={'s'} disabled={false} type={'text'} value={''} onChange={function (e: React.ChangeEvent<HTMLInputElement>): void {
        throw new Error('Function not implemented.')
      } } />
    </>
  )
}

export default App
