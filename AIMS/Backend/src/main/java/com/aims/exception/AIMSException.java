package com.aims.exception;;

public class AIMSException extends RuntimeException {

    public AIMSException() {
        super("Something went wrong!");
    }
    public AIMSException(String message) {
        super(message);
    }
}