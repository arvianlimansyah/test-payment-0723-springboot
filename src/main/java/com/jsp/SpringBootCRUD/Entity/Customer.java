package com.jsp.SpringBootCRUD.Entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int customer_id;

    public String customer_name;

    public String customer_email;
}
