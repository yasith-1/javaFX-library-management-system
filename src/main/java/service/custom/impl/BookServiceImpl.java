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

    BookRepositoryImpl bookRepository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.BOOK);
    ModelMapper modelMapper = Mapper.getInstance().getModelMapper();


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
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        return bookRepository.update(bookEntity);
    }


    @Override
    public Boolean deleteBook(Book book) {
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        return bookRepository.delete(bookEntity);
    }

    @Override
    public Book searchByBook(Book book) {
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        BookEntity foundEntity = bookRepository.search(bookEntity);
        if (foundEntity != null) {
            Book foundBook = modelMapper.map(foundEntity, Book.class);
            return foundBook;
        }
        return null;
    }

    @Override
    public List<Book> getBookList() {
        ArrayList<Book> bookList = new ArrayList<>();
        List<BookEntity> bookEntityList = bookRepository.getBookEntityList();

//        bookEntityList map to --> booklist type (DTO|model) and creating booklist
        for (BookEntity bookEntity : bookEntityList) {
            bookList.add(modelMapper.map(bookEntity, Book.class));
        }
        return bookList;
    }
}
