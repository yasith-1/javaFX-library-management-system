package service;

import service.custom.impl.BookServiceImpl;
import service.custom.impl.CategoryServiceImpl;
import service.custom.impl.DashboardServiceImpl;
import util.ServiceType;

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

    public <T extends SuperService> T getServiceType(ServiceType type) {
        switch (type) {
            case DASHBOARD : return (T) new DashboardServiceImpl();
            case BOOK : return (T) new BookServiceImpl();
            case CATEGORY:return (T) new CategoryServiceImpl();
//            case MEMBER : return (T) new DashboardServiceImpl();
        }
        return null;
    }

}
