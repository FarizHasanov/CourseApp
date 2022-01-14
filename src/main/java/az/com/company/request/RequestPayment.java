package az.com.company.request;

import az.com.company.model.Lesson;
import az.com.company.model.Student;
import az.com.company.model.Teacher;
import lombok.Data;

import java.util.Date;

@Data
public class RequestPayment {
    private Long paymentId;
    private Student student;
    private Teacher teacher;
    private Lesson lesson;
    private Date payDate;
    private Double amount;
}
