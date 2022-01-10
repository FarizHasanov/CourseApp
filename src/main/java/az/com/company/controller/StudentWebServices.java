package az.com.company.controller;

import az.com.company.request.RequestStudent;
import az.com.company.response.ResponseStatus;
import az.com.company.response.ResponseStudent;
import az.com.company.response.ResponseStudentList;
import az.com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/student")
public class StudentWebServices {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/getStudentList", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseStudentList getStudentList() {
        return studentService.getStudentList();
    }

    @GetMapping(value = "/getStudentById")
    public ResponseStudent getStudentById(@RequestParam("studentId") Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PostMapping(value = "/addStudent")
    public ResponseStatus addStudent(@RequestBody RequestStudent requestStudent){
        return studentService.addStudent(requestStudent);
    }

    @PutMapping(value = "/updateStudent")
    public ResponseStatus updateStudent(@RequestBody RequestStudent requestStudent){
        return studentService.updateStudent(requestStudent);
    }

    @PutMapping(value = "/deleteStudent")
    public ResponseStatus deleteStudent(@RequestParam("studentId") Long studentId){
        return studentService.deleteStudent(studentId);
    }
}
