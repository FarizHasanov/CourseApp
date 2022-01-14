package az.com.company.service.impl;

import az.com.company.enums.EnumAvailableStatus;
import az.com.company.model.Lesson;
import az.com.company.model.Payment;
import az.com.company.model.Student;
import az.com.company.model.Teacher;
import az.com.company.repository.PaymentDao;
import az.com.company.request.RequestPayment;
import az.com.company.response.*;
import az.com.company.service.inter.PaymentService;
import az.com.company.util.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public ResponsePaymentList getPaymentList() {
        ResponsePaymentList response = new ResponsePaymentList();
        List<ResponsePayment> responsePaymentList = new ArrayList<>();
        try {
            List<Payment> paymentList = paymentDao.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (paymentList.isEmpty()) {
                response.setStatus(new ResponseStatus(ExceptionConstants.PAYMENT_NOT_FOUND, "Payment not found"));
                return response;
            }
            for (Payment payment : paymentList) {
                ResponsePayment responsePayment = new ResponsePayment();
                responsePayment.setPaymentId(payment.getId());
                responsePayment.setPayDate(payment.getPayDate());
                responsePayment.setAmount(payment.getAmount());
                ResponseStudent responseStudent = new ResponseStudent();
                responseStudent.setId(payment.getStudent().getId());
                responseStudent.setName(payment.getStudent().getName());
                responseStudent.setSurname(payment.getStudent().getSurname());
                responsePayment.setStudent(responseStudent);
                ResponseTeacher responseTeacher = new ResponseTeacher();
                responseTeacher.setId(payment.getTeacher().getId());
                responseTeacher.setName(payment.getTeacher().getName());
                responseTeacher.setSurname(payment.getTeacher().getSurname());
                responsePayment.setTeacher(responseTeacher);
                ResponseLesson responseLesson = new ResponseLesson();
                responseLesson.setLessonId(payment.getLesson().getId());
                responseLesson.setLessonName(payment.getLesson().getName());
                responsePayment.setLesson(responseLesson);
                responsePaymentList.add(responsePayment);
            }
            response.setPaymentList(responsePaymentList);
            response.setStatus(ResponseStatus.getSuccessMessage());

        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public ResponsePayment getPaymentById(Long paymentId) {
        ResponsePayment responsePayment = new ResponsePayment();
        try {
            if (paymentId == null) {
                responsePayment.setStatus(new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                return responsePayment;
            }
            Payment payment = paymentDao.findPaymentByIdAndActive(paymentId, EnumAvailableStatus.ACTIVE.getValue());
            if (payment == null) {
                responsePayment.setStatus(new ResponseStatus(ExceptionConstants.PAYMENT_NOT_FOUND, "Payment not found"));
                return responsePayment;
            }
            responsePayment.setPaymentId(payment.getId());
            responsePayment.setAmount(payment.getAmount());
            responsePayment.setPayDate(payment.getPayDate());
            ResponseStudent responseStudent = new ResponseStudent();
            responseStudent.setId(payment.getStudent().getId());
            responseStudent.setName(payment.getStudent().getName());
            responseStudent.setSurname(payment.getStudent().getSurname());
            responsePayment.setStudent(responseStudent);
            ResponseTeacher responseTeacher = new ResponseTeacher();
            responseTeacher.setId(payment.getTeacher().getId());
            responseTeacher.setName(payment.getTeacher().getName());
            responseTeacher.setSurname(payment.getTeacher().getSurname());
            responsePayment.setTeacher(responseTeacher);
            ResponseLesson responseLesson = new ResponseLesson();
            responseLesson.setLessonId(payment.getLesson().getId());
            responseLesson.setLessonName(payment.getLesson().getName());
            responsePayment.setLesson(responseLesson);
        } catch (Exception ex) {
            ex.printStackTrace();
            responsePayment.setStatus(new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return responsePayment;
    }

    @Override
    public ResponseStatus addPayment(RequestPayment requestPayment) {
        ResponseStatus responseStatus = null;
        Student student = requestPayment.getStudent();
        Teacher teacher = requestPayment.getTeacher();
        Lesson lesson = requestPayment.getLesson();
        Double amount = requestPayment.getAmount();
        try {
            if (student == null || teacher == null) {
                return new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Payment payment = new Payment();
            payment.setStudent(student);
            payment.setTeacher(teacher);
            payment.setLesson(lesson);
            payment.setAmount(amount);
            paymentDao.save(payment);
            responseStatus = ResponseStatus.getSuccessMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
            responseStatus = new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception");
        }
        return responseStatus;
    }

    @Override
    public ResponseStatus updatePayment(RequestPayment requestPayment) {
        return null;
    }

    @Override
    public ResponseStatus deletePayment(Long paymentId) {
        return null;
    }
}
