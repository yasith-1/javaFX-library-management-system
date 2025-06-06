package service.custom.impl;

import database.DBConnection;
import dto.Book;
import repository.custom.impl.DashboardRepositoryImpl;
import service.ServiceFactory;
import service.custom.DashboardService;
import util.ServiceType;

public class DashboardServiceImpl implements DashboardService {

    DashboardRepositoryImpl dashboardRepository = ServiceFactory.getInstance().getServiceType(ServiceType.DASHBOARD);

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
    public Integer getIssudedBookCount() {
        return dashboardRepository.totalIssuedBooksCount();
    }

}
