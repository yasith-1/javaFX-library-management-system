package repository.custom.impl;

import entity.FineEntity;
import repository.custom.FineRepository;
import util.CrudUtil;
import util.Fine;
import util.MapCollection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FineRepositoryImpl implements FineRepository {

    HashMap<String, String> memberMap = MapCollection.getInstance().getMemberMap();
    HashMap<String, String> bookMap = MapCollection.getInstance().getBookMap();
    HashMap<String, String> fineStatusMap = MapCollection.getInstance().getFineStatusMap();


    @Override
    public List<FineEntity> allFineList() {
        ArrayList<FineEntity> fineEntityArrayList = new ArrayList<>();
        try {
            ResultSet result = CrudUtil.execute("SELECT `fine`.`id`,`reason`,`paid_date`,`paid_time`," +
                    "`amount`,`member`.`name`,`book`.`title`,`status` FROM `fine` INNER JOIN `member` " +
                    "ON fine.member_id = `member`.id INNER JOIN `book` ON fine.book_isbn=book.isbn\n" +
                    " INNER JOIN fine_status ON fine.fine_status_id=fine_status.id ");

            while (result.next()) {
                FineEntity fineEntity = new FineEntity(
                        result.getString("id"),
                        result.getString("reason"),
                        result.getDate("paid_date").toLocalDate(),
                        result.getTime("paid_time").toLocalTime(),
                        Fine.AMOUNT.getFee() * getDelayedDaysCount(result.getString("id")),
                        result.getString("name"),
                        result.getString("title"),
                        result.getString("status"));
                fineEntityArrayList.add(fineEntity);

            }
            return fineEntityArrayList;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    private Double getDelayedDaysCount(String fineId) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT \n" +
                    "\t`fine`.`id`,\n" +
                    "    DATEDIFF(`returned_date`, `return_date`) AS `delayed_days`\n" +
                    "FROM `member_has_book` \n" +
                    "INNER JOIN `member` ON `member_has_book`.`member_id` = `member`.`id` \n" +
                    "INNER JOIN `return_book` ON `member`.`id` = `return_book`.`member_id`\n" +
                    "INNER JOIN `fine` ON  `member_has_book`.member_id=fine.member_id AND `member_has_book`.book_isbn =fine.book_isbn\n" +
                    "INNER JOIN `fine_status`ON fine.fine_status_id=fine_status.id WHERE `fine`.`id`=?", fineId);

            if (resultSet.next()) {
                return Double.valueOf(resultSet.getInt("delayed_days"));
            }
            return 0.0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0.0;
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
            e.getMessage();
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
            e.getMessage();
            return null;
        }
    }

    @Override
    public HashMap<String, String> getFineStatusSet() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT * FROM `fine_status`");
            while (resultset.next()) {
                fineStatusMap.put(resultset.getString("status"), resultset.getString("id"));
            }
            return fineStatusMap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public String getLastFineId() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `fine` ORDER BY `id` DESC LIMIT 1");
            if (resultSet.next()) {
                return resultSet.getString("id");
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean add(FineEntity entity) {
        try {
            Boolean result = CrudUtil.execute("INSERT INTO `fine` VALUES (?,?,?,?,?,?,?,?)",
                    entity.getId(),
                    entity.getReason(),
                    entity.getPaidDate(),
                    entity.getPaidTime(),
                    entity.getAmount(),
                    entity.getMemberId(),
                    entity.getBookIsbn(),
                    entity.getStatusId());

            return result;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public Boolean update(FineEntity entity) {
        try {
            Boolean result = CrudUtil.execute("UPDATE `fine` SET `reason`=? ,`amount`=? , `fine_status_id`=? " +
                            "WHERE `id`=? AND `member_id`=? AND `book_isbn`=?",
                    entity.getReason(),
                    entity.getAmount(),
                    entity.getStatusId(),
                    entity.getId(),
                    entity.getMemberId(),
                    entity.getBookIsbn()
            );

            return result;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public Boolean delete(FineEntity entity) {
        try {
            Boolean result = CrudUtil.execute("DELETE FROM `fine` WHERE `id`=? AND `member_id`=? AND `book_isbn`=?",
                    entity.getId(),
                    entity.getMemberId(),
                    entity.getBookIsbn());
            return result;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public FineEntity search(FineEntity entity) {
        try {
            ResultSet result = CrudUtil.execute("SELECT `fine`.`id`,`reason`,`paid_date`,`paid_time`,`amount`," +
                            "`member`.`name`,`book`.`title`,`status` FROM `fine` INNER JOIN `member` " +
                            "ON fine.member_id = `member`.id INNER JOIN `book` ON fine.book_isbn=book.isbn" +
                            " INNER JOIN fine_status ON fine.fine_status_id=fine_status.id " +
                            "WHERE `fine`.`id`=? AND `member_id`=? AND `book_isbn`=?",
                    entity.getId(),
                    entity.getMemberId(),
                    entity.getBookIsbn());

            if (result.next()) {
                FineEntity fineEntity = new FineEntity(
                        result.getString("id"),
                        result.getString("reason"),
                        result.getDate("paid_date").toLocalDate(),
                        result.getTime("paid_time").toLocalTime(),
                        result.getDouble("amount"),
                        result.getString("name"),
                        result.getString("title"),
                        result.getString("status")
                );
                return fineEntity;
            }
            return null;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Double totalFineAmount(String memberId,String bookId) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT \n" +
                    "    member_has_book.member_id,\n" +
                    "    member_has_book.book_isbn,\n" +
                    "    member.name,\n" +
                    "    member_has_book.issue_date,\n" +
                    "    member_has_book.return_date AS date_to_return,\n" +
                    "    return_book.returned_date,\n" +
                    "    return_book.returned_time,\n" +
                    "    DATEDIFF(return_book.returned_date, member_has_book.return_date) AS delayed_days\n" +
                    "FROM member_has_book\n" +
                    "INNER JOIN member ON member_has_book.member_id = member.id\n" +
                    "INNER JOIN return_book ON member.id = return_book.member_id\n" +
                    "WHERE member_has_book.member_id = ?\n" +
                    "  AND member_has_book.book_isbn = ?\n", memberId,bookId);

            if (resultSet.next()) {
                return resultSet.getInt("delayed_days") * Fine.AMOUNT.getFee();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
