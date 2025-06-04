package service.custom;

import dto.Book;
import service.SuperService;
import java.util.HashMap;
import java.util.List;

public interface BookService extends SuperService {
    String bookId();

    HashMap<String, String> getBookGerneMap();

    HashMap<String, String> getAuthorMap();

    HashMap<String, String> getStatusMap();

    Boolean addBook(Book book);

    Boolean updateBook(Book book);

    Boolean deleteBook(String id);

    Book searchByBookId(String value);

    List<Book> getBookList();
}
