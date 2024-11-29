package lk.ijse.managementSystem.bo.custom.impl;

import lk.ijse.managementSystem.bo.custom.PlacePaymentBO;
import lk.ijse.managementSystem.dto.CourseDTO;
import lk.ijse.managementSystem.dto.PaymentDTO;
import lk.ijse.managementSystem.dto.StudentCourseDetailDTO;
import lk.ijse.managementSystem.dto.StudentDTO;

import java.util.List;

public class PlacePaymentBOImpl implements PlacePaymentBO {
    @Override
    public List<CourseDTO> getAllCourses() {
        return List.of();
    }

    @Override
    public StudentDTO getStudent(String contact) {
        return null;
    }

    @Override
    public CourseDTO getCourse(String selectedCourseDescription) {
        return null;
    }

    @Override
    public boolean placePayment(StudentCourseDetailDTO studentCourseDetailDTO, PaymentDTO paymentDTO) {
        return false;
    }
}
