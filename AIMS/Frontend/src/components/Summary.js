import { useContext } from "react";
import { CartContext } from "../providers/CartContext";
import {convertToVND} from "../utils";
const Summary = (props) => {
  const {totalPrice,shippingPrice} = useContext(CartContext);
  
  const taxPrice = (totalPrice / 10);
  const checkoutPrice = (totalPrice + taxPrice + shippingPrice);
  return (
    <div className="mt-10 w-52">
      <h1 className="font-bold">Summary</h1>
      <div className="py-4 mt-4 border-y border-black">
        <div className="flex justify-between">
          <div>Sub-total</div>
          <div className="font-bold">{convertToVND(totalPrice)}</div>
        </div>
        <div className="flex justify-between mt-2">
          <div>Tax (10%)</div>
          <div className="font-bold">{convertToVND(taxPrice)}</div>
        </div>
        <div className="flex justify-between mt-2">
          <div>Shipping fee</div>
          <div className="font-bold">{convertToVND(shippingPrice)}</div>
        </div>
      </div>
      <div className="font-bold flex justify-between mt-2">
        <div>Total</div>
        <div>{convertToVND(checkoutPrice)}</div>
      </div>
    </div>
  );
};

export default Summary;
