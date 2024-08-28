package ca.sheridancollege.sukhmanmeetsinghfinal.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourses {
    private Student student;
    private List<Course> courses;
}
