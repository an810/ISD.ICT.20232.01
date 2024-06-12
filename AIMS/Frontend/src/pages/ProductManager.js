import React, { useState, useEffect } from "react";
import Modal from "react-modal";
import axios from "axios";
import { toast } from "react-toastify";
Modal.setAppElement("#root");

const ProductManager = () => {
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [productType, setProductType] = useState("book");
  const [products, setProducts] = useState([]);
  const [formData, setFormData] = useState({
    title: "",
    importPrice: "",
    sellPrice: "",
    quantity: "",
    isRushDeliverySupport: "",
  });

  const handleInputChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .post("product/add", formData)
      .then((response) => {
        fetchBooks();
        toast.success("Product added successfully");
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
        toast.success("Book deleted successfully");
      })
      .catch((error) => {
        console.error("Error deleting book: ", error);
      });
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

      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Add Product"
        className="m-4 p-4 border-2 border-gray-300 rounded-md bg-gray-50 overflow-y-scroll h-4/5	"
      >
        <h2 className="mb-4 font-bold">Add Product</h2>
        <form onSubmit={handleSubmit}>
        <label className="block mb-4">
            Product
            <select
              value={productType}
              onChange={(e)=>setProductType(e.target.value)}
              className="border px-2 py-1 w-full"
            >
              <option value="book">Book</option>
              <option value="cd">CD</option>
              <option value="dvd">DVD</option>

            </select>
          </label>
          <label className="block mb-2">
            Title
            <input
              type="text"
              name="title"
              className="border px-2 py-1 w-full"
              value={formData.title}
              onChange={handleInputChange}
              required
            />
          </label>
          <label className="block mb-2">
            Import Price
            <input
              type="number"
              name="importPrice"
              className="border px-2 py-1 w-full"
              value={formData.importPrice}
              onChange={handleInputChange}
              required
            />
          </label>
          <label className="block mb-2">
            Sell Price
            <input
              type="number"
              name="sellPrice"
              className="border px-2 py-1 w-full"
              value={formData.sellPrice}
              onChange={handleInputChange}
              required
            />
          </label>
          <label className="block mb-2">
            Quantity
            <input
              type="number"
              name="quantity"
              className="border px-2 py-1 w-full"
              value={formData.quantity}
              onChange={handleInputChange}
              required
            />
          </label>
          <label className="block mb-2">
            ImageURL
            <input
              type="number"
              name="quantity"
              className="border px-2 py-1 w-full"
              value={formData.imageURL}
              onChange={handleInputChange}
              required
            />
          </label>
          <label className="block mb-2">
            Rush Delivery Support
            <select
              value={formData.rushDeliverySupport}
              onChange={handleInputChange}
              className="border px-2 py-1 w-full"
            >
              <option value="true">Yes</option>
              <option value="false">No</option>
            </select>
          </label>

          {productType === "book" && (
            <>
              <label className="block mb-2">
                Author
                <input
                  type="text"
                  name="author"
                  className="border px-2 py-1 w-full"
                  value={formData.author}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Cover Type
                <input
                  type="text"
                  name="coverType"
                  className="border px-2 py-1 w-full"
                  value={formData.coverType}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Publisher
                <input
                  type="text"
                  name="publisher"
                  className="border px-2 py-1 w-full"
                  value={formData.publisher}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Publish Date
                <input
                  type="date"
                  name="publishDate"
                  className="border px-2 py-1 w-full"
                  value={formData.publishDate}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Number of Pages
                <input
                  type="number"
                  name="numOfPages"
                  className="border px-2 py-1 w-full"
                  value={formData.numOfPages}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Language
                <input
                  type="text"
                  name="language"
                  className="border px-2 py-1 w-full"
                  value={formData.language}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Book Category
                <input
                  type="text"
                  name="bookCategory"
                  className="border px-2 py-1 w-full"
                  value={formData.bookCategory}
                  onChange={handleInputChange}
                  required
                />
              </label>
            </>
          )}

          {productType === "cd" && (
            <>
              <label className="block mb-2">
                Artist
                <input
                  type="text"
                  name="artist"
                  className="border px-2 py-1 w-full"
                  value={formData.artist}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Record Label
                <input
                  type="text"
                  name="recordLabel"
                  className="border px-2 py-1 w-full"
                  value={formData.recordLabel}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Music Type
                <input
                  type="text"
                  name="musicType"
                  className="border px-2 py-1 w-full"
                  value={formData.musicType}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Released Date
                <input
                  type="date"
                  name="releasedDate"
                  className="border px-2 py-1 w-full"
                  value={formData.releasedDate}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Form
                <input
                  type="text"
                  name="form"
                  className="border px-2 py-1 w-full"
                  value={formData.form}
                  onChange={handleInputChange}
                  required
                />
              </label>
            </>
          )}

{
            productType === "dvd" && (
              <>
              <label className="block mb-2">
                Form
                <input
                  type="text"
                  name="form"
                  className="border px-2 py-1 w-full"
                  value={formData.form}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Disc Type
                <input
                  type="text"
                  name="discType"
                  className="border px-2 py-1 w-full"
                  value={formData.discType}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Director
                <input
                  type="text"
                  name="director"
                  className="border px-2 py-1 w-full"
                  value={formData.director}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Runtime
                <input
                  type="text"
                  name="runtime"
                  className="border px-2 py-1 w-full"
                  value={formData.runtime}
                  onChange={handleInputChange}
                  required
                />
              </label>
              <label className="block mb-2">
                Studio
                <input
                  type="text"
                  name="studio"
                  className="border px-2 py-1 w-full"
                  value={formData.studio}
                  onChange={handleInputChange}
                  required
                />
              </label>

              <label className="block mb-2">
                Subtitles
                <input
                  type="text"
                  name="subtitles"
                  className="border px-2 py-1 w-full"
                  value={formData.subtitles}
                  onChange={handleInputChange}
                  required
                />
              </label>

              <label className="block mb-2">
                Release Date
                <input
                  type="date"
                  name="releasedDate"
                  className="border px-2 py-1 w-full"
                  value={formData.releasedDate}
                  onChange={handleInputChange}
                  required
                />
              </label>

              <label className="block mb-2">
                Film Type
                <input
                  type="text"
                  name="filmType"
                  className="border px-2 py-1 w-full"
                  value={formData.filmType}
                  onChange={handleInputChange}
                  required
                />
              </label>
              </>
            )
          }

          <button
            onClick={() => setModalIsOpen(false)}
            className="border px-4 py-2"
          >
            Cancel
          </button>
          <button type="submit" className="border px-4 py-2">
            Add Product
          </button>
        </form>
      </Modal>

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
                  // onClick={() => handleEditProduct(product)}
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
                  // onClick={() => handleUpdatePrice(product.id)}
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

export default ProductManager;
