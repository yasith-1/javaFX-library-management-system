package repository.custom.impl;

import alert.Alert;
import alert.AlertType;
import entity.DelayReturnEntity;
import org.checkerframework.checker.units.qual.A;
import repository.custom.DelayReturnRepository;
import util.CrudUtil;
import util.Fine;
import util.MapCollection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DelayReturnRepositoryImpl implements DelayReturnRepository {

    HashMap<String, String> memberMap = MapCollection.getInstance().getMemberMap();

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
    public List<DelayReturnEntity> delayReturnedMembersList() {
        List<DelayReturnEntity> delayReturnEntityMemberList = new ArrayList<>();
        try {
//            ResultSet resultSet = CrudUtil.execute(
//                    "SELECT `member_has_book`.`member_id`, `name`, " +
//                            "DATEDIFF(`returned_date`, `return_date`) AS `delayed_days` " +
//                            "FROM `member_has_book` " +
//                            "INNER JOIN `member` ON `member_has_book`.`member_id` = `member`.`id` " +
//                            "INNER JOIN `return_book` ON `member`.`id` = `return_book`.`member_id`"
//            );

            ResultSet resultSet = CrudUtil.execute(
                    "SELECT \n" +
                            "    `member_has_book`.`member_id`,\n" +
                            "    `member_has_book`.`book_isbn`,\n" +
                            "    `name`,\n" +
                            "    `issue_date`,\n" +
                            "    `return_date` AS `date_to_return`,\n" +
                            "    `returned_date`,\n" +
                            "    `returned_time`,\n" +
                            "    DATEDIFF(`returned_date`, `return_date`) AS `delayed_days`,\n" +
                            "    `status`\n" +
                            "FROM `member_has_book` \n" +
                            "INNER JOIN `member` ON `member_has_book`.`member_id` = `member`.`id` \n" +
                            "INNER JOIN `return_book` ON `member`.`id` = `return_book`.`member_id`\n" +
                            "INNER JOIN `fine` ON  `member_has_book`.member_id=fine.member_id AND `member_has_book`.book_isbn =fine.book_isbn\n" +
                            "INNER JOIN `fine_status`ON fine.fine_status_id=fine_status.id WHERE `status`=?", "Unpaid"
            );

            while (resultSet.next()) {
                if (resultSet.getInt("delayed_days") > 0) {
                    DelayReturnEntity delayReturnEntity = new DelayReturnEntity(
                            resultSet.getString("member_id"),
                            null,
                            resultSet.getString("name"),
                            null, null, null, null, null, null
                    );
                    delayReturnEntityMemberList.add(delayReturnEntity);
                }
            }
            return delayReturnEntityMemberList;
        } catch (SQLException e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve delayed return members: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<String> delayReturnedMembersNameList() {
        List<String> delayReturnEntityMemberNameList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute(
                    "SELECT \n" +
                            "    `member_has_book`.`member_id`,\n" +
                            "    `member_has_book`.`book_isbn`,\n" +
                            "    `name`,\n" +
                            "    `issue_date`,\n" +
                            "    `return_date` AS `date_to_return`,\n" +
                            "    `returned_date`,\n" +
                            "    `returned_time`,\n" +
                            "    DATEDIFF(`returned_date`, `return_date`) AS `delayed_days`,\n" +
                            "    `status`\n" +
                            "FROM `member_has_book` \n" +
                            "INNER JOIN `member` ON `member_has_book`.`member_id` = `member`.`id` \n" +
                            "INNER JOIN `return_book` ON `member`.`id` = `return_book`.`member_id`\n" +
                            "INNER JOIN `fine` ON  `member_has_book`.member_id=fine.member_id AND `member_has_book`.book_isbn =fine.book_isbn\n" +
                            "INNER JOIN `fine_status`ON fine.fine_status_id=fine_status.id WHERE `status`=?", "Unpaid"
            );

            while (resultSet.next()) {
                if (resultSet.getInt("delayed_days") > 0) {
                    delayReturnEntityMemberNameList.add(resultSet.getString("name"));
                }
            }
            return delayReturnEntityMemberNameList;
        } catch (SQLException e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve delayed return member names: " + e.getMessage());
            return null;
        }
    }


    @Override
    public List<DelayReturnEntity> delayReturnedOverviewList(String memberId) {
        List<DelayReturnEntity> delayReturnEntityOverviewList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute(
                    "SELECT \n" +
                            "    `member_has_book`.`member_id`,\n" +
                            "    `member_has_book`.`book_isbn`,\n" +
                            "    `name`,\n" +
                            "    `issue_date`,\n" +
                            "    `return_date` AS `date_to_return`,\n" +
                            "    `returned_date`,\n" +
                            "    `returned_time`,\n" +
                            "    DATEDIFF(`returned_date`, `return_date`) AS `delayed_days`,\n" +
                            "    `status`\n" +
                            "FROM `member_has_book` \n" +
                            "INNER JOIN `member` ON `member_has_book`.`member_id` = `member`.`id` \n" +
                            "INNER JOIN `return_book` ON `member`.`id` = `return_book`.`member_id`\n" +
                            "INNER JOIN `fine` ON  `member_has_book`.member_id=fine.member_id AND " +
                            "`member_has_book`.book_isbn =fine.book_isbn INNER JOIN `fine_status`ON " +
                            "fine.fine_status_id=fine_status.id WHERE `status`=? AND `member_has_book`.`member_id`=?",
                    "Unpaid", memberId);

            while (resultSet.next()) {
                if (resultSet.getInt("delayed_days") > 0) {
                    DelayReturnEntity delayReturnEntity = new DelayReturnEntity(
                            resultSet.getString("member_id"),
                            resultSet.getString("book_isbn"),
                            resultSet.getString("name"),
                            resultSet.getDate("issue_date").toLocalDate(),
                            resultSet.getDate("date_to_return").toLocalDate(),
                            resultSet.getDate("returned_date").toLocalDate(),
                            resultSet.getTime("returned_time").toLocalTime(),
                            resultSet.getInt("delayed_days"),
                            resultSet.getInt("delayed_days") * Fine.AMOUNT.getFee()
                    );
                    delayReturnEntityOverviewList.add(delayReturnEntity);
                }
            }
            return delayReturnEntityOverviewList;
        } catch (SQLException e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve delayed return overview: " + e.getMessage());
            return null;
        }
    }
}
