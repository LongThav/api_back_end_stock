package com.learn.api.constants.Error;

public class ErrorDetail {
    private Integer saleOrderId;
    private String message;

    public ErrorDetail(Integer saleOrderId, String message) {
        this.saleOrderId = saleOrderId;
        this.message = message;
    }

    public Integer getSaleOrderId() {
        return saleOrderId;
    }

    public String getMessage() {
        return message;
    }
}
