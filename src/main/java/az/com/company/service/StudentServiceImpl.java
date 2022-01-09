package az.com.company.service;

import az.com.company.dao.StudentDao;
import az.com.company.model.Student;
import az.com.company.response.ResponseStatus;
import az.com.company.response.ResponseStudent;
import az.com.company.response.ResponseStudentList;
import az.com.company.util.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public ResponseStudentList getStudentList() {
        ResponseStudentList response = new ResponseStudentList();
        List<ResponseStudent> responseStudentList = new ArrayList<>();
        try {
            List<Student> studentList = studentDao.getStudentList();
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
            Student student = studentDao.getStudentById(studentId);
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


}
