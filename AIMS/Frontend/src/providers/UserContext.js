import {createContext, useState, useEffect} from "react";
import { getItemFromLocalStorage } from "../utils";
export const UserContext = createContext({});

export function UserProvider({children}) {
  const [isAuthen, setIsAuthen] = useState(false);
  const [userId, setUserId] = useState(null);
  useEffect(()=>{
    const isAuthen = getItemFromLocalStorage("isAuthen");
    const userId = getItemFromLocalStorage("userId");
    if(isAuthen){
      setIsAuthen(isAuthen);
    }

    if(userId){
      setUserId(userId);
    }
  },[])

  return (
    <UserContext.Provider value={{isAuthen, setIsAuthen, userId, setUserId}}>
      {children}
    </UserContext.Provider>
  );
}