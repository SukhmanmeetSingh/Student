package ca.sheridancollege.sukhmanmeetsinghfinal.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.sheridancollege.sukhmanmeetsinghfinal.beans.Course;
import ca.sheridancollege.sukhmanmeetsinghfinal.beans.Student;

@Repository
public class DatabaseAccess {


    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public void insertStudent(Student student) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("number", student.getNumber());
        namedParameters.addValue("name", student.getName());

        String query = "INSERT INTO students (number, name) " +
                "VALUES (:number, :name)";

        int rowsAffected = jdbc.update(query, namedParameters);

        if (rowsAffected > 0) {
            System.out.println("Inserted student into database.");
        }
    }

    public List<Student> getStudents() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM students";

        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
    }
    public List<Student> getStudentsName() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT name FROM students";

        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
    }
    public List<Course> getCoursesForStudent(Student student) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", student.getId());
        String query = "SELECT * FROM courses WHERE studentid = :id";

        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Course>(Course.class));
    }

    public Student getStudentById(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        String query = "SELECT * FROM students WHERE id = :id";

        return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
    }


    public void updateStudent(Student student) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", student.getId());
        namedParameters.addValue("number", student.getNumber());
        namedParameters.addValue("name", student.getName());

        String query = "UPDATE students SET number = :number, name = :name WHERE id = :id";

        int rowsAffected = jdbc.update(query, namedParameters);

        if (rowsAffected > 0) {
            System.out.println("Updated student in database.");
        }
    }

    public void deleteStudent(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        String query = "DELETE FROM students WHERE id = :id";

        int rowsAffected = jdbc.update(query, namedParameters);

        if (rowsAffected > 0) {
            System.out.println("Deleted student from database.");
        }
    }

    public void addCourse(Long studentId, String courseName, Long grade) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("studentId", studentId);
        namedParameters.addValue("courseName", courseName);
        namedParameters.addValue("grade", grade);

        String query = "INSERT INTO courses (studentid, name, grade) " +
                "VALUES (:studentId, :courseName, :grade)";

        int rowsAffected = jdbc.update(query, namedParameters);

        if (rowsAffected > 0) {
            System.out.println("Added course to database.");
        }
    }


    public List<Course> getCoursesByStudentId(Long studentId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("studentid", studentId);
        String query = "SELECT * FROM courses WHERE studentid = :studentid";

        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Course>(Course.class));
    }

    public void updateCourse(Course course) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", course.getId());
        namedParameters.addValue("studentid", course.getStudentId());
        namedParameters.addValue("name", course.getName());
        namedParameters.addValue("grade", course.getGrade());

        String query = "UPDATE courses SET studentid = :studentid, name = :name, grade = :grade WHERE id = :id";

        int rowsAffected = jdbc.update(query, namedParameters);

        if (rowsAffected > 0) {
            System.out.println("Updated course in database.");
        }
    }

    public void deleteCourse(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        String query = "DELETE FROM courses WHERE id = :id";

        int rowsAffected = jdbc.update(query, namedParameters);

        if (rowsAffected > 0) {
            System.out.println("Deleted course from database.");
        }
    }

    public List<Course> getCoursesForStudent(String studentName) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("name", studentName);
        String query = "SELECT * FROM courses WHERE studentid IN (SELECT id FROM students WHERE name = :name)";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Course.class));
    }

    public Student getStudentById(int id){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String q = "Select * from students where id=:id";
        parameters.addValue("id", id);


        ArrayList<Student> stlist = new ArrayList<>();

        List<Map<String, Object>> rows = jdbc.queryForList(q, parameters);

        for(Map<String, Object> row: rows) {
            Student s = new Student();
            s.setId((long)row.get("id"));
            s.setName((String)row.get("name"));
            stlist.add(s);

        }
        if(stlist.size() > 0)
            return stlist.get(0);
        else
            return null;
    }

    public void addStudent(Student student) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String q = "insert into students (name) values (:name)";
        parameters.addValue("name", student.getName());

        jdbc.update(q, parameters);
    }

    public void deleteStudent() {
        String query = "Delete from students";
        jdbc.update(query, new HashMap());
    }

    public void resetIndex() {
        String q = "Alter table students alter column id restart with 1";
        jdbc.update(q, new HashMap());

    }
    public List<Course> getCoursesForStudentId(Long studentId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("studentId", studentId);
        String query = "SELECT * FROM courses WHERE studentid = :studentId";

        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Course>(Course.class));
    }
}



