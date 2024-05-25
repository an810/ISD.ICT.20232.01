package com.aims.utils;

import com.aims.entity.Order.Order;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author nguyenlm Contains helper functions
 */
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

    /**
     * Return a {@link java.lang.String String} that represents the current time in the format of yyyy-MM-dd HH:mm:ss.
     *
     * @author hieudm
     * @return the current time as {@link java.lang.String String}.
     */
    public static String getToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Return a {@link java.lang.String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @author hieudm vnpay
     * @param message - plain text as {@link java.lang.String String}.
     * @return cipher text as {@link java.lang.String String}.
     */
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
        info.put("customerName", order.getUserName());
        info.put("customerPhone", order.getUserPhone());
        info.put("customerEmail", order.getUserEmail());
        info.put("customerAddress",order.getUserAddress());
        LOGGER.info(info.toString());
        Validates.validateDeliveryInfo(info);
    }
//    public static int calculateShippingFee(Order order) {
//        Random rand = new Random();
//        int fees = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount());
//        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
//        return fees;
//    }

}