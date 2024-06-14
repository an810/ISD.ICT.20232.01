import Summary from "../components/Summary";
import { Link } from "react-router-dom";
import { useLocation } from 'react-router-dom';
import axios from "axios";
import { getItemFromLocalStorage, removeItemFromLocalStorage } from "../utils";
import { useEffect } from "react";
const Result = () => {
  const location = useLocation();
  const orderId = getItemFromLocalStorage("orderId");
  const order = {};
  const queryParams = new URLSearchParams(location.search);
  const vnp_Amount = queryParams.get('vnp_Amount');
  // const vnp_BankCode = queryParams.get('vnp_BankCode');
  // const vnp_BankTranNo = queryParams.get('vnp_BankTranNo');
  // const vnp_CardType = queryParams.get('vnp_CardType');
  const vnp_OrderInfo = queryParams.get('vnp_OrderInfo');
  const vnp_PayDate = queryParams.get('vnp_PayDate');
  const vnp_ResponseCode = queryParams.get('vnp_ResponseCode');
  // const vnp_TmnCode = queryParams.get('vnp_TmnCode');
  const vnp_TransactionNo = queryParams.get('vnp_TransactionNo');
  const vnp_TransactionStatus = queryParams.get('vnp_TransactionStatus');
  const vnp_TxnRef = queryParams.get('vnp_TxnRef');
  // const vnp_TxnRef = queryParams.get('vnp_TxnRef');
  // const vnp_SecureHash = queryParams.get('vnp_SecureHash');
  
  useEffect(() => {
    axios.post("payment/save-payment-transaction",{
      orderId: orderId,
      errorCode: vnp_ResponseCode,
      transactionNum : vnp_TransactionNo,
      amount : vnp_Amount,
      transactionContent : vnp_OrderInfo,
      message: vnp_TransactionStatus,
      createdAt : vnp_PayDate,
      vnpTxnRef : vnp_TxnRef
    }).then((response) => {
      console.log(response.data);
    }).catch((error) => {
      console.log(error.data);
    });

    // get order by orderId
    axios.get(`order/${orderId}`).then((response) => {
      order = response.data.data;
    }).catch((error) => {
      console.log(error.data);
    });

    const invoice = {
      invoiceId: 'INV12345',
      order,
      paymentTransaction: {
        transactionId: 'TRANS67890',
        orderId: 'ORD54321',
        errorCode: '00',
        amount: 100000,
        transactionNum: 'TXN789',
        transactionContent: 'Payment for order #ORD54321',
        message: 'Transaction successful',
        createdAt: '2023-06-14T10:30:00Z',
        vnpTxnRef: 'TXNREF123'
      }
    };

    axios.post("invoice/create", invoice).then((response) => {
      console.log(response.data);
    }).catch((error) => {
      console.log(error.data);
    } );
  }, [orderId, vnp_ResponseCode, vnp_TransactionNo, vnp_Amount, vnp_OrderInfo, vnp_TransactionStatus, vnp_PayDate])

  // // clear cart if the result is successful
  // if (vnp_ResponseCode === "00") {
  //   axios
  //     .post(`cart/${cartId}/clear`)
  //     .then((response) => {
  //       console.log(response.data);
  //     })
  //     .catch((error) => {
  //       console.log(error.data);
  //     });
  // }

  
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
              <div className="text-2xl mt-10">{vnp_ResponseCode === "00" ? "Sucessfull" : "Failed"}</div>
            </div>

            <div className="flex mt-40">
              <Link to="/">
                <div className="bg-black text-white px-20 py-2 rounded-xl mr-4">
                  Done
                </div>
              </Link>
            </div>
          </div>
          <Summary />
        </div>
      </div>
    </div>
  );
};

export default Result;
