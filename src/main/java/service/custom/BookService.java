package service.custom;

import dto.Book;
import service.SuperService;

import java.util.HashMap;

public interface BookService extends SuperService {
    String bookId();

    HashMap<String, String> getBookGerneMap();

    HashMap<String, String> getAuthorMap();

    HashMap<String, String> getStatusMap();

    Boolean addBook(Book book);
}
