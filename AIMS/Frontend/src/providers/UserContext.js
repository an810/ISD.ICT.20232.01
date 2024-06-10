import {createContext, useEffect, useState} from "react";
import axios from "axios";
import { getItemFromLocalStorage, removeItemFromLocalStorage } from '../utils';

export const UserContext = createContext({});

export function UserProvider({children}) {
  const [user,setUser] = useState(null);
  const [isAuthen, setIsAuthen] = useState(false);

  return (
    <UserContext.Provider value={{user,setUser, isAuthen, setIsAuthen}}>
      {children}
    </UserContext.Provider>
  );
}