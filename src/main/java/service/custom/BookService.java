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

    HashMap<String, String> getBookMap();

    Boolean addBook(Book book);

    Boolean updateBook(Book book);

    Boolean deleteBook(Book book) ;

    Book searchByBook(Book book);

    List<Book> getBookList();

    List<Book> searchBooks(String title, String author, String category);
}
