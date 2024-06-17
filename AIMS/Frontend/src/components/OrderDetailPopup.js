import React from "react";
import Modal from "react-modal";

Modal.setAppElement("#root");

const OrderDetailPopup = ({ isOpen, closeModal, order }) => {
  if (!order) return null;

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={closeModal}
      contentLabel="Order Details"
      className="m-4 p-4 border-2 border-gray-300 rounded-md bg-gray-50 overflow-y-scroll h-4/5"
    >
      <h2 className="mb-4 font-bold">Order Details</h2>
      <p><strong>Order ID:</strong> {order.orderId}</p>
      <p><strong>Cart ID:</strong> {order.cartId}</p>
      <p><strong>Total Amount:</strong> {order.totalAmount}</p>
      <p><strong>Status:</strong> {order.status.toUpperCase()}</p>

      <h3 className="text-lg mt-4">Delivery Info</h3>
      <p><strong>Receiver Name:</strong> {order.deliveryInfo.receiverName}</p>
      <p><strong>Address:</strong> {order.deliveryInfo.address}</p>
      <p><strong>Phone Number:</strong> {order.deliveryInfo.phoneNumber}</p>
      <p><strong>Province:</strong> {order.deliveryInfo.province}</p>
      <p><strong>Rush Delivery:</strong> {order.deliveryInfo.rushDelivery ? 'Yes' : 'No'}</p>
      {order.deliveryInfo.rushDeliveryTime && (
        <p><strong>Rush Delivery Time:</strong> {order.deliveryInfo.rushDeliveryTime}</p>
      )}
      <p><strong>Shipping Fees:</strong> {order.deliveryInfo.shippingFees}</p>
      <p><strong>Instruction:</strong> {order.deliveryInfo.instruction}</p>

      <h3 className="text-lg mt-4">Order Items</h3>
      {order.listOrderItem.map((item, index) => (
        <div key={index} className="mb-2">
          <p><strong>Product:</strong> {item.product.title}</p>
          <p><strong>Quantity:</strong> {item.quantity}</p>
          <p><strong>Price:</strong> {item.price}</p>
        </div>
      ))}

      <div className="flex justify-end mt-4">
        <button
          type="button"
          onClick={closeModal}
          className="mr-2 px-4 py-2 bg-gray-500 text-white rounded-md"
        >
          Close
        </button>
      </div>
    </Modal>
  );
};

export default OrderDetailPopup;
