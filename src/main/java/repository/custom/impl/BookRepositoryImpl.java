package repository.custom.impl;

import entity.BookEntity;
import repository.custom.BookRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private HashMap<String, String> gerneMap = new HashMap<>();
    private HashMap<String, String> authorMap = new HashMap<>();
    private HashMap<String, String> statusMap = new HashMap<>();
    private ArrayList<BookEntity> bookEntityList = new ArrayList<>();

    @Override
    public String getLastBookId() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `book` ORDER BY `isbn` DESC LIMIT 1");
            if (resultSet.next()) {
                return resultSet.getString("isbn");
            } else {
                return null;
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

    @Override
    public List<BookEntity> getBookEntityList() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `isbn`,`title`,author.`name` AS `author_name`,gerne.`name`AS `gerne_name`,`copies`,`status` FROM `book` INNER JOIN `book_status` ON book.status_id= book_status.id INNER JOIN `gerne` ON book.gerne_id = gerne.gerne_id INNER JOIN `author` ON book.author_id=author.id");
            while (resultset.next()) {
                BookEntity bookEntity = new BookEntity(
                        resultset.getString("isbn"),
                        resultset.getString("title"),
                        resultset.getInt("copies"),
                        resultset.getString("status"),
                        resultset.getString("gerne_name"),
                        resultset.getString("author_name"));

                bookEntityList.add(bookEntity);
            }

            return bookEntityList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean add(BookEntity entity) {
        try {
            Boolean result = CrudUtil.execute("INSERT INTO `book` VALUES(?,?,?,?,?,?)",
                    entity.getIsbn(),
                    entity.getTitle(),
                    entity.getCopies(),
                    entity.getStatusId(),
                    entity.getGerneId(),
                    entity.getAuthorId());

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean update(BookEntity entity) {
        try {
            Boolean result = CrudUtil.execute("UPDATE `book` SET `title`=?,`copies`=?,`status_id`=?,`gerne_id`=?,`author_id`=? WHERE `isbn`=?",
                    entity.getTitle(),
                    entity.getCopies(),
                    entity.getStatusId(),
                    entity.getGerneId(),
                    entity.getAuthorId(),
                    entity.getIsbn());

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean delete(String id) {
        try {
            boolean result = CrudUtil.execute("DELETE FROM `book` WHERE `isbn`=?", id); // <-- if record delete statement true else false
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookEntity search(String value) {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `isbn`,`title`,`copies`,`status`," +
                    "gerne.`name`AS `gerne_name`,author.`name` AS `author_name` FROM `book` " +
                    "INNER JOIN `book_status` ON book.status_id= book_status.id " +
                    "INNER JOIN `gerne` ON book.gerne_id = gerne.gerne_id " +
                    "INNER JOIN `author` ON book.author_id=author.id WHERE `title`=? OR `isbn`=?", value,value);
            if (resultset.next()) {
                BookEntity bookEntity = new BookEntity(
                        resultset.getString("isbn"),
                        resultset.getString("title"),
                        resultset.getInt("copies"),
                        resultset.getString("status"),
                        resultset.getString("gerne_name"),
                        resultset.getString("author_name"));

                return bookEntity;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
