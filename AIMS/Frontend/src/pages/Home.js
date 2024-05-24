import React from "react";
import ItemCard from "../components/ItemCard";

const Home = () => {
  const productData = [
    {
      name: "Product 1",
      price: 100,
      stock: 10,
      image: "https://via.placeholder.com/150",
    },
    {
      name: "Product 2",
      price: 200,
      stock: 10,
      image: "https://via.placeholder.com/150",
    },
    {
      name: "Product 3",
      price: 300,
      stock: 10,
      image: "https://via.placeholder.com/150",
    },
    {
        name: "Product 4",
        price: 300,
        stock: 10,
        image: "https://via.placeholder.com/150",
      },
  ];

return (
    <div>
        <div className="border-b-4">
            <div className="flex justify-between px-10 py-5">
                <h1 className="text-2xl font-bold">Products</h1>

                <div className="space-x-2">
                    <button className="  font-bold py-2 px-4 rounded-3xl bg-gray-500 text-white border">
                        Default
                    </button>
                    <button className="  font-bold py-2 px-4 rounded-3xl border">
                        A-Z
                    </button>
                    <button className=" font-bold py-2 px-4 rounded-3xl border">
                        List view
                    </button>
                </div>
            </div>
        </div>
        <div className="mt-20 grid grid-cols-4 gap-8 px-10">
            {productData.map((product) => (
                <ItemCard key={product.name} product={product} />
            ))}
        </div>
    </div>
);
};

export default Home;
