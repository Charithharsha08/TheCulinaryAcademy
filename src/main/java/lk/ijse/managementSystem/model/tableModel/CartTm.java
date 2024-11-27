package lk.ijse.managementSystem.model.tableModel;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.jfoenix.controls.JFXButton;

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
