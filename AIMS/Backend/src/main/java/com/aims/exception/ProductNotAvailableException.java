package com.aims.exception;;

/**
 * The MediaNotAvailableException wraps all unchecked exceptions You can use this
 * exception to inform
 *
 * @author nguyenlm
 */
public class ProductNotAvailableException extends AIMSException {

    private static final long serialVersionUID = 1091337136123906298L;

    public ProductNotAvailableException() {

    }

    public ProductNotAvailableException(String message) {
        super(message);
    }

}