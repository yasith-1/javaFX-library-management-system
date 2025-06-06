package service.custom;

import dto.Book;
import service.SuperService;

public interface DashboardService extends SuperService {
    Integer getBookCount();

    Integer getMemberCount();

    Integer getAuthorCount();

    Integer getIssudedBookCount();
}
