package service;

import service.custom.impl.DashboardServiceImpl;
import util.Type;

public class ServiceFactory {

    private static ServiceFactory instance;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            return instance = new ServiceFactory();
        }
        return instance;
    }

    public <T extends SuperService> T getServiceType(Type type) {
        switch (type) {
            case DASHBOARD:
                return (T) new DashboardServiceImpl();
//            case MEMBER: : return (T) new DashboardServiceImpl();
//            case BOOK: : return (T) new DashboardServiceImpl();
        }
        return null;
    }

}
