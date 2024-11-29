package lk.ijse.managementSystem.bo.custom;

import lk.ijse.managementSystem.bo.SuperBo;
import lk.ijse.managementSystem.dto.CourseDTO;
import lk.ijse.managementSystem.dto.PaymentDTO;
import lk.ijse.managementSystem.dto.StudentDTO;
import lk.ijse.managementSystem.dto.UserDTO;

import java.util.List;

public interface HomeBO extends SuperBo {

    List<StudentDTO> getAllStudents();

    List<UserDTO> getAllUsers();

    List<CourseDTO> getAllCourses();

    List<PaymentDTO> getAllPayments();
}
