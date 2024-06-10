import React, { useContext } from "react";
import { Link } from "react-router-dom";

import { CartContext } from "../providers/CartContext";
const Header = () => {
  const { item } = useContext(CartContext);
  return (
    <header className="flex p-4 justify-between	px-10">
      <Link to="/">
        <button className="mr-4 border p-2 rounded-xl">
          <span role="img" aria-label="home">
            🏠
          </span>
          <span className="ml-2 font-bold">AIMS</span>
        </button>
      </Link>

      <div className="flex">
        <button className="mr-4">
          <span>About</span>
        </button>
        <button className="mr-4">
          <span>Shop</span>
        </button>
        <button className="mr-4">
          <span>Help</span>
        </button>
        <Link to="login" className="flex items-center">
          <button className="mr-4">
            <span>Login</span>
          </button>
        </Link>

        <Link to="/cart">
          <div className="flex items-center rounded-2xl bg-gray-200 px-4 py-2">
            <button className="mr-2">
              <span role="img" aria-label="cart">
                🛒
              </span>
            </button>
            <div>Your cart</div>
            <div className="ml-2 text-red-600 ">
              ({item?.length ? item.length : 0})
            </div>
          </div>
        </Link>
      </div>
    </header>
  );
};

export default Header;
