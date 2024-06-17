import React, { useState, useEffect } from "react";
import { toast } from "react-toastify";
import axios from "axios";
import ProductPopUp from "../components/ProductPopUp";
import EditPriceModal from "../components/EditPriceModal";

const ProductManager = () => {
  const [products, setProducts] = useState([]);
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [formData, setFormData] = useState({
    type: "book",
    title: "",
    importPrice: "",
    sellPrice: "",
    quantity: "",
    imageURL: "",
    rushDeliverySupport: "true",
    // Book specific fields
    author: "",
    coverType: "",
    publisher: "",
    publishDate: "",
    numOfPages: "",
    language: "",
    bookCategory: "",
    // CD specific fields
    artist: "",
    recordLabel: "",
    musicType: "",
    releasedDate: "",
    form: "",
    // DVD specific fields
    discType: "",
    director: "",
    runtime: "",
    movieCategory: "",
  });
  const [isEdit, setIsEdit] = useState(false);
  const [currentProductId, setCurrentProductId] = useState(null);
  const [priceModalIsOpen, setPriceModalIsOpen] = useState(false);
  const [editProductId, setEditProductId] = useState(null);
  const [newPrice, setNewPrice] = useState("");
  const [currentPrice, setCurrentPrice] = useState("");

  const openPriceModal = (product) => {
    setEditProductId(product.id);
    setPriceModalIsOpen(true);
    setCurrentPrice(product.sellPrice);
  };

  const closePriceModal = () => {
    setPriceModalIsOpen(false);
    setNewPrice("");
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get("/product/all");
      setProducts(response.data.data);
    } catch (error) {
      console.error("Failed to fetch products:", error);
      toast.error("Failed to fetch products. Please try again later.");
    }
  };

  const openModal = () => {
    setIsEdit(false);
    setModalIsOpen(true);
  };

  const closeModal = () => {
    setModalIsOpen(false);
    setFormData({
      type: "book",
      title: "",
      importPrice: "",
      sellPrice: "",
      quantity: "",
      imageURL: "",
      rushDeliverySupport: "true",
      author: "",
      coverType: "",
      publisher: "",
      publishDate: "",
      numOfPages: "",
      language: "",
      bookCategory: "",
      artist: "",
      recordLabel: "",
      musicType: "",
      releasedDate: "",
      form: "",
      discType: "",
      director: "",
      runtime: "",
      movieCategory: "",
    });
  };

  const handleProductTypeChange = (event) => {
    setFormData({ ...formData, type: event.target.value });
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await axios.post(`/product/add-${formData.type}`, formData);
      toast.success("Product added successfully!");
      closeModal();
      fetchProducts();
    } catch (error) {
      console.error("Failed to add product:", error);
      toast.error("Failed to add product. Please try again.");
    }
  };

  const handleEditProduct = async (event) => {
    event.preventDefault();
    try {
      await axios.put(`/product/update-${formData.type}/${currentProductId}`, formData);
      toast.success("Product updated successfully!");
      closeModal();
      fetchProducts();
    } catch (error) {
      console.error("Failed to update product:", error);
      toast.error("Failed to update product. Please try again.");
    }
  };

  const handleDeleteProduct = async (productId) => {
    try {
      await axios.delete(`/product/delete/${productId}`);
      toast.success("Product deleted successfully!");
      fetchProducts();
    } catch (error) {
      console.error("Failed to delete product:", error);
      toast.error("Failed to delete product. Please try again.");
    }
  };

  const handleEditClick = (product) => {
    setIsEdit(true);
    setCurrentProductId(product.id);
    setFormData(product);
    console.log("editing product: ", product);
    setModalIsOpen(true);
  };

  

  const handlePriceChange = (event) => {
    setNewPrice(event.target.value);
  };

  const handleEditPrice = async (event) => {
    event.preventDefault();
    try {
      await axios.put(`/product/update-price/${editProductId}?newPrice=${newPrice}`);
      toast.success("Price updated successfully!");
      closePriceModal();
      fetchProducts();
    } catch (error) {
      console.error("Error updating price:", error);
      toast.error("Error updating price. Please try again.");
    }
  };

  return (
    <div>
      <button onClick={openModal} className="px-4 py-2 bg-blue-500 text-white rounded-md mb-4">
        Add Product
      </button>

      <ProductPopUp
        modalIsOpen={modalIsOpen}
        closeModal={closeModal}
        formData={formData}
        handleProductTypeChange={handleProductTypeChange}
        handleSubmit={handleSubmit}
        handleInputChange={handleInputChange}
        setModalIsOpen={setModalIsOpen}
        isEdit={isEdit}
        handleEditProduct={handleEditProduct}
      />

      {priceModalIsOpen && (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={closePriceModal}>&times;</span>
            <form onSubmit={handleEditPrice}>
              <h2>Edit Price</h2>
              <input
                type="number"
                value={newPrice}
                onChange={handlePriceChange}
                placeholder="New Price"
                required
              />
              <button type="submit" className="px-4 py-2 bg-green-500 text-white rounded-md">
                Update Price
              </button>
            </form>
          </div>
        </div>
      )}

      <table className="min-w-full bg-white border">
        <thead>
          <tr>
            <th className="py-2 px-4 border-b">Type</th>
            <th className="py-2 px-4 border-b">Title</th>
            <th className="py-2 px-4 border-b">Import Price</th>
            <th className="py-2 px-4 border-b">Sell Price</th>
            <th className="py-2 px-4 border-b">Quantity</th>
            <th className="py-2 px-4 border-b">Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            console.log(product),
            <tr key={product.id}>
              <td className="py-2 px-4 border-b">{product.type}</td>
              <td className="py-2 px-4 border-b">{product.title}</td>
              <td className="py-2 px-4 border-b">{product.importPrice}</td>
              <td className="py-2 px-4 border-b">{product.sellPrice}</td>
              <td className="py-2 px-4 border-b">{product.quantity}</td>
              <td className="py-2 px-4 border-b">
                <button onClick={() => handleEditClick(product)} className="px-2 py-1 bg-yellow-500 text-white rounded-md mr-2">
                  Edit
                </button>
                <button onClick={() => handleDeleteProduct(product.id)} className="px-2 py-1 bg-red-500 text-white rounded-md mr-2">
                  Delete
                </button>
                <button onClick={() => openPriceModal(product)} className="px-2 py-1 bg-blue-500 text-white rounded-md">
                  Edit Price
                </button>
                {priceModalIsOpen && (
                  <EditPriceModal
                    isOpen={priceModalIsOpen}
                    onRequestClose={closePriceModal}
                    productId={editProductId}
                    fetchProducts={fetchProducts} 
                    currentPrice={currentPrice}
                  />
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    
  );

};

export default ProductManager;
