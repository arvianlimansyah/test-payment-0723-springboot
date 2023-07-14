package com.jsp.SpringBootCRUD.Dto;

public class PaymentFilter {
    private Integer customer_id;

    private String payment_type;

    private float amount;

    public String date;

    public Integer getCustomer_id() {
        return customer_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public float getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
