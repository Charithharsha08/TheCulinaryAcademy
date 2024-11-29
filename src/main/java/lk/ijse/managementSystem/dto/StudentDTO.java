package lk.ijse.managementSystem.dto;

import lk.ijse.managementSystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private String contact;
    private User user;
}
