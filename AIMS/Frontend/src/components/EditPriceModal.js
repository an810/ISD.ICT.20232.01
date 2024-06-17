import React, { useState } from "react";
import Modal from "react-modal";
import axios from "axios";
import { toast } from "react-toastify";

Modal.setAppElement("#root");

const EditPriceModal = ({ isOpen, onRequestClose, productId, fetchProducts, currentPrice }) => {
  const [newPrice, setNewPrice] = useState("");

  const handlePriceChange = (event) => {
    setNewPrice(event.target.value);
  };

  const handleEditPrice = async (event) => {
    event.preventDefault();
    try {
      await axios.put(`/product/update-price/${productId}?newPrice=${newPrice}`);
      toast.success("Price updated successfully!");
      onRequestClose();
      fetchProducts(); // Refresh product list after price update
    } catch (error) {
      console.error("Error updating price:", error);
      toast.error("Error updating price. Please try again.");
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      contentLabel="Edit Price"
      className="m-4 p-4 border-2 border-gray-300 rounded-md bg-gray-50"
    >
      <h2 className="mb-4 font-bold">Edit Price</h2>
      <p> Current selling price: {currentPrice}</p>
      <form onSubmit={handleEditPrice}>
        <input
          type="number"
          value={newPrice}
          onChange={handlePriceChange}
          placeholder="New Price"
          className="border px-2 py-1 mb-2"
          required
        />
        <button type="submit" className="px-4 py-2 bg-green-500 text-white rounded-md">
          Update Price
        </button>
      </form>
      <button
        onClick={onRequestClose}
        className="px-4 py-2 bg-gray-500 text-white rounded-md mt-2"
      >
        Cancel
      </button>
    </Modal>
  );
};

export default EditPriceModal;
