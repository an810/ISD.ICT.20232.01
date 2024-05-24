import Summary from "../components/Summary";
import { Link } from "react-router-dom";
const Result = () => {
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
          <div className=" font-bold my-10 text-xl w-full items-center justify-center flex flex-col">
            <div className="flex flex-col justify-center items-center mt-40">
              <div className="text-6xl	">PAYMENT RESULT</div>
              <div className="text-2xl mt-10">Successfull</div>
            </div>

            <div className="flex mt-40">
              <Link to="/">
                <div className="bg-black text-white px-20 py-2 rounded-xl mr-4">
                  Done
                </div>
              </Link>
            </div>
          </div>
          <Summary />
        </div>
      </div>
    </div>
  );
};

export default Result;
