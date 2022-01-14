package az.com.company.service.impl;

import az.com.company.enums.EnumAvailableStatus;
import az.com.company.model.Student;
import az.com.company.repository.StudentDao;
import az.com.company.request.RequestStudent;
import az.com.company.response.ResponseStatus;
import az.com.company.response.ResponseStudent;
import az.com.company.response.ResponseStudentList;
import az.com.company.service.inter.StudentService;
import az.com.company.util.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private az.com.company.dao.StudentDao studentDao;

    @Autowired
    private StudentDao studentDao1;

    @Override
    public ResponseStudentList getStudentList() {
        ResponseStudentList response = new ResponseStudentList();
        List<ResponseStudent> responseStudentList = new ArrayList<>();
        try {
//            List<Student> studentList = studentDao.getStudentList();
            List<Student> studentList=studentDao1.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (studentList.isEmpty()) {
                response.setStatus(new ResponseStatus(ExceptionConstants.STUDENT_NOT_FOUND, "Student not found"));
                return response;
            }
            for (Student student : studentList) {
                ResponseStudent responseStudent = new ResponseStudent();
                responseStudent.setId(student.getId());
                responseStudent.setName(student.getName());
                responseStudent.setSurname(student.getSurname());
                responseStudent.setDob(student.getDob());
                responseStudent.setAddress(student.getAddress());
                responseStudent.setPhone(student.getPhone());
                responseStudentList.add(responseStudent);
            }
            response.setStudentList(responseStudentList);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    @Override
    public ResponseStudent getStudentById(Long studentId) {
        ResponseStudent response = new ResponseStudent();
        try {
            if (studentId == null) {
                response.setStatus(new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                return response;
            }
//            Student student = studentDao.getStudentById(studentId);
            Student student=studentDao1.findStudentByIdAndActive(studentId, EnumAvailableStatus.ACTIVE.getValue());
            if (student == null) {
                response.setStatus(new ResponseStatus(ExceptionConstants.STUDENT_NOT_FOUND, "Student not found"));
                return response;
            }
            response.setId(student.getId());
            response.setName(student.getName());
            response.setSurname(student.getSurname());
            response.setDob(student.getDob());
            response.setAddress(student.getAddress());
            response.setPhone(student.getPhone());
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    @Override
    public ResponseStatus addStudent(RequestStudent requestStudent) {
        ResponseStatus responseStatus = null;
        String name = requestStudent.getName();
        String surname = requestStudent.getSurname();
        Date dob = requestStudent.getDob();
        String address = requestStudent.getAddress();
        String phone = requestStudent.getPhone();
        try {
            if (name == null || surname == null) {
                return new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Student student = new Student();
            student.setName(name);
            student.setSurname(surname);
            student.setDob(dob);
            student.setAddress(address);
            student.setPhone(phone);
//            studentDao.addStudent(student);
            studentDao1.save(student);
            responseStatus = ResponseStatus.getSuccessMessage();

        } catch (Exception ex) {
            ex.printStackTrace();
            responseStatus = new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception");
        }
        return responseStatus;
    }

    @Override
    public ResponseStatus updateStudent(RequestStudent requestStudent) {
        ResponseStatus responseStatus = null;
        Long studentId = requestStudent.getStudentId();
        String name = requestStudent.getName();
        String surname = requestStudent.getSurname();
        Date dob = requestStudent.getDob();
        String address = requestStudent.getAddress();
        String phone = requestStudent.getPhone();
        try {
            if (studentId == null || name == null || surname == null) {
                return new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Student student = studentDao1.findStudentByIdAndActive(studentId,EnumAvailableStatus.ACTIVE.getValue());
            if (student == null) {
                responseStatus = new ResponseStatus(ExceptionConstants.STUDENT_NOT_FOUND, "Student not found");
                return responseStatus;
            }
            student.setName(name);
            student.setSurname(surname);
            student.setDob(dob);
            student.setAddress(address);
            student.setPhone(phone);
//            studentDao.updateStudent(student);
            studentDao1.save(student);
            responseStatus = ResponseStatus.getSuccessMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
            responseStatus = new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception");
        }
        return responseStatus;
    }

    @Override
    public ResponseStatus deleteStudent(Long studentId) {
        ResponseStatus responseStatus = null;
        try {
            if (studentId == null) {
                responseStatus = new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
                return responseStatus;
            }
            Student student = studentDao1.findStudentByIdAndActive(studentId,EnumAvailableStatus.ACTIVE.getValue());
            if (student == null) {
                responseStatus = new ResponseStatus(ExceptionConstants.STUDENT_NOT_FOUND, "Student not found");
                return responseStatus;
            }
//            studentDao.deleteStudent(studentId);
            student.setActive(EnumAvailableStatus.DEACTIVE.getValue());
            studentDao1.save(student);
            responseStatus = ResponseStatus.getSuccessMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
            responseStatus = new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception");
        }
        return responseStatus;
    }


}
