package az.com.company.response;

import lombok.Data;

import java.util.Date;

@Data
public class ResponsePayment {

    private Long paymentId;
    private ResponseStudent student;
    private ResponseTeacher teacher;
    private ResponseLesson lesson;
    private Double amount;
    private Date payDate;
    private ResponseStatus status;
}
