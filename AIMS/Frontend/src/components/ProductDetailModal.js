import Modal from "react-modal";
Modal.setAppElement("#root");

const ProductDetailModal = ({ isOpen, onRequestClose, product }) => {
  if (!product) return null;

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      contentLabel="Product Details"
      className="m-4 p-4 border-2 border-gray-300 rounded-md bg-gray-50 overflow-y-scroll h-4/5"
    >
      <h2 className="mb-4 font-bold">{product.title}</h2>
      
      <button onClick={onRequestClose} className="border px-4 py-2 mt-4">
        Close
      </button>
    </Modal>
  );
};

export default ProductDetailModal;
