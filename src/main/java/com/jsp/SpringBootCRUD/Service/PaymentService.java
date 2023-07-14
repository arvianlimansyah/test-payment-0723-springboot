package com.jsp.SpringBootCRUD.Service;

import com.jsp.SpringBootCRUD.Dao.CustomerDao;
import com.jsp.SpringBootCRUD.Dao.PaymentDao;
import com.jsp.SpringBootCRUD.Dao.PaymentTypeDao;
import com.jsp.SpringBootCRUD.Dto.CreatePaymentRequest;
import com.jsp.SpringBootCRUD.Dto.PaymentDataResponse;
import com.jsp.SpringBootCRUD.Entity.Customer;
import com.jsp.SpringBootCRUD.Entity.Payment;
import com.jsp.SpringBootCRUD.Dto.PaymentFilter;
import com.jsp.SpringBootCRUD.Dto.ResponseStructure;
import com.jsp.SpringBootCRUD.Dto.SearchCriteria;
import com.jsp.SpringBootCRUD.Entity.PaymentType;
import com.jsp.SpringBootCRUD.Spesification.PaymentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    PaymentTypeDao paymentTypeDao;

    @Autowired
    CustomerDao customerDao;

    public ResponseStructure<Payment> createPaymentRecord(CreatePaymentRequest input){
        ResponseStructure<Payment> responseStructure = new ResponseStructure<Payment>();
        PaymentType paymentTypeData = paymentTypeDao.getPaymentTypeByName(input.getPaymentType());
        Customer customerData = customerDao.getCustomerById(input.getCustomerId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(input.getDate(), formatter);
        Payment newData = new Payment(input.getAmount(), localDate, paymentTypeData, customerData);

        Payment newPaymentRecord = paymentDao.createPaymentRecord(newData);
        if(newPaymentRecord != null) {
            responseStructure.setData(newPaymentRecord);
            responseStructure.setStatusCode(HttpStatus.CREATED.value());
            responseStructure.setMessage("Payment created successfully");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseStructure.setMessage("Failed to create payment data");
        }
        return responseStructure;
    }

    public ResponseStructure<List<PaymentDataResponse>> getAllPaymentRecord(){
        ResponseStructure<List<PaymentDataResponse>> responseStructure = new ResponseStructure<List<PaymentDataResponse>>();
        List<Payment> payments = paymentDao.getAllPayment();
        if(payments.size() > 0){
            List<PaymentDataResponse> paymentData = mapToPaymentDtos(payments);
            responseStructure.setData(paymentData);
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Payment data obtained successfully");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseStructure.setMessage("Failed to get payment data");
        }

        return responseStructure;
    }

    public ResponseStructure<PaymentDataResponse> getPaymentRecordById(Integer id){
        ResponseStructure<PaymentDataResponse> responseStructure = new ResponseStructure<PaymentDataResponse>();
        Payment paymentRecord = paymentDao.getPaymentRecordById(id);
        
        if(paymentRecord != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            PaymentDataResponse result = new PaymentDataResponse(paymentRecord.getPayment_id(), paymentRecord.getCustomer().customer_id, paymentRecord.getPaymentType().type_name, paymentRecord.getAmount(), paymentRecord.getDate().format(formatter));

            responseStructure.setData(result);
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Payment data obtained successfully");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseStructure.setMessage("Failed to get payment data");
        }

        return responseStructure;
    }

    public ResponseStructure<Payment> updatePaymentRecord(CreatePaymentRequest input, Integer id){
        ResponseStructure<Payment> responseStructure = new ResponseStructure<Payment>();
        PaymentType paymentTypeData = paymentTypeDao.getPaymentTypeByName(input.getPaymentType());
        Customer customerData = customerDao.getCustomerById(input.getCustomerId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(input.getDate(), formatter);
        Payment payment = new Payment(input.getAmount(), localDate, paymentTypeData, customerData);


        Payment newPaymentRecord = paymentDao.updatePaymentRecord(payment, id);
        if(newPaymentRecord != null) {
            responseStructure.setData(newPaymentRecord);
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Payment updated successfully");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseStructure.setMessage("Failed to update payment data");
        }
        return responseStructure;
    }

    public ResponseStructure<String> deletePaymentRecord(Integer id){
        ResponseStructure<String> responseStructure = new ResponseStructure<String>();
        boolean isTrue = paymentDao.deletePaymentRecord(id);
        if(isTrue) {
            responseStructure.setData("Payment record removed");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Payment record  deleted successfully");
        } else {
            responseStructure.setData("Failed to delete");
            responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseStructure.setMessage("Failed to delete payment data");
        }
        return responseStructure;
    }

    public ResponseStructure<Page<Payment>> findPaymentRecordWithFilter(PaymentFilter filter, int page) {
        ResponseStructure<Page<Payment>> responseStructure = new ResponseStructure<Page<Payment>>();
        Specification<Payment> customerSpec = PaymentSpecification.byCustomerId(filter.getCustomer_id());
        Specification<Payment> typeSpec = PaymentSpecification.byPaymentType(filter.getPayment_type());
        Specification<Payment> spec = Specification.where(customerSpec).and(typeSpec);

        if (filter.getDate() != ""){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse(filter.getDate(), formatter);
            spec = spec.and(PaymentSpecification.byDate(localDate));
        }

        if (filter.getAmount() > 0){
            PaymentSpecification amountSpec = new PaymentSpecification(new SearchCriteria("amount", ":", filter.getAmount()));
            spec = spec.and(amountSpec);
        }

        Page<Payment> paymentRecords = paymentDao.getPaymentBySpecification(page, spec);
        if (paymentRecords.getSize() > 0) {
            responseStructure.setData(paymentRecords);
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Payment data obtained successfully");
        } else {
            responseStructure.setData(null);
            responseStructure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseStructure.setMessage("Failed to get payment data");
        }

        return responseStructure;
    }

    private List<PaymentDataResponse> mapToPaymentDtos(List<Payment> payments) {
        return payments.stream()
                .map(payment -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    PaymentDataResponse paymentDto = new PaymentDataResponse();
                    paymentDto.setPayment_id(payment.getPayment_id());
                    paymentDto.setPayment_type(payment.getPaymentType().type_name);
                    paymentDto.setCustomer_id(payment.getCustomer().customer_id);
                    paymentDto.setDate(payment.getDate().format(formatter));
                    paymentDto.setAmount(payment.getAmount());
                    return paymentDto;
                })
                .collect(Collectors.toList());
    }


}
