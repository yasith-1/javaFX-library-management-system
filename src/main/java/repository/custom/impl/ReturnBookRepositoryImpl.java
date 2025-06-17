package repository.custom.impl;

import database.DBConnection;
import entity.ReturnBookEntity;
import repository.custom.ReturnBookRepository;
import util.CrudUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ReturnBookRepositoryImpl implements ReturnBookRepository {

    HashMap<String, String> bookmap = new HashMap<>();
    HashMap<String, String> memberMap = new HashMap<>();

    @Override
    public HashMap<String, String> getMemberSet() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `id` ,`name` FROM `member` WHERE `type_id`=?", 2);
            while (resultset.next()) {
                memberMap.put(resultset.getString("name"), resultset.getString("id"));
            }
            return memberMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, String> getBookSet() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `title` ,`isbn` FROM `book` WHERE `status_id`=?", "S001");
            while (resultset.next()) {
                bookmap.put(resultset.getString("title"), resultset.getString("isbn"));
            }
            return bookmap;
        } catch (Exception e) {
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
                    if (removeIssuedBookRecord(entity)) {
                        connection.commit();
                        return true;
                    }
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
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Boolean removeIssuedBookRecord(ReturnBookEntity entity) {
        try {
            Boolean result = CrudUtil.execute("DELETE FROM `member_has_book` WHERE `member_id`=? AND `book_isbn`=? ",
                    entity.getMemberId(),
                    entity.getIsbn());

            return result;
        } catch (Exception e) {
            e.getMessage();
            return false;
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
            e.getMessage();
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
            e.getMessage();
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
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ReturnBookEntity search(ReturnBookEntity entity) {
        return null;
    }
}
