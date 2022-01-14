package az.com.company.controller;

import az.com.company.request.RequestLesson;
import az.com.company.response.ResponseLesson;
import az.com.company.response.ResponseLessonList;
import az.com.company.response.ResponseStatus;
import az.com.company.service.inter.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/lesson")
public class LessonWebServices {

    @Autowired
    private LessonService lessonService;

    @RequestMapping(value = "/getLessonList")
    public ResponseLessonList getLessonList(){
        return lessonService.getLessonList();
    }

    @GetMapping(value = "getLessonById")
    public ResponseLesson getLessonById(@RequestParam(value = "lessonId") Long lessonId){
        return lessonService.getLessonById(lessonId);
    }

    @PostMapping(value = "/addLesson")
    public ResponseStatus addLesson(@RequestBody RequestLesson requestLesson){
        return lessonService.addLesson(requestLesson);
    }

    @PutMapping(value = "/updateLesson")
    public ResponseStatus  updateLesson(@RequestBody RequestLesson requestLesson){
        return lessonService.updateLesson(requestLesson);
    }

    @PutMapping(value = "/deleteLesson")
    public ResponseStatus deleteLesson(Long lessonId){
        return lessonService.deleteLesson(lessonId);
    }
}
