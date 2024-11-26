package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterFormController {

    public JFXTextField txtJobRole;
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnRegister;


    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtUserId.clear();
        txtUserName.clear();
        txtPassword.clear();

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        User user = new User( txtUserName.getText(), txtPassword.getText(), txtJobRole.getText());
        Session userSaveSession = SessionFactoryConfig.getInstance().getSession();
        Transaction userSaveTransaction = userSaveSession.beginTransaction();
        userSaveSession.save(user);
        userSaveTransaction.commit();
        userSaveSession.clear();

    }


    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

    public void txtJobRoleOnAction(ActionEvent actionEvent) {
    }
}
