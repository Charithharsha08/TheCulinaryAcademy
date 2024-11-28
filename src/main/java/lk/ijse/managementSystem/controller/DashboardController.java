package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class DashboardController {

    public JFXButton btnStudent;
    public JFXButton btnLogOut;
    @FXML
    private JFXButton btnAdmin;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnPrograme;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private VBox sideBar;

    @FXML
    private AnchorPane sidePane;

    private JFXButton selectedButton;

    public void initialize() {
        //checkAvailability();
        loadPane("/view/home-page.fxml", btnHome);
    }

    private void checkAvailability() {
        if (LoginFormController.user.getJobRole() == "USER") {
            btnPayment.setDisable(false);
            btnAdmin.setDisable(false);
            new Alert(Alert.AlertType.ERROR, "You are not allowed to access this page").show();
        } else {
            btnPayment.setDisable(true);
            btnAdmin.setDisable(true);
        }
    }


    @FXML
    void BtnAdminOnAction(ActionEvent event) {
        loadPane("/view/admin-manage.fxml", btnAdmin);
    }

    @FXML
    void BtnHomeOnAction(ActionEvent event) {
        loadPane("/view/home-page.fxml", btnHome);
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {

        loadPane("/view/payment-manage.fxml", btnPayment);
    }

    @FXML
    void btnProgramOnAction(ActionEvent event) {
        loadPane("/view/programe-manage.fxml", btnPrograme);
    }

    public void loadPane(String name, JFXButton selectedButton) {
        handleSelection(selectedButton);
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(this.getClass().getResource(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(pane);
        animatePane(pane); // Call the animation method
    }

    private void animatePane(Pane pane) {
        pane.setTranslateX(-pane.getWidth()); // Start the pane outside the view
        pane.setOpacity(0); // Start with opacity 0 for a fade-in effect

        // Create TranslateTransition to move the pane into view
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), pane);
        translateTransition.setToX(0);

        // Create FadeTransition to fade the pane in
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), pane);
        fadeTransition.setToValue(1);

        // Play both animations together
        translateTransition.play();
        fadeTransition.play();
    }

    private void handleSelection(JFXButton button) {
        if (selectedButton != null) {
            selectedButton.setStyle(""); // Deselect the previously selected button
        }
        selectedButton = button; // Set the new selected button
        selectedButton.setStyle("-fx-background-radius: 20px 20px 20px 20px;\n" +
                "    -fx-background-color: #372214;\n" +
                "    -fx-text-fill: #ffffff;"); // Apply the selected style
    }

    public void BtnStudentOnAction(ActionEvent actionEvent) {
        loadPane("/view/student-manage.fxml", btnStudent);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Stage stage = (Stage) btnLogOut.getScene().getWindow();
            stage.close();
            try {
                stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login-page.fxml"))));
//            stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("The Culinary Academy");
                stage.centerOnScreen();
                stage.show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
}
