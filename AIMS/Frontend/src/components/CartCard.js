const CartCard = (props) => {
  const { product } = props;
  
  return (
    <div className="border rounded shadow-md flex-row w-fit flex mb-4">
      <img src={product.image ? product.image : "https://via.placeholder.com/150"} alt={product.name} className="h-40 w-80" />
      <div className="flex justify-between mt-6 items-center ml-6">
        <div>
          <p className="text-black mb-2 text-2xl">${product.product.importPrice} <a className="text-red-600 mb-2 text-sm line-through">${product.product.sellPrice} </a> </p>
          <h2 className="text-xl font-semibold mb-2 w-32	">{product.product.title}</h2>
          <p className="text-gray-600 mb-4">Stock: {product.stock}</p>
        </div>

        <div className="ml-40 mr-10">Quantity: {product.quantity}</div>
      </div>
    </div>
  );
};

export default CartCard;
