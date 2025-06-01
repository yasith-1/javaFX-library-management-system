package service.custom.impl;

import database.DBConnection;
import dto.Book;
import service.custom.DashboardService;

import java.sql.ResultSet;

public class DashboardServiceImpl implements DashboardService {
    @Override
    public Integer getBookCount() {
        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().
                    executeQuery("SELECT COUNT(isbn) FROM `book`");

            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString(1));
            }

            return 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer getMemberCount() {
        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().
                    executeQuery("SELECT COUNT(id) FROM `member`");

            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString(1));
            }

            return 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
