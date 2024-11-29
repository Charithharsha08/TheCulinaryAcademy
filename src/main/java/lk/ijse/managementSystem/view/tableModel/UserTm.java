package lk.ijse.managementSystem.view.tableModel;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserTm {
    private String userName;
    private String password;
    private String jobRole;
    private JFXButton action;

}
