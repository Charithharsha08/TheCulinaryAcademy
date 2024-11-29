package lk.ijse.managementSystem.bo.custom;

import lk.ijse.managementSystem.bo.SuperBo;
import lk.ijse.managementSystem.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBo {
    boolean addStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    StudentDTO search(String contact);

    boolean updateStudent(StudentDTO dto);

    boolean deleteStudent(String id);
}
