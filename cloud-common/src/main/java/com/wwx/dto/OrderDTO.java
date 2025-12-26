package com.wwx.dto;

import java.io.Serializable;

public class OrderDTO implements Serializable {

    private String orderId;
    private String customerId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
