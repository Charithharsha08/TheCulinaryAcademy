package lk.ijse.managementSystem.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.model.Course;
import lk.ijse.managementSystem.model.Payment;
import lk.ijse.managementSystem.model.Student;
import lk.ijse.managementSystem.model.User;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.List;

public class HomePageController {
    public Label lblDayStatus;
    public Label lblUserName;
    public Label lblStudentCount;
    public Label lblPaymentCount;
    public Label lblCourseCount;
    public Label lblUsersCount;

    List<Student> studentList ;
    List<Payment> paymentList;
    List<Course> programList;
    List<User> userList;

    public void initialize(){
        setDayStatus();
        setUserName();
        setCounts();
    }

    private void setCounts() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {

            studentList = session.createQuery("FROM Student", Student.class).getResultList();
             paymentList = session.createQuery("FROM Payment", Payment.class).getResultList();
             programList = session.createQuery("FROM Course", Course.class).getResultList();
             userList = session.createQuery("FROM User", User.class).getResultList();
            lblStudentCount.setText(String.valueOf(studentList.size()));
            lblPaymentCount.setText(String.valueOf(paymentList.size()));
            lblCourseCount.setText(String.valueOf(programList.size()));
            lblUsersCount.setText(String.valueOf(userList.size()));
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
        }


    }

    private void setUserName() {
        lblUserName.setText(LoginFormController.user.getUserName());
    }

    private void setDayStatus() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        int hour = currentTimestamp.getHours();
        if (hour < 12) {
            lblDayStatus.setText(" Morning");
        } else if (hour < 18) {
            lblDayStatus.setText(" Afternoon");
        } else {
            lblDayStatus.setText(" Evening");
        }
    }


}