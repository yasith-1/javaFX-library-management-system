package service.custom.impl;

import com.google.inject.Inject;
import repository.RepositoryFactory;
import repository.custom.DashboardRepository;
import repository.custom.impl.DashboardRepositoryImpl;
import service.custom.DashboardOverviewService;
import util.RepositoryType;

public class DashboardOverviewServiceImpl implements DashboardOverviewService {


    //        DashboardRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.DASHBOARD);

    //        Using Dependency Injection for better testability and maintainability
    @Inject
    DashboardRepository repository;

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
