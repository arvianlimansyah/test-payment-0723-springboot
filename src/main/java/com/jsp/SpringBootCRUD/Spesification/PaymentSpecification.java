package com.jsp.SpringBootCRUD.Spesification;

import com.jsp.SpringBootCRUD.Entity.Customer;
import com.jsp.SpringBootCRUD.Entity.Payment;
import com.jsp.SpringBootCRUD.Entity.PaymentType;
import com.jsp.SpringBootCRUD.Dto.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;

public class PaymentSpecification implements Specification<Payment> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate
            (Root<Payment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }

    public static Specification<Payment> byPaymentType(String paymentType) {
        return (root, query, criteriaBuilder) -> {
            Join<Payment, PaymentType> roleJoin = root.join("payment_type");
            return criteriaBuilder.equal(roleJoin.get("type_name"), paymentType);
        };
    }

    public static Specification<Payment> byCustomerId(int customerId) {
        return (root, query, criteriaBuilder) -> {
            Join<Payment, Customer> roleJoin = root.join("customer");
            return criteriaBuilder.equal(roleJoin.get("customer_id"), customerId);
        };
    }

    public static Specification<Payment> byDate(LocalDate date) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("date"), date);
        };
    }

    public PaymentSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }
}
