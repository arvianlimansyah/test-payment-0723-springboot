package com.jsp.SpringBootCRUD.Dto;

public class PaymentDataResponse {
    private Integer payment_id;

    private Integer customer_id;

    private String payment_type;

    private float amount;

    private String date;

    public PaymentDataResponse(Integer payment_id, Integer customer_id, String payment_type, float amount, String date) {
        this.payment_id = payment_id;
        this.customer_id = customer_id;
        this.payment_type = payment_type;
        this.amount = amount;
        this.date = date;
    }

    public PaymentDataResponse(){}

    public Integer getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Integer payment_id) {
        this.payment_id = payment_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}