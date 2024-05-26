import { createContext, useEffect, useState } from "react";

export const CartContext = createContext([]);

export const CartProvider = ({ children }) => { 
    const [item, setItem] = useState([]);

    useEffect(() => {
        setItem([
            {
              name: "Product 1",
              price: 100,
              stock: 10,
              image: "https://via.placeholder.com/150",
              quantity: 1,
            },
            {
              name: "Product 2",
              price: 200,
              stock: 10,
              image: "https://via.placeholder.com/150",
              quantity: 1,
            },
            {
              name: "Product 3",
              price: 300,
              stock: 10,
              image: "https://via.placeholder.com/150",
              quantity: 1,
            },
            {
              name: "Product 4",
              price: 300,
              stock: 10,
              image: "https://via.placeholder.com/150",
              quantity: 1,
            },
          ])
     }, []);

    return (
        <CartContext.Provider
          value={{ item, setItem}}
        >
          {children}
        </CartContext.Provider>
      );

}