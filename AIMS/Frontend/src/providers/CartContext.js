import { createContext, useEffect, useState } from "react";

export const CartContext = createContext([]);

export const CartProvider = ({ children }) => { 
    const cartId = "6652d55e801ec577200fcc3f";
  
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