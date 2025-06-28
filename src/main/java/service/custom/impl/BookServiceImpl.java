package service.custom.impl;

import dto.Book;
import entity.BookEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.BookRepositoryImpl;
import service.custom.BookService;
import util.Mapper;
import util.RepositoryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookServiceImpl implements BookService {

    BookRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.BOOK);
    ModelMapper modelMapper = Mapper.getInstance().getModelMapper();


    @Override
    public String bookId() {
        String currentId = repository.getLastBookId(); // e.g., "B001"
        if (currentId != null) {
            return String.format("B%03d", Integer.parseInt(currentId.substring(1)) + 1);
        } else {
            return "B001";
        }
    }

    @Override
    public HashMap<String, String> getBookGerneMap() {
        return repository.getAllGernes();
    }

    @Override
    public HashMap<String, String> getAuthorMap() {
        return repository.getAllAuthors();
    }

    @Override
    public HashMap<String, String> getStatusMap() {
        return repository.getAllStatus();
    }

    @Override
    public HashMap<String, String> getBookMap() {
        return repository.getAllBooks();
    }

    @Override
    public Boolean addBook(Book book) {
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        return repository.add(bookEntity);
    }

    @Override
    public Boolean updateBook(Book book) {
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        return repository.update(bookEntity);
    }


    @Override
    public Boolean deleteBook(Book book) {
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        return repository.delete(bookEntity);
    }

    @Override
    public Book searchByBook(Book book) {
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        BookEntity foundEntity = repository.search(bookEntity);
        if (foundEntity != null) {
            Book foundBook = modelMapper.map(foundEntity, Book.class);
            return foundBook;
        }
        return null;
    }

    @Override
    public List<Book> getBookList() {
        ArrayList<Book> bookList = new ArrayList<>();
        List<BookEntity> bookEntityList = repository.getBookEntityList();

//        bookEntityList map to --> booklist type (DTO|model) and creating booklist
        for (BookEntity bookEntity : bookEntityList) {
            bookList.add(modelMapper.map(bookEntity, Book.class));
        }
        return bookList;
    }

    @Override
    public List<Book> searchBooks(String title, String author, String category) {
        List<BookEntity> bookEntities = repository.searchBooks(title, author, category);
        if (bookEntities != null && !bookEntities.isEmpty()) {
            List<Book> bookList = new ArrayList<>();
            for (BookEntity bookEntity : bookEntities) {
                bookList.add(modelMapper.map(bookEntity, Book.class));
            }
            return bookList;
        }
        return null;
    }
}
