import "./App.css";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Layout from "./components/Layout";
import Cart from "./pages/Cart";
import Shipping from "./pages/Shipping";
import RushOrder from "./pages/RushOrder";
import Payment from "./pages/Payment";
import Result from "./pages/Result";
import Login from "./pages/Login";
import AdminLogin from "./pages/AdminLogin";
import Admin from "./pages/Admin";
import ProductManager from "./pages/ProductManager";
import OrderManger from "./pages/OrderManager";
import ChangePassword from "./pages/ChangePassword";
import { CartProvider } from "./providers/CartContext";
import { UserProvider } from "./providers/UserContext";
import axios from "axios";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

axios.defaults.baseURL = "http://localhost:8080/api/v1/";

function App() {

  return (
    <div>
      <UserProvider>
      <CartProvider>
        <ToastContainer />
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="cart" element={<Cart />} />
            <Route path="shipping" element={<Shipping />} />
            <Route path="rush-order" element={<RushOrder />} />
            <Route path="payment" element={<Payment />} />
            <Route path="result" element={<Result />} />
            <Route path="login" element={<Login />} />
            <Route path="admin/login" element={<AdminLogin />} />
            <Route path="admin" element={<Admin />} />
            <Route path="product" element={<ProductManager/>} />
            <Route path="order" element={<OrderManger/>} />
            <Route path="change-password" element={<ChangePassword/>} />
          </Route>
        </Routes>
      </CartProvider>
      </UserProvider>
    </div>
  );
}

export default App;
