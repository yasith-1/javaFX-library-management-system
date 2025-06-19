package service.custom;

import dto.Book;
import dto.Category;
import service.SuperService;

import java.util.List;

public interface CategoryService extends SuperService {
    String getCategoryId();

    Boolean addCategory(Category category);

    Boolean updateCategory(Category category);

    Boolean deleteCategory(Category category);

    List<Category> getCategoryList();
}
