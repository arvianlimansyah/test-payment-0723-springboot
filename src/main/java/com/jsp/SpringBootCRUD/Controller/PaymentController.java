package com.jsp.SpringBootCRUD.Controller;

import com.jsp.SpringBootCRUD.Dto.CreatePaymentRequest;
import com.jsp.SpringBootCRUD.Dto.PaymentDataResponse;
import com.jsp.SpringBootCRUD.Entity.Payment;
import com.jsp.SpringBootCRUD.Dto.PaymentFilter;
import com.jsp.SpringBootCRUD.Dto.ResponseStructure;
import com.jsp.SpringBootCRUD.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/payment/create")
    public ResponseStructure<Payment> createPaymentRecord(@RequestBody CreatePaymentRequest payment){
        return paymentService.createPaymentRecord(payment);
    }

    @GetMapping("/payment")
    public ResponseStructure<List<PaymentDataResponse>> getAllPaymentRecord(){
        ResponseStructure<List<PaymentDataResponse>> result = paymentService.getAllPaymentRecord();

        return result;
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseStructure<PaymentDataResponse> getPaymentData(@PathVariable int paymentId){
        return paymentService.getPaymentRecordById(paymentId);
    }

    @PostMapping("/payment/update/{paymentId}")
    public ResponseStructure<Payment> updatePaymentRecord(@RequestBody CreatePaymentRequest payment, @PathVariable int paymentId){
        return paymentService.updatePaymentRecord(payment, paymentId);
    }

    @GetMapping("/payment/delete/{paymentId}")
    public ResponseStructure<String> deletePaymentRecord(@PathVariable int paymentId){
        return paymentService.deletePaymentRecord(paymentId);
    }

    @PostMapping("/payment/filter/{page}")
    public ResponseStructure<Page<Payment>> findPaymentRecordWithFilter(@RequestBody PaymentFilter paymentFilter, @PathVariable int page){
        return paymentService.findPaymentRecordWithFilter(paymentFilter, page);
    }
}
