import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Summary from "../components/Summary";
import { CartContext } from "../providers/CartContext";
import axios from "axios";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import {
  RegionDropdown,
} from "react-country-region-selector";
import { processString } from "../utils";

const Shipping = () => {
  const { cartId, setShippingPrice, shippingPrice } = useContext(CartContext);

  const navigate = useNavigate();
  const [isShippingData, setIsShippingData] = useState(false);
  const [formData, setFormData] = useState({
    name: "",
    phone: "",
    email: "",
    province: "",
    address: "",
    instructions: "",
  });
  
  function selectRegion(val) {
    setFormData({
      ...formData,
      province: val,
    });
  }

  useEffect(() => {
    setShippingPrice(0);
  }, [setShippingPrice]);

  const getShippingPrice = (e) => {
    e.preventDefault();
    if (formData.province === "") {
      toast.error("Please select a province");
      return;
    }
    axios
      .get(
        `delivery-info/shipping-fee?province=${processString(formData.province)}&isRushDelivery=false`
      )
      .then((response) => {
        setIsShippingData(true);
        toast.success("Shipping fee is " + response.data);
        setShippingPrice(response.data);
      })
      .catch((error) => {
        toast.error("Error placing order");
      });
  };
  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData({
      ...formData,
      [id]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    for (const key in formData) {
      if (formData[key] === "") {
      toast.error(`${key.charAt(0).toUpperCase() + key.slice(1)} is required`);
      return;
      }
    }
    axios
      .post(`place-order?cartId=${cartId}`, {
        receiverName: formData.name,
        phoneNumber: formData.phone,
        address: formData.address,
        province: formData.province,
        rushDelivery: false,
        instruction: formData.instructions,
        shippingFees: shippingPrice,
      })
      .then((response) => {
        console.log(response.data);
        toast.success("Order placed successfully");
        navigate("/payment", {state : {
          orderId: response.data.orderId,
          totalAmount: response.data.totalAmount,
        }});
      })
      .catch((error) => {
        toast.error("Error placing order");
      });
  };

  return (
    <div>
      <div className="flex px-40 justify-between shadow-sm py-10">
        <div className="text-2xl font-bold text-gray-300">1. Shopping Cart</div>
        <h1 className="text-2xl font-bold ">2. Shipping Details</h1>
        <h1 className="text-2xl font-bold text-gray-300">3. Payment Options</h1>
      </div>
      <div className="px-40">
        <form onSubmit={handleSubmit} className="flex justify-between">
          <div className="flex flex-col mr-10">
            <div className="font-bold my-10 text-xl">Delivery Form</div>

            <label htmlFor="name" className="text-lg font-bold mb-2">
              Name
            </label>
            <input
              type="text"
              id="name"
              value={formData.name}
              onChange={handleChange}
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
              required
            />

            <label htmlFor="phone" className="text-lg font-bold mb-2">
              Phone Number
            </label>
            <input
              type="tel"
              id="phone"
              value={formData.phone}
              onChange={handleChange}
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
              required
            />

            <label htmlFor="email" className="text-lg font-bold mb-2">
              Email
            </label>
            <input
              type="email"
              id="email"
              value={formData.email}
              onChange={handleChange}
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
              required
            />

            <label htmlFor="province" className="text-lg font-bold mb-2">
              Province
            </label>

            <RegionDropdown
              country={"Vietnam"}
              value={formData.province}
              onChange={(val) => selectRegion(val)}
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
              defaultOptionLabel={"Select a province"} 
              required
            />

            <label htmlFor="address" className="text-lg font-bold mb-2">
              Address
            </label>
            <input
              type="text"
              id="address"
              value={formData.address}
              onChange={handleChange}
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
              required
            />

            <label htmlFor="instructions" className="text-lg font-bold mb-2">
              Shipping Instructions
            </label>
            <textarea
              id="instructions"
              value={formData.instructions}
              onChange={handleChange}
              className="border border-gray-300 px-4 py-2 mb-4 rounded-xl"
              rows="4"
            ></textarea>

            <div className="flex pl-10 mt-10">
              {isShippingData ? (
                <button
                  type="submit"
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
              <button
                type="submit"
                className="bg-black text-white px-20 py-2 rounded-xl mr-4"
              >
                Continue
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
          <Summary />
        </form>
      </div>
    </div>
  );
};

export default Shipping;
