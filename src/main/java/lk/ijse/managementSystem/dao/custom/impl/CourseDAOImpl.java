package lk.ijse.managementSystem.dao.custom.impl;

import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.dao.custom.CourseDAO;
import lk.ijse.managementSystem.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    Session session;
    @Override
    public List<Course> getAll() throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        List<Course> courseList = session.createQuery("FROM Course", Course.class).getResultList();
        return courseList;
    }

    @Override
    public boolean add(Course entity) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course entity) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery("DELETE FROM course WHERE course_id = :id").setParameter("id", id).executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Course search(String id) throws Exception {
      return null;
    }
}
