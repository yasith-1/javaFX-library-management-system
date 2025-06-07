package repository;

import repository.custom.DashboardRepository;
import repository.custom.impl.BookRepositoryImpl;
import repository.custom.impl.CategoryRepositoryImpl;
import repository.custom.impl.DashboardRepositoryImpl;
import util.RepositoryType;

public class RepositoryFactory {
    private static RepositoryFactory instance;

    public static RepositoryFactory getInstance() {
        return instance == null ? instance = new RepositoryFactory() : instance;
    }

    public <T extends SuperRepository> T getRepositoryType(RepositoryType type) {
        switch (type) {
            case DASHBOARD:return (T) new DashboardRepositoryImpl();
            case BOOK:return (T) new BookRepositoryImpl();
            case CATEGORY:return (T) new CategoryRepositoryImpl();
        }
        return null;
    }
}
