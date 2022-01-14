package az.com.company.repository;

import az.com.company.model.Lesson;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonDao extends CrudRepository<Lesson,Long> {
    List<Lesson> findAllByActive(Integer active);
    Lesson findLessonByIdAndActive(Long id,Integer active);
}
