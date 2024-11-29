package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.entity.Course;
import lk.ijse.managementSystem.entity.Payment;
import lk.ijse.managementSystem.entity.Student;
import lk.ijse.managementSystem.entity.StudentCourseDetail;
import lk.ijse.managementSystem.view.tableModel.CartTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class PaymentManageController {

    @FXML
    private JFXComboBox<String> cmbPaymentType;

    @FXML
    private JFXComboBox<?> cmbSelectedCourceDiscount;

    @FXML
    private JFXComboBox<String> cmbSelectedProgramme;

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
    private TableView<CartTm> tblPayment;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtSearchId;

    Date currentDate = new Date(System.currentTimeMillis());
    Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

    Course selectedCourse = null;
    Student selectedStudent = null;

    double total = 00.00;

    public void initialize(){
        colDiscount.setVisible(false);
        setCellValueFactory();
        loadCources();
        LoadPaymentMethods();

    }

    private void LoadPaymentMethods() {
        cmbPaymentType.getItems().addAll("CASH","ADVANCE");
        cmbPaymentType.setValue("CASH");
    }

    private void loadCources() {
        Session session = SessionFactoryConfig.getInstance().getSession();

        List<Course> programList = session.createQuery("FROM Course", Course.class).getResultList();

        cmbSelectedProgramme.getItems().clear();

        for (Course course : programList) {
            cmbSelectedProgramme.getItems().add(course.getDescription());
        }
    }

    private void setCellValueFactory() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));


    }

    @FXML
    void btnAddToCartCourceOnAction(ActionEvent event) {
        if ( selectedCourse == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a course").show();
            return;
        }if (selectedStudent == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a student").show();
            return;
        }
        total+=selectedCourse.getPrice();
        lblNetTotal.setText(String.valueOf(total));

        JFXButton removeButton = createRemoveButton(selectedCourse);

        CartTm cartTm = new CartTm( 1,selectedStudent.getName(),selectedCourse.getDescription(),selectedCourse.getPrice(), removeButton);
        tblPayment.getItems().add(cartTm);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        try {
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Please Fill Fields Before Clear").show();
        }
    }

    private void clearFields() {
        lblCustomerId.setText("");
        txtSearchId.clear();
        tblPayment.getItems().clear();
        lblNetTotal.setText("0.00");
        total = 0.00;
        lblProgramId.setText("");
        lblCustomerName.setText("");
        lblDate.setText("");
        lblTime.setText("");
        lblTel.setText("");

        lblProgramId.setText("");
        lblDuration.setText("");
        lblSelectedCourceCost.setText("");
        cmbPaymentType.setValue("CASH");
        cmbSelectedProgramme.setValue("");
        txtAmount.clear();

    }

    @FXML
    void btnPlacePaymentOnAction(ActionEvent event) {
        if (checkDetails()) {
            Session session = SessionFactoryConfig.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            StudentCourseDetail studentCourseDetail = new StudentCourseDetail(1, currentDate, selectedStudent, selectedCourse);
            session.save(studentCourseDetail);
            double amount = selectedCourse.getPrice() - Double.parseDouble(txtAmount.getText());
            Payment payment = new Payment(1, cmbPaymentType.getValue(), currentTimestamp, amount, total, studentCourseDetail);
            session.save(payment);
            transaction.commit();
            session.close();

            new Alert(Alert.AlertType.INFORMATION, "Payment Done Successfully").show();

        }

    }

    @FXML
    void btncreateNewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void cmbPaymentTypeOnAction(ActionEvent event) {
            cmbPaymentType.getValue();
    }

    @FXML
    void cmbSelectedCourceDiscountOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSelectedCourceOnAction(ActionEvent event) {
       String selectedProgramme = cmbSelectedProgramme.getValue();

       try {
           Session session = SessionFactoryConfig.getInstance().getSession();
           selectedCourse = session.createQuery("FROM Course WHERE description = :desc", Course.class)
                   .setParameter("desc", selectedProgramme)
                   .uniqueResult();
           System.out.println(selectedCourse);
           lblSelectedCourceCost.setText(String.valueOf(selectedCourse.getPrice()));
           lblProgramId.setText(String.valueOf(selectedCourse.getId()));
           lblDuration.setText(selectedCourse.getDuration());

       } catch (Exception e) {
           e.printStackTrace();
       }


    }

    @FXML
    void txtAmountOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchIdOnAction(ActionEvent event) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
        selectedStudent = session.createQuery("FROM Student WHERE contact = :student_contact", Student.class)
                .setParameter("student_contact", txtSearchId.getText())
                .uniqueResult();

        new Alert(Alert.AlertType.INFORMATION, "Student : "+ selectedStudent.getName() +", selected successfully.").show();


        lblCustomerId.setText(selectedStudent.getId());
        lblCustomerName.setText(selectedStudent.getName());
        lblTel.setText(selectedStudent.getContact());
        lblDate.setText(currentDate.toString());
        lblTime.setText(currentTimestamp.toString());

        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "No Student Found").show();
        }finally {
            session.close();

        }

    }

    private boolean checkDetails() {
        if (selectedStudent == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a student first.").show();
            return false;
        }
        if (selectedCourse == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a course first.").show();
            return false;
        }
        if (cmbPaymentType.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a payment method first.").show();
            return false;
        }
        return true;
    }

    private JFXButton createRemoveButton(Course course) {
        JFXButton remove = new JFXButton("Remove");
        remove.setStyle("-fx-background-color: #ff0000; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius:0;");
        remove.setOnAction(event -> {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to remove this course?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                // Remove the course from the cart
                total -= course.getPrice();
                lblNetTotal.setText(String.valueOf(total));
                tblPayment.getItems().remove(tblPayment.getSelectionModel().getSelectedItem());
            }
        });
        return remove;
    }

}
