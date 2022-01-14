package az.com.company.controller;

import az.com.company.request.RequestPayment;
import az.com.company.response.ResponsePayment;
import az.com.company.response.ResponsePaymentList;
import az.com.company.response.ResponseStatus;
import az.com.company.service.inter.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentWebServices {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/getPaymentList")
    public ResponsePaymentList getPaymentList(){
        return paymentService.getPaymentList();
    }

    @GetMapping(value = "/getPaymentById")
    public ResponsePayment getPaymentById(@RequestParam("paymentId") Long paymentId){
        return paymentService.getPaymentById(paymentId);
    }

    @PostMapping(value = "/addPayment")
    public ResponseStatus addPayment(@RequestBody RequestPayment requestPayment){
        return paymentService.addPayment(requestPayment);
    }

    @PutMapping(value = "/updatePayment")
    public ResponseStatus updatePayment(@RequestBody RequestPayment requestPayment){
        return paymentService.updatePayment(requestPayment);
    }

    @PutMapping(value = "/deletePayment")
    public  ResponseStatus deletePayment(@RequestParam("paymentId") Long paymentId){
        return paymentService.deletePayment(paymentId);
    }
}
