package az.com.company.repository;

import az.com.company.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherDao extends CrudRepository<Teacher,Long> {
    List<Teacher> findAllByActive(Integer active);

    Teacher findTeacherByIdAndActive(Long id,Integer active);
}
