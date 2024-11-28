package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.model.Course;
import lk.ijse.managementSystem.model.tableModel.CourseTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class ProgramManageController {

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private TableView<CourseTm> tblProgram;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXTextField txtFee;

    @FXML
    private JFXTextField txtProgramId;

    @FXML
    private JFXTextField txtProgramName;

    @FXML
    private JFXTextField txtSearchProgramm;
    private ObservableList<CourseTm> obList = FXCollections.observableArrayList();

    public void initialize(){

        txtSearchProgramm.setVisible(false);
        txtProgramId.setVisible(false);
        setCellValueFactory();
        loadProgramTable();
    }

    private void loadProgramTable() {
        tblProgram.getItems().clear();

        List<Course> programList = SessionFactoryConfig.getInstance().getSession().createQuery("FROM Course", Course.class).getResultList();
        for (Course course : programList) {
            CourseTm courseTm = new CourseTm(
                    course.getId(),
                    course.getDescription(),
                    course.getDuration(),
                    course.getPrice()
            );
            obList.add(courseTm);
        }
tblProgram.setItems(obList);
    }

    private void setCellValueFactory() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void btnClearBtnOnAction(ActionEvent event) {
       clearFields();
    }

    private void clearFields() {

        txtProgramId.clear();
        txtProgramName.clear();
        txtDuration.clear();
        txtFee.clear();
    }

    @FXML
    void btnDeleleProgramOnAction(ActionEvent event) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        CourseTm selectedProgram = tblProgram.getSelectionModel().getSelectedItem();

        if (selectedProgram == null) {
            new Alert(Alert.AlertType.WARNING, "No Program Selected").show();
            return;
        }
        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this program?").showAndWait();
        if (result.get() == ButtonType.OK) {

            try {
            Course course = session.get(Course.class, selectedProgram.getId());
            session.delete(course);
            transaction.commit();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Cannot delete this program this program is assigned to a student").show();
                return;
            }

            session.close();
            new Alert(Alert.AlertType.INFORMATION, "Program Deleted").show();
            loadProgramTable();
            clearFields();
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Program Not Deleted").show();
            clearFields();
        }
    }

    @FXML
    void btnProgramAddButtonOnAction(ActionEvent event) {
        if (isValid()){

           Session session = SessionFactoryConfig.getInstance().getSession();
                   Course  course = new Course(1,txtProgramName.getText(),txtDuration.getText(), Double.parseDouble(txtFee.getText()));
                   session.save(course);
                   session.beginTransaction().commit();
                   session.close();
            new Alert(Alert.AlertType.INFORMATION, "Program Added").show();
            loadProgramTable();
            clearFields();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateProgramOnAction(ActionEvent event) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        CourseTm selectedProgram = tblProgram.getSelectionModel().getSelectedItem();

        if (selectedProgram == null) {
            new Alert(Alert.AlertType.WARNING, "No Program Selected").show();
            return;
        }
        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update this program?").showAndWait();
        if (result.get() == ButtonType.OK) {
            Course course = session.get(Course.class, selectedProgram.getId());
            course.setDescription(txtProgramName.getText());
            course.setDuration(txtDuration.getText());
            course.setPrice(Double.parseDouble(txtFee.getText()));
            session.update(course);
            transaction.commit();
            session.close();
            new Alert(Alert.AlertType.INFORMATION, "Program Updated").show();
            loadProgramTable();
            clearFields();
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Program Not Updated").show();
            clearFields();
        }
    }

    @FXML
    void txtDurationOnAction(ActionEvent event) {

    }

    @FXML
    void txtFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtProgramIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtProgramNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtProgramSearchOnAction(ActionEvent event) {

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

    public boolean isValid() {
     if ( txtProgramName.getText().isEmpty() || txtDuration.getText().isEmpty() || txtFee.getText().isEmpty()) {
         new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
         return false;
     }
     try{
         Double.parseDouble(txtFee.getText());
     }catch (NumberFormatException e){
         new Alert(Alert.AlertType.ERROR, "Invalid Fee").show();
         return false;
     }
        return true;
    }

    public void tblProgramMousePressed(MouseEvent mouseEvent) {
        btnClearBtnOnAction(null);
        CourseTm selectedProgram = tblProgram.getSelectionModel().getSelectedItem();
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();

        Course course = session.get(Course.class, selectedProgram.getId() );

        txtProgramId.setText(String.valueOf(course.getId()));
        txtProgramName.setText(course.getDescription());
        txtDuration.setText(course.getDuration());
        txtFee.setText(String.valueOf(course.getPrice()));

        transaction.commit();
        session.close();
    }
}
