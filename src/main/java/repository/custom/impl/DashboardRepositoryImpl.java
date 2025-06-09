package repository.custom.impl;

import repository.custom.DashboardRepository;
import util.CrudUtil;

import java.sql.ResultSet;

public class DashboardRepositoryImpl implements DashboardRepository {

    @Override
    public Integer totalBooksCount() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT COUNT(isbn) FROM `book`");

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
            ResultSet resultSet = CrudUtil.execute("SELECT COUNT(id) FROM `member`");

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
            ResultSet resultSet = CrudUtil.execute("SELECT COUNT(id) FROM `author`");

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
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT COUNT(member_id AND book_isbn) FROM `member_has_book`");

            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString(1));
            }

            return 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
