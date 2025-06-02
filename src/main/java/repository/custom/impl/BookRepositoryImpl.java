package repository.custom.impl;

import entity.BookEntity;
import repository.CrudRepository;
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
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `book` ORDER BY `isbn` DESC LIMIT 1");
            if (resultSet.next()) {
                return resultSet.getString("isbn");
            } else {
                return "B001";
            }
        } catch (Exception e) {

//            Notifications.create()
//                    .title("Error")
//                    .text(e.getMessage())
//                    .graphic(new ImageView(new Image(""))) // You can use an icon: new ImageView(new Image("icon.png"))
//                    .hideAfter(Duration.seconds(3))
//                    .position(Pos.TOP_RIGHT)
//                    .showInformation();

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
        return null;
    }

    @Override
    public Boolean delete(String s) {
        return null;
    }

    @Override
    public BookEntity search(String s) {
        return null;
    }
}
