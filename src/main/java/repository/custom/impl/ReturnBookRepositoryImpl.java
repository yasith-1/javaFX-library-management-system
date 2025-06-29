package repository.custom.impl;

import alert.Alert;
import alert.AlertType;
import database.DBConnection;
import entity.ReturnBookEntity;
import repository.custom.ReturnBookRepository;
import util.CrudUtil;
import util.MapCollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReturnBookRepositoryImpl implements ReturnBookRepository {

    HashMap<String, String> bookMap = MapCollection.getInstance().getBookMap();
    HashMap<String, String> memberMap = MapCollection.getInstance().getMemberMap();

    @Override
    public List<ReturnBookEntity> returnBookList() {
        ArrayList<ReturnBookEntity> returnBookEntitiesList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT `member`.`name`,book.title ,returned_date,returned_time " +
                    "FROM `return_book` INNER JOIN `book` ON return_book.book_isbn = book.isbn " +
                    "INNER JOIN `member` ON return_book.member_id = `member`.id");

            while (resultSet.next()) {
                ReturnBookEntity returnBookEntity = new ReturnBookEntity(
                        resultSet.getString("name"),
                        resultSet.getString("title"),
                        resultSet.getDate("returned_date").toLocalDate(),
                        resultSet.getTime("returned_time").toLocalTime());

                returnBookEntitiesList.add(returnBookEntity);
            }
            return returnBookEntitiesList;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve return book list: " + e.getMessage());
            return null;
        }

    }

    @Override
    public HashMap<String, String> getMemberSet() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `id` ,`name` FROM `member` WHERE `type_id`=?", "T2");
            while (resultset.next()) {
                memberMap.put(resultset.getString("name"), resultset.getString("id"));
            }
            return memberMap;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve members: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, String> getBookSet() {
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

    //    Return book record add and transaction controlled
    @Override
    public Boolean add(ReturnBookEntity entity) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement("INSERT INTO `return_book` VALUES (?,?,?,?)");
            pst.setObject(1, entity.getMemberId());
            pst.setObject(2, entity.getIsbn());
            pst.setObject(3, entity.getReturnDate());
            pst.setObject(4, entity.getReturnTime());

            boolean isAddedReturnRecord = pst.executeUpdate() > 0;
            if (isAddedReturnRecord) {
                Boolean isRenewedQuantity = renewbookQuantity(entity);
                if (isRenewedQuantity) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            e.getMessage();
            return false;
        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                Alert.trigger(AlertType.ERROR, "Failed to reset auto-commit: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public Boolean renewbookQuantity(ReturnBookEntity entity) {
        try {
            Boolean result = CrudUtil.execute("UPDATE `book` SET `copies`=copies+? WHERE `isbn`=?",
                    1,
                    entity.getIsbn());
            return result;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to renew book quantity: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deductbookQuantity(ReturnBookEntity entity) {
        try {
            Boolean result = CrudUtil.execute("UPDATE `book` SET `copies`=copies-? WHERE `isbn`=?",
                    1,
                    entity.getIsbn());
            return result;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to deduct book quantity: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(ReturnBookEntity entity) {
        return null;
    }

    @Override
    public Boolean delete(ReturnBookEntity entity) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement("DELETE FROM `return_book` WHERE `member_id`=? AND `book_isbn`=?");
            pst.setObject(1, entity.getMemberId());
            pst.setObject(2, entity.getIsbn());

            boolean isDeletedReturnRecord = pst.executeUpdate() > 0;
            if (isDeletedReturnRecord) {
                Boolean isReturnBookQtyDeducted = deductbookQuantity(entity);
                if (isReturnBookQtyDeducted) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            e.getMessage();
            return false;
        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                Alert.trigger(AlertType.ERROR, "Failed to reset auto-commit: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ReturnBookEntity search(ReturnBookEntity entity) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT `member`.`name`,`book`.`title` FROM `return_book` " +
                            "INNER JOIN `book` ON return_book.book_isbn = book.isbn " +
                            "INNER JOIN `member` ON return_book.member_id = `member`.id " +
                            "WHERE `member_id`=? AND `book_isbn`=?",
                    entity.getMemberId(),
                    entity.getIsbn());

            if (resultSet.next()) {
                return new ReturnBookEntity(
                        resultSet.getString("name"),
                        resultSet.getString("title"),
                        null,
                        null);
            }
            return null;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to search return book: " + e.getMessage());
            return null;
        }
    }
}
