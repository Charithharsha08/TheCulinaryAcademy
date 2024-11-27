package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.model.User;
import lk.ijse.managementSystem.model.tableModel.AdminTm;
import lk.ijse.managementSystem.model.tableModel.UserTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class AdminManageController {

    public TableColumn <?, ?> colJobRole ;
    @FXML
    private JFXButton btnusers;


    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<AdminTm> tblAdmin;

    @FXML
    private JFXTextField txtAdminId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtSearchAdmin;

    @FXML
    private JFXTextField txtUserName;

    ObservableList<AdminTm> obList = FXCollections.observableArrayList();

    public void initialize(){
        setCellValueFactory();
        loadAdminTable();
        txtAdminId.setVisible(false);
        txtSearchAdmin.setVisible(false);
    }

    private void loadAdminTable() {
        tblAdmin.getItems().clear();

        List<User> adminList = SessionFactoryConfig.getInstance().getSession().createQuery("FROM User", User.class).getResultList();
        for (User admin : adminList) {
            AdminTm userTm = new AdminTm(
                    admin.getUserName(),
                    admin.getPassword(),
                    admin.getJobRole()
            );
            if (admin.getJobRole().equals("ADMIN")) {
                obList.add(userTm);
            }
        }
        tblAdmin.setItems(obList);
    }

    private void setCellValueFactory() {

        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("jobRole"));

    }

    @FXML
    void btnAdminAddButtonOnAction(ActionEvent event) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
try {
    User user = new User(txtUserName.getText(), txtPassword.getText(), "ADMIN");

    session.save(user);
    transaction.commit();
}catch (Exception e){
    new Alert(Alert.AlertType.ERROR, "Admin Already Exists or Invalid Credentials").show();
    return;
}
        session.close();
        new Alert(Alert.AlertType.INFORMATION, "Admin Added").show();
        loadAdminTable();
    }

    @FXML
    void btnClearBtnOnAction(ActionEvent event) {
        clearFields();


    }

    private void clearFields() {
        txtAdminId.clear();
        txtName.clear();
        txtPassword.clear();
        txtSearchAdmin.clear();
        txtUserName.clear();
    }

    @FXML
    void btnDeleleAdminOnAction(ActionEvent event) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, txtUserName.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this admin?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            session.delete(user);
            transaction.commit();
            session.close();
            new Alert(Alert.AlertType.INFORMATION, "Admin Deleted").show();
            loadAdminTable();
            clearFields();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateAdminOnAction(ActionEvent event) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, txtUserName.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update this admin?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            user.setUserName(txtUserName.getText());
            user.setPassword(txtPassword.getText());
            session.update(user);
            transaction.commit();
            session.close();
            new Alert(Alert.AlertType.INFORMATION, "Admin Updated").show();
            loadAdminTable();
            clearFields();
        }
    }

    @FXML
    void btnUsersOnAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/view/user-table.fxml"));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Cant Load User Table try again !! ").show();
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("User Table");

        stage.show();
    }

    @FXML
    void txtAdminIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtAdminSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentNameOnKeyRelease(KeyEvent event) {

    }

    @FXML
    void txtStudentOnKeyRelease(KeyEvent event) {

    }

    @FXML
    void txtTelOnKeyRelease(KeyEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

    public void tblAdminMouseClick(MouseEvent mouseEvent) {
        btnClearBtnOnAction(null);
        AdminTm selectedAdmin = tblAdmin.getSelectionModel().getSelectedItem();
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();

        User user = session.get(User.class, selectedAdmin.getUserName() );

        txtUserName.setText(user.getUserName());
        txtPassword.setText(user.getPassword());
        txtName.setText(user.getJobRole());

        transaction.commit();
        session.close();
    }
}
