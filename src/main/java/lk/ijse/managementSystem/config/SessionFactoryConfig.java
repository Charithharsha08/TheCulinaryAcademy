package lk.ijse.managementSystem.config;

import lk.ijse.managementSystem.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * This is the session factory configuration class
 */
public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    /**
     * Defines default constructor as private
     * to avoid object creation of this class from outside
     */
    private SessionFactoryConfig(){
       sessionFactory = new MetadataSources(new StandardServiceRegistryBuilder()
               .loadProperties("hibernate.properties").build())
               .addAnnotatedClass(Student.class)
               .addAnnotatedClass(User.class)
               .addAnnotatedClass(Course.class)
               .addAnnotatedClass(Payment.class)
               .addAnnotatedClass(StudentCourseDetail.class)
               .getMetadataBuilder()
               .build().buildSessionFactory();
    }

    /**
     * @return lk.ijse.gdse.orm.hibernate.config.SessionFactoryConfig
     * Singleton the class to avoid object re-creation
     */
    public static SessionFactoryConfig getInstance() {
        return (null == factoryConfig)
                ? factoryConfig = new SessionFactoryConfig()
                : factoryConfig;
    }

    /**
     * @return org.hibernate.Session
     * Returns Hibernate session whenever this method is called
     */
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
