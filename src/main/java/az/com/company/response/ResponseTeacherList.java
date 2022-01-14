package az.com.company.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseTeacherList {
    private List<ResponseTeacher> teacherList;
    private  ResponseStatus status;
}
