package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.model.Course;
import lk.ijse.managementSystem.model.User;
import lk.ijse.managementSystem.model.tableModel.UserTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserTableController {

    @FXML
    private JFXButton btnBack;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colJobRole;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableView<UserTm> tblUsers;
    ObservableList<UserTm> obList = FXCollections.observableArrayList();

    public void initialize(){
        setCellValueFactory();
        loadUsersTable();
    }

    private void loadUsersTable() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        tblUsers.getItems().clear();
         List<User> userList = session.createQuery("FROM User", User.class).getResultList();
         for (User user : userList) {
             UserTm userTm = new UserTm(
                     user.getUserName(),
                     user.getPassword(),
                     user.getJobRole(),
                     createRemoveButton(user));
             if (user.getJobRole().equals("USER")) {
                 obList.add(userTm);
             }

         }
         tblUsers.setItems(obList);

    }

    private void setCellValueFactory() {
       // colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password") );
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
    colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
    }


    @FXML
    void btnbackOnAction(ActionEvent event) {
        Window window = btnBack.getScene().getWindow();
        Stage stage = (Stage) window;
        stage.close();
    }

    private JFXButton createRemoveButton(User user) {
        JFXButton remove = new JFXButton("Remove");
        remove.setStyle("-fx-background-color: #ff0000; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius:0;");
        remove.setOnAction(event -> {
            if (tblUsers.getSelectionModel().getSelectedItem() == null) {
                new Alert(Alert.AlertType.INFORMATION, "Please select a user first").show();
                return;
            }
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to remove this course?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                Session session = null;
                try {
                    session = SessionFactoryConfig.getInstance().getSession();
                    Transaction transaction = session.beginTransaction();
                    session.delete(user);
                    transaction.commit();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Logged User Cannot be deleted, Please Create New User And Login Then Delete").show();
                    tblUsers.getItems().remove(tblUsers.getSelectionModel().getSelectedItem());
                } finally {
                    session.close();
                     loadUsersTable();
                }

            }
        });
        return remove;
    }


}
