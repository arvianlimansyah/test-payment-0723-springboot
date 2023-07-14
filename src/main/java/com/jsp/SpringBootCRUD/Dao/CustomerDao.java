package com.jsp.SpringBootCRUD.Dao;

import com.jsp.SpringBootCRUD.Entity.Customer;
import com.jsp.SpringBootCRUD.Entity.PaymentType;
import com.jsp.SpringBootCRUD.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomerById(int id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if(optional.isEmpty()) {
            return null;
        } else return optional.get();
    }
}
