package com.aims.exception;;

/**
 * The AimsException wraps all unchecked exceptions You can use this
 * exception to inform
 *
 * @author nguyenlm
 */
public class AIMSException extends RuntimeException {

    public AIMSException() {

    }

    public AIMSException(String message) {
        super(message);
    }
}