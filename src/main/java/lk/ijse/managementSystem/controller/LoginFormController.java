package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class LoginFormController {

    @FXML
    private Hyperlink hyperLinkRegisterNow;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUserName;



    @FXML
    void OnKeyReleaseTxtPassword(KeyEvent event) {

    }

    @FXML
    void OnKeyReleaserUsername(KeyEvent event) {

    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String username = txtUserName.getText();
        String password = txtPassword.getText();
    }

    @FXML
    void hyperLinkRegisterNowOnAction(ActionEvent event) {

    }

}
