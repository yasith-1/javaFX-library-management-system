package repository.custom.impl;

import entity.MemberEntity;
import repository.custom.MemberRepository;
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
            System.out.println(e.getMessage());
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
            e.getMessage();
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
            e.getMessage();
            return null;
        }
    }

    @Override
    public Boolean add(MemberEntity entity) {
        try {
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
            e.getMessage();
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
            e.getMessage();
            return false;
        }
    }

    @Override
    public Boolean delete(MemberEntity entity) {
        try {
            Boolean result = CrudUtil.execute("DELETE FROM `member` WHERE `id`=?", entity.getId());
            return result;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public MemberEntity search(MemberEntity entity) {
        return null;
    }

}
