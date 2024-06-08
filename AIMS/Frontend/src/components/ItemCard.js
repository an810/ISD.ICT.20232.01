import React, {useContext} from "react";
import axios from "axios";
import { CartContext } from "../providers/CartContext";
import {toast} from "react-toastify";
const ItemCard = (props) => {
  const { product } = props;
  const {cartId, setItem, setTotalPrice} = useContext(CartContext);
  const qty = 1;

  const handleAddToCart = () => {
    if(product.quantity < qty) {
      toast.error("Out of stock");
      return;
    }    

    axios.post(`/cart/${cartId}/add?productId=${product.id}&quantity=${qty}`).then((response) => {
      setItem(response.data.listCartItem);
      setTotalPrice(response.data.totalPrice);
      toast.success("Added to cart");
    }).catch((error) => {
      toast.error("Error adding to cart");
      console.error("Error adding to cart: ", error);
    })
  }

  return (
    <div className="bg-gray-100 rounded-lg shadow-md p-4">
      <img
        src={product.image ? product.image : "https://via.placeholder.com/150"}
        alt={product.name}
        className="w-full rounded-lg h-60"
      />
      <div className="flex justify-between mt-6 items-center	">
        <div>
          <span className="flex flex-col">
          <h2 className="text-xl font-semibold mb-2 max-w-24 text-ellipsis overflow-hidden	whitespace-nowrap">{product.title}</h2>
          <p className="text-gray-600 text-sm mb-2">{product.cat}</p>
          </span>
          <p className="text-gray-600 mb-2">${product.importPrice}</p>
          <p className="text-gray-600 sm mb-2 line-through italic">${product.sellPrice}</p>
          <p className="text-gray-600 text-xs mb-4 italic">Stock: <div>{product.quantity ? 'Available(' + product.quantity + ')' : 'Out of stock'}</div></p>
        </div>
        <button className=" text-white font-semibold py-2 px-4 rounded-3xl bg-gray-500 h-10" onClick={handleAddToCart}>
          Add to Cart
        </button>
      </div>
    </div>
  );
};

export default ItemCard;
