package lk.ijse.managementSystem.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.managementSystem.bo.custom.StudentBO;
import lk.ijse.managementSystem.controller.LoginFormController;
import lk.ijse.managementSystem.dao.DAOFactory;
import lk.ijse.managementSystem.dao.custom.StudentDAO;
import lk.ijse.managementSystem.dto.StudentDTO;
import lk.ijse.managementSystem.entity.Student;
import lk.ijse.managementSystem.entity.User;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean addStudent(StudentDTO studentDTO) {
        try {
            return studentDAO.add(new Student(
                    studentDTO.getId(),
                    studentDTO.getName(),
                    studentDTO.getAddress(),
                    studentDTO.getEmail(),
                    studentDTO.getContact(),
                    studentDTO.getUser()
            ));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Already Exists user or Invalid Credentials").show();
            return false;

        }
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        try {
            List<Student> allStudents = studentDAO.getAll();
            for (Student student : allStudents) {
                studentDTOS.add(new StudentDTO(
                        student.getId(),
                        student.getName(),
                        student.getAddress(),
                        student.getEmail(),
                        student.getContact(),
                        student.getUser()
                ));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
        }
        return studentDTOS;
    }

    @Override
    public StudentDTO search(String contact) {
        return null;
    }

    @Override
    public boolean updateStudent(StudentDTO dto) {
        try {
            return studentDAO.update(new Student(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getContact(),
                dto.getUser()
            ));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong please try again later").show();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(String id) {
        try {
            return studentDAO.delete(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "This student is in use and cannot be deleted").show();
            return false;
        }
    }
}
