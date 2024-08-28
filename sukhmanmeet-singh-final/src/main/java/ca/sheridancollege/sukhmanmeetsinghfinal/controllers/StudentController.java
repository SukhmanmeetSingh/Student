package ca.sheridancollege.sukhmanmeetsinghfinal.controllers;

import ca.sheridancollege.sukhmanmeetsinghfinal.beans.Course;
import ca.sheridancollege.sukhmanmeetsinghfinal.beans.Student;
import ca.sheridancollege.sukhmanmeetsinghfinal.beans.StudentCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ca.sheridancollege.sukhmanmeetsinghfinal.database.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private DatabaseAccess da;

    @GetMapping("/students")
    public List<Student> getStudents(){
        return da.getStudents();
    }

    @GetMapping("/students/{id}")
    public StudentCourses getStudentById(@PathVariable Long id) {
        Student student = da.getStudentById(id);
        List<Course> courses = da.getCoursesForStudentId(id);
        return new StudentCourses(student, courses);
    }

    @PostMapping(value="/students", headers= {"Content-type=application/json"})
    public String addStudent(@RequestBody Student student) {
        da.addStudent(student);
        return "student is added";
    }

    @PutMapping(value="/students", headers= {"Content-type=application/json"})
    public String putStudent(@RequestBody List<Student> studentList) {
        da.deleteStudent();
        da.resetIndex();
        for(Student s:studentList)
            da.addStudent(s);
        return "student is added" + da.getStudents().size();
    }
}
