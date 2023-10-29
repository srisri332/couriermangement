import logo from "./logo.svg";
import "./App.css";
import { Button, ButtonGroup } from "@chakra-ui/react";
import Navbar from "./components/Navbar";
import Login from "./components/Login";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import User from "./components/User";
import Admin from "./components/Admin";

function App() {
  return (
    <div className='App'>
      <BrowserRouter>
        <Routes>
          <Route exact path='/' Component={Login} />
          <Route path='/user' Component={User} />
          <Route path='/admin' Component={Admin} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
