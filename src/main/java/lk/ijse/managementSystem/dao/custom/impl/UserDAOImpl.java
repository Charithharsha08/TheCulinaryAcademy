package lk.ijse.managementSystem.dao.custom.impl;

import lk.ijse.managementSystem.config.SessionFactoryConfig;
import lk.ijse.managementSystem.dao.custom.UserDAO;
import lk.ijse.managementSystem.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    Session session;
    @Override
    public List<User> getAll() throws Exception {
         session = SessionFactoryConfig.getInstance().getSession();
        List<User> userList = session.createQuery("FROM User", User.class).getResultList();
        return userList;
    }

    @Override
    public boolean add(User entity) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery("DELETE FROM user WHERE user_name = :id").setParameter("id", id).executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User search(String id) throws Exception {
        return null;
    }
}
