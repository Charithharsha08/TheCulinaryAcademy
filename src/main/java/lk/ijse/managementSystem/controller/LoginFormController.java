package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

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

    public static User user = null;

    @FXML
    void OnKeyReleaserUsername(KeyEvent event) {

    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {

        if (checkCredentials()) {
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
    }

    @FXML
    void hyperLinkRegisterNowOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/register-form.fxml"));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Payment Form");
        stage.centerOnScreen();

        stage.show();

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
    btnLoginOnAction(event);
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
    txtPassword.requestFocus();
    }

    public boolean checkCredentials(){
        Session session = null;
        try {
         session = SessionFactoryConfig.getInstance().getSession();
         user = session.get(User.class,txtUserName.getText());
        if (user != null && user.getPassword().equals(txtPassword.getText())) {
            return true;
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
            return false;
        }
    }catch (HibernateException e){
e.printStackTrace();
}finally {
            txtUserName.clear();
            txtPassword.clear();
            session.close();
        }
        return false;
    }

    public void hyperLinkForgetPasswordOnAction(ActionEvent actionEvent) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/forgot-password-form.fxml"));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Payment Form");
        stage.centerOnScreen();

        stage.show();
    }
}
