package lk.ijse.managementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StudentCourseDetailDTO {
    private int stuCouDetailId;
    private Date registrationDate;
    private StudentDTO student;
    private CourseDTO course;

}
