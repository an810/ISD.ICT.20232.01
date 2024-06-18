import Summary from "../components/Summary";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import axios from "axios";
import { getItemFromLocalStorage, removeItemFromLocalStorage } from "../utils";
import { useEffect, useState, useContext } from "react";
import { CartContext } from "../providers/CartContext";
const Result = () => {
  const { cartId, setItem } = useContext(CartContext);
  const location = useLocation();
  const [order, setOrder] = useState({});
  const orderId = getItemFromLocalStorage("orderId");
  const queryParams = new URLSearchParams(location.search);
  const vnp_Amount = queryParams.get("vnp_Amount");
  // const vnp_BankCode = queryParams.get('vnp_BankCode');
  // const vnp_BankTranNo = queryParams.get('vnp_BankTranNo');
  // const vnp_CardType = queryParams.get('vnp_CardType');
  const vnp_OrderInfo = queryParams.get("vnp_OrderInfo");
  const vnp_PayDate = queryParams.get("vnp_PayDate");
  const vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
  // const vnp_TmnCode = queryParams.get('vnp_TmnCode');
  const vnp_TransactionNo = queryParams.get("vnp_TransactionNo");
  const vnp_TransactionStatus = queryParams.get("vnp_TransactionStatus");
  const vnp_TxnRef = queryParams.get("vnp_TxnRef");
  // const vnp_TxnRef = queryParams.get('vnp_TxnRef');
  // const vnp_SecureHash = queryParams.get('vnp_SecureHash');

  useEffect(() => {
    axios
      .post("payment/save-payment-transaction", {
        orderId: orderId,
        vnp_ResponseCode: vnp_ResponseCode,
        vnp_TransactionNo: vnp_TransactionNo,
        vnp_Amount: vnp_Amount,
        vnp_OrderInfo: vnp_OrderInfo,
        vnp_TransactionStatus: vnp_TransactionStatus,
        vnp_PayDate: vnp_PayDate,
        vnp_TxnRef: vnp_TxnRef,
      })
      .then((response) => {
        axios
          .get(`order/${orderId}`)
          .then((response) => {
            setOrder(response.data.data);
            setItem([]);
          })
          .catch((error) => {
            console.log(error.data);
          });

        const invoice = {
          order,
          paymentTransaction: {
            orderId: orderId,
            vnp_ResponseCode: vnp_ResponseCode,
            vnp_TransactionNo: vnp_TransactionNo,
            vnp_Amount: vnp_Amount,
            vnp_OrderInfo: vnp_OrderInfo,
            vnp_TransactionStatus: vnp_TransactionStatus,
            vnp_PayDate: vnp_PayDate,
            vnp_TxnRef: vnp_TxnRef,
          },
        };

        axios
          .post("invoice/create", invoice)
          .then((response) => {
            if (vnp_ResponseCode === "00") {
              axios
                .post(`cart/${cartId}/clear`)
                .then((response) => {
                  removeItemFromLocalStorage("orderId");
                })
                .catch((error) => {
                  console.log(error.data);
                });
            }
          })
          .catch((error) => {
            console.log(error.data);
          });

        axios
        .post(`cart/${cartId}/clear`)
        .then((response) => {
          console.log("clear cart success", cartId);
        })
        .catch((error) => {
          console.log(error.data);
        });
      })
      .catch((error) => {
        console.log(error.data);
      });
  }, [
    orderId,
    vnp_ResponseCode,
    vnp_TransactionNo,
    vnp_Amount,
    vnp_OrderInfo,
    vnp_TransactionStatus,
    vnp_PayDate,
    order,
    vnp_TxnRef,
    cartId,
  ]);

  return (
    <div>
      <div className="flex px-40 justify-between shadow-sm py-10">
        <div className="text-2xl font-bold text-gray-300">1. Shopping Cart</div>
        <h1 className="text-2xl font-bold text-gray-300 ">
          2. Shipping Details
        </h1>
        <h1 className="text-2xl font-bold">3. Payment Options</h1>
      </div>

      <div className="px-40">
        <div className="flex justify-between">
          <div className=" font-bold my-10 text-xl w-full items-center justify-center flex flex-col">
            <div className="flex flex-col justify-center items-center mt-40">
              <div className="text-6xl	">PAYMENT RESULT</div>
              <div className="text-2xl mt-10">
                {vnp_ResponseCode === "00" ? "Sucessfull" : "Failed"}
              </div>
            </div>

            <div className="flex mt-40">
              <Link to="/">
                <div className="bg-black text-white px-20 py-2 rounded-xl mr-4">
                  Done
                </div>
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Result;
