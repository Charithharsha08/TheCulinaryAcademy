package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PaymentManageController {

    @FXML
    private JFXComboBox<?> cmbPaymentType;

    @FXML
    private JFXComboBox<?> cmbSelectedCourceDiscount;

    @FXML
    private JFXComboBox<?> cmbSelectedProgramme;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDuration;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblProgramId;

    @FXML
    private Label lblSelectedCourceCost;

    @FXML
    private Label lblTel;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtSearchId;

    @FXML
    void btnAddToCartCourceOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlacePaymentOnAction(ActionEvent event) {

    }

    @FXML
    void btncreateNewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void cmbPaymentTypeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSelectedCourceDiscountOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSelectedCourceOnAction(ActionEvent event) {

    }

    @FXML
    void txtAmountOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchIdOnAction(ActionEvent event) {

    }

}
