package repository.custom.impl;

import entity.IssuedBookEntity;
import repository.custom.IssuedBookRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.util.HashMap;

public class IssuedBookRepositoryImpl implements IssuedBookRepository {

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
            ResultSet resultset = CrudUtil.execute("SELECT `title` ,`isbn` FROM `book`");
            while (resultset.next()) {
                bookmap.put(resultset.getString("title"), resultset.getString("isbn"));
            }
            return bookmap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deductbookQuantity(IssuedBookEntity entity) {
        try {
            Boolean result = CrudUtil.execute("UPDATE `book` SET `copies`=copies-? WHERE `isbn`=?",
                    entity.getQty(),
                    entity.getIsbn());
            return result;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public Boolean renewbookQuantity(IssuedBookEntity entity) {
        try {
            Boolean result = CrudUtil.execute("UPDATE `book` SET `copies`=copies+? WHERE `isbn`=?",
                    entity.getQty(),
                    entity.getIsbn());
            return result;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public Boolean add(IssuedBookEntity entity) {
        try {
//            Check same user try to borrow same book--------------------------------------------------------------
            ResultSet resultSet = CrudUtil.execute("SELECT `member_id`,`book_isbn` FROM `member_has_book` " +
                            "WHERE `member_id`=? AND `book_isbn` =?",
                    entity.getMemberId(),
                    entity.getIsbn());

            if (resultSet.next()) {
                return false;
            }
//          -------------------------------------------------------------------------------------------------------
            Boolean result = CrudUtil.execute("INSERT INTO `member_has_book` VALUES (?,?,?,?,?,?)",
                    entity.getMemberId(),
                    entity.getIsbn(),
                    entity.getQty(),
                    entity.getDate(),
                    entity.getTime(),
                    entity.getReturnDate());
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(IssuedBookEntity entity) {
        try {
            // Check if same user is trying to borrow the same book
            ResultSet resultSet = CrudUtil.execute(
                    "SELECT `member_id`, `book_isbn` FROM `member_has_book` WHERE `member_id` = ? AND `book_isbn` = ?",
                    entity.getMemberId(),
                    entity.getIsbn()
            );

            if (!resultSet.next()) {
                // Record does not exist, cannot update
                System.out.println("Record does not exist, cannot update");
                return false;
            }

            // Update record
            Boolean result = CrudUtil.execute(
                    "UPDATE `member_has_book` SET `issue_qty` = ? WHERE `member_id` = ? AND `book_isbn` = ?",
                    entity.getQty(),
                    entity.getMemberId(),
                    entity.getIsbn()
            );

            System.out.println(result);
            return result;
        } catch (Exception e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }


    @Override
    public Boolean delete(String s) {
        return null;
    }

    @Override
    public IssuedBookEntity search(String s) {
        return null;
    }

    @Override
    public Boolean deleteIssuedBook(IssuedBookEntity entity) {
        try {
            // Check if same user is trying to borrow the same book
            ResultSet resultSet = CrudUtil.execute(
                    "SELECT `member_id`, `book_isbn` FROM `member_has_book` WHERE `member_id` = ? AND `book_isbn` = ?",
                    entity.getMemberId(),
                    entity.getIsbn()
            );

            if (!resultSet.next()) {
                // Record does not exist, cannot update
                return false;
            }

            // Update record
            Boolean result = CrudUtil.execute(
                    "DELETE FROM `member_has_book` WHERE `member_id` = ? AND `book_isbn` = ?",
                    entity.getMemberId(), entity.getIsbn());

            return result;
        } catch (Exception e) {
            System.out.println("Delete failed: " + e.getMessage());
            return false;
        }
    }

}
