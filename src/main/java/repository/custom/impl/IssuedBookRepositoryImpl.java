package repository.custom.impl;

import database.DBConnection;
import entity.IssuedBookEntity;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import repository.custom.IssuedBookRepository;
import util.CrudUtil;
import util.MapCollection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IssuedBookRepositoryImpl implements IssuedBookRepository {

    HashMap<String, String> bookMap = MapCollection.getInstance().getBookMap();
    HashMap<String, String> memberMap = MapCollection.getInstance().getMemberMap();
    HashMap<String, Integer> bookQuantityMap = MapCollection.getInstance().getBookQuantityMap();

    @Override
    public HashMap<String, String> getMemberSet() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `id` ,`name` FROM `member` WHERE `type_id`=?", "T2");
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
                bookMap.put(resultset.getString("title"), resultset.getString("isbn"));
            }
            return bookMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, Integer> getBookQuantityMap() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `isbn` ,`copies` FROM `book` WHERE `status_id`=?", "S001");
            while (resultset.next()) {
                bookQuantityMap.put(resultset.getString("isbn"), resultset.getInt("copies"));
            }
            return bookQuantityMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
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
                Notifications.create()
                        .title("Warning")
                        .text("Sorry That user Already Borrowed this Book !")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .showWarning();
                return false;
            }
//          ----------------------------------------------Transaction implemented---------------------------------------
            Connection connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement("INSERT INTO `member_has_book` VALUES (?,?,?,?,?,?)");
            pst.setObject(1, entity.getMemberId());
            pst.setObject(2, entity.getIsbn());
            pst.setObject(3, entity.getQty());
            pst.setObject(4, entity.getDate());
            pst.setObject(5, entity.getTime());
            pst.setObject(6, entity.getReturnDate());

            boolean isIssueBookAdded = pst.executeUpdate() > 0;
            if (isIssueBookAdded) {
                Boolean isBookCopiesDeducted = deductbookQuantity(entity);
                if (isBookCopiesDeducted) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Boolean deductbookQuantity(IssuedBookEntity entity) {
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
    public Boolean update(IssuedBookEntity entity) {
//        try {
//            ResultSet resultSet = CrudUtil.execute(
//                    "SELECT `member_id`, `book_isbn` ,`issue_qty` FROM `member_has_book` WHERE `member_id` = ? AND `book_isbn` = ?",
//                    entity.getMemberId(),
//                    entity.getIsbn()
//            );
//
//            if (!resultSet.next()) {
//                // Record does not exist, cannot update
//                Notifications.create()
//                        .title("Warning")
//                        .text("Record does not exist, cannot update")
//                        .hideAfter(Duration.seconds(3))
//                        .position(Pos.BOTTOM_RIGHT)
//                        .showWarning();
//                return false;
//            }
//
////            Restore issued book quantity to book copies before the update----------------------------------------------
//            int issueQty = resultSet.getInt("issue_qty");
//            String isbn = resultSet.getString("book_isbn");
//            renewbookQuantity(new IssuedBookEntity(null,isbn,issueQty,null,null,null));
////            ----------------------------------------------------------------------------------------------------------
//
//            // Update record
//            Connection connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//            PreparedStatement pst = connection.prepareStatement("UPDATE `member_has_book` SET " +
//                    "`issue_qty` = ? WHERE `member_id` = ? AND `book_isbn` = ?");
//            pst.setObject(1, entity.getQty());
//            pst.setObject(2, entity.getMemberId());
//            pst.setObject(3, entity.getIsbn());
//
//            boolean isIssueBookUpdated = pst.executeUpdate() > 0;
//            if (isIssueBookUpdated) {
//                Boolean isDeductedCopies = deductbookQuantity(entity);
//                if (isDeductedCopies) {
//                    connection.commit();
//                    return true;
//                } else {
//                    connection.rollback();
//                    return false;
//                }
//            }
//            connection.rollback();
//            return false;
//        } catch (Exception e) {
//            System.out.println("Update failed: " + e.getMessage());
//            return false;
//        } finally {
//            try {
//                DBConnection.getInstance().getConnection().setAutoCommit(true);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
        return false;
    }

    @Override
    public Boolean delete(IssuedBookEntity entity) {
        try {
            ResultSet resultSet = CrudUtil.execute(
                    "SELECT `member_id`, `book_isbn` FROM `member_has_book` WHERE `member_id` = ? AND `book_isbn` = ?",
                    entity.getMemberId(),
                    entity.getIsbn()
            );

            if (!resultSet.next()) {
                // Record does not exist, cannot delete
                Notifications.create()
                        .title("Warning")
                        .text("Sorry , Book not Found !")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .showWarning();
                return false;
            }

            // Delete record
            Connection connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement("DELETE FROM `member_has_book` WHERE `member_id` = ? AND `book_isbn` = ?");
            pst.setObject(1, entity.getMemberId());
            pst.setObject(2, entity.getIsbn());

            boolean isIssueBookDeleted = pst.executeUpdate() > 0;
            if (isIssueBookDeleted) {
                Boolean isCopiesRenewed = renewbookQuantity(entity);
                if (isCopiesRenewed) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            System.out.println("Delete failed: " + e.getMessage());
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
    public IssuedBookEntity search(IssuedBookEntity entity) {
        try {
            ResultSet result = CrudUtil.execute("SELECT `member`.`name`,`book`.`title`,`issue_qty` ," +
                    "issue_date,issue_time,return_date " +
                    "FROM `member_has_book` INNER JOIN " +
                    "`member`ON member_has_book.member_id= member.id " +
                    "INNER JOIN `book` ON member_has_book.book_isbn=book.isbn " +
                    "WHERE `member_id`=? AND `book_isbn`=?", entity.getMemberId(), entity.getIsbn());

            if (result.next()) {
                IssuedBookEntity issuedBookEntity = new IssuedBookEntity(
                        result.getString("name"),
                        result.getString("title"),
                        result.getInt("issue_qty"),
                        result.getDate("issue_date").toLocalDate(),
                        result.getTime("issue_time").toLocalTime(),
                        result.getDate("return_date").toLocalDate());

                return issuedBookEntity;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Boolean renewbookQuantity(IssuedBookEntity entity) {
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
    public List<IssuedBookEntity> issuedBookList() {
        List<IssuedBookEntity> issuedBookEntityList = new ArrayList<>();

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT `member`.`name` AS `member_id`,`book`.`title` " +
                    "AS `book_isbn`,`issue_qty` ,issue_date,issue_time,return_date " +
                    "FROM `member_has_book` INNER JOIN `member`" +
                    "ON member_has_book.member_id= member.id INNER JOIN `book` " +
                    "ON member_has_book.book_isbn=book.isbn ");
            while (resultSet.next()) {
                IssuedBookEntity issuedBookEntity = new IssuedBookEntity(
                        resultSet.getString("member_id"),
                        resultSet.getString("book_isbn"),
                        resultSet.getInt("issue_qty"),
                        resultSet.getDate("issue_date").toLocalDate(),
                        resultSet.getTime("issue_time").toLocalTime(),
                        resultSet.getDate("return_date").toLocalDate());

                issuedBookEntityList.add(issuedBookEntity);
            }
            return issuedBookEntityList;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

}
