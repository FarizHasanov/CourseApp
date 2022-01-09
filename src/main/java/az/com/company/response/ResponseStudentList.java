package az.com.company.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseStudentList {
    private List<ResponseStudent> studentList;
    private ResponseStatus status;
}
