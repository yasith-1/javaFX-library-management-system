package repository.custom.impl;

import entity.MemberEntity;
import repository.custom.MemberRepository;
import alert.Alert;
import alert.AlertType;
import util.CrudUtil;
import util.MapCollection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberRepositoryImpl implements MemberRepository {

    HashMap<String, String> memberTypeMap = MapCollection.getInstance().getMemberTypeMap();

    @Override
    public String getId() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `member` ORDER BY `id` DESC LIMIT 1");
            if (resultSet.next()) {
                return resultSet.getString("id");
            }
            return null;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve last member ID: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<MemberEntity> getMembersList() {
        ArrayList<MemberEntity> memberEntityArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT `member`.`id`,`name`,`nic`,`email`,`address`,`type` FROM `member` " +
                    "INNER JOIN `member_type` ON `member`.`type_id`=`member_type`.`id`");

            while (resultSet.next()) {
                MemberEntity memberEntity = new MemberEntity(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("nic"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        null,
                        resultSet.getString("type"));
                memberEntityArrayList.add(memberEntity);
            }
            return memberEntityArrayList;
        } catch (SQLException e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve members list: " + e.getMessage());
            return null;
        }
    }

    @Override
    public HashMap<String, String> getMemberTypeSet() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `member_type`");
            while (resultSet.next()) {
                memberTypeMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }
            return memberTypeMap;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve member types: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean add(MemberEntity entity) {
        try {
//            check first this entity member already in a table ?
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `member` WHERE `nic`=? AND `email`=?",
                    entity.getNic(), entity.getEmail());

            if (resultSet.next()) {
                Alert.trigger(AlertType.WARNING, "Sorry this Admin already Registered !");
                return false;
            }

            Boolean result = CrudUtil.execute("INSERT INTO `member` VALUES (?,?,?,?,?,?,?)",
                    entity.getId(),
                    entity.getName(),
                    entity.getNic(),
                    entity.getEmail(),
                    entity.getAddress(),
                    entity.getPassword(),
                    entity.getTypeId());

            return result;
        } catch (SQLException e) {
            Alert.trigger(AlertType.ERROR, "Failed to add member: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(MemberEntity entity) {
        try {
            Boolean result = CrudUtil.execute("UPDATE `member` SET `name`=? ,`nic`=?,`email`=?,`address`=?,`type_id`=? WHERE `id`=?",
                    entity.getName(),
                    entity.getNic(),
                    entity.getEmail(),
                    entity.getAddress(),
                    entity.getTypeId(),
                    entity.getId());

            return result;
        } catch (SQLException e) {
            Alert.trigger(AlertType.ERROR, "Failed to update member: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(MemberEntity entity) {
        try {
            Boolean result = CrudUtil.execute("DELETE FROM `member` WHERE `id`=?", entity.getId());
            return result;
        } catch (SQLException e) {
            Alert.trigger(AlertType.ERROR, "Failed to delete member: " + e.getMessage());
            return false;
        }
    }

    @Override
    public MemberEntity search(MemberEntity entity) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `member` " +
                            "WHERE `nic`=? OR `email`=? AND `type_id`=?",
                    entity.getNic(),
                    entity.getEmail(),
                    entity.getTypeId());

            if (resultSet.next()) {
                MemberEntity memberEntity = new MemberEntity(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("nic"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("password"),
                        resultSet.getString("type_id"));

                return memberEntity;
            }
            return null;
        } catch (SQLException e) {
            Alert.trigger(AlertType.ERROR, "Failed to search member: " + e.getMessage());
            return null;
        }
    }
}
