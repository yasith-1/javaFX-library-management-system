package service.custom;

import service.SuperService;

public interface DashboardOverviewService extends SuperService {
    Integer getBookCount();

    Integer getMemberCount();

    Integer getAuthorCount();

    Integer getIssuedBookCount();

    Boolean updateBookStatus();
}
