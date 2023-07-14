package com.jsp.SpringBootCRUD.Entity;

import javax.persistence.*;

@Entity
@Table(name = "payment_type")
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int payment_type_id;

    @Column(name = "type_name")
    public String type_name;
}
