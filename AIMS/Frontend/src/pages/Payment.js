import Summary from "../components/Summary";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";
import { toast } from "react-toastify";
import axios from "axios";
const Payment = () => {
  const { state } = useLocation();
  const navigate = useNavigate();
  const { orderId, totalAmount, formData } = state;
  const [url, setUrl] = useState("");

  function handlePayOrder() {
    if (method === "") {
      toast.error("Please select a payment method");
      return;
    }

    axios
      .get(`payment/pay?amount=${totalAmount}&orderId=${orderId}`)
      .then((response) => {
        setUrl(response.data.data);
      })
      .catch((error) => {
        console.log(error.data);
      });
  }

  const [method, setMethod] = useState("");

  function handleRushOrder(e) {
    e.preventDefault();
    for (const key in formData) {
      if (formData[key] === "") {
        toast.error(
          `${key.charAt(0).toUpperCase() + key.slice(1)} is required`
        );
        return;
      }
    }

    if (formData.province !== "Hà Nội") {
      toast.error("Rush delivery is only available in Hanoi");
      return;
    } else {
    }
    navigate("/rush-order", { state: { formData: formData } });
  }

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

            {url ? (
              <a
                className="border px-4 py-2 mt-10 bg-gray-200 rounded-xl"
                href={url}
                target="_blank"
                rel="noreferrer"
              >
                Click here to pay
              </a>
            ) : (
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
            )}
          </div>
          <Summary />
        </div>

        <div className="flex pl-10 mt-40">
          <button
            onClick={handlePayOrder}
            className="bg-black text-white px-20 py-2 rounded-xl mr-4"
          >
            Pay order
          </button>
          <button
            onClick={handleRushOrder}
            className="bg-black text-white px-20 py-2 rounded-xl mr-4"
          >
            Place Rush Order
          </button>
          <Link to="/">
            <div className="px-20 py-2 rounded-xl border">Cancel all</div>
          </Link>
        </div>
      </div>
    </div>
  );
};
export default Payment;
