import logo from "./logo.svg";
import "./App.css";
import Login from "./components/login_form";
import Register from "./components/register_form";
import { BrowserRouter, Route, Routes, useNavigate } from "react-router-dom";
import {
  Button,
  Col,
  Container,
  Form,
  FormControl,
  ListGroup,
  ListGroupItem,
  Nav,
  Navbar,
  NavDropdown,
  Row,
} from "react-bootstrap";
import { Link } from "react-router-dom";

import Home from "./components/Home";
import { ToastContainer } from "react-toastify";
import AddProduct from "./components/product/AddProduct";
import ModifyProduct from "./components/product/ModifyProduct";
import AllProducts from "./components/product/AllProducts";
import Menu from "./components/Menu";
import NaviMenu from "./components/NaviMenu";
import Cart from "./components/Cart";
import Order from "./components/order";
import Paynow from "./components/payment/paynow";
import Paysuccess from "./components/payment/paysuccess";

function App() {

  const value = localStorage.getItem("role")
  console.log(value)


  return (
    // <div>
    <BrowserRouter>
      <NaviMenu />
      
      <div>
        
            {" "}
            <Routes>
              <Route path="/register" element={<Register />} exact></Route>
              <Route path="/login" element={<Login />} exact></Route>
              <Route path="/addproduct" element={<AddProduct />} exact></Route>
              <Route path="/" element={<Home />} exact></Route>
              <Route path="/mycart" element={<Cart />} exact></Route>
              <Route path="/order" element={<Order />} exact></Route>
              <Route path="/pay" element={<Paynow />} exact></Route>
              <Route path="/payment/success" element={<Paysuccess />} exact></Route>
              <Route
                path="/updateproduct"
                element={<ModifyProduct update="1" />}
                exact
              ></Route>
              <Route
                path="/allproducts"
                element={<AllProducts />}
                exact
              ></Route>
            </Routes>
          </div>
        
      
    </BrowserRouter>

    // </div>
  );
}

export default App