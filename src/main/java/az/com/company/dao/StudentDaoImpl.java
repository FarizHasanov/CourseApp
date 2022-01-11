package az.com.company.dao;

import az.com.company.model.Student;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private DataSource dataSource;

    public List<Student> getStudentList() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM STUDENT WHERE ACTIVE=1";
        List<Student> studentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Student.class));
        return studentList;
    }

    public void addStudent(Student student) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO STUDENT(NAME,SURNAME,DOB, ADDRESS,PHONE) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{student.getName(), student.getSurname(), student.getDob(),
                student.getAddress(), student.getPhone()});
    }

    public void updateStudent(Student student) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE STUDENT SET NAME=?,SURNAME=?,DOB=?,ADDRESS=?,PHONE=? WHERE ID=?";
        jdbcTemplate.update(sql, new Object[]{student.getName(), student.getSurname(), student.getDob(),
                student.getAddress(), student.getPhone(), student.getId()});
    }

    public Student getStudentById(Long studentId) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM STUDENT WHERE ACTIVE=1 AND ID=?";
        List<Student> studentList = jdbcTemplate.query(sql, new Object[]{studentId},
                new BeanPropertyRowMapper<Student>(Student.class));
        if (!studentList.isEmpty()) {
            return studentList.get(0);
        }
        return null;
    }

    public void deleteStudent(Long studentId) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM  STUDENT WHERE ID=?";
        jdbcTemplate.update(sql, studentId);
    }

    public Long studentCount() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT COUNT (*) FROM STUDENT WHERE ACTIVE =1";
        Long studentCount = jdbcTemplate.queryForObject(sql, Long.class);
        return studentCount;
    }
}
