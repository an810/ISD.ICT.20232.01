import React, { useContext, useState } from "react";
import Modal from "react-modal";
import axios from "axios";
import { CartContext } from "../providers/CartContext";
import { toast } from "react-toastify";

Modal.setAppElement("#root");

const ProductDetailModal = ({ isOpen, onRequestClose, product }) => {
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
        onRequestClose(); 
      })
      .catch((error) => {
        toast.error("Error adding to cart");
      });
  };

  if (!product) return null;

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      contentLabel="Product Details"
      className="m-4 p-4 border-2 border-gray-300 rounded-md bg-gray-50 overflow-y-scroll h-4/5"
    >
      <h2 className="mb-4 font-bold">Product Details</h2>
      <p><strong>Title:</strong> {product.title}</p>
      <p><strong>Import Price:</strong> {product.importPrice}</p>
      <p><strong>Sell Price:</strong> {product.sellPrice}</p>
      <p><strong>Quantity:</strong> {product.quantity}</p>
      <p><strong>Rush Delivery Support:</strong> {product.rushDeliverySupport ? 'Yes' : 'No'}</p>
      <img src={product.imageURL} alt={product.title} className="my-4 max-w-full h-auto" />

      {product.type === "book" && (
        <>
          <p><strong>Author:</strong> {product.author}</p>
          <p><strong>Cover Type:</strong> {product.coverType}</p>
          <p><strong>Publisher:</strong> {product.publisher}</p>
          <p><strong>Publish Date:</strong> {product.publishDate}</p>
          <p><strong>Number of Pages:</strong> {product.numOfPages}</p>
          <p><strong>Language:</strong> {product.language}</p>
          <p><strong>Book Category:</strong> {product.bookCategory}</p>
        </>
      )}

      {product.type === "cd" && (
        <>
          <p><strong>Artist:</strong> {product.artist}</p>
          <p><strong>Record Label:</strong> {product.recordLabel}</p>
          <p><strong>Music Type:</strong> {product.musicType}</p>
          <p><strong>Released Date:</strong> {product.releasedDate}</p>
          <p><strong>Form:</strong> {product.form}</p>
        </>
      )}

      {product.type === "dvd" && (
        <>
          <p><strong>Form:</strong> {product.form}</p>
          <p><strong>Disc Type:</strong> {product.discType}</p>
          <p><strong>Director:</strong> {product.director}</p>
          <p><strong>Runtime:</strong> {product.runtime}</p>
          <p><strong>Movie Category:</strong> {product.movieCategory}</p>
        </>
      )}

      <div className="flex items-center mt-4">
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
        className="text-white font-semibold py-2 px-4 rounded-3xl bg-gray-500 mt-4"
        onClick={handleAddToCart}
      >
        Add to Cart
      </button>

      <div className="flex justify-end mt-4">
        <button
          type="button"
          onClick={onRequestClose}
          className="mr-2 px-4 py-2 bg-gray-500 text-white rounded-md"
        >
          Close
        </button>
      </div>
    </Modal>
  );
};

export default ProductDetailModal;
