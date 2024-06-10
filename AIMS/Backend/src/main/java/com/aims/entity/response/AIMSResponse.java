package com.aims.entity.response;

import lombok.Data;

@Data
public class AIMSResponse<T> {
    private int status;
    private String message;
    private T data;

    public AIMSResponse(int status, String message) {
         this.status = status;
         this.message = message;
    }

    public AIMSResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
