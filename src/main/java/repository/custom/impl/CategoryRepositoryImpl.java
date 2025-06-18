package repository.custom.impl;

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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public Boolean update(CategoryEntity entity) {
        return null;
    }

    @Override
    public Boolean delete(CategoryEntity entity) {
        return null;
    }

    @Override
    public CategoryEntity search(CategoryEntity entity) {
        return null;
    }
}
