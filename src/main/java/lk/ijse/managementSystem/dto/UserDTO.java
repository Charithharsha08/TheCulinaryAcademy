package lk.ijse.managementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class UserDTO {
    private String username;
    private String password;
    private String jobRole;
}
