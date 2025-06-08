package service.custom.impl;

import dto.Category;
import entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.CategoryRepositoryImpl;
import service.custom.CategoryService;
import util.RepositoryType;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    CategoryRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.CATEGORY);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public String getCategoryId() {
        String currentId = repository.getId();
        if (currentId != null) {
            return String.format("G%03d", Integer.parseInt(currentId.substring(1)) + 1);
        } else {
            return "G001";
        }
    }

    @Override
    public List<Category> getCategoryList() {
        List<CategoryEntity> categoryEntities = repository.getCategoryEntityList();
        ArrayList<Category> categoriesList = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            categoriesList.add(modelMapper.map(categoryEntity, Category.class));
        }
        return categoriesList;
    }

    @Override
    public Boolean addBook(Category category) {
        CategoryEntity categoryEntity = modelMapper.map(category, CategoryEntity.class);
        return repository.add(categoryEntity);
    }
}
