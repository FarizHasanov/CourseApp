package az.com.company.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponsePaymentList {
    private List<ResponsePayment> paymentList;
    private ResponseStatus status;
}
