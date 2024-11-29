package lk.ijse.managementSystem.view.tableModel;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartTm {
    private int id;
    private String studentName;
    private String description;
    private double total;
    private JFXButton action;


}
