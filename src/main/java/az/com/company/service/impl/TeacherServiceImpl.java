package az.com.company.service.impl;

import az.com.company.enums.EnumAvailableStatus;
import az.com.company.model.Teacher;
import az.com.company.repository.TeacherDao;
import az.com.company.request.RequestTeacher;
import az.com.company.response.ResponseStatus;
import az.com.company.response.ResponseTeacher;
import az.com.company.response.ResponseTeacherList;
import az.com.company.service.inter.TeacherService;
import az.com.company.util.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public ResponseTeacherList getTeacherList() {
        ResponseTeacherList response = new ResponseTeacherList();
        List<ResponseTeacher> responseTeacherList = new ArrayList<>();
        try {
            List<Teacher> teacherList = teacherDao.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (teacherList.isEmpty()) {
                response.setStatus(new ResponseStatus(ExceptionConstants.TEACHER_NOT_FOUND, "Teacher not found"));
                return response;
            }
            for (Teacher teacher : teacherList) {
                ResponseTeacher responseTeacher = new ResponseTeacher();
                responseTeacher.setId(teacher.getId());
                responseTeacher.setName(teacher.getName());
                responseTeacher.setSurname(teacher.getSurname());
                responseTeacher.setDob(teacher.getDob());
                responseTeacher.setAddress(teacher.getAddress());
                responseTeacher.setPhone(teacher.getPhone());
                responseTeacher.setWorkExperience(teacher.getWorkExperience());
                responseTeacherList.add(responseTeacher);
            }
            response.setTeacherList(responseTeacherList);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public ResponseTeacher getTeacherById(Long teacherId) {
        ResponseTeacher response = new ResponseTeacher();
        try {
            if (teacherId == null) {
                response.setStatus(new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                return response;
            }
            Teacher teacher = teacherDao.findTeacherByIdAndActive(teacherId, EnumAvailableStatus.ACTIVE.getValue());
            if (teacher == null) {
                response.setStatus(new ResponseStatus(ExceptionConstants.TEACHER_NOT_FOUND, "Teacher not found"));
                return response;
            }
            response.setId(teacher.getId());
            response.setName(teacher.getName());
            response.setSurname(teacher.getSurname());
            response.setAddress(teacher.getAddress());
            response.setPhone(teacher.getPhone());
            response.setDob(teacher.getDob());
            response.setWorkExperience(teacher.getWorkExperience());
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public ResponseStatus addTeacher(RequestTeacher requestTeacher) {
        ResponseStatus response = null;
        String name = requestTeacher.getName();
        String surname = requestTeacher.getSurname();
        Date dob = requestTeacher.getDob();
        String address = requestTeacher.getAddress();
        String phone = requestTeacher.getPhone();
        Integer workExperience = requestTeacher.getWorkExperience();
        try {
            if (name == null || surname == null) {
                response = new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
                return response;
            }
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setSurname(surname);
            teacher.setDob(dob);
            teacher.setAddress(address);
            teacher.setPhone(phone);
            teacher.setWorkExperience(workExperience);
            teacherDao.save(teacher);
            response = ResponseStatus.getSuccessMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception");
        }
        return response;
    }

    @Override
    public ResponseStatus updateTeacher(RequestTeacher requestTeacher) {
        ResponseStatus response = null;
        Long teacherId = requestTeacher.getTeacherId();
        String name = requestTeacher.getName();
        String surname = requestTeacher.getSurname();
        Date dob = requestTeacher.getDob();
        String address = requestTeacher.getAddress();
        String phone = requestTeacher.getPhone();
        Integer workExperience = requestTeacher.getWorkExperience();
        try {
            if (teacherId == null || name == null || surname == null) {
                response = new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
                return response;
            }
            Teacher teacher = teacherDao.findTeacherByIdAndActive(teacherId, EnumAvailableStatus.ACTIVE.getValue());
            if (teacher == null) {
                response = new ResponseStatus(ExceptionConstants.TEACHER_NOT_FOUND, "Teacher not found");
                return response;
            }
            teacher.setName(name);
            teacher.setSurname(surname);
            teacher.setDob(dob);
            teacher.setAddress(address);
            teacher.setPhone(phone);
            teacher.setWorkExperience(workExperience);
            teacherDao.save(teacher);
            response = ResponseStatus.getSuccessMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception");
        }
        return response;
    }

    @Override
    public ResponseStatus deleteTeacher(Long teacherId) {
        ResponseStatus response = null;
        try {
            if (teacherId == null) {
                response = new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invallid request data");
                return response;
            }
            Teacher teacher = teacherDao.findTeacherByIdAndActive(teacherId, EnumAvailableStatus.ACTIVE.getValue());
            if (teacher == null) {
                response = new ResponseStatus(ExceptionConstants.TEACHER_NOT_FOUND, "Teacher not found");
                return response;
            }
            teacher.setActive(EnumAvailableStatus.DEACTIVE.getValue());
            teacherDao.save(teacher);
            response = ResponseStatus.getSuccessMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception");
        }
        return response;
    }
}
