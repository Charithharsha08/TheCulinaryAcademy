package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

public class StudentManageController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colTelephoneNumber;

    @FXML
    private TableView<?> tblStudent;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtMail;

    @FXML
    private JFXTextField txtSearchStudent;

    @FXML
    private JFXTextField txtStudentId;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXTextField txtTel;

    @FXML
    void btnClearBtnOnAction(ActionEvent event) {
        txtAddress.clear();
        txtMail.clear();
        txtSearchStudent.clear();
        txtStudentId.clear();
        txtStudentName.clear();
        txtTel.clear();

    }

    @FXML
    void btnDeleleStudentOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnStudentAddButtonOnAction(ActionEvent event) {

        }

    @FXML
    void btnUpdateStudentOnAction(ActionEvent event) {

    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {

    }

    @FXML
    void txtMailOnAction(ActionEvent event) {

    }

    @FXML
    void txtMailOnKeyRelease(KeyEvent event) {

    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentNameOnKeyRelease(KeyEvent event) {

    }

    @FXML
    void txtStudentOnKeyRelease(KeyEvent event) {

    }

    @FXML
    void txtStudentSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtTelOnAction(ActionEvent event) {

    }

    @FXML
    void txtTelOnKeyRelease(KeyEvent event) {

    }

}
