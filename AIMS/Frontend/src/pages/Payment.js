import Summary from "../components/Summary";
import { Link, useLocation } from "react-router-dom";
import { useState } from "react";
import { toast } from "react-toastify";
import axios from "axios";
const Payment = () => {
  const { state } = useLocation();

  const { orderId, totalAmount } = state;

  const [url, setUrl] = useState("");

  function handlePayOrder() {
    if (method === "") {
      toast.error("Please select a payment method");
      return;
    }

    axios
      .get(`payment/pay?amount=${totalAmount * 10000}&orderId=${orderId}`)
      .then((response) => {
       setUrl(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  const [method, setMethod] = useState("");

  return (
    <div>
      <div className="flex px-40 justify-between shadow-sm py-10">
        <div className="text-2xl font-bold text-gray-300">1. Shopping Cart</div>
        <h1 className="text-2xl font-bold text-gray-300 ">
          2. Shipping Details
        </h1>
        <h1 className="text-2xl font-bold">3. Payment Options</h1>
      </div>
      <div className="px-40">
        <div className="flex justify-between">
          <div>
            <div className=" font-bold my-10 text-xl">Payment methods</div>
            <div className="flex items-center">
              <input
                type="radio"
                id="vnpay"
                name="paymentMethod"
                value="vnpay"
                onChange={(e) => setMethod(e.target.value)}
              />
              <label htmlFor="vnpay" className="ml-2">
                VnPay
              </label>
            </div>
            <div>{url && <a href={url}>{url}</a>}</div>
            {/* <div className="flex items-center mt-6">
              <input
                type="radio"
                id="creditCard"
                name="paymentMethod"
                value="creditCard"
                onChange={(e) => setMethod(e.target.value)}
              />
              <label htmlFor="creditCard" className="ml-2">
                Credit Card
              </label>
            </div> */}
          </div>
          <Summary />
        </div>

        <div className="flex pl-10 mt-40">
          <button
            onClick={handlePayOrder}
            className="bg-black text-white px-20 py-2 rounded-xl mr-4"
          >
            Place Order
          </button>
          <Link to="/rush-order">
            <div className="bg-black text-white px-20 py-2 rounded-xl mr-4">
              Place Rush Order
            </div>
          </Link>
          <Link to="/">
            <div className="px-20 py-2 rounded-xl border">Cancel all</div>
          </Link>
        </div>
      </div>
    </div>
  );
};
export default Payment;
