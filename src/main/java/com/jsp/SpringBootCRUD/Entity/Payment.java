package com.jsp.SpringBootCRUD.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int payment_id;

    public float amount;
    
    @DateTimeFormat(pattern = "dd-mm-yy")
    @JsonFormat
    public LocalDate date;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "payment_type_id")
//    @JsonIgnore
    public PaymentType payment_type;

    // public int payment_type_id;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "customer_id")
//    @JsonIgnore
    public Customer customer;

    // public int customer_id;

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PaymentType getPaymentType() {
        return payment_type;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.payment_type = paymentType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment(float amount, LocalDate date, PaymentType paymentType, Customer customer) {
        this.amount = amount;
        this.date = date;
        this.payment_type = paymentType;
        this.customer = customer;
    }

    public Payment(){}
}
