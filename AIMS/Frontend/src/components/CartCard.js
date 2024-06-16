import axios from "axios";
import { useContext } from "react";
import { CartContext } from "../providers/CartContext";
import { toast } from "react-toastify";
const CartCard = (props) => {
  const { product } = props;
  console.log(product);
  const { cartId, setItem, setTotalPrice } = useContext(CartContext);
  const handleChangeQuantity = (qty) => {
    if(product.quantity + qty > product.product.quantity || product.quantity + qty < 1){
      toast.error("Out of stock");
      return;
    }
    axios
      .post(`/cart/${cartId}/add?productId=${product.product.id}&quantity=${qty}`)
      .then((response) => {
        setItem(response.data.data.listCartItem);
        setTotalPrice(response.data.data.totalPrice);
        toast.success("Added to cart");
      })
      .catch((error) => {
        console.error("Error adding to cart");
      });
  }

  return (
    <div className="border rounded shadow-md flex-row w-fit flex mb-4">
      <div className="flex items-center	">
        <img
          src={
            product.product.image
              ? product.product.image
              : "https://via.placeholder.com/150"
          }
          alt={product.name}
          className="h-40 w-80"
        />
      </div>
      <div className="flex justify-between mt-6 items-center ml-6">
        <div>
          <p className="text-black mb-2 text-2xl">
            ${product.product.sellPrice}
          </p>
          <h2 className="text-xl font-semibold mb-2 w-32	">
            {product.product.title}
          </h2>
          <p className="text-gray-600 mb-4">
            Stock: {product.product.quantity}
          </p>
        </div>
        <div className="flex justify-end ml-40 mr-10 items-center">
          <button className="border px-2 py-1 bg-white rounded" onClick={()=>handleChangeQuantity(-1)}>-</button>
          <div className="px-4">{product.quantity}</div>
          <button className="border bg-white px-2 py-1 rounded" onClick={()=>handleChangeQuantity(1)}>+</button>
        </div>
      </div>
    </div>
  );
};

export default CartCard;
