package lk.ijse.managementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentDTO {
    private int id;
    private String method;
    private Timestamp orderDateTime;
    private double balance = 0;
    private double total;
    private StudentCourseDetailDTO studentCourseDetail;

}


