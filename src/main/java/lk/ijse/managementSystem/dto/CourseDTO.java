package lk.ijse.managementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CourseDTO {
    private int id;
    private String description;
    private String duration;
    private Double price;
}
