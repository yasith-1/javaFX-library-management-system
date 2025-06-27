package repository;

import repository.custom.impl.*;
import service.custom.impl.PendingFineServiceImpl;
import util.RepositoryType;

public class RepositoryFactory {
    private static RepositoryFactory instance;

    public static RepositoryFactory getInstance() {
        return instance == null ? instance = new RepositoryFactory() : instance;
    }

    public <T extends SuperRepository> T getRepositoryType(RepositoryType type) {
        switch (type) {
            case DASHBOARD:
                return (T) new DashboardRepositoryImpl();
            case BOOK:
                return (T) new BookRepositoryImpl();
            case CATEGORY:
                return (T) new CategoryRepositoryImpl();
            case ISSUEDBOOK:
                return (T) new IssuedBookRepositoryImpl();
            case MEMBER:
                return (T) new MemberRepositoryImpl();
            case RETURNBOOK:
                return (T) new ReturnBookRepositoryImpl();
            case FINE:
                return (T) new FineRepositoryImpl();
            case AUTHOR:
                return (T) new AuthorRepositoryImpl();
            case DELAYEDRETURN:
                return (T) new DelayReturnRepositoryImpl();
            case PENDINGFINE:
                return (T) new PendingFineRepositoryImpl();
            default:
                throw new IllegalArgumentException("Unknown RepositoryType: " + type);
        }
    }
}
