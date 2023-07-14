package com.jsp.SpringBootCRUD.Repository;

import com.jsp.SpringBootCRUD.Entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentTypeRepository  extends JpaRepository<PaymentType, Integer> {
    @Query(value = "SELECT * FROM payment_type pt WHERE pt.type_name = ?1", nativeQuery = true)
    Optional<PaymentType> findByTypeName(String typeName);
}
