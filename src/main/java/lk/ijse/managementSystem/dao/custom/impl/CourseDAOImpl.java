package lk.ijse.managementSystem.dao.custom.impl;

import lk.ijse.managementSystem.dao.custom.CourseDAO;
import lk.ijse.managementSystem.entity.Course;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public List<Course> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean add(Course entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Course entity) throws Exception {
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
