import Summary from "../components/Summary";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { useState, useContext } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import { processString } from "../utils";
import { CartContext } from "../providers/CartContext";

const RushOrder = () => {
  const { cartId, setShippingPrice, shippingPrice } = useContext(CartContext);
  const { state } = useLocation();
  const [isShippingData, setIsShippingData] = useState(false);
  const [rushOrderData, setRushOrderData] = useState({
    fromTime: "",
    toTime: "",
    instructions: state.formData.instructions,
  });

  const getShippingPrice = (e) => {
    e.preventDefault();
    if (rushOrderData.fromTime === "") {
      toast.error("From Time is required");
      return;
    }
    axios
      .get(
        `delivery-info/shipping-fee?province=${processString(
          state.formData.province
        )}&isRushDelivery=true`
      )
      .then((response) => {
        setIsShippingData(true);
        toast.success("Shipping fee is " + response.data.data);
        setShippingPrice(response.data.data);
      })
      .catch((error) => {
        toast.error("Error placing order");
      });
  };

  const handleChange = (e) => {
    const { id, value } = e.target;
    let newToTime = rushOrderData.toTime;

    if (id === "fromTime") {
      const [hours, minutes] = value.split(":").map(Number);
      const endTime = new Date();
      endTime.setHours(hours + 2, minutes);
      newToTime = endTime.toTimeString().slice(0, 5);
    }

    setRushOrderData({
      ...rushOrderData,
      [id]: value,
      toTime: newToTime,
    });
  };

  const handleRushOrder = () => {
    // Implement the rush order handling logic
  };

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
            <div className="font-bold my-10 text-xl">Rush Delivery Form</div>
            <div>
              <div className="text-lg font-bold mb-2">Time</div>
              <div className="flex">
                <input
                  className="border rounded px-4"
                  id="fromTime"
                  type="time"
                  value={rushOrderData.fromTime}
                  onChange={handleChange}
                />
                <div className="mx-4">to</div>
                <input
                  className="border rounded px-4"
                  id="toTime"
                  type="time"
                  value={rushOrderData.toTime}
                  readOnly
                />
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
                value={rushOrderData.instructions}
                onChange={handleChange}
              />
            </div>
          </div>
          <Summary />
        </div>
        <div className="flex pl-10 mt-40">
          {isShippingData ? (
            <button
              onClick={handleRushOrder}
              className="bg-black text-white px-20 py-2 rounded-xl mr-4"
            >
              Place Order
            </button>
          ) : (
            <button
              onClick={getShippingPrice}
              className="bg-black text-white px-20 py-2 rounded-xl mr-4"
            >
              Submit data
            </button>
          )}
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
