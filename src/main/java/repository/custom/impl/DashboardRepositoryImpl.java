package repository.custom.impl;

import database.DBConnection;
import repository.custom.DashboardRepository;

import java.sql.ResultSet;

public class DashboardRepositoryImpl implements DashboardRepository {

    @Override
    public Integer totalBooksCount() {
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
    public Integer totalMembersCount() {
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

    @Override
    public Integer totalAuthorsCount() {
        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().
                    executeQuery("SELECT COUNT(id) FROM `author`");

            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString(1));
            }

            return 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer totalIssuedBooksCount() {
        return 0;
    }
}
