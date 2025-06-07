package service.custom;

import dto.Book;
import dto.Category;
import service.SuperService;

import java.util.List;

public interface CategoryService extends SuperService {
    String getCategoryId();

    Boolean addBook(Category category);

    List<Category> getCategoryList();
}
