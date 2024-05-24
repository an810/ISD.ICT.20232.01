import { useContext } from "react";
import { CartContext } from "../providers/CartContext";
const Summary = (props) => {
  const { item } = useContext(CartContext);

  const totalPrice = () => {
    let total = 0;
    item.map((product) => {
      return (total += product.price * product.quantity);
    });
    return total;
  };
  return (
    <div className="mt-10">
      <h1 className="font-bold">Summary</h1>
      <input
        className="rounded border px-4 py-2 my-4"
        placeholder="Enter Discount Coupon Here"
      />

      <div className="py-4 border-y border-black">
        <div className="flex justify-between">
          <div>Sub-total</div>
          <div className="font-bold">${totalPrice()}</div>
        </div>
        <div className="flex justify-between mt-2">
          <div>Tax (10%)</div>
          <div className="font-bold">${totalPrice() / 10}</div>
        </div>
        <div className="flex justify-between mt-2">
          <div>Shipping fee</div>
          <div className="font-bold">$23.98</div>
        </div>
      </div>

      <div className="font-bold flex justify-between mt-2">
        <div>Total</div>
        <div>$23.98</div>
      </div>
    </div>
  );
};

export default Summary;
