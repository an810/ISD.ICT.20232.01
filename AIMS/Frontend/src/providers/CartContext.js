import { createContext, useEffect, useState } from "react";

export const CartContext = createContext([]);

export const CartProvider = ({ children }) => { 
    const cartId = "665440a6ce247243f9072091";
  
    const [item, setItem] = useState([]);

    useEffect(() => { }, []);


    return (
        <CartContext.Provider
          value={{ item, setItem, cartId}}
        >
          {children}
        </CartContext.Provider>
      );

}