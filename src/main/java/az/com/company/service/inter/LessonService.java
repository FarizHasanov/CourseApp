package az.com.company.service.inter;


import az.com.company.request.RequestLesson;
import az.com.company.response.ResponseLesson;
import az.com.company.response.ResponseLessonList;
import az.com.company.response.ResponseStatus;

public interface LessonService {

    ResponseLessonList getLessonList();

    ResponseLesson getLessonById(Long lessonId);

    ResponseStatus addLesson(RequestLesson requestLesson);

    ResponseStatus updateLesson(RequestLesson requestLesson);

    ResponseStatus deleteLesson(Long lessonId);

}
