import Modal from "react-modal";
import { toast } from "react-toastify";
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
  } = props;

  const handleEditProduct = () => {
    //POST 1 cái gì đấy
    // axios
    //   .put(`product/update-${formData.type}/${formData.id}`, formData)
    //   .then((response) => {
    //     fetchBooks();
    //     toast.success("Product updated successfully: ", response.data.message);
    //     closeModal();
    //   })
    //   .catch((error) => {
    //     console.error("Error updating product: ", error);
    //   });
  }

  return (
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
        )}

        <button
          onClick={() => setModalIsOpen(false)}
          className="border px-4 py-2"
        >
          Cancel
        </button>

        {formData.isEdit ? (
          <button
            onClick={() => handleEditProduct(false)}
            className="border px-4 py-2"
          >
            Edit product
          </button>
        ) : (
          <button type="submit" className="border px-4 py-2">
            Add Product
          </button>
        )}
      </form>
    </Modal>
  );
};

export default ProductPopUp;
