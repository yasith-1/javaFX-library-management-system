package service.custom.impl;

import dto.Book;
import entity.BookEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.BookRepositoryImpl;
import service.custom.BookService;
import util.RepositoryType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookServiceImpl implements BookService {

    BookRepositoryImpl bookRepository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.BOOK);
    ModelMapper modelMapper = new ModelMapper();
    ArrayList<Book> bookList = new ArrayList<>();

    @Override
    public String bookId() {
        String currentId = bookRepository.getLastBookId(); // e.g., "B001"
        if (currentId != null) {
            return String.format("B%03d", Integer.parseInt(currentId.substring(1)) + 1);
        } else {
            return "B001";
        }
    }

    @Override
    public HashMap<String, String> getBookGerneMap() {
        return bookRepository.getAllGernes();
    }

    @Override
    public HashMap<String, String> getAuthorMap() {
        return bookRepository.getAllAuthors();
    }

    @Override
    public HashMap<String, String> getStatusMap() {
        return bookRepository.getAllStatus();
    }

    @Override
    public Boolean addBook(Book book) {
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        return bookRepository.add(bookEntity);
    }

    @Override
    public Boolean updateBook(Book book) {
        return null;
    }

    @Override
    public Book searchByBookId(String value) {
        BookEntity foundEntity = bookRepository.search(value);
        if (foundEntity != null) {
            Book book = modelMapper.map(foundEntity, Book.class);
            return book;
        }
        return null;
    }

    @Override
    public List<Book> getBookList() {
        List<BookEntity> bookEntityList = bookRepository.getBookEntityList();

//        bookEntityList map to --> booklist type (DTO|model) and creating booklist
        for (BookEntity bookEntity : bookEntityList) {
            bookList.add(modelMapper.map(bookEntity, Book.class));
        }
        return bookList;
    }
}
