import React, {useContext} from "react";
import axios from "axios";
import { CartContext } from "../providers/CartContext";
import {toast} from "react-toastify";
const ItemCard = (props) => {
  const {cartId, setItem} = useContext(CartContext);

  const { product } = props;
  console.log(product);

  const handleAddToCart = () => {
    console.log(cartId + " " + product.id);
    axios.post(`/cart/${cartId}/add?productId=${product.id}&quantity=1`).then((response) => {
      setItem(response.data.listCartItem);
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
        className="w-full rounded-lg"
      />
      <div className="flex justify-between mt-6 items-center	">
        <div>
          <span className="flex">
          <h2 className="text-xl font-semibold mb-2">{product.title}</h2>
          <p className="text-gray-600 text-sm mb-2"> - {product.cat}</p>
          </span>
          <p className="text-gray-600 mb-2">${product.price}</p>
          <p className="text-gray-600 mb-4">Stock: <a>{product.quantity ? 'Available(' + product.quantity + ')' : 'Out of stock'}</a></p>
        </div>
        <button className=" text-white font-semibold py-2 px-4 rounded-3xl bg-gray-500 h-10" onClick={handleAddToCart}>
          Add to Cart
        </button>
      </div>
    </div>
  );
};

export default ItemCard;
