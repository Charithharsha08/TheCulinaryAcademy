package lk.ijse.managementSystem.bo.custom.impl;

import lk.ijse.managementSystem.bo.custom.StudentBO;
import lk.ijse.managementSystem.dto.StudentDTO;

import java.util.List;

public class StudentBOImpl implements StudentBO {
    @Override
    public boolean addStudent(StudentDTO studentDTO) {
        return false;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return List.of();
    }

    @Override
    public StudentDTO search(String contact) {
        return null;
    }

    @Override
    public boolean update(StudentDTO dto) {
        return false;
    }
}
