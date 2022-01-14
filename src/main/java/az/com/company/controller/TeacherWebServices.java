package az.com.company.controller;

import az.com.company.request.RequestTeacher;
import az.com.company.response.ResponseStatus;
import az.com.company.response.ResponseTeacher;
import az.com.company.response.ResponseTeacherList;
import az.com.company.service.inter.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherWebServices {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/getTeacherList")
    public ResponseTeacherList getTeacherList(){
        return teacherService.getTeacherList();
    }

    @GetMapping(value = "/getTeacherById")
    public ResponseTeacher getTeacherById(@RequestParam(value = "teacherId") Long teacherId){
        return teacherService.getTeacherById(teacherId);
    }

    @PostMapping(value = "/addTeacher")
    public ResponseStatus addTeacher(@RequestBody RequestTeacher requestTeacher){
        return teacherService.addTeacher(requestTeacher);
    }

    @PutMapping(value = "/updateTeacher")
    public ResponseStatus updateTeacher(@RequestBody RequestTeacher requestTeacher){
        return teacherService.updateTeacher(requestTeacher);
    }

    @PutMapping(value = "/deleteTeacher")
    public ResponseStatus deleteTeacher(@RequestParam(value = "teacherId") Long teacherId){
        return teacherService.deleteTeacher(teacherId);
    }
}
