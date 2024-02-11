import './App.css';

import { Route, Routes } from 'react-router-dom';
import ListProduct from './component/ListProduct';

function App() {
  return (
    <div className="App">
       <Routes>
        <Route path='/' Component={ListProduct}/>  
       </Routes>
    </div>
  );
}
export default App;
