package lk.ijse.managementSystem.dao.custom.impl;

import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.dao.custom.StudentDAO;
import lk.ijse.managementSystem.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    Session session;
    @Override
    public List<Student> getAll() throws Exception {
         session = SessionFactoryConfig.getInstance().getSession();
        List<Student> studentList = session.createQuery("FROM Student", Student.class).getResultList();
        session.close();
        return studentList;

    }

    @Override
    public boolean add(Student entity) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
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
        session.createNativeQuery("DELETE FROM student WHERE student_id = :id").setParameter("id", id).executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }
}
