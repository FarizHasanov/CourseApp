package az.com.company.service.impl;

import az.com.company.enums.EnumAvailableStatus;
import az.com.company.model.Lesson;
import az.com.company.repository.LessonDao;
import az.com.company.request.RequestLesson;
import az.com.company.response.ResponseLesson;
import az.com.company.response.ResponseLessonList;
import az.com.company.response.ResponseStatus;
import az.com.company.service.inter.LessonService;
import az.com.company.util.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonDao lessonDao;

    @Override
    public ResponseLessonList getLessonList() {
        ResponseLessonList response=new ResponseLessonList();
        List<ResponseLesson> responseLessonList=new ArrayList<>();
        try {
            List<Lesson> lessonList=lessonDao.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (lessonList.isEmpty()){
                response.setStatus(new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data"));
                return response;
            }
            for(Lesson lesson:lessonList){
                ResponseLesson responseLesson=new ResponseLesson();
                responseLesson.setLessonId(lesson.getId());
                responseLesson.setLessonName(lesson.getName());
                responseLesson.setLessonTime(lesson.getTime());
                responseLesson.setLessonPrice(lesson.getPrice());
                responseLessonList.add(responseLesson);
            }
            response.setLessonList(responseLessonList);
            response.setStatus(ResponseStatus.getSuccessMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal exception"));
        }
        return response;
    }

    @Override
    public ResponseLesson getLessonById(Long lessonId) {
        ResponseLesson response=new ResponseLesson();
        try {
            if (lessonId==null){
                response.setStatus(new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA,"Inalid request data"));
                return response;
            }
            Lesson lesson=lessonDao.findLessonByIdAndActive(lessonId,EnumAvailableStatus.ACTIVE.getValue());
            if (lesson==null){
                response.setStatus(new ResponseStatus(ExceptionConstants.LESSON_NOT_FOUND,"lESSON NOT FOUND"));
                return response;
            }
            response.setLessonId(lesson.getId());
            response.setLessonName(lesson.getName());
            response.setLessonTime(lesson.getTime());
            response.setLessonPrice(lesson.getPrice());
            response.setStatus(ResponseStatus.getSuccessMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal exception"));
        }
        return response;
    }

    @Override
    public ResponseStatus addLesson(RequestLesson requestLesson) {
        ResponseStatus response=null;
        String name=requestLesson.getName();
        Integer time=requestLesson.getTime();
        Double price=requestLesson.getPrice();
        try {
            if (name==null||time==null||price==null){
                response=new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data");
                return response;
            }
            Lesson lesson=new Lesson();
            lesson.setName(name);
            lesson.setTime(time);
            lesson.setPrice(price);
            lessonDao.save(lesson);
            response=ResponseStatus.getSuccessMessage();
        }catch (Exception ex){
            ex.printStackTrace();
            response=new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal exception");
        }
        return response;
    }

    @Override
    public ResponseStatus updateLesson(RequestLesson requestLesson) {
        ResponseStatus response=null;
        Long lessonId=requestLesson.getLessonId();
        String name=requestLesson.getName();
        Integer time=requestLesson.getTime();
        Double price=requestLesson.getPrice();
        try {
            if (lessonId==null||name==null){
                response=new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data");
                return response;
            }
            Lesson lesson=lessonDao.findLessonByIdAndActive(lessonId,EnumAvailableStatus.ACTIVE.getValue());
            if (lesson==null){
                response=new ResponseStatus(ExceptionConstants.LESSON_NOT_FOUND,"Lesson not found");
                return response;
            }
            lesson.setName(name);
            lesson.setTime(time);
            lesson.setPrice(price);
            lessonDao.save(lesson);
            response=ResponseStatus.getSuccessMessage();
        }catch (Exception ex){
            ex.printStackTrace();
            response=new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal exception");
        }
        return response;
    }

    @Override
    public ResponseStatus deleteLesson(Long lessonId) {
        ResponseStatus response=null;
        try {
            if (lessonId==null){
                response=new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data");
                return response;
            }
            Lesson lesson=lessonDao.findLessonByIdAndActive(lessonId,EnumAvailableStatus.ACTIVE.getValue());
            if (lesson==null){
                response=new ResponseStatus(ExceptionConstants.LESSON_NOT_FOUND,"Lesson not found");
                return response;
            }
            lesson.setActive(EnumAvailableStatus.DEACTIVE.getValue());
            lessonDao.save(lesson);
            response=ResponseStatus.getSuccessMessage();
        }catch (Exception ex){
            ex.printStackTrace();
            response=new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal exception");
        }
        return response;
    }
}
