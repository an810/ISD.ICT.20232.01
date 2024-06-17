import React, { useContext, useState } from "react";
import axios from "axios";
import { CartContext } from "../providers/CartContext";
import { toast } from "react-toastify";
const ItemCard = (props) => {
  const { product } = props;
  const { cartId, setItem, setTotalPrice } = useContext(CartContext);
  const [qty, setQty] = useState(1);

  const incrementQty = () => {
    if (qty < product.quantity) {
      setQty(qty + 1);
    }
  };

  const decrementQty = () => {
    if (qty > 1) {
      setQty(qty - 1);
    }
  };
  const handleAddToCart = () => {
    if (product.quantity < qty) {
      toast.error("Out of stock");
      return;
    }

    axios
      .post(`/cart/${cartId}/add?productId=${product.id}&quantity=${qty}`)
      .then((response) => {
        setItem(response.data.data.listCartItem);
        setTotalPrice(response.data.data.totalPrice);
        toast.success("Added to cart");
      })
      .catch((error) => {
        toast.error("Error adding to cart");
      });
  };

  return (
    <div className="bg-gray-100 rounded-lg shadow-md p-4">
      <img
        src={product.imageURL ? product.imageURL : "https://via.placeholder.com/150"}
        alt={product.title}
        className="w-full rounded-lg h-60"
      />
      <div className="flex justify-between mt-6 items-center	">
        <div>
          <span className="flex flex-col">
            <h2 className="text-xl font-semibold mb-2 max-w-24 text-ellipsis overflow-hidden	whitespace-nowrap">
              {product.title}
            </h2>
            <p className="text-gray-600 text-sm mb-2">{product.category}</p>
          </span>
          <p className="text-gray-600 mb-2">${product.sellPrice}</p>
          <p className="text-gray-600 text-xs mb-4 italic">
            Stock:{" "}
            <div>
              {product.quantity
                ? "Available(" + product.quantity + ")"
                : "Out of stock"}
            </div>
          </p>
        </div>

        <div>
          <div className="flex items-center mb-4 w-full">
            <button onClick={decrementQty} className="border bg-white px-2 py-1 rounded">
              -
            </button>
            <input
              type="number"
              value={qty}
              onChange={(e) => setQty(parseInt(e.target.value))}
              className="border px-2 py-1 w-10 text-center mx-2"
            />
            <button onClick={incrementQty} className="border px-2 py-1 bg-white rounded">
              +
            </button>
          </div>
          <button
            className=" text-white font-semibold py-2 px-4 rounded-3xl bg-gray-500 h-10"
            onClick={handleAddToCart}
          >
            Add to Cart
          </button>
        </div>
      </div>
    </div>
  );


};

export default ItemCard;
