import {createContext, useState, useEffect} from "react";
import { getItemFromLocalStorage } from "../utils";
export const UserContext = createContext({});

export function UserProvider({children}) {
  const [isAuthen, setIsAuthen] = useState(false);

  useEffect(()=>{
    const isAuthen = getItemFromLocalStorage("isAuthen");
    if(isAuthen){
      setIsAuthen(isAuthen);
    }
  },[])

  return (
    <UserContext.Provider value={{isAuthen, setIsAuthen}}>
      {children}
    </UserContext.Provider>
  );
}