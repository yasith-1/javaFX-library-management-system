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
        ArrayList<DelayReturnEntity> delayReturnEntityMemberList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT `member_has_book`.`member_id`,`name`,DATEDIFF(`returned_date`, `return_date`) " +
                    "AS `delayed_days` FROM `member_has_book` INNER JOIN `member` ON `member_has_book`.`member_id` = `member`.`id` " +
                    "INNER JOIN `return_book` ON `member`.`id` = `return_book`.`member_id`");

            while (resultSet.next()) {
                if (resultSet.getInt("delayed_days") > 0) {
                    DelayReturnEntity delayReturnEntity = new DelayReturnEntity(resultSet.getString("member_id"),
                            null,
                            resultSet.getString("name"),
                            null, null, null, null, null);

                    delayReturnEntityMemberList.add(delayReturnEntity);
                }
                continue;
            }
            return delayReturnEntityMemberList;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<DelayReturnEntity> delayReturnedOverviewList() {
        return List.of();
    }
}
