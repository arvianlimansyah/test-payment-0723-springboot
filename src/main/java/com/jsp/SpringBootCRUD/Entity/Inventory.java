package com.jsp.SpringBootCRUD.Entity;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int item_id;
    public String item_name;
    public float quantity;
    public float price;
}
