import Summary from "../components/Summary";
import { Link } from "react-router-dom";
const Payment = () => {
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
              />
              <label htmlFor="vnpay" className="ml-2">
                VnPay
              </label>
            </div>
            <div className="flex items-center mt-6">
              <input
                type="radio"
                id="creditCard"
                name="paymentMethod"
                value="creditCard"
              />
              <label htmlFor="creditCard" className="ml-2">
                Credit Card
              </label>
            </div>
          </div>
          <Summary />
        </div>

        <div className="flex pl-10 mt-40">
          <Link to="/result">
            <div className="bg-black text-white px-20 py-2 rounded-xl mr-4">
              Place Order
            </div>
          </Link>
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
