import { Link } from "react-router-dom";
import Summary from "../components/Summary";
const Shipping = (props) => {
  return (
    <div>
      <div className="flex px-40 justify-between shadow-sm py-10">
        <div className="text-2xl font-bold text-gray-300">1. Shopping Cart</div>
        <h1 className="text-2xl font-bold ">2. Shipping Details</h1>
        <h1 className="text-2xl font-bold text-gray-300	">3. Payment Options</h1>
      </div>
      <div className="px-40">
        <div className="flex justify-between">
          <div className="flex flex-col">
            <div className=" font-bold my-10 text-xl">Delivery Form</div>

            <label htmlFor="name" className="text-lg font-bold mb-2">
              Name
            </label>
            <input
              type="text"
              id="name"
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
            />

            <label htmlFor="phone" className="text-lg font-bold mb-2">
              Phone Number
            </label>
            <input
              type="tel"
              id="phone"
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
            />

            <label htmlFor="email" className="text-lg font-bold mb-2">
              Email
            </label>
            <input
              type="email"
              id="email"
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
            />

            <label htmlFor="province" className="text-lg font-bold mb-2">
              Province
            </label>
            <input
              type="text"
              id="province"
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
            />

            <label htmlFor="address" className="text-lg font-bold mb-2">
              Address
            </label>
            <input
              type="text"
              id="address"
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
            />

            <label htmlFor="instructions" className="text-lg font-bold mb-2">
              Shipping Instructions
            </label>
            <textarea
              id="instructions"
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
              rows="4"
            ></textarea>
          </div>
          <Summary />
        </div>
        <div className="flex pl-10 mt-10">
          <Link to="/payment">
            <div className="bg-black text-white px-20 py-2 rounded-xl mr-4">
              Continue
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

export default Shipping;
