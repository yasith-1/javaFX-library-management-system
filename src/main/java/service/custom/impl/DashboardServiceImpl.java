package service.custom.impl;

import repository.RepositoryFactory;
import repository.custom.impl.DashboardRepositoryImpl;
import service.ServiceFactory;
import service.custom.DashboardService;
import util.RepositoryType;
import util.ServiceType;

public class DashboardServiceImpl implements DashboardService {

    DashboardRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.DASHBOARD);

    @Override
    public Integer getBookCount() {
        return repository.totalBooksCount();
    }

    @Override
    public Integer getMemberCount() {
        return repository.totalMembersCount();
    }

    @Override
    public Integer getAuthorCount() {
        return repository.totalAuthorsCount();
    }

    @Override
    public Integer getIssuedBookCount() {
        return repository.totalIssuedBooksCount();
    }

    @Override
    public Boolean updateBookStatus() {
        return repository.updateDatabaseBooksStatus();
    }

}
