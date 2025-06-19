package repository.custom.impl;

import entity.AuthorEntity;
import entity.CategoryEntity;
import repository.custom.AuthorRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {
    @Override
    public String getId() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `author` ORDER BY `id` DESC LIMIT 1");
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
    public List<AuthorEntity> getAuthorEntityList() {
        ArrayList<AuthorEntity> authorEntityArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `author`");

            while (resultSet.next()) {
                AuthorEntity authorEntity = new AuthorEntity(
                        resultSet.getString("id"),
                        resultSet.getString("name"));

                authorEntityArrayList.add(authorEntity);
            }
            return authorEntityArrayList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean add(AuthorEntity entity) {
        try {
            Boolean result = CrudUtil.execute("INSERT INTO `author` VALUES(?,?)",
                    entity.getId(),
                    entity.getName());

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(AuthorEntity entity) {
        try {
            Boolean result = CrudUtil.execute("UPDATE `author` SET `name`=? WHERE `id`=?",
                    entity.getName(),
                    entity.getId());

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(AuthorEntity entity) {
        try {
            Boolean result = CrudUtil.execute("DELETE FROM `author` WHERE `id`=?", entity.getId());

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public AuthorEntity search(AuthorEntity entity) {
        return null;
    }
}
