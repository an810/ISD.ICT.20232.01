import { useContext } from "react";
import { CartContext } from "../providers/CartContext";
const Summary = (props) => {
  const {totalPrice,shippingPrice} = useContext(CartContext);

  const taxPrice = (totalPrice / 10);
  const checkoutPrice = (totalPrice + taxPrice + shippingPrice).toFixed(2);
  return (
    <div className="mt-10 w-52">
      <h1 className="font-bold">Summary</h1>
      <div className="py-4 mt-4 border-y border-black">
        <div className="flex justify-between">
          <div>Sub-total</div>
          <div className="font-bold">${totalPrice.toFixed(2)}</div>
        </div>
        <div className="flex justify-between mt-2">
          <div>Tax (10%)</div>
          <div className="font-bold">${taxPrice.toFixed(2)}</div>
        </div>
        <div className="flex justify-between mt-2">
          <div>Shipping fee</div>
          <div className="font-bold">{shippingPrice}</div>
        </div>
      </div>
      <div className="font-bold flex justify-between mt-2">
        <div>Total</div>
        <div>${checkoutPrice}</div>
      </div>
    </div>
  );
};

export default Summary;
