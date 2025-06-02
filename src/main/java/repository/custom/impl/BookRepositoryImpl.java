package repository.custom.impl;

import repository.custom.BookRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.util.HashMap;

public class BookRepositoryImpl implements BookRepository {

    private HashMap<String, String> gerneMap = new HashMap<>();
    private HashMap<String, String> authorMap = new HashMap<>();
    private HashMap<String, String> statusMap = new HashMap<>();

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

    @Override
    public HashMap<String, String> getAllGernes() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `gerne`");
            while (resultSet.next()) {
                gerneMap.put(resultSet.getString("name"), resultSet.getString("gerne_id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return gerneMap;
    }

    @Override
    public HashMap<String, String> getAllAuthors() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `author`");
            while (resultSet.next()) {
                authorMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return authorMap;
    }

    @Override
    public HashMap<String, String> getAllStatus() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `book_status`");
            while (resultSet.next()) {
                statusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return statusMap;
    }

}
