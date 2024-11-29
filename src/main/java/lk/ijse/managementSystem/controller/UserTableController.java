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
import lk.ijse.managementSystem.bo.BOFactory;
import lk.ijse.managementSystem.bo.custom.UserBO;
import lk.ijse.managementSystem.bo.custom.impl.UserBOImpl;
import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.dto.UserDTO;
import lk.ijse.managementSystem.entity.User;
import lk.ijse.managementSystem.view.tableModel.UserTm;
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

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public void initialize(){
        setCellValueFactory();
        loadUsersTable();
    }

    private void loadUsersTable() {
        List<UserDTO> userList = userBO.getAllUsers();
        for (UserDTO user : userList) {
            UserTm userTm = new UserTm(
                    user.getUsername(),
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

    private JFXButton createRemoveButton(UserDTO user) {
        JFXButton remove = new JFXButton("Remove");
        remove.setStyle("-fx-background-color: #ff0000; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius:0;");
        remove.setOnAction(event -> {
            if (tblUsers.getSelectionModel().getSelectedItem() == null) {
                new Alert(Alert.AlertType.INFORMATION, "Please select a user first").show();
                return;
            }
            if (tblUsers.getSelectionModel().getSelectedItem().getUserName().equals(LoginFormController.user.getUserName())) {
                new Alert(Alert.AlertType.ERROR, "Logged User Cannot be deleted, Please Create New User And Login Then Delete").show();
                return;
            }
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to remove this course?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get() == ButtonType.YES) {

                try {
                    boolean isRemoved = userBO.removeUser(user.getUsername());

                    if (isRemoved) {
                        tblUsers.getItems().remove(tblUsers.getSelectionModel().getSelectedItem());
                        new Alert(Alert.AlertType.INFORMATION, "User Deleted").show();
                    }
                }catch (Exception e){
                    new Alert(Alert.AlertType.ERROR, "Something went wrong Please try again Later").show();

                }
            }
        });
        return remove;
    }


}
