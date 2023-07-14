package com.jsp.SpringBootCRUD.Repository;

import com.jsp.SpringBootCRUD.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer, Integer> {
}
