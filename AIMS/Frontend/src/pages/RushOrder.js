import Summary from "../components/Summary";
import { Link } from "react-router-dom";
const RushOrder = () => {
  return (
    <div>
      <div className="flex px-40 justify-between shadow-sm py-10">
        <div className="text-2xl font-bold text-gray-300">1. Shopping Cart</div>
        <h1 className="text-2xl font-bold ">2. Shipping Details</h1>
        <h1 className="text-2xl font-bold text-gray-300	">3. Payment Options</h1>
      </div>

      <div className="px-40">
        <div className="flex justify-between">
          <div>
            <div className=" font-bold my-10 text-xl">Rush Delivery Form</div>

            <div>
              <div className="text-lg font-bold mb-2">Time</div>
              <div className="flex">
                <input className="border rounded px-4" type="date" />
                <div className="mx-4">
                  to
                </div>
                <input className="border rounded px-4" type="date" />
              </div>
            </div>

            <div className="flex flex-col">
              <label htmlFor="instructions" className="text-lg font-bold mb-2">
                Shipping Instructions
              </label>
              <textarea
                id="instructions"
                className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
                rows="4"
              ></textarea>
            </div>
          </div>
          <Summary />
        </div>
        <div className="flex pl-10 mt-40">
          <Link to="/payment">
            <div className="bg-black text-white px-20 py-2 rounded-xl mr-4">
              Continue
            </div>
          </Link>
          <Link to="/shipping">
            <div className="bg-black text-white px-20 py-2 rounded-xl mr-4">
              Place Normal Order
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

export default RushOrder;
