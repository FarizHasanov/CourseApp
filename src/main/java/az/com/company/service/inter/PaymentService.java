package az.com.company.service.inter;

import az.com.company.request.RequestPayment;
import az.com.company.response.ResponsePayment;
import az.com.company.response.ResponsePaymentList;
import az.com.company.response.ResponseStatus;

public interface PaymentService {
    ResponsePaymentList getPaymentList();

    ResponsePayment getPaymentById(Long paymentId);

    ResponseStatus addPayment(RequestPayment requestPayment);

    ResponseStatus updatePayment(RequestPayment requestPayment);

    ResponseStatus deletePayment(Long paymentId);
}
