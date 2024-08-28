package ca.sheridancollege.sukhmanmeetsinghfinal.controllers;

import ca.sheridancollege.sukhmanmeetsinghfinal.beans.Course;
import ca.sheridancollege.sukhmanmeetsinghfinal.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.sukhmanmeetsinghfinal.database.DatabaseAccess;

import java.util.List;
@Controller
public class HomeController {

    @Autowired
    private DatabaseAccess databaseaccess;

    @GetMapping("/")
    public String getAllStudents(Model model) {
        List<Student> students = databaseaccess.getStudents();
        model.addAttribute("students", students);
        return "index.html";
    }

    @PostMapping("/")
    public String viewCourses(Model model, @RequestParam("students") Long studentId) {
        Student student = databaseaccess.getStudentById(studentId);
        List<Course> courses = databaseaccess.getCoursesForStudent(student.getName());

        model.addAttribute("student", student);
        model.addAttribute("courses", courses);
        model.addAttribute("studentName", student.getName());
        return "view-students.html";
    }

    @GetMapping("/addCourse/{id}")
    public String addCourseForm(@PathVariable Long id, Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("studentId", id);

        return "add-course.html";
    }

    @PostMapping("/addCourse/{id}")
    public String addCourseSubmit(@PathVariable Long id, @ModelAttribute Course course, Model model) {
        databaseaccess.addCourse(id, course.getName(), course.getGrade());
        return "redirect:/";
    }
}
