import Modal from "react-modal";
import { toast } from "react-toastify";
import axios from "axios";
Modal.setAppElement("#root");

const ProductPopUp = (props) => {
  const {
    modalIsOpen,
    closeModal,
    formData,
    handleProductTypeChange,
    handleSubmit,
    handleInputChange,
    setModalIsOpen,
    isEdit,
    handleEditProduct,
  } = props;

  return (
    <Modal
      isOpen={modalIsOpen}
      onRequestClose={closeModal}
      contentLabel={isEdit ? "Edit Product" : "Add Product"}
      className="m-4 p-4 border-2 border-gray-300 rounded-md bg-gray-50 overflow-y-scroll h-4/5"
    >
      <h2 className="mb-4 font-bold">{isEdit ? "Edit Product" : "Add Product"}</h2>
      <form onSubmit={isEdit ? handleEditProduct : handleSubmit}>
        <label className="block mb-4">
          Product
          <select
            value={formData.type}
            onChange={handleProductTypeChange}
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
            type="text"
            name="imageURL"
            className="border px-2 py-1 w-full"
            value={formData.imageURL}
            onChange={handleInputChange}
            required
          />
        </label>
        <label className="block mb-2">
          Rush Delivery Support
          <select
            name="rushDeliverySupport"
            value={formData.rushDeliverySupport}
            onChange={handleInputChange}
            className="border px-2 py-1 w-full"
          >
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>
        </label>

        {formData.type === "book" && (
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

        {formData.type === "cd" && (
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

        {formData.type === "dvd" && (
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
              Movie Category
              <input
                type="text"
                name="movieCategory"
                className="border px-2 py-1 w-full"
                value={formData.movieCategory}
                onChange={handleInputChange}
                required
              />
            </label>
          </>
        )}

        <div className="flex justify-end mt-4">
          <button
            type="button"
            onClick={closeModal}
            className="mr-2 px-4 py-2 bg-gray-500 text-white rounded-md"
          >
            Cancel
          </button>
          <button
            type="submit"
            className="px-4 py-2 bg-blue-500 text-white rounded-md"
          >
            {isEdit ? "Update" : "Add"}
          </button>
        </div>
      </form>
    </Modal>
  );
};

export default ProductPopUp;
