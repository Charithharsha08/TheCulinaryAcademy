package lk.ijse.managementSystem.dao.custom.impl;

import lk.ijse.managementSystem.dao.custom.PaymentDAO;
import lk.ijse.managementSystem.entity.Payment;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public List<Payment> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean add(Payment entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws Exception {
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
    public Payment search(String id) throws Exception {
        return null;
    }
}
