package service;

import service.custom.impl.*;
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
            case DASHBOARD:
                return (T) new DashboardServiceImpl();
            case BOOK:
                return (T) new BookServiceImpl();
            case CATEGORY:
                return (T) new CategoryServiceImpl();
            case ISSUEDBOOK:
                return (T) new IssuedBookServiceImpl();
            case MEMBER:
                return (T) new MemberServiceImpl();
            case RETURNBOOK:
                return (T) new ReturnBookServiceImpl();
            case FINE:
                return (T) new FineServiceImpl();
            case AUTHOR:
                return (T) new AuthorServiceImpl();
            case DELAYEDRETURN:
                return (T) new DelayReturnServiceImpl();
            case PENDINGFINE:
                return (T) new PendingFineServiceImpl();
        }
        return null;
    }

}
