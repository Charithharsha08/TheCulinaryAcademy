package lk.ijse.managementSystem.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.managementSystem.bo.custom.HomeBO;
import lk.ijse.managementSystem.bo.custom.StudentBO;
import lk.ijse.managementSystem.dao.DAOFactory;
import lk.ijse.managementSystem.dao.custom.CourseDAO;
import lk.ijse.managementSystem.dao.custom.PaymentDAO;
import lk.ijse.managementSystem.dao.custom.StudentDAO;
import lk.ijse.managementSystem.dao.custom.UserDAO;
import lk.ijse.managementSystem.dto.CourseDTO;
import lk.ijse.managementSystem.dto.PaymentDTO;
import lk.ijse.managementSystem.dto.StudentDTO;
import lk.ijse.managementSystem.dto.UserDTO;
import lk.ijse.managementSystem.entity.Course;
import lk.ijse.managementSystem.entity.Payment;
import lk.ijse.managementSystem.entity.Student;
import lk.ijse.managementSystem.entity.User;

import java.util.List;

public class HomeBOImpl implements HomeBO {
StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public List<StudentDTO> getAllStudents() {
        try {
            List<Student> students = studentDAO.getAll();
            List<StudentDTO> studentDTOS = List.of();
            for (Student student : students) {
                studentDTOS.add(new StudentDTO(
                        student.getId(),
                        student.getName(),
                        student.getAddress(),
                        student.getEmail(),
                        student.getContact(),
                        student.getUser()
                ));
            }
            return studentDTOS;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
            return null;
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        try {
            List<User> users = userDAO.getAll();
            List<UserDTO> userDTOS = List.of();
            for (User user : users) {
                userDTOS.add(new UserDTO(
                        user.getUserName(),
                        user.getPassword(),
                        user.getJobRole()
                ));
            }
            return userDTOS;
        } catch (Exception e) {
        new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
        return null;
        }

    }

    @Override
    public List<CourseDTO> getAllCourses() {
        try {
            List<Course> courses = courseDAO.getAll();
            List<CourseDTO> courseDTOS = List.of();
            for (Course course : courses) {
                courseDTOS.add(new CourseDTO(
                        course.getId(),
                        course.getDescription(),
                        course.getDuration(),
                        course.getPrice()
                ));
            }
            return courseDTOS;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
            return null;
        }
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
       /* try {
            List<Payment> payments = paymentDAO.getAll();
            List<PaymentDTO> paymentDTOS = List.of();
            for (Payment payment : payments) {
                paymentDTOS.add(new PaymentDTO(
                        payment.getId(),
                        payment.getMethod(),
                        payment.getOrderDateTime(),
                        payment.getBalance(),
                        payment.getTotal(),
                        payment.getStudentCourseDetail(new StudentCourseDetailBOImpl());
                ));
            }
            return paymentDTOS;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
            return null;
        }*/
        return null;
    }
}
