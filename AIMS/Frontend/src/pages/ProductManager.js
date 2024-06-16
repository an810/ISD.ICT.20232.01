import React, { useState, useEffect } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import ProductPopUp from "../components/ProductPopUp";
import Modal from "react-modal";
Modal.setAppElement("#root");
const ProductManager = () => {
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [priceModelIsOpen, setPriceModelIsOpen] = useState(false);
  const [editProductId, setEditProductId] = useState(null);
  const [products, setProducts] = useState([]);
  const [formData, setFormData] = useState({
    title: "",
    importPrice: "",
    sellPrice: "",
    quantity: "",
    isRushDeliverySupport: "",
    type: "book",
  });

  const handleInputChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value,
    });
  };

  const handleProductTypeChange = (event) => {
    setFormData({
      ...formData,
      type: event.target.value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .post(`product/add-${formData.type}`, formData)
      .then((response) => {
        fetchBooks();
        toast.success("Product added successfully: ", response.data.message);
        closeModal();
      })
      .catch((error) => {
        console.error("Error adding product: ", error);
      });
  };

  const fetchBooks = () => {
    axios
      .get("/product/all")
      .then((response) => {
        setProducts(response.data.data);
      })
      .catch((error) => {
        console.error("Error fetching data: ", error);
      });
  };
  useEffect(() => {
    fetchBooks();
  }, []);

  const openModal = () => {
    setModalIsOpen(true);
  };

  const closeModal = () => {
    setModalIsOpen(false);
  };

  const handleDeleteProduct = (id) => {
    axios
      .delete(`product/delete/${id}`)
      .then((response) => {
        fetchBooks();
        toast.success("Product deleted successfully: ", response.data.message);
      })
      .catch((error) => {
        toast.error("Error deleting product");
      });
  };

  const handleUpdatePrice = (id) => {
    setEditProductId(id);
    setPriceModelIsOpen(true);
  };

  const handleEditProduct = (product) => {
    setFormData({
      title: product.title,
      importPrice: product.importPrice,
      sellPrice: product.sellPrice,
      quantity: product.quantity,
      isRushDeliverySupport: product.rushDeliverySupport,
      type: product.type,
      isEdit: true,
    });
    openModal();
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl mb-4">Product</h1>

      <button
        onClick={openModal}
        className="border-2 rounded-2xl px-4 py-2 mr-2"
      >
        Add product
      </button>

      <PriceEditPopup
        editProductId={editProductId}
        isOpen={priceModelIsOpen}
        onRequestClose={() => {
          setPriceModelIsOpen(false);
        }}
      />
      <ProductPopUp
        modalIsOpen={modalIsOpen}
        closeModal={closeModal}
        handleInputChange={handleInputChange}
        handleProductTypeChange={handleProductTypeChange}
        handleSubmit={handleSubmit}
        formData={formData}
        setModalIsOpen={setModalIsOpen}
      />

      <table className="w-full table-auto">
        <thead>
          <tr>
            <th className="px-4 py-2">Id</th>
            <th className="px-4 py-2">Product Name</th>
            <th className="px-4 py-2">Type</th>
            <th className="px-4 py-2">Import Price</th>
            <th className="px-4 py-2">Price</th>
            <th className="px-4 py-2">Quantity</th>
            <th className="px-4 py-2">Rush Delivery Support</th>
            <th className="px-4 py-2">Action</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td className="border px-4 py-2">{product.id}</td>
              <td className="border px-4 py-2">{product.title}</td>
              <td className="border px-4 py-2">{product.type}</td>
              <td className="border px-4 py-2">{product.importPrice}</td>
              <td className="border px-4 py-2">{product.sellPrice}</td>
              <td className="border px-4 py-2">{product.quantity}</td>
              <td className="border px-4 py-2">
                {product.rushDeliverySupport ? "Yes" : "No"}
              </td>
              <td className="border px-4 py-2">
                <button
                  className="border-2 rounded-2xl px-4 py-2 mr-2"
                  onClick={() => handleEditProduct(product)}
                >
                  Edit
                </button>
                <button
                  className="border-2 rounded-2xl px-4 py-2 mr-2"
                  onClick={() => handleDeleteProduct(product.id)}
                >
                  Delete
                </button>
                <button
                  className="border-2 rounded-2xl px-4 py-2"
                  onClick={() => handleUpdatePrice(product.id)}
                >
                  Update Price
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

const PriceEditPopup = (props) => {
  const { isOpen, onRequestClose, editProductId } = props;
  const [sellPrice, setSellPrice] = useState("");
  const [newPrice, setNewPrice] = useState("");

  const handleInputChange = (event) => {
    setNewPrice(event.target.value);
  }

  useEffect(() => { 
    // GET PRICE
    if (!editProductId) return;
    axios.get(`product/${editProductId}`).then((response) => {
      setSellPrice(response.data.data.sellPrice);
      setNewPrice(response.data.data.sellPrice);
    });
  }, [editProductId]);
  

  const handleEditPrice = (e) => {
    e.preventDefault();
    //POST edit product
    axios.put(`product/update-price/${editProductId}?newPrice=${newPrice}`)
    .then((response) => {
      // fetchBooks();
      toast.success("Price updated successfully: ", response.data.message);
      onRequestClose();
    })
    .catch((error) => {
      toast.error("Error updating price: ", error);
    });
  }
  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      contentLabel="Add Product"
      className="m-4 p-4 border-2 border-gray-300 rounded-md bg-gray-50 overflow-y-scroll h-4/5	"
    >
      <h2 className="mb-4 font-bold">Edit price</h2>

      <form onSubmit={handleEditPrice}>
        <label className="block mb-2">
          Selling Price
          <input
            type="number"
            name="newPrice"
            className="border px-2 py-1 w-full"
            value={newPrice}
            onChange={handleInputChange}
            required
          />
        </label>
        
      </form>
      <button onClick={onRequestClose} className="border px-4 py-2">
        Cancel
      </button>
      <button onClick={handleEditPrice} type="submit" className="border px-4 py-2">
        Update
      </button>
    </Modal>
  );
};

export default ProductManager;
