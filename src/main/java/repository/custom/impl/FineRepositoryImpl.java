package repository.custom.impl;

import entity.FineEntity;
import repository.custom.FineRepository;
import util.CrudUtil;
import util.MapCollection;
import java.sql.ResultSet;
import java.util.HashMap;

public class FineRepositoryImpl implements FineRepository {

    HashMap<String, String> memberMap = MapCollection.getInstance().getMemberMap();
    HashMap<String, String> bookMap = MapCollection.getInstance().getBookMap();
    HashMap<String, Integer> fineStatusMap = MapCollection.getInstance().getFineStatusMap();


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
            ResultSet resultset = CrudUtil.execute("SELECT `id` ,`name` FROM `member` WHERE `type_id`=?", 2);
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
    public HashMap<String, Integer> getFineStatusSet() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT * FROM `fine_status`");
            while (resultset.next()) {
                fineStatusMap.put(resultset.getString("status"), resultset.getInt("id"));
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
                return resultSet.getString("isbn");
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean add(FineEntity entity) {
        return null;
    }

    @Override
    public Boolean update(FineEntity entity) {
        return null;
    }

    @Override
    public Boolean delete(FineEntity entity) {
        return null;
    }

    @Override
    public FineEntity search(FineEntity entity) {
        return null;
    }

}
