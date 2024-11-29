package lk.ijse.managementSystem.dao.custom;

import lk.ijse.managementSystem.dao.CrudDAO;
import lk.ijse.managementSystem.entity.StudentCourseDetail;
import org.hibernate.Session;

public interface StudentCourseDetailDAO extends CrudDAO<StudentCourseDetail> {
    void addTransaction(Session session, StudentCourseDetail obj);

}
