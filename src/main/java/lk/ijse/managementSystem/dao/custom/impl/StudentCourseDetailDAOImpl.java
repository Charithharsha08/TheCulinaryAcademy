package lk.ijse.managementSystem.dao.custom.impl;

import lk.ijse.managementSystem.dao.custom.StudentCourseDetailDAO;
import lk.ijse.managementSystem.entity.StudentCourseDetail;

import java.util.List;

public class StudentCourseDetailDAOImpl implements StudentCourseDetailDAO {
    @Override
    public List<StudentCourseDetail> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean add(StudentCourseDetail entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(StudentCourseDetail entity) throws Exception {
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

    @Override
    public StudentCourseDetail search(String id) throws Exception {
        return null;
    }
}
