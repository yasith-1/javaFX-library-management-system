package repository.custom.impl;

import entity.DelayReturnEntity;
import repository.custom.DelayReturnRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DelayReturnRepositoryImpl implements DelayReturnRepository {

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
                            null, null, null, null, null
                    );
                    delayReturnEntityMemberList.add(delayReturnEntity);
                }
            }
            return delayReturnEntityMemberList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<String> delayReturnedMembersNameList() {
        List<String> delayReturnEntityMemberNameList = new ArrayList<>();
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
                    delayReturnEntityMemberNameList.add(resultSet.getString("name"));
                }
            }
            return delayReturnEntityMemberNameList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public List<DelayReturnEntity> delayReturnedOverviewList() {
        return List.of();
    }
}
