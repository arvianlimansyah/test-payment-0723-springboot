package com.jsp.SpringBootCRUD.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CreatePaymentRequest {

    public float amount;

    public String date;

    public int customer_id;

    public String payment_type;

    public float getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getPaymentType() {
        return payment_type;
    }

    public int getCustomerId() {
        return customer_id;
    }
}
