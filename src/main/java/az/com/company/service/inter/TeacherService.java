package az.com.company.service.inter;

import az.com.company.request.RequestTeacher;
import az.com.company.response.ResponseStatus;
import az.com.company.response.ResponseTeacher;
import az.com.company.response.ResponseTeacherList;

public interface TeacherService {

    ResponseTeacherList getTeacherList();

    ResponseTeacher getTeacherById(Long teacherId);

    ResponseStatus addTeacher(RequestTeacher requestTeacher);

    ResponseStatus updateTeacher(RequestTeacher requestTeacher);

    ResponseStatus deleteTeacher(Long teacherId);
}
