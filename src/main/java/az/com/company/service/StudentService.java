package az.com.company.service;

import az.com.company.response.ResponseStudent;
import az.com.company.response.ResponseStudentList;

public interface StudentService {

    ResponseStudentList getStudentList();
    ResponseStudent getStudentById(Long studentId);
}
