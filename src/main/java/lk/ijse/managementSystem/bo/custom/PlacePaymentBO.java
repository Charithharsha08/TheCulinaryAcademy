package lk.ijse.managementSystem.bo.custom;

import lk.ijse.managementSystem.bo.SuperBo;
import lk.ijse.managementSystem.dto.CourseDTO;
import lk.ijse.managementSystem.dto.PaymentDTO;
import lk.ijse.managementSystem.dto.StudentCourseDetailDTO;
import lk.ijse.managementSystem.dto.StudentDTO;

import java.util.List;

public interface PlacePaymentBO extends SuperBo {
    List<CourseDTO> getAllCourses();

    StudentDTO getStudent(String contact);

    CourseDTO getCourse(String selectedCourseDescription);

    void placePayment(StudentCourseDetailDTO studentCourseDetailDTO, PaymentDTO paymentDTO);
}
