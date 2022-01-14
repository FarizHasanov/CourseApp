package az.com.company.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseLessonList {
    private List<ResponseLesson> lessonList;
    private ResponseStatus status;
}
