package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    public AnchorPane rootNode;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Hyperlink hyperLinkRegisterNow;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void OnKeyReleaserUsername(KeyEvent event) {

    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        }

        Scene scene = new Scene(pane);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");

    }

    @FXML
    void hyperLinkRegisterNowOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

}
