package repository.custom.impl;

import alert.Alert;
import alert.AlertType;
import entity.BookEntity;
import repository.custom.BookRepository;
import util.CrudUtil;
import util.MapCollection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private HashMap<String, String> gerneMap = MapCollection.getInstance().getGerneMap();
    private HashMap<String, String> authorMap = MapCollection.getInstance().getAuthorMap();
    private HashMap<String, String> statusMap = MapCollection.getInstance().getBookStatusMap();
    private HashMap<String, String> bookMap = MapCollection.getInstance().getBookMap();

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
            Alert.trigger(AlertType.ERROR, "Failed to retrieve last book ID: " + e.getMessage());
            return null;
        }
    }

    @Override
    public HashMap<String, String> getAllGernes() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `gerne`");
            while (resultSet.next()) {
                gerneMap.put(resultSet.getString("name"), resultSet.getString("gerne_id"));
            }
            return gerneMap;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve genres: " + e.getMessage());
            return null;
        }

    }

    @Override
    public HashMap<String, String> getAllAuthors() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `author`");
            while (resultSet.next()) {
                authorMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }
            return authorMap;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve authors: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, String> getAllStatus() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `book_status`");
            while (resultSet.next()) {
                statusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }
            return statusMap;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve book statuses: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, String> getAllBooks() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `title` ,`isbn` FROM `book` WHERE `status_id`=?", "S001");
            while (resultset.next()) {
                bookMap.put(resultset.getString("title"), resultset.getString("isbn"));
            }
            return bookMap;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve books: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookEntity> getBookEntityList() {
        ArrayList<BookEntity> bookEntityList = new ArrayList<>();
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
            Alert.trigger(AlertType.ERROR, "Failed to create book list: " + e.getMessage());
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
            Alert.trigger(AlertType.ERROR, "Failed to add book: " + e.getMessage());
            return false;
        }
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
            Alert.trigger(AlertType.ERROR, "Failed to update book: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(BookEntity entity) {
        try {
            boolean result = CrudUtil.execute("DELETE FROM `book` WHERE `isbn`=?", entity.getIsbn()); // <-- if record delete statement true else false
            return result;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to delete book: " + e.getMessage());
            return false;
        }
    }

    @Override
    public BookEntity search(BookEntity entity) {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `isbn`,`title`,`copies`,`status`," +
                    "gerne.`name`AS `gerne_name`,author.`name` AS `author_name` FROM `book` " +
                    "INNER JOIN `book_status` ON book.status_id= book_status.id " +
                    "INNER JOIN `gerne` ON book.gerne_id = gerne.gerne_id " +
                    "INNER JOIN `author` ON book.author_id=author.id WHERE `title`=? OR `isbn`=?", entity.getTitle(), entity.getIsbn());
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
            Alert.trigger(AlertType.ERROR, "Failed to search book: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<BookEntity> searchBooks(String title, String author, String category) {
        ArrayList<BookEntity> bookEntityList = new ArrayList<>();
        try {
            ResultSet resultset = CrudUtil.execute("SELECT \n" +
                            "    book.title,\n" +
                            "    author.name AS author_name,\n" +
                            "    gerne.name AS gerne_name\n" +
                            "FROM \n" +
                            "    book\n" +
                            "INNER JOIN \n" +
                            "    book_status ON book.status_id = book_status.id\n" +
                            "INNER JOIN \n" +
                            "    gerne ON book.gerne_id = gerne.gerne_id\n" +
                            "INNER JOIN \n" +
                            "    author ON book.author_id = author.id\n" +
                            "WHERE \n" +
                            "    book.title = ?\n" +
                            "    OR book.gerne_id = ?\n" +
                            "    OR book.author_id = ?\n",
                    title, category, author);
            if (resultset.next()) {
                BookEntity bookEntity = new BookEntity(
                        null,
                        resultset.getString("title"),
                        null,
                        null,
                        resultset.getString("gerne_name"),
                        resultset.getString("author_name"));

                bookEntityList.add(bookEntity);
                return bookEntityList;
            }
            return null;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to search books: " + e.getMessage());
            return null;
        }
    }
}
