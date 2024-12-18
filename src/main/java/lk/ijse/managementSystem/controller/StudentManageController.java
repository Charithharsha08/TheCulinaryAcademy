package lk.ijse.managementSystem.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.managementSystem.bo.BOFactory;
import lk.ijse.managementSystem.bo.custom.StudentBO;
import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.dto.StudentDTO;
import lk.ijse.managementSystem.dto.UserDTO;
import lk.ijse.managementSystem.entity.Student;
import lk.ijse.managementSystem.entity.User;
import lk.ijse.managementSystem.view.tableModel.StudentTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class StudentManageController {

    public JFXButton btnSearch;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colTelephoneNumber;

    @FXML
    private TableView<StudentTm> tblStudent;

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

    User currentUser = LoginFormController.user;



    private ObservableList<StudentTm> obList = FXCollections.observableArrayList();

    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);

    public void initialize(){

        txtStudentId.setVisible(false);
        txtSearchStudent.setVisible(false);
        btnSearch.setVisible(false);
        setCellValueFactory();
        loadStudentTable();
    }

    private void loadStudentTable() {
        tblStudent.getItems().clear();
       /* Session session = SessionFactoryConfig.getInstance().getSession();

        List<Student> studentList = session.createQuery("FROM Student", Student.class).getResultList();
        for (Student student : studentList) {
            StudentTm studentTm = new StudentTm(
                    student.getId(),
                    student.getName(),
                    student.getAddress(),
                    student.getContact());
            obList.addAll(studentTm);
        }
        System.out.println(studentList.size());
        tblStudent.setItems(obList);*/

        List<StudentDTO> studentDTOS = studentBO.getAllStudents();
        for (StudentDTO studentDTO : studentDTOS) {
            StudentTm studentTm = new StudentTm(
                    studentDTO.getId(),
                    studentDTO.getName(),
                    studentDTO.getAddress(),
                    studentDTO.getContact());
            obList.addAll(studentTm);
        }
        tblStudent.setItems(obList);
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTelephoneNumber.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    @FXML
    void btnClearBtnOnAction(ActionEvent event) {

clearFields();
    }

    public void clearFields(){
        txtAddress.clear();
        txtMail.clear();
        txtSearchStudent.clear();
        txtStudentId.clear();
        txtStudentName.clear();
        txtTel.clear();
    }

    @FXML
    void btnDeleleStudentOnAction(ActionEvent event) {
        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this student?").showAndWait();
        boolean isDeleted = false;
        if (result.get() == ButtonType.OK) {

            /*Session session = null;
            Student student = null;
            Transaction transaction = null;
            try {
                session = SessionFactoryConfig.getInstance().getSession();
                transaction = session.beginTransaction();
                StudentTm selectedStudent = tblStudent.getSelectionModel().getSelectedItem();

                if (selectedStudent == null) {
                    new Alert(Alert.AlertType.WARNING, "No Student Selected").show();
                    return;
                }

                student = session.get(Student.class, selectedStudent.getStudentId());
                session.delete(student);
                transaction.commit();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Can't Delete this Student as it is in use").showAndWait();
                return;
            }


            session.close();
            new Alert(Alert.AlertType.INFORMATION, "Student Deleted").show();
            loadStudentTable();
            clearFields();
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Student Not Deleted").show();
            clearFields();*/

            StudentTm selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
            if (selectedStudent == null) {
                new Alert(Alert.AlertType.WARNING, "No Student Selected").show();
                return;
            }

            isDeleted = studentBO.deleteStudent(String.valueOf(selectedStudent.getStudentId()));
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Student Deleted").show();
                loadStudentTable();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Can't Delete this Student as it is in use").showAndWait();
            }


        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();

        Student student = session.get(Student.class, txtSearchStudent.getText() );

        if (student == null || student.getId() == 0) {
            new Alert(Alert.AlertType.WARNING, "No Student Found").show();
            return;
        }

        txtStudentId.setText(String.valueOf(student.getId()));
        txtStudentName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtMail.setText(student.getEmail());
        txtTel.setText(student.getContact());

        transaction.commit();
        session.close();

    }

    @FXML
    void btnStudentAddButtonOnAction(ActionEvent event) {
        if (isValid()){

           /* Session session = SessionFactoryConfig.getInstance().getSession();
            Student student = new Student(
                    "1",
                    txtStudentName.getText(),
                    txtAddress.getText(),
                    txtMail.getText(),
                    txtTel.getText(),
                    currentUser);

            session.save(student);
            session.beginTransaction().commit();
            session.close();
            loadStudentTable();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Student Added").show();*/

            StudentDTO student = new StudentDTO(
                    1,
                    txtStudentName.getText(),
                    txtAddress.getText(),
                    txtMail.getText(),
                    txtTel.getText(),
                    currentUser);

           boolean isAdded = studentBO.addStudent(student);
           if (isAdded) {
               new Alert(Alert.AlertType.INFORMATION, "Student Added").show();
               loadStudentTable();
               clearFields();
           } else {
               new Alert(Alert.AlertType.ERROR, "Student Already Exists or Invalid Credentials").show();
           }
        }
    }
    @FXML
    void btnUpdateStudentOnAction(ActionEvent event) {
       /* Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        StudentTm selectedStudent = tblStudent.getSelectionModel().getSelectedItem();

        if (selectedStudent == null ) {
            new Alert(Alert.AlertType.WARNING, "No Student Selected").show();
           return ;
        }
        Student student = session.get(Student.class, selectedStudent.getStudentId()  );
        student.setName(txtStudentName.getText());
        student.setAddress(txtAddress.getText());
        student.setEmail(txtMail.getText());
        student.setContact(txtTel.getText());
        session.update(student);
        transaction.commit();
        session.close();
        new Alert(Alert.AlertType.INFORMATION, "Student Updated").show();
        loadStudentTable();
        clearFields();*/
        StudentTm selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            new Alert(Alert.AlertType.WARNING, "No Student Selected").show();
            return;
        }
        StudentDTO student = new StudentDTO(
                selectedStudent.getStudentId(),
                txtStudentName.getText(),
                txtAddress.getText(),
                txtMail.getText(),
                txtTel.getText(),
                currentUser);
        boolean isUpdated = studentBO.updateStudent(student);
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Student Updated").show();
            loadStudentTable();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Student Already Exists or Invalid Credentials").show();
        }

    }



    @FXML
    void txtAddressOnAction(ActionEvent event) {
txtMail.requestFocus();
    }

    @FXML
    void txtMailOnAction(ActionEvent event) {
        txtTel.requestFocus();
    }

    @FXML
    void txtMailOnKeyRelease(KeyEvent event) {

    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentNameOnAction(ActionEvent event) {
txtAddress.requestFocus();
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
btnStudentAddButtonOnAction(null);
    }

    @FXML
    void txtTelOnKeyRelease(KeyEvent event) {

    }
    private boolean isValid(){
//if (txtStudentId.getText() == null || txtStudentId.getText().trim().isEmpty()) {
//    new Alert(Alert.AlertType.WARNING, "Student Id can't be empty").show();
//    return false;
//}
if (txtStudentName.getText() == null || txtStudentName.getText().trim().isEmpty()) {
    new Alert(Alert.AlertType.WARNING, "Student Name can't be empty").show();
    return false;
}if (txtAddress.getText() == null || txtAddress.getText().trim().isEmpty()) {
    new Alert(Alert.AlertType.WARNING, "Student Address can't be empty").show();
    return false;
}if (txtTel.getText() == null || txtTel.getText().trim().isEmpty()) {
    new Alert(Alert.AlertType.WARNING, "Student Telephone can't be empty").show();
    return false;
}if (txtMail.getText() == null || txtMail.getText().trim().isEmpty()) {
    new Alert(Alert.AlertType.WARNING, "Student Email can't be empty").show();
    return false;
}
return true;

    }

    public void tblMouseClickedOnAction(MouseEvent mouseEvent) {
        btnClearBtnOnAction(null);
        StudentTm selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();

        Student student = session.get(Student.class, selectedStudent.getStudentId() );

        txtStudentId.setText(String.valueOf(student.getId()));
        txtStudentName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtMail.setText(student.getEmail());
        txtTel.setText(student.getContact());

        transaction.commit();
        session.close();
    }

}
