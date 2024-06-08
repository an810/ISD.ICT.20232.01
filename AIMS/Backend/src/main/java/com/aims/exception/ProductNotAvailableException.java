package com.aims.exception;;

/**
 * The MediaNotAvailableException wraps all unchecked exceptions You can use this
 * exception to inform
 */
public class ProductNotAvailableException extends AIMSException {

    public ProductNotAvailableException() {

    }

    public ProductNotAvailableException(String message) {
        super(message);
    }

}