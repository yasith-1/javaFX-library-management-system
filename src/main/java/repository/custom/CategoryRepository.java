package repository.custom;

import entity.CategoryEntity;
import repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, String> {
    String getId();

    List<CategoryEntity> getCategoryEntityList();
}
