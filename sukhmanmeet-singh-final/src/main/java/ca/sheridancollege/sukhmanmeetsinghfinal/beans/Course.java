package ca.sheridancollege.sukhmanmeetsinghfinal.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Long id;
    private Long studentId;
    private String name;
    private Long grade;

}