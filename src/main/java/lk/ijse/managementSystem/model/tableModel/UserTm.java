package lk.ijse.managementSystem.model.tableModel;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserTm {
    private String userId;
    private String userName;
    private String password;
    private String jobRole;
    private JFXButton btn;
}
