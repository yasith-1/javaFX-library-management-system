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
                    "    member_has_book.member_id,\n" +
                    "    member_has_book.book_isbn,\n" +
                    "    member.name,\n" +
                    "    DATEDIFF(return_book.returned_date, member_has_book.return_date) AS delayed_days\n" +
                    "FROM member_has_book\n" +
                    "INNER JOIN member \n" +
                    "    ON member_has_book.member_id = member.id\n" +
                    "INNER JOIN return_book \n" +
                    "    ON member.id = return_book.member_id\n" +
                    "INNER JOIN fine \n" +
                    "    ON member_has_book.member_id = fine.member_id \n" +
                    "    AND member_has_book.book_isbn = fine.book_isbn\n" +
                    "INNER JOIN fine_status \n" +
                    "    ON fine.fine_status_id = fine_status.id\n" +
                    "WHERE fine_status.status =?","unpaid");

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
