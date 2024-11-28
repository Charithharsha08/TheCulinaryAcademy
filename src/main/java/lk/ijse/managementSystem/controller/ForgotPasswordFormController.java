package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ForgotPasswordFormController {

    @FXML
    private JFXButton btnChangePassword;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXTextField txtJobRole;

    @FXML
    private JFXTextField txtNewPassword;

    @FXML
    private JFXTextField txtRetypePassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, txtUserName.getText());

        List<User> userList = session.createQuery("FROM User", User.class).getResultList();

        for (User u : userList) {
            if (u.getUserName().equals(txtUserName.getText()) && u.getJobRole().equals(txtJobRole.getText())) {
                break;
            } else {
                new Alert(Alert.AlertType.ERROR, "User Name or Job Role Not Matched").show();
                return;
            }
        }

        if (!txtNewPassword.getText().equals(txtRetypePassword.getText())) {
            new Alert(Alert.AlertType.ERROR, "Password Not Matched").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Forgot this admin?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            user.setUserName(txtUserName.getText());
            user.setPassword(txtRetypePassword.getText());
            session.update(user);
            transaction.commit();
            session.close();
            new Alert(Alert.AlertType.INFORMATION, "Admin Updated").show();
            clearFields();
            Window window = btnChangePassword.getScene().getWindow();
            Stage stage = (Stage) window;
            stage.close();
        }

    }

    private void clearFields() {
        txtJobRole.clear();
        txtNewPassword.clear();
        txtRetypePassword.clear();
        txtUserName.clear();
    }

    @FXML
    void txtJobRoleOnAction(ActionEvent event) {
txtNewPassword.requestFocus();
    }

    @FXML
    void txtNewPasswordOnAction(ActionEvent event) {
txtRetypePassword.requestFocus();
    }

    @FXML
    void txtRetypePasswordOnAction(ActionEvent event) {
btnChangePasswordOnAction(event);
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
txtJobRole.requestFocus();
    }

}
