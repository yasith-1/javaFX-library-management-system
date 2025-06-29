package repository.custom.impl;

import alert.Alert;
import alert.AlertType;
import entity.CategoryEntity;
import repository.custom.CategoryRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public String getId() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `gerne` ORDER BY `gerne_id` DESC LIMIT 1");
            if (resultSet.next()) {
                return resultSet.getString("gerne_id");
            }
            return null;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve last category ID: " + e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<CategoryEntity> getCategoryEntityList() {
        ArrayList<CategoryEntity> categoryEntityList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM `gerne`");

            while (resultSet.next()) {
                CategoryEntity categoryEntity = new CategoryEntity(
                        resultSet.getString("gerne_id"),
                        resultSet.getString("name"));

                categoryEntityList.add(categoryEntity);
            }
            return categoryEntityList;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to retrieve category list: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean add(CategoryEntity entity) {
        try {
            Boolean result = CrudUtil.execute("INSERT INTO `gerne` VALUES(?,?)",
                    entity.getGerneId(),
                    entity.getName());

            return result;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to add category: " + e.getMessage());
            return false;
        }
    }


    @Override
    public Boolean update(CategoryEntity entity) {
        try {
            Boolean result = CrudUtil.execute("UPDATE `gerne` SET `name`=? WHERE `gerne_id`=?",
                    entity.getName(),
                    entity.getGerneId());

            return result;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to update category: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(CategoryEntity entity) {
        try {
            Boolean result = CrudUtil.execute("DELETE FROM `gerne` WHERE `gerne_id`=?", entity.getGerneId());

            return result;
        } catch (Exception e) {
            Alert.trigger(AlertType.ERROR, "Failed to delete category: " + e.getMessage());
            return false;
        }
    }

    @Override
    public CategoryEntity search(CategoryEntity entity) {
        return null;
    }
}
