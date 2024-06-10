package com.aims.exception.handler;

import com.aims.entity.response.AIMSResponse;
import com.aims.exception.AIMSException;
import com.aims.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AIMSException.class)
    public ResponseEntity<AIMSResponse<Void>> handleAIMSException(AIMSException e) {
        System.out.println("AIMSException caught: " + e.getMessage());
        AIMSResponse<Void> response = new AIMSResponse<>(Constants.ERROR_CODE, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AIMSResponse<Void>> handleGenericException(Exception ex) {
        System.out.println("Exception caught: " + ex.getMessage());
        AIMSResponse<Void> response = new AIMSResponse<>(Constants.ERROR_CODE, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
