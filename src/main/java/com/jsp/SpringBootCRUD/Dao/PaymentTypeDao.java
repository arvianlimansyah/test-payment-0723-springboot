package com.jsp.SpringBootCRUD.Dao;

import com.jsp.SpringBootCRUD.Entity.PaymentType;
import com.jsp.SpringBootCRUD.Repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PaymentTypeDao {

    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    public PaymentType getPaymentTypeById(int id) {
        Optional<PaymentType> optional = paymentTypeRepository.findById(id);
        if(optional.isEmpty()) {
            return null;
        } else return optional.get();
    }

    public PaymentType getPaymentTypeByName(String id) {
        Optional<PaymentType> optional = paymentTypeRepository.findByTypeName(id);
        if(optional.isEmpty()) {
            return null;
        } else return optional.get();
    }
}
