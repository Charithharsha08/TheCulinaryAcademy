package lk.ijse.managementSystem.dao.custom;

import lk.ijse.managementSystem.dao.CrudDAO;
import lk.ijse.managementSystem.entity.Payment;
import org.hibernate.Session;

public interface PaymentDAO extends CrudDAO<Payment> {
    void addTransaction(Session session, Payment obj);

}
