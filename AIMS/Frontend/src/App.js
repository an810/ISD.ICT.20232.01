import "./App.css";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Layout from "./components/Layout";
import Cart from "./pages/Cart";
import Shipping from "./pages/Shipping";
import RushOrder from "./pages/RushOrder";
import Payment from "./pages/Payment";
import Result from "./pages/Result";
import { CartProvider } from "./providers/CartContext";
import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/api/v1/";

function App() {

  return (
    <div>
      <CartProvider>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="cart" element={<Cart />} />
            <Route path="shipping" element={<Shipping />} />
            <Route path="rush-order" element={<RushOrder />} />
            <Route path="payment" element={<Payment />} />
            <Route path="result" element={<Result />} />
          </Route>
        </Routes>
      </CartProvider>
    </div>
  );
}

export default App;
