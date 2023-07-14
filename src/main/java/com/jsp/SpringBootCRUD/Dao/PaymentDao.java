package com.jsp.SpringBootCRUD.Dao;

import com.jsp.SpringBootCRUD.Entity.Payment;
import com.jsp.SpringBootCRUD.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaymentDao {
    @Autowired
    PaymentRepository paymentRepository;

    public Payment createPaymentRecord(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    public Page<Payment> getPaymentBySpecification(int pageNumber, Specification spec){
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Payment> result = paymentRepository.findAll(spec, pageable);

        return result;
    }

    public Payment getPaymentRecordById(int id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if(optional.isEmpty()) {
            return null;
        } else return optional.get();
    }

    public Payment updatePaymentRecord(Payment payment, int id) {
        Payment paymentRecord = getPaymentRecordById(id);
        if(paymentRecord != null) {
            paymentRecord.setPaymentType(payment.getPaymentType());
            paymentRecord.setAmount(payment.getAmount());
            paymentRecord.setCustomer(payment.getCustomer());
            paymentRecord.setDate(payment.getDate());
            return paymentRepository.save(paymentRecord);
        } else return null;
    }

    // delete student
    public Boolean deletePaymentRecord(int id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if(optional.isPresent()) {
            paymentRepository.delete(optional.get());
            return true;
        } else return false;
    }

}
