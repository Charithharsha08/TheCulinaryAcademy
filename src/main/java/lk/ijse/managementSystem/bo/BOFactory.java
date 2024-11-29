package lk.ijse.managementSystem.bo;

import lk.ijse.managementSystem.bo.custom.impl.*;

public class BOFactory implements SuperBo {
    private static BOFactory boFactory;
    private static StudentBOImpl studentBo;
    private static CourseBOImpl courseBo;
    private static PlacePaymentBOImpl placePaymentBo;
    private static UserBOImpl userBO;
    private static PaymentBOImpl paymentBO;
    private static DashboardBOImpl dashboardBo;

    private BOFactory(){
        userBO = new UserBOImpl();
        studentBo = new StudentBOImpl();
        courseBo = new CourseBOImpl();
        placePaymentBo = new PlacePaymentBOImpl();
        paymentBO = new PaymentBOImpl();
        dashboardBo = new DashboardBOImpl();
    }

    public static BOFactory getInstance(){
        return boFactory = boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOType {
        USER,STUDENT,COURSE,PLACEPAYMENT,PAYMENT,DASHBOARD
    }
    public SuperBo getBO(BOType type){
        return switch (type) {
            case PAYMENT -> paymentBO;
            case USER -> userBO;
            case STUDENT -> studentBo;
            case COURSE -> courseBo;
            case PLACEPAYMENT -> placePaymentBo;
            case DASHBOARD -> dashboardBo;
        };
    }

    }

