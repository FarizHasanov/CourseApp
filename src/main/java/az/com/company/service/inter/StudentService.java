package az.com.company.service.inter;

import az.com.company.request.RequestStudent;
import az.com.company.request.RequestToken;
import az.com.company.response.ResponseStatus;
import az.com.company.response.ResponseStudent;
import az.com.company.response.ResponseStudentList;

public interface StudentService {

    ResponseStudentList getStudentList(RequestToken requestToken);

    ResponseStudent getStudentById(Long studentId);

    ResponseStatus addStudent(RequestStudent requestStudent);

    ResponseStatus updateStudent(RequestStudent requestStudent);

    ResponseStatus deleteStudent(Long studentId);
}
