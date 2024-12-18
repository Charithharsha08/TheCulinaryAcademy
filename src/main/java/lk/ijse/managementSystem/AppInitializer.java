package lk.ijse.managementSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.managementSystem.controller.LoginFormController;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
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
