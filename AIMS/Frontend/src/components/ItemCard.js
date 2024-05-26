import React from "react";

const ItemCard = (props) => {
  const { product } = props;
  console.log(product.image );
  return (
    <div className="bg-gray-100 rounded-lg shadow-md p-4">
      <img
        src={product.image ? product.image : "https://via.placeholder.com/150"}
        alt={product.name}
        className="w-full rounded-lg"
      />
      <div className="flex justify-between mt-6 items-center	">
        <div>
          <h2 className="text-xl font-semibold mb-2">{product.title}</h2>
          <p className="text-gray-600 mb-2">${product.sellPrice}</p>
          <p className="text-gray-600 mb-4">Stock: {product.stock}</p>
        </div>
        <button className=" text-white font-semibold py-2 px-4 rounded-3xl bg-gray-500 h-10 	">
          Add to Cart
        </button>
      </div>
    </div>
  );
};

export default ItemCard;
