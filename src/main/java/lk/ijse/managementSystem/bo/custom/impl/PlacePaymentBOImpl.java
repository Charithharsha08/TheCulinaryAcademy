package lk.ijse.managementSystem.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.managementSystem.bo.custom.PlacePaymentBO;
import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.dao.DAOFactory;
import lk.ijse.managementSystem.dao.custom.CourseDAO;
import lk.ijse.managementSystem.dao.custom.PaymentDAO;
import lk.ijse.managementSystem.dao.custom.StudentCourseDetailDAO;
import lk.ijse.managementSystem.dao.custom.StudentDAO;
import lk.ijse.managementSystem.dto.CourseDTO;
import lk.ijse.managementSystem.dto.PaymentDTO;
import lk.ijse.managementSystem.dto.StudentCourseDetailDTO;
import lk.ijse.managementSystem.dto.StudentDTO;
import lk.ijse.managementSystem.entity.Course;
import lk.ijse.managementSystem.entity.Payment;
import lk.ijse.managementSystem.entity.Student;
import lk.ijse.managementSystem.entity.StudentCourseDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlacePaymentBOImpl implements PlacePaymentBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    StudentCourseDetailDAO studentCourseDetailDAO = (StudentCourseDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT_COURSE_DETAIL);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public List<CourseDTO> getAllCourses() {
        return List.of();
    }

    @Override
    public StudentDTO getStudent(String contact) {
        try {
            Student student =   studentDAO.search(contact);
            return new StudentDTO(student.getId(), student.getName(), student.getAddress(), student.getEmail(), student.getContact(), student.getUser());
        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
            return null;
        }
    }

    @Override
    public CourseDTO getCourse(String selectedCourseDescription) {
        Course course = null;
        try {
            course = courseDAO.search(selectedCourseDescription);
        } catch (Exception e) {
             new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
        }
        return new CourseDTO(course.getId(), course.getDescription(), course.getDuration(), course.getPrice());
    }

    @Override
    public void placePayment(StudentCourseDetailDTO studentCourseDetailDTO, PaymentDTO paymentDTO) {

        StudentDTO studentDTO = studentCourseDetailDTO.getStudent();
        CourseDTO courseDTO = studentCourseDetailDTO.getCourse();

        Student student = new Student(studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getEmail(),
                studentDTO.getContact(),
                studentDTO.getUser()
        );

        Course course = new Course(courseDTO.getId(),
                courseDTO.getDescription(),
                courseDTO.getDuration(),
                courseDTO.getPrice()
        );


        StudentCourseDetail studentCourseDetail = new StudentCourseDetail(studentCourseDetailDTO.getStuCouDetailId(),
                studentCourseDetailDTO.getRegistrationDate(),
                student,
                course
        );


        Payment payment = new Payment(paymentDTO.getId(),
                paymentDTO.getMethod(),
                paymentDTO.getOrderDateTime(),
                paymentDTO.getBalance(),
                paymentDTO.getTotal(),
                studentCourseDetail
        );

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        studentCourseDetailDAO.addTransaction(session, studentCourseDetail);
        paymentDAO.addTransaction(session, payment);
        transaction.commit();
        new Alert(Alert.AlertType.INFORMATION, "Payment Saved Successfully").show();
        session.close();


    }
}
