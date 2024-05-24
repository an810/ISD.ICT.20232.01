import Header from "./Header";
import { Outlet } from "react-router-dom";

export default function Layout() {
  return (
    <div>
      <Header />
      <div className="py-4 flex flex-col min-h-screen mx-auto">
        <Outlet />
      </div>
    </div>
  );
}