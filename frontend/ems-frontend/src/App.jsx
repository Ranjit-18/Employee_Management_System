import './App.css'
import EmployeeComponent from './assets/components/EmployeeComponent'
import FooterComponent from './assets/components/FooterComponent'
import HeaderComponent from './assets/components/HeaderComponent'
import ListEmployeeComponent from './assets/components/ListEmployeeComponent'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
function App() {

  return (
    <>
    <div className="app-container">
      <BrowserRouter>
        <HeaderComponent/>
        <div className="content">
          <Routes>
            {/* //http://localhost:3000 */}
            <Route path="/" element ={<ListEmployeeComponent />}></Route>
            {/* //http://localhost:3000/employees */}
            <Route path="/employees" element ={<ListEmployeeComponent />}></Route>

            {/* //http://localhost:3000/add-employee */}
            <Route path="/add-employee" element ={<EmployeeComponent />}></Route>
            
            {/* //http://localhost:3000/edit-employee/1 */}
            <Route path="/edit-employee/:id" element ={<EmployeeComponent />}></Route>
          </Routes>
        </div>
        <FooterComponent />
      </BrowserRouter>
      </div>
    </>
    
  )
}

export default App
