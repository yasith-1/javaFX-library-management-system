package repository.custom.impl;

import repository.custom.BookRepository;
import util.CrudUtil;

import java.sql.ResultSet;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public String getLastBookId() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `book` LIMIT 1");
            if (resultSet.next()) {
                return resultSet.getString("isbn");
            } else {
                return "B001";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
