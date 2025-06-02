import { Route } from 'react-router-dom';
import './App.css';
import Chatting2 from './Chatting2';

function App() {
  
  return (
    <>
      <div className="boxA">
        <div className="box1"><Chatting2 /></div>
        <div className="box2"><Chatting2 /></div>        
      </div>
    </>
  );
}

export default App;