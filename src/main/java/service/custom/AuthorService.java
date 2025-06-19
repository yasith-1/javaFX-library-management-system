package service.custom;

import dto.Author;
import dto.Category;
import service.SuperService;

import java.util.List;

public interface AuthorService extends SuperService {
    String getCategoryId();

    Boolean addAuthor(Author author);

    Boolean updateAuthor(Author author);

    Boolean deleteAuthor(Author author);

    List<Category> getAuthorList();
}
