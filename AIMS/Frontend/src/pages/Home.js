import React, {useState, useEffect} from "react";
import ItemCard from "../components/ItemCard";
import axios from "axios";
import { toast } from "react-toastify";
const Home = () => {
    const [productData, setProductData] = useState([]);

    useEffect(() => {
        axios.get("/product/all")
        .then((response) => {
            if(response.status) {
                setProductData(response.data.data);
                toast.success(response.data.message);   
            }
        }).catch((error) => {
            console.error("Error fetching data: ", error);
        });
    }, []);

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
