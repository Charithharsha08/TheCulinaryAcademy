package lk.ijse.managementSystem.dao.custom.impl;

import lk.ijse.managementSystem.dao.custom.StudentDAO;
import lk.ijse.managementSystem.entity.Student;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean add(Student entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        return false;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }
}