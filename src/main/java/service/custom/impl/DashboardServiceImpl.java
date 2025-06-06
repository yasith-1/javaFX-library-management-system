package service.custom.impl;

import repository.RepositoryFactory;
import repository.custom.impl.DashboardRepositoryImpl;
import service.ServiceFactory;
import service.custom.DashboardService;
import util.RepositoryType;
import util.ServiceType;

public class DashboardServiceImpl implements DashboardService {

    DashboardRepositoryImpl dashboardRepository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.DASHBOARD);

    @Override
    public Integer getBookCount() {
        return dashboardRepository.totalBooksCount();
    }

    @Override
    public Integer getMemberCount() {
        return dashboardRepository.totalMembersCount();
    }

    @Override
    public Integer getAuthorCount() {
        return dashboardRepository.totalAuthorsCount();
    }

    @Override
    public Integer getIssuedBookCount() {
        return dashboardRepository.totalIssuedBooksCount();
    }

}
