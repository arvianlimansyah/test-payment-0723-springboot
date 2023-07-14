package com.jsp.SpringBootCRUD.Repository;

import com.jsp.SpringBootCRUD.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentRepository  extends JpaRepository<Payment, Integer>, JpaSpecificationExecutor<Payment> {
}
