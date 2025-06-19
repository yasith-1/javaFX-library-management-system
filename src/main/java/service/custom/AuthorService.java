package service.custom;

import dto.Author;
import service.SuperService;
import java.util.List;

public interface AuthorService extends SuperService {
    String getAuthoryId();

    Boolean addAuthor(Author author);

    Boolean updateAuthor(Author author);

    Boolean deleteAuthor(Author author);

    List<Author> getAuthorList();
}
