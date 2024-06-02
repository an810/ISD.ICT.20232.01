package com.aims.utils;

import com.aims.entity.order.Order;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;


public class Utils {

    public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    static Logger LOGGER = getLogger(Utils.class.getName());

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
    }

    public static Logger getLogger(String className) {
        return Logger.getLogger(className);
    }

    public static String getCurrencyFormat(int num) {
        Locale vietname = new Locale("vi", "VN");
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(vietname);
        return defaultFormat.format(num);
    }


    public static String getToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String md5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Utils.getLogger(Utils.class.getName());
            digest = "";
        }
        return digest;
    }

    public static void processDeliveryInfo(Order order) throws InterruptedException, IOException {
        LOGGER.info("Process Delivery Info");
        HashMap<String, String> info = new HashMap<>();
        info.put("customerName", order.getDeliveryInfo().getReceiverName());
        info.put("customerPhone", order.getDeliveryInfo().getPhoneNumber());
        info.put("customerAddress",order.getDeliveryInfo().getAddress());
        LOGGER.info(info.toString());
        Validates.validateDeliveryInfo(info);
    }

    public static double calculateShippingFee(Double total) {
        Random rand = new Random();
        double fees = (double) (((rand.nextFloat() * 10) / 100) * total);
        LOGGER.info("Order Amount: " + total + " -- Shipping Fees: " + fees);
        return fees;
    }

    public static String convertPaymentTimeFormat(String input) {
        try {
            // Định dạng của đầu vào
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            // Định dạng của đầu ra
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            // Chuyển đổi từ chuỗi sang đối tượng Date
            Date date = inputFormat.parse(input);

            // Chuyển đổi từ Date sang chuỗi theo định dạng mong muốn
            return outputFormat.format(date);
        } catch (ParseException e) {
            // Xử lý nếu có lỗi khi chuyển đổi
            System.err.println("Error converting date: " + e.getMessage());
            return null; // hoặc giá trị mặc định khác tùy ý
        }
    }
    public static String convertDateFormat(String inputDate) {
        try {
            // Định dạng của chuỗi ngày giờ đầu vào
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            // Định dạng của chuỗi ngày giờ đầu ra
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMddHHmmss");

            // Chuyển đổi chuỗi ngày giờ thành đối tượng Date
            Date date = inputFormat.parse(inputDate);

            // Chuyển đổi đối tượng Date thành chuỗi ngày giờ theo định dạng mới
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace(); // Xử lý lỗi theo yêu cầu của bạn
            return null;
        }
    }
    public static Map<String, String> parseQueryString(String query) {
        Map<String, String> params = new HashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }

}