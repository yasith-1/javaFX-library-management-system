package repository.custom.impl;

import entity.PendingFineEntity;
import repository.custom.PendingFineRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PendingFineRepositoryImpl implements PendingFineRepository {
    @Override
    public List<PendingFineEntity> getPendingFineList() {
        ArrayList<PendingFineEntity> pendingFineEntityArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT \n" +
                    "    `member_has_book`.`member_id`,\n" +
                    "    `member_has_book`.book_isbn,\n" +
                    "    `name`,\n" +
                    "    DATEDIFF(`returned_date`, `return_date`) AS `delayed_days`\n" +
                    "FROM `member_has_book` \n" +
                    "INNER JOIN `member` ON `member_has_book`.`member_id` = `member`.`id` \n" +
                    "INNER JOIN `return_book` ON `member`.`id` = `return_book`.`member_id`");

            while (resultSet.next()) {
                if (resultSet.getInt("delayed_days") > 0) {
                    PendingFineEntity pendingFineEntity = new PendingFineEntity(
                            resultSet.getString("member_id"),
                            resultSet.getString("book_isbn"),
                            resultSet.getString("name"),
                            resultSet.getInt("delayed_days"));

                    pendingFineEntityArrayList.add(pendingFineEntity);
                }
            }
            return pendingFineEntityArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
